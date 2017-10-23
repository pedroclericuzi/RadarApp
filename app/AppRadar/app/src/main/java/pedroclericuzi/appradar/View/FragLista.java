package pedroclericuzi.appradar.View;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pedroclericuzi.appradar.R;

/**
 * Created by pedroclericuzi on 22/10/17.
 */

public class FragLista extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_lista, container, false);

        return v;
    }
}
