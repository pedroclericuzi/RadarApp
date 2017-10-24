package pedroclericuzi.appradar.View;

import android.content.Context;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pedroclericuzi.appradar.Model.InfoPlaces;
import pedroclericuzi.appradar.R;

/**
 * Created by pedroclericuzi on 24/10/17.
 */

public class LugarAdapter extends ArrayAdapter<InfoPlaces> {
    private Context context;
    private ArrayList<InfoPlaces> infoPlaces;
    public LugarAdapter(Context context, ArrayList<InfoPlaces> infoPlaces) {
        super(context, R.layout.row_custom, infoPlaces);
        this.context = context;
        this.infoPlaces = infoPlaces;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.row_custom, parent, false);
        TextView tv_nome = (TextView) v.findViewById(R.id.nomeLugar);
        TextView tv_endereco = (TextView) v.findViewById(R.id.enderecoLugar);
        TextView tv_cep = (TextView) v.findViewById(R.id.cepLugar);

        tv_nome.setText(infoPlaces.get(position).getName());
        tv_endereco.setText(infoPlaces.get(position).getVicinity());
        tv_cep.setText("CEP: " + infoPlaces.get(position).getCep());

        return v;
    }
}
