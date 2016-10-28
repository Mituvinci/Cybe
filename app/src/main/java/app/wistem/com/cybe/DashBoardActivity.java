package app.wistem.com.cybe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import app.wistem.com.cybe.fragments.EmergencyCallListFragment;
import app.wistem.com.cybe.fragments.NewsFeedFragment;
import app.wistem.com.cybe.fragments.ReportFragment;

public class DashBoardActivity extends AppCompatActivity {
    private TextView mTextViewReport;
    private TextView mTextViewNewsFeed;
    private TextView mTextViewEmergencyCallList;
    private TextView mTextViewEnterOurForum;
    private TextView mTextViewVisitFaceBookpage;
    private TextView mTextViewNotificationSetting;
    private SessionManager mSessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTextViewReport = (TextView) findViewById(R.id.textViewreport);
        mTextViewNewsFeed = (TextView) findViewById(R.id.textViewnewsFeed);
        mTextViewEmergencyCallList = (TextView) findViewById(R.id.textViewnewsEmergencyCallList);
        mTextViewEnterOurForum = (TextView) findViewById(R.id.textViewnewsEnterForum);
        mTextViewVisitFaceBookpage = (TextView) findViewById(R.id.textViewVisitFaceBook);
        mTextViewNotificationSetting = (TextView) findViewById(R.id.textViewnotificationSetting);



        mSessionManager = new SessionManager(getBaseContext());
        report();
        newsfeed();
        emergencyCallList();
        enterForum();
        visitFaceBookPage();
        mTextViewEnterOurForum.setText("Enter Our Forum");
        mTextViewVisitFaceBookpage.setText("Visit facebook page");
        mTextViewNotificationSetting.setText("Notification and Settings");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void report(){
            mTextViewReport.setText("Report For Harassing");

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
        mTextViewNewsFeed.setText("Check news Feed");

        mTextViewNewsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.main, new NewsFeedFragment()).commit();
            }
        });
    }

    private void emergencyCallList(){
        mTextViewEmergencyCallList.setText("Emergency Call List");

        mTextViewEmergencyCallList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.main, new EmergencyCallListFragment()).commit();
            }
        });
    }
}
