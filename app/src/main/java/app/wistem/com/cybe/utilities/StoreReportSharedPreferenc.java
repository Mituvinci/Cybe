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
    public static final String GENDER="gender";
    public static final String MORE_DETAILS ="moredetails";
    public static final String HARASSER_CERTAINTY = "harassercertainty";
    public static final String PUBLIC = "public";
    ;




    public StoreReportSharedPreferenc(Context context){
        this.mContext = context;
        mPref = mContext.getSharedPreferences(PREF_NAME_REPORT, PRIVATE_MODE);
        mEditor = mPref.edit();
    }

    public void storeUserInformation(String email, String name, String mobile, String gender, String address, String districtbangla,
                                     String districtenglish){

        mEditor.putString(HARASSED,email);
        mEditor.putString(SCARE_SCORE,name);
        mEditor.putString(SUMMERIZE,mobile);
        mEditor.putString(GENDER,gender);
        mEditor.putString(MORE_DETAILS,address);
        mEditor.putString(HARASSER_CERTAINTY,districtbangla);
        mEditor.putString(PUBLIC,districtenglish);
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
        UserInformation.put(GENDER, mPref.getString(GENDER, ""));
        UserInformation.put(MORE_DETAILS, mPref.getString(MORE_DETAILS, ""));
        UserInformation.put(HARASSER_CERTAINTY, mPref.getString(HARASSER_CERTAINTY, ""));
        UserInformation.put(PUBLIC, mPref.getString(PUBLIC, ""));

        return UserInformation;
    }


  /*  public HashMap<String, Integer> getLocationsID(){
        HashMap<String, Integer> district = new HashMap<String, Integer>();
        district.put(DISTRICT_ID_PRF, mPref.getInt(DISTRICT_ID_PRF,-1));
        district.put(AREA_ID_PRF, mPref.getInt(AREA_ID_PRF, -1));
        return district;
    }*/
}
