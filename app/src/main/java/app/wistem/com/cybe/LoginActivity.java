package app.wistem.com.cybe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private Button facebookBtn;
    private LoginButton facebookLoginButton;
    private CallbackManager callbackManager;
    private SessionManager mSessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login);
        mSessionManager = new SessionManager(getBaseContext());
        facebookLoginButton = (LoginButton) findViewById(R.id.login_button);
        facebookBtn = (Button) findViewById(R.id.facebookBtn);

        mSessionManager.createLoginSession();
        callbackManager = CallbackManager.Factory.create();

        facebookLoginButton.setReadPermissions(Arrays.asList("email"));

        facebookLoginButton.setReadPermissions(Arrays.asList("email"));
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(final JSONObject object, GraphResponse response) {
                                try {
                                    //info.setText("Hi, " + object.getString("name"));

                                    final String FBemail = object.getString("email");

                                    final String nameFacebook = object.getString("name");
                                    //info.setText(emailFacebook);
                                    mSessionManager.createLoginSession();
                                    setResult(RESULT_OK);
                                    finish();


                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getBaseContext(),"problem occurred login with facebook ", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getBaseContext(),"problem occurred login with facebook", Toast.LENGTH_SHORT).show();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (view == facebookBtn) {
            facebookLoginButton.performClick();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Log.d("google3",checkinsignin+"");
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //startActivity( new Intent(LoginActivity.this, DashBoardActivity.class));
    }
}
