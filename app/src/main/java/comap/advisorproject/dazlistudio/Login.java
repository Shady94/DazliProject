package comap.advisorproject.dazlistudio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends Activity implements FacebookCallback<LoginResult>{

    Button btn_login;
    private  LoginButton loginButton;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("Debbug", "Entramos en onCreate");
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent vis = new Intent(getApplicationContext(), Visualizador.class);
                startActivity(vis);
            }
        });

        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");

        loginButton.registerCallback(callbackManager, this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.d("Debbug", "RequestCode: "+requestCode+"");
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        // App code
        Log.d("Debbug" , "Entrando en onSuccess");
        AccessToken.getCurrentAccessToken();
        GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject json,
                                            GraphResponse response) {
                        // TODO Auto-generated method stub
                        if (response.getError() != null) {
                            // handle error
                        } else {
                            try {
                                String email_usr = json.getString("email");
                                String name = json.getString("name");
                                Log.d("Debbug" , name);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }).executeAsync();
        Log.d("Debbug", "Entrando en onSuccess antes de ir a visualizador");
        Intent vis = new Intent(getApplicationContext(), Visualizador.class);
        startActivity(vis);
    }

    @Override
    public void onCancel() {
        // App code
        Log.d("Debbug" , "Entrando en onCancel");
    }

    @Override
    public void onError(FacebookException exception) {
        // App code
        Log.d("Debbug" , "Entrando en onError");
    }

}