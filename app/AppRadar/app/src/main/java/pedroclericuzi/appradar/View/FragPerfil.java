package pedroclericuzi.appradar.View;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;

import pedroclericuzi.appradar.Controller.AsyncImagem;
import pedroclericuzi.appradar.R;

/**
 * Created by pedroclericuzi on 22/10/17.
 */

public class FragPerfil extends Fragment {

    ImageView fb_img_user;
    TextView fb_nome_user;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_perfil, container, false);
        Profile profile = Profile.getCurrentProfile();
        fb_img_user = (ImageView) v.findViewById(R.id.fb_img_user);
        fb_nome_user = (TextView) v.findViewById(R.id.fb_nome_user);
        String url = profile.getProfilePictureUri(100, 100).toString();
        String nome = profile.getName();
        new AsyncImagem(fb_img_user).execute(url);
        fb_nome_user.setText(nome);
        return v;
    }
}
