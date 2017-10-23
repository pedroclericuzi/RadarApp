package pedroclericuzi.appradar.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.FacebookSdk;

import pedroclericuzi.appradar.Controller.HelperClass;
import pedroclericuzi.appradar.Controller.sdkFacebook;
import pedroclericuzi.appradar.R;

public class Splash extends AppCompatActivity {
    HelperClass helperClass;
    sdkFacebook sdkFacebook;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        helperClass = new HelperClass(Splash.this);
        sdkFacebook = new sdkFacebook();
        setContentView(R.layout.activity_splash);
        splash();
    }
    public void splash(){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sdkFacebook.estaLogado()){
                    helperClass.transicaoTela(Splash.this, Main.class);
                    finish();
                } else {
                    helperClass.transicaoTela(Splash.this, LoginUsuario.class);
                    finish();
                }
            }
        },2000);
    }

}
