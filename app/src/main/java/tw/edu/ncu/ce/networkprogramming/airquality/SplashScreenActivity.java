package tw.edu.ncu.ce.networkprogramming.airquality;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import tw.edu.ncu.ce.networkprogramming.airquality.AppCallbacks.RefreshAirQualityDataCallback;


public class SplashScreenActivity extends Activity implements RefreshAirQualityDataCallback {

    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        AirQualityApp.getInstance(this).reFreshData(this);

    }

    @Override
    public void refresh() {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);


    }


}
