package app.wistem.com.cybe.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.wistem.com.cybe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFeedBackFragment extends Fragment {


    public ReportFeedBackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_feed_back, container, false);
    }

}
