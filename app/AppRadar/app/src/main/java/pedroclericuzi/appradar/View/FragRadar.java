package pedroclericuzi.appradar.View;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
