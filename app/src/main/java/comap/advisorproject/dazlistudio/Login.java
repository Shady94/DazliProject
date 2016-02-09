package comap.advisorproject.dazlistudio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends Activity implements FacebookCallback<LoginResult>{

    Button btn_login;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;


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
        loginButton.setReadPermissions("email,user_friends");
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
        Log.d("Debbug", "Entrando en onSuccess");
        //Intent vis = new Intent(getApplicationContext(), Visualizador.class);
        //startActivity(vis);

        GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject me, GraphResponse response) {
                        if (response.getError() != null) {
                        } else {
                            try {
                                String email = response.getJSONObject().get("email").toString();
                                Log.d("Debbug", "email"+email);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.d("Debbug", "Error: "+e);
                            }
                            String email = me.optString("email");
                            // send email and id to your web server
                            Log.d("Debbug", response.getRawResponse());
                            Log.d("Debbug", "JSOn: "+me);
                        }
                    }
                }).executeAsync();
        Log.d("Debbug", "Saliendo de onSuccess");
    }

    @Override
    public void onCancel() {
        // En caso de que se cancele el ingreso de datos
        Log.d("Debbug" , "Entrando en onCancel");
    }

    @Override
    public void onError(FacebookException exception) {
        // En caso de que algo malo salga
        Log.d("Debbug" , "Entrando en onError");
    }

}