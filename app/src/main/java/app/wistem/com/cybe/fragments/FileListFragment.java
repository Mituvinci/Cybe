package app.wistem.com.cybe.fragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Arrays;

import app.wistem.com.cybe.utilities.FileClass;
import app.wistem.com.cybe.R;
import app.wistem.com.cybe.adapters.FileNameAdapter;


@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class FileListFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private FileNameAdapter mAdapter;
    private String[] items ;

    private Bundle mBundles;


    public FileListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_file_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBundles = this.getArguments();
        String fileType = mBundles.getString("file");

        if (fileType.equals("audio")){

            if (FileClass.isSdCardPresent()) {
                items = FileClass.AllAudioOfMemory();
                mAdapter = new FileNameAdapter(getActivity(), Arrays.asList(items));
                mRecyclerView.setAdapter(mAdapter);


            }else {
                Toast.makeText(getActivity(),"Your phone doesn't have any SD card",Toast.LENGTH_SHORT).show();
            }

        }else if (fileType.equals("video"))
        {

            if (FileClass.isSdCardPresent()) {
                items = FileClass.AllVideOfMemory();
                mAdapter = new FileNameAdapter(getActivity(), Arrays.asList(items));
                mRecyclerView.setAdapter(mAdapter);


            }else {
                Toast.makeText(getActivity(),"Your phone doesn't have any SD card",Toast.LENGTH_SHORT).show();
            }

        }



        return view;
    }




}
