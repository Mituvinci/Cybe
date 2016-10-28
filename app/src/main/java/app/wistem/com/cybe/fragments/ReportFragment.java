package app.wistem.com.cybe.fragments;


import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Arrays;

import app.wistem.com.cybe.R;
import app.wistem.com.cybe.utilities.SessionManager;
import app.wistem.com.cybe.adapters.ScareScoreAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {

    private static String[] mScore = {"1","2","3","4","5","6","7","8","9","10"};
    private final static String KNOWING_POSITION = "mScareScorePosition";
    private String mScareScorePosition;
    private Button mButtonSubmit;

    private RadioButton mRadioButtonMe;
    private RadioButton mRadioButtonSomeOneElse;
    private RadioButton mRadioButtonVerCertain;
    private RadioButton mRadioButtonUncertain;
    private RadioButton mRadioButtonYes;
    private RadioButton mRadioButtonNo;
    private TextView mTextViewScareScore;

    private EditText mEditTextSummarize;
    private EditText mEditTextMoreDetails;


    private SessionManager mSessionManager;


    private TextView mTextViewScore;
    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_report, container, false);
        mSessionManager = new SessionManager(getActivity());
        mTextViewScore = (TextView) view.findViewById(R.id.textViewScoreit);
        mButtonSubmit = (Button) view.findViewById(R.id.buttonsubmit);

        mRadioButtonMe = (RadioButton) view.findViewById(R.id.radioButtonMe);
        mRadioButtonSomeOneElse = (RadioButton) view.findViewById(R.id.radioButtonSomeoneIknow);;
        mRadioButtonVerCertain = (RadioButton) view.findViewById(R.id.radioButtonverycertain);;
        mRadioButtonUncertain = (RadioButton) view.findViewById(R.id.radioButtonuncertain);;
        mRadioButtonYes = (RadioButton) view.findViewById(R.id.radioButtonpublic);;
        mRadioButtonNo = (RadioButton) view.findViewById(R.id.radioButtonunnonpublic);;

        mEditTextSummarize = (EditText) view.findViewById(R.id.editTextSummarize);
        mEditTextMoreDetails = (EditText) view.findViewById(R.id.edittextMoredetails);


        scareScore();
        submitbutton();



        return  view;
    }

    private  void submitbutton(){

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getFragmentManager().beginTransaction().addToBackStack(null)
                            .replace(R.id.main, new ReportFeedBackFragment()).commit();

            }
        });
    }
    private  void  scareScore(){
        mTextViewScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(getActivity());
                View promptsView = li.inflate(R.layout.choose_dialog_scare_score_layout, null);
                TextView textView;
                textView = (TextView) promptsView.findViewById(R.id.titleactivity);
                textView.setText("Score how much scared you are");
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());
                alertDialogBuilder.setView(promptsView);
                final RecyclerView mRecyclerViewchooseDialog;
                final RecyclerView.LayoutManager mLayoutManagerChoosDialog;
                ScareScoreAdapter mAdapterChoosDialog;
                mRecyclerViewchooseDialog = (RecyclerView) promptsView.findViewById(R.id.districtchoose);
                mLayoutManagerChoosDialog = new LinearLayoutManager(getActivity());
                mRecyclerViewchooseDialog.setHasFixedSize(true);
                mRecyclerViewchooseDialog.setLayoutManager(mLayoutManagerChoosDialog);
                mAdapterChoosDialog = new ScareScoreAdapter(Arrays.asList(mScore),getActivity(),"1");
                mRecyclerViewchooseDialog.setAdapter(mAdapterChoosDialog);
                mRecyclerViewchooseDialog.setNestedScrollingEnabled(false);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Save",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        SharedPreferences prefschck = PreferenceManager.getDefaultSharedPreferences(getActivity());
                                        int knowingrposition = prefschck.getInt(KNOWING_POSITION,-1);
                                        prefschck.edit().clear().apply();
                                        if(knowingrposition != -1) {
                                            mScareScorePosition = mScore[knowingrposition];
                                            mTextViewScore.setText(mScareScorePosition +" out of 10");

                                        }else {
                                        }

                                    }
                                })
                        .setNegativeButton("Cance",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }
}
