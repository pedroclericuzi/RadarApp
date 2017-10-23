package pedroclericuzi.appradar.Controller;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.ArrayList;

import pedroclericuzi.appradar.View.Splash;

/**
 * Created by pedroclericuzi on 23/10/17.
 */

public class GetLocation {
    public static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    //static final int REQUEST_LOCATION = 1;
    private Context context;
    private Activity activity;
    public GetLocation(Context context, Activity activity){
        this.context = context;
        this.activity = activity;
        locationManager = (LocationManager) activity.getSystemService(context.LOCATION_SERVICE);
    }

    public ArrayList<Double> getLocation() {

        ArrayList<Double> posicao = new ArrayList();

        if(ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {

            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null){
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                posicao.add(0,latitude);
                posicao.add(1,longitude);
                Log.d("Latitude: ",latitude +" e "+ longitude);
            } else {
                //Log.d("Latitude: ","Nao estamos localizando sua latitude e nem logitude");
                posicao.add(0,0.0);
            }
        }
        return posicao;
    }
}
