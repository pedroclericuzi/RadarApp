package pedroclericuzi.appradar.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import pedroclericuzi.appradar.Controller.HelperClass;
import pedroclericuzi.appradar.R;

public class LoginUsuario extends AppCompatActivity {
    LoginButton loginButton;
    CallbackManager callBackManager;
    TextView textView3;
    HelperClass helperClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        inicializarControles();
        LoginFB();
    }

    private void inicializarControles (){
        loginButton = (LoginButton) findViewById(R.id.login_button);
        //loginButton.setPublishPermissions(Arrays.asList("public_profile"));
        callBackManager = CallbackManager.Factory.create();
    }

    private void LoginFB(){
        loginButton.setReadPermissions(Arrays.asList("email"));
        loginButton.registerCallback(callBackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //textView3.setText("Nome: ");
                helperClass = new HelperClass(LoginUsuario.this);
                helperClass.transicaoTela(LoginUsuario.this, Main.class);
                finish();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginUsuario.this, "Autorize o login para poder acessar nosso conteúdo.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(LoginUsuario.this, "Ocorreu o seguinte erro: "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Log.d("teste", "6");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callBackManager.onActivityResult(requestCode, resultCode, data);
    }

}
