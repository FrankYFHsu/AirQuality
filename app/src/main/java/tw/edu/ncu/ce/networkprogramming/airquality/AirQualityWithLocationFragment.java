package tw.edu.ncu.ce.networkprogramming.airquality;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class AirQualityWithLocationFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;



    public static AirQualityWithLocationFragment newInstance(int position) {
        AirQualityWithLocationFragment f = new AirQualityWithLocationFragment();
        Bundle args = new Bundle();
        args.putInt(AirQualityWithLocationFragment.ARG_POSITION, position);
        f.setArguments(args);
        return f;
    }

    public AirQualityWithLocationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);

        }
        return inflater.inflate(R.layout.fragment_air_quality_with_location, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();


        Bundle args = getArguments();

        if (args != null) {

            updateDetailsView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {

            updateDetailsView(mCurrentPosition);
        }
    }

    public void updateDetailsView(int position) {

        AirQualityData data = AirQualityApp.getInstance(getActivity()).getALLAirQualityData().get(position);

        mCurrentPosition = position;

        ListView detailsView = (ListView)getView().findViewById(R.id.details);
        TextView sitetextView = (TextView)getView().findViewById(R.id.sitetextView);
        TextView statustextView = (TextView)getView().findViewById(R.id.statustextView);

        sitetextView.setText(data.getSiteName());
        statustextView.setText(data.getStatus());



        List<String> result = data.getDetails();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, result);


        detailsView.setAdapter(adapter);



    }





}
