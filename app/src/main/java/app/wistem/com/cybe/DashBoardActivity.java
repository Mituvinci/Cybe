package app.wistem.com.cybe;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class DashBoardActivity extends AppCompatActivity {


   /* private Button mTextViewReport;
    private Button mTextViewNewsFeed;
    private Button mTextViewEmergencyCallList;
    private Button mTextViewEnterOurForum;
    private Button mTextViewVisitFaceBookpage;
    private Button mTextViewNotificationSetting;
    private SessionManager mSessionManager;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Fragment fragment = new DashBoardFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.containerHome, fragment);
        fragmentTransaction.commit();

//


//        mTextViewReport = (Button) findViewById(R.id.textViewreport);
//        mTextViewNewsFeed = (Button) findViewById(R.id.textViewnewsFeed);
//        mTextViewEmergencyCallList = (Button)findViewById(R.id.textViewnewsEmergencyCallList);
//        mTextViewEnterOurForum = (Button) findViewById(R.id.textViewnewsEnterForum);
//        mTextViewVisitFaceBookpage = (Button) findViewById(R.id.textViewVisitFaceBook);
//        mTextViewNotificationSetting = (Button) findViewById(R.id.textViewnotificationSetting);
//
//
//
//        mSessionManager = new SessionManager(getBaseContext());
      /*  report();
        newsfeed();
        emergencyCallList();
        enterForum();
        visitFaceBookPage();*/




    }


/*    private void report(){
//        mTextViewReport.setText("Report Cyber Harassment");

        mTextViewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSessionManager.isLoggedIn()) {
                    getFragmentManager().beginTransaction().addToBackStack(null)
                            .replace(R.id.main, new ReportFragment()).commit();
                }else {
                     startActivity(new Intent(DashBoardActivity.this, LoginActivity.class));


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
                getFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.main, new NewsFeedFragment()).commit();
            }
        });
    }

    private void emergencyCallList(){
       // mTextViewEmergencyCallList.setText("Emergency Call List");

        mTextViewEmergencyCallList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.content_dash_board, new EmergencyCallListFragment()).commit();

              *//*  Fragment fragment = new EmergencyCallListFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.replaceit, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*//*
            }
        });
    }*/

}
