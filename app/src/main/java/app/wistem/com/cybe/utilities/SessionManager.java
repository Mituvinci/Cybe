package app.wistem.com.cybe.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hi on 28-Oct-16.
 */

public class SessionManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "cybepref";
    private static final String IS_LOGIN = "IsLoggedIn";


    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        editor.commit();
    }




    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
