package pedroclericuzi.appradar.Controller;

import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;

/**
 * Created by pedroclericuzi on 21/10/17.
 */

public class sdkFacebook {

    HelperClass helperClass;

    public boolean estaLogado(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        //Log.d("Está logado?", "Res: "+accessToken);
        return accessToken != null;
    }

    public void Sair (){
        if (AccessToken.getCurrentAccessToken() == null) { //Verifica se o usuário já não saiu
            return;
        }
        //Limpa as sessões do usuário
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE,
                new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }


}
