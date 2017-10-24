package pedroclericuzi.appradar.Model;

/**
 * Created by pedroclericuzi on 24/10/17.
 */

public class InfoPlaces {

    private double lat;
    private double lng;
    private String urlIcon;
    private String name;
    private String vicinity;
    private String cep;
    public InfoPlaces(double lat, double lng, String urlIcon, String name, String vicinity, String cep) {
        this.lat = lat;
        this.lng = lng;
        this.urlIcon = urlIcon;
        this.name = name;
        this.vicinity = vicinity;
        this.cep = cep;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
