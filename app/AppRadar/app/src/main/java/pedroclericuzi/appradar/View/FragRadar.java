package pedroclericuzi.appradar.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import pedroclericuzi.appradar.Controller.GetLocation;
import pedroclericuzi.appradar.R;

/**
 * Created by pedroclericuzi on 22/10/17.
 */

public class FragRadar extends Fragment {
    GetLocation getLocationClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_radar, container, false);
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        // lat,lng, your current location
        getLocationClass = new GetLocation(getContext(), getActivity());
        int sizeLista = getLocationClass.getLocation().size();
        if (sizeLista>1){
            Double lat = getLocationClass.getLocation().get(0);
            Double lng = getLocationClass.getLocation().get(1);
            Log.d("Posição", "" + lat);
            try {
                List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
                Log.d("Meu numero", "" + addresses.get(0).getPostalCode());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Log.d("Posição", "Nenhuma coordenada encontrada");
        }

        return v;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case GetLocation.REQUEST_LOCATION:
                getLocationClass.getLocation();
                break;
        }
    }

}
