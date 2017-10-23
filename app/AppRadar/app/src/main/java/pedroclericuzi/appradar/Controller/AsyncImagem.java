package pedroclericuzi.appradar.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pedroclericuzi on 21/10/17.
 */

public class AsyncImagem extends AsyncTask<String, Void, Bitmap> {
    ImageView foto;
    public AsyncImagem(ImageView foto){
        this.foto = foto;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap img = null;
        try {
            URL urlConnection = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream in = connection.getInputStream();
            img = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Ocorreu o seguinte erro", e.getMessage());
        }
        return img;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        foto.setImageBitmap(bitmap);
    }
}
