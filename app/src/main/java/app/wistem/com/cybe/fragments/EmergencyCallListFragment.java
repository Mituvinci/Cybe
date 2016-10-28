package app.wistem.com.cybe.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import app.wistem.com.cybe.R;
import app.wistem.com.cybe.adapters.RecyclerViewAdapterFirstWay;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmergencyCallListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterFirstWay mAdapter;
    private String[] items = {"75348",
    "423948","324923","3248293","237489"};
    public EmergencyCallListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_emergency_call_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RecyclerViewAdapterFirstWay(getActivity(), Arrays.asList(items));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerViewAdapterFirstWay.customInterface() {
            @Override
            public void onItemClick(int position, View v) {

                String phoneNumber = items[position];
                Intent dial = new Intent();
                dial.setAction("android.intent.action.DIAL");
                dial.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(dial);

            }
        });
        mRecyclerView.setNestedScrollingEnabled(false);



        return view;

    }

}
