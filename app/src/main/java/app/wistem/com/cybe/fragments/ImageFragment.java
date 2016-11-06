package app.wistem.com.cybe.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import app.wistem.com.cybe.FileClass;
import app.wistem.com.cybe.R;
import app.wistem.com.cybe.adapters.ImageFileAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ImageFileAdapter mImageAdapter;
    private List<File> image;

    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_image, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler1);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        if (FileClass.isSdCardPresent()) {
            image = FileClass.AllImagesOfMemory();
            mImageAdapter = new ImageFileAdapter(getActivity(),image);
            mRecyclerView.setAdapter(mImageAdapter);


        }else {
            Toast.makeText(getActivity(),"Your phone doesn't have any SD card",Toast.LENGTH_SHORT).show();
        }
        return  view;
    }

}
