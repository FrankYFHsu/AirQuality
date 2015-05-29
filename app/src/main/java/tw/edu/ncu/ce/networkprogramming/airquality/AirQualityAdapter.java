package tw.edu.ncu.ce.networkprogramming.airquality;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class AirQualityAdapter extends RecyclerView.Adapter<AirQualityHolder> {

    private List<AirQualityData> mAllAirQualityData;
    private Context mContext;
    private AirQualityHolder.AirQualityDataPressedCallback mCallback;


    public AirQualityAdapter(Context context, List<AirQualityData> airQualityDataList,
                             AirQualityHolder.AirQualityDataPressedCallback callback) {
        mCallback = callback;
        this.mAllAirQualityData = airQualityDataList;
        this.mContext = context;
    }


    @Override
    public AirQualityHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_air_quality, parent, false);

        /* R.layout.list_item_air_quality define the item layout */
        return new AirQualityHolder(itemLayoutView, mCallback);

    }

    @Override
    public void onBindViewHolder(AirQualityHolder holder, int position) {
        AirQualityData airQualityData = mAllAirQualityData.get(position);
        holder.bindAirQuality(airQualityData, position);


    }

    @Override
    public int getItemCount() {
        return mAllAirQualityData.size();
    }


}
