package app.wistem.com.cybe.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import app.wistem.com.cybe.R;
import app.wistem.com.cybe.utilities.StoreReportSharedPreferenc;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {

    private static String[] mScore = {"1","2","3","4","5","6","7","8","9","10"};
    private final static String KNWOING_SOURCE = "knowingSource";
    private final static String KNOWING_POSITION = "knowingposition";
    private static String mScareScorePosition = "1";
    private Button mButtonSubmit;

    private Button ButtonYesPublic;
    private Button ButtonNoPublic;


    private EditText mEditTextSummarize;

    private TextView mTextViewAudio;
    private TextView mTextViewVideo;
    private TextView mTextViewImage;
    private SeekBar mSeekBar;

    private  String mScarePerson;
    private  String mCertainty;
    private  String mPublicNot;
    private  String mScoreNumber;
    private  String mSummarize ="";
    private  String mMoreDetails = "";

    private StoreReportSharedPreferenc mSessionManager;
    private List<File> image;



    //private TextView mTextViewScore;
    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_report, container, false);
        mSessionManager = new StoreReportSharedPreferenc(getActivity());
       // mTextViewScore = (TextView) view.findViewById(R.id.textViewScoreit);
        mSeekBar = (SeekBar) view.findViewById(R.id.seekBar1);
        mButtonSubmit = (Button) view.findViewById(R.id.buttonsubmit);

        ButtonYesPublic = (Button) view.findViewById(R.id.radioButtonMe);
        ButtonNoPublic = (Button) view.findViewById(R.id.radioButtonSomeoneIknow);;

        mEditTextSummarize = (EditText) view.findViewById(R.id.editTextSummarize);
        mTextViewAudio = (TextView) view.findViewById(R.id.textViewAudio);
        mTextViewVideo = (TextView) view.findViewById(R.id.textViewVideo);
        mTextViewImage = (TextView) view.findViewById(R.id.textViewImage);
        scareScore();
        submitReport();

        fileFetch();

        ButtonYesPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonNoPublic.setBackgroundColor(Color.parseColor("#F1F1F1"));
                ButtonYesPublic.setBackgroundColor(Color.parseColor("#29AAF1"));
                ButtonYesPublic.setTextColor(Color.parseColor("#F1F1F1"));
                ButtonNoPublic.setTextColor(Color.parseColor("#000000"));

                mScarePerson = "YES";
            }
        });


        ButtonNoPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonNoPublic.setBackgroundColor(Color.parseColor("#29AAF1"));
                ButtonNoPublic.setTextColor(Color.parseColor("#F1F1F1"));
                ButtonYesPublic.setTextColor(Color.parseColor("#000000"));
                ButtonYesPublic.setBackgroundColor(Color.parseColor("#F1F1F1"));
                mScarePerson = "NO";

            }
        });





        return  view;
    }

    private void fileFetch(){

        mTextViewAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new FileListFragment();
                Bundle bundle = new Bundle();

                bundle.putString("file","audio");
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.main, fragment).commit();
            }
        });

        mTextViewVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FileListFragment();
                Bundle bundle = new Bundle();

                bundle.putString("file","video");
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.main, fragment).commit();
            }
        });


        mTextViewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment fragment = new ImageFragment();
                getFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.main, fragment).commit();
            }
        });


    }
    private  void submitReport(){

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSummarize = mEditTextSummarize.getText().toString();
               // mMoreDetails = mEditTextMoreDetails.getText().toString();

                    View view = getActivity().getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    mSessionManager.storeUserInformation(mScarePerson,mScoreNumber,mSummarize,mMoreDetails,mCertainty,mPublicNot);

                    // Log.d("report", "onClick: "+mScarePerson+" ,"+mScoreNumber+" , "+mSummarize+" ,"+mMoreDetails+" , "+mCertainty+" ,"+mPublicNot);

                if (!TextUtils.isEmpty(mPublicNot)) {
                    FragmentManager fm = getActivity().getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.main, new ReportFeedBackFragment());
                    ft.addToBackStack(null);
                    fm.popBackStackImmediate();
                    ft.commit();
                }else {
                    Toast.makeText(getActivity(),"Choose to post it in public or Not",Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
    private  void  scareScore(){
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d("seekbar", "onProgressChanged: "+i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
