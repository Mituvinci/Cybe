package app.wistem.com.cybe.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Hi on 28-Oct-16.
 */

public class StoreReportSharedPreferenc {

    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private Context mContext;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME_REPORT = "cybeprefForReportInformation";
    public static final String HARASSED = "harassed";
    public static final String SCARE_SCORE = "scarescore";
    public static final String SUMMERIZE = "summerize";





    public StoreReportSharedPreferenc(Context context){
        this.mContext = context;
        mPref = mContext.getSharedPreferences(PREF_NAME_REPORT, PRIVATE_MODE);
        mEditor = mPref.edit();
    }

    public void storeUserInformation(String mPublicNot, String mScareScore, String mSummarize){

        mEditor.putString(HARASSED,mPublicNot);
        mEditor.putString(SCARE_SCORE,mScareScore);
        mEditor.putString(SUMMERIZE,mSummarize);
        mEditor.commit();
    }


    public void clearInformation(Context context) {
        // Clearing all data from Shared Preferences
        if (context != null) {
            mEditor.clear();
            mEditor.commit();
        }
    }

    public HashMap<String, String> getUserInformation(){
        HashMap<String, String> UserInformation = new HashMap<String, String>();

        UserInformation.put(HARASSED, mPref.getString(HARASSED, ""));
        UserInformation.put(SCARE_SCORE, mPref.getString(SCARE_SCORE, ""));
        UserInformation.put(SUMMERIZE, mPref.getString(SUMMERIZE, ""));
        return UserInformation;
    }


  /*  public HashMap<String, Integer> getLocationsID(){
        HashMap<String, Integer> district = new HashMap<String, Integer>();
        district.put(DISTRICT_ID_PRF, mPref.getInt(DISTRICT_ID_PRF,-1));
        district.put(AREA_ID_PRF, mPref.getInt(AREA_ID_PRF, -1));
        return district;
    }*/
}
