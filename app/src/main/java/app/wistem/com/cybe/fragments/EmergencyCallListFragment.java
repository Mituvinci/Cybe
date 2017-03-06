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

import java.util.ArrayList;
import java.util.List;

import app.wistem.com.cybe.R;
import app.wistem.com.cybe.adapters.EmergencyCallListAdapter;
import app.wistem.com.cybe.modelclass.PhoneNumberModel;
import app.wistem.com.cybe.utilities.ImportantPhoneNumbers;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmergencyCallListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private EmergencyCallListAdapter mAdapter;
    private List<PhoneNumberModel> mPhoneNumberList = new ArrayList<>();
    private ImportantPhoneNumbers mImportantPhoneNumbers;


    public EmergencyCallListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_emergency_call_list, container, false);
        mImportantPhoneNumbers = ImportantPhoneNumbers.getInstance();

        mPhoneNumberList = mImportantPhoneNumbers.getPhoneNumber();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new EmergencyCallListAdapter(getActivity(), mPhoneNumberList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new EmergencyCallListAdapter.customInterface() {
            @Override
            public void onItemClick(int position, View v) {

                String phoneNumber = mPhoneNumberList.get(position).getmPhoneNumber();
                /*Intent dial = new Intent();
                dial.setAction("android.intent.action.DIAL");
                dial.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(dial); */



                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phoneNumber));
                startActivity(callIntent);


            }
        });
        mRecyclerView.setNestedScrollingEnabled(false);



        return view;

    }

}
