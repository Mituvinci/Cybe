package app.wistem.com.cybe;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.wistem.com.cybe.fragments.EmergencyCallListFragment;
import app.wistem.com.cybe.fragments.NewsFeedFragment;
import app.wistem.com.cybe.fragments.ReportFragment;
import app.wistem.com.cybe.utilities.SessionManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashBoardFragment extends Fragment {



    private ImageView mTextViewReport;
    private ImageView mTextViewNewsFeed;
    private ImageView mTextViewEmergencyCallList;
    private ImageView mTextViewEnterOurForum;
    private ImageView mTextViewVisitFaceBookpage;
    private ImageView mTextViewNotificationSetting;
    private TextView mTextViewSplash;
    private SessionManager mSessionManager;

    public DashBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dashboard_testing_layout, container, false);
        mTextViewSplash = (TextView) view.findViewById(R.id.textViewsplash);
        mTextViewReport = (ImageView) view.findViewById(R.id.textViewreport);
        mTextViewNewsFeed = (ImageView) view.findViewById(R.id.textViewnewsFeed);
        mTextViewEmergencyCallList = (ImageView)view.findViewById(R.id.textViewnewsEmergencyCallList);
        mTextViewEnterOurForum = (ImageView) view.findViewById(R.id.textViewnewsEnterForum);
        mTextViewVisitFaceBookpage = (ImageView) view.findViewById(R.id.textViewVisitFaceBook);
        mTextViewNotificationSetting = (ImageView) view.findViewById(R.id.textViewnotificationSetting);



        mSessionManager = new SessionManager(getActivity());
       // mTextViewSplash.setVisibility(View.VISIBLE);

        mTextViewSplash.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTextViewSplash.setVisibility(View.GONE);
            }
        },4000);
        report();
        newsfeed();
        emergencyCallList();
        enterForum();
        visitFaceBookPage();

        return  view;
    }


//    private void report(){
////        mTextViewReport.setText("Report Cyber Harassment");
//
//        mTextViewReport.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new ReportFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.add(R.id.containerHome, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
//            }
//        });
//
//
//    }


       private void report(){
//        mTextViewReport.setText("Report Cyber Harassment");

        mTextViewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSessionManager.isLoggedIn()) {
                    Fragment fragment = new ReportFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.containerHome, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }else {
                     startActivity(new Intent(getActivity(), LoginActivity.class));


                }

            }
        });


    }

    private void enterForum(){

        mTextViewEnterOurForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://wistembangladesh.org/#primary"));
                startActivity(intent);
            }
        });

    }


    private void  visitFaceBookPage(){
        mTextViewVisitFaceBookpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/groups/1072957612794138/"));
                startActivity(intent);
            }
        });

    }


    private void  newsfeed(){
      //  mTextViewNewsFeed.setText("Check news Feed");

        mTextViewNewsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getFragmentManager().beginTransaction().addToBackStack(null)
//                        .replace(R.id.main, new NewsFeedFragment()).commit();

                Fragment fragment = new NewsFeedFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.containerHome, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    private void emergencyCallList(){
       // mTextViewEmergencyCallList.setText("Emergency Call List");

        mTextViewEmergencyCallList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getFragmentManager().beginTransaction().addToBackStack(null)
//                        .replace(R.id.content_dash_board, new EmergencyCallListFragment()).commit();
                Fragment fragment = new EmergencyCallListFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.containerHome, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

}
