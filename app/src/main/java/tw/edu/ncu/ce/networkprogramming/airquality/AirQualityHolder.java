package tw.edu.ncu.ce.networkprogramming.airquality;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class AirQualityHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private AirQualityData mAirQualityData;
    private AirQualityDataPressedCallback mCallback;
    private TextView psi_display;
    private TextView sitename_display;
    private TextView status_display;
    private int mPosition;


    public AirQualityHolder(View itemView,AirQualityDataPressedCallback callback) {
        super(itemView);
        itemView.setOnClickListener(this);
        mCallback = callback;
        psi_display = (TextView) itemView.findViewById(R.id.psi_display);
        sitename_display = (TextView) itemView.findViewById(R.id.sitename_display);
        status_display = (TextView) itemView.findViewById(R.id.status_display);
    }

    public void bindAirQuality(AirQualityData airQualityData,int position) {
        this.mPosition = position;
        mAirQualityData = airQualityData;
        sitename_display.setText(mAirQualityData.getSiteName());

        int psi = Integer.parseInt(mAirQualityData.getPSI());
        status_display.setText("空氣品質:"+mAirQualityData.getStatus());

        Resources resources = AirQualityApp.getInstance().getAppContext().getResources();
        if (psi <= 50) {
            //psi_display.setBackgroundResource(R.color.psi_good);
            psi_display.setTextColor(resources.getColor(R.color.psi_good));
        } else if (psi > 50 && psi <= 100) {
            //psi_display.setBackgroundResource(R.color.psi_moderate);
            psi_display.setTextColor(resources.getColor(R.color.psi_moderate));
        } else if (psi > 100 && psi <= 199) {
            //psi_display.setBackgroundResource(R.color.psi_unhealthful);
            psi_display.setTextColor(resources.getColor(R.color.psi_unhealthful));
        } else if (psi > 199 && psi <= 299) {
            //psi_display.setBackgroundResource(R.color.psi_very_unhealthful);
            psi_display.setTextColor(resources.getColor(R.color.psi_very_unhealthful));
        } else if (psi > 299) {
            //psi_display.setBackgroundResource(R.color.psi_hazardous);
            psi_display.setTextColor(resources.getColor(R.color.psi_hazardous));

        }
        psi_display.setText("PSI:"+mAirQualityData.getPSI());


    }

    @Override
    public void onClick(View view) {
        mCallback.onItemPressed(mPosition);

    }

    public interface AirQualityDataPressedCallback {
        public void onItemPressed(int pos);

    }
}
