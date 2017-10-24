package pedroclericuzi.appradar.Controller;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pedroclericuzi on 24/10/17.
 */

public class DownloadData extends AsyncTask<String, Void, String>{
    @Override
    protected String doInBackground(String... link)  {
        String file = "";
        InputStream in = null;
        try {
            URL url = new URL(link[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.getRequestProperty("GET");
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.connect();

            in = conn.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int count; (count = in.read(buffer)) != -1; ) {
                out.write(buffer, 0, count);
            }
            byte[] response = out.toByteArray();
            file = new String(response, "UTF-8");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
