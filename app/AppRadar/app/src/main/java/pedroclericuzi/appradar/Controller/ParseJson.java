package pedroclericuzi.appradar.Controller;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pedroclericuzi.appradar.Model.InfoPlaces;

/**
 * Created by pedroclericuzi on 24/10/17.
 */

public class ParseJson {

    private Context context;

    public ParseJson(Context context) {
        this.context = context;
    }

    public ArrayList<InfoPlaces> parse (String file){
        ArrayList<InfoPlaces> listPlaces = new ArrayList<>();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            JSONObject obj = new JSONObject(file);
            JSONArray getResults = obj.getJSONArray("results");
            for (int i = 0; i < getResults.length();i++) {
                if (i>0){ //A posição 0 contém apenas o nome da Cidade
                    JSONObject place = getResults.getJSONObject(i);//.get("name");
                    JSONObject geometry = place.getJSONObject("geometry");
                    JSONObject location = geometry.getJSONObject("location");
                    //DADOS QUE SERAO USADOS
                    Double latPlace = location.getDouble("lat");
                    Double lngPlace = location.getDouble("lng");
                    String nomePlace = place.get("name").toString();
                    String iconPlace = place.get("icon").toString();
                    String vicinity = place.get("vicinity").toString();
                    List<Address> addresses = geocoder.getFromLocation(latPlace, lngPlace, 1);
                    String cep = addresses.get(0).getPostalCode();
                    Log.d("Dados lugar", place.get("name") + ", CEP: " + addresses.get(0).getPostalCode());
                    listPlaces.add(new InfoPlaces(latPlace,lngPlace, iconPlace, nomePlace, vicinity, cep));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listPlaces;
    }

}
