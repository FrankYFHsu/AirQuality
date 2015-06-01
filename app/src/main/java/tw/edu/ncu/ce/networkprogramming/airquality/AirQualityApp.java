package tw.edu.ncu.ce.networkprogramming.airquality;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import net.danlew.android.joda.JodaTimeAndroid;
import org.joda.time.DateTime;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tw.edu.ncu.ce.networkprogramming.airquality.AppCallbacks.RefreshAirQualityDataCallback;


public class AirQualityApp {

    public static final String AQX_JSON_API = "http://opendata.epa.gov.tw/ws/Data/AQX/?$format=json";
    private static final String TAG = AirQualityApp.class.getName();

    private List<AirQualityData> mAllAirQualityData;

    private Gson gson;


    private static AirQualityApp airQualityAppInstance;
    private Context mAppContext;//will be used if we want to register broadcast receivers?


    private AirQualityApp(Context appContext) {
        mAppContext = appContext;
        JodaTimeAndroid.init(appContext);
        gson = new Gson();

    }
    public Context getAppContext(){
        return mAppContext;
    }

    public static synchronized AirQualityApp getInstance(Context c) {
        if (airQualityAppInstance == null) {
            airQualityAppInstance = new AirQualityApp(c.getApplicationContext());
        }
        return airQualityAppInstance;
    }
    /*Dangerous ?*/
    public static AirQualityApp getInstance() {
        if (airQualityAppInstance == null) {
            throw new IllegalStateException("have you called getInstance(context)?");
        }
        return airQualityAppInstance;
    }

    public List<AirQualityData> getALLAirQualityData() {

        return mAllAirQualityData;
    }

    public void setAllAirQualityData(List<AirQualityData> allAirQualityData) {
        mAllAirQualityData = allAirQualityData;
    }

    public boolean isOldData() {

        if (mAllAirQualityData != null && mAllAirQualityData.size()!=0) {
            AirQualityData data = mAllAirQualityData.get(0);
            String time = data.getPublishTime();

            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time);
                DateTime publishDate = new DateTime(date);
                Log.d(TAG, "publishDate = " + publishDate.toString());
                //Current time
                DateTime updateTime = new DateTime().withMinuteOfHour(0).withSecondOfMinute(0).withMillis(0);
                Log.d(TAG, "update time = " + updateTime.toString());
                if (publishDate.isBefore(updateTime)) {
                    return true;
                } else {
                    return false;
                }

            } catch (ParseException e) {
                Log.e(TAG, e.getMessage());
            }

            return false;
        }
        Log.d(TAG, "no AirQualityData.");
        return true;
    }

    public void reFreshData(RefreshAirQualityDataCallback callback) {

        new RefreshDataTask(callback).execute(AQX_JSON_API);
    }


    private class RefreshDataTask extends AsyncTask<String, Integer, List<AirQualityData>> {

        private RefreshAirQualityDataCallback mCallback;

        public RefreshDataTask(RefreshAirQualityDataCallback c) {
            mCallback = c;
        }

        @Override
        protected List<AirQualityData> doInBackground(String... urls) {
            List<AirQualityData> result = new ArrayList<>();

            try {

                URL url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);


                if (conn.getResponseCode() == 200) {



                    AirQualityData[] data;
                    data = gson.fromJson(new InputStreamReader(conn.getInputStream(), "UTF-8"), AirQualityData[].class);

                    for (AirQualityData airQualityData : data) {
                        result.add(airQualityData);
                    }

                    conn.disconnect();
                    return result;

                }


            } catch (Exception e) {
                Log.e(this.getClass().getName(), "Exception :" + e.getMessage());
            }


            return result;
        }


        @Override
        protected void onPostExecute(List<AirQualityData> airQualityDataList) {
            setAllAirQualityData(airQualityDataList);
            mCallback.refresh();

        }


    }


}

