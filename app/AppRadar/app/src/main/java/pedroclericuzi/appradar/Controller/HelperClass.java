package pedroclericuzi.appradar.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by pedroclericuzi on 21/10/17.
 */

public class HelperClass {
    private Activity activity;
    public HelperClass(Activity activity){
        this.activity = activity;
    }

    public void transicaoTela(Context context, Class classeSeguinte){
        Intent i = new Intent(context, classeSeguinte);
        context.startActivity(i);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


}
