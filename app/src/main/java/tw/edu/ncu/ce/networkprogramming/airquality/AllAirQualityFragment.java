package tw.edu.ncu.ce.networkprogramming.airquality;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tw.edu.ncu.ce.networkprogramming.airquality.AppCallbacks.RefreshAirQualityDataCallback;



public class AllAirQualityFragment extends Fragment implements RefreshAirQualityDataCallback,AirQualityHolder.AirQualityDataPressedCallback {

    private final String TAG =AllAirQualityFragment.class.getName();
    private RecyclerView mRecyclerView;
    private OnFragmentInteractionListener mListener;
    private Context mContext;
    private AirQualityApp mApp;
    private AirQualityAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
           //TODO
        }
        mApp= AirQualityApp.getInstance(mContext);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all_air_quality, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.abc_list_divider_mtrl_alpha)));

        adapter = new AirQualityAdapter(mContext, mApp.getALLAirQualityData(),this);
        mRecyclerView.setAdapter(adapter);
        if(mApp.isOldData()){
            Log.d(TAG,"is old, need update");
            mApp.reFreshData(this);
        }

        return v;
    }


    public void onItemPressed(int pos) {
        if (mListener != null) {
            mListener.onFragmentInteraction(pos);
            Log.d(TAG,"position "+pos+" is be clicked");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mContext = activity;
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void refresh() {
        Log.d(TAG, "update data");
        mRecyclerView.setAdapter(new AirQualityAdapter(mContext, mApp.getALLAirQualityData(),this));
    }


    public interface OnFragmentInteractionListener {

        public void onFragmentInteraction(int position);
    }




}
