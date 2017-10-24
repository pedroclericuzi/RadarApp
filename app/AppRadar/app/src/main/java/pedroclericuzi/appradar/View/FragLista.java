package pedroclericuzi.appradar.View;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import pedroclericuzi.appradar.Controller.DownloadData;
import pedroclericuzi.appradar.Controller.GetLocation;
import pedroclericuzi.appradar.Controller.ParseJson;
import pedroclericuzi.appradar.Model.InfoPlaces;
import pedroclericuzi.appradar.R;

/**
 * Created by pedroclericuzi on 22/10/17.
 */

public class FragLista extends Fragment {
    GetLocation getLocationClass;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_lista, container, false);
        listView = (ListView) v.findViewById(R.id.listaLugares);
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        // lat,lng, your current location
        getLocationClass = new GetLocation(getContext(), getActivity());
        int sizeLista = getLocationClass.getLocation().size();
        if (sizeLista>1){
            Double lat = getLocationClass.getLocation().get(0);
            Double lng = getLocationClass.getLocation().get(1);
            String urlJson = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyD6-wfuz3IJgo8xhP8uF9KU3Ht2QAuGO4A&location="+lat+","+lng+"&radius=1000";

            ParseJson parseJson = new ParseJson(getContext());
            try {
                String conteudo = new DownloadData().execute(urlJson).get();
                ArrayList<InfoPlaces> getInfoPlaces = parseJson.parse(conteudo);
                ArrayAdapter<InfoPlaces> adapterInfoPlaces = new LugarAdapter(getContext(), getInfoPlaces);
                listView.setAdapter(adapterInfoPlaces);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
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
