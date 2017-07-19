package hu.ait.android.weatherinfo.connection;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhou_xiaoquan on 6/28/16.
 */
public class HttpGetTask extends AsyncTask<String, Void, String> {

    private Context context;

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        HttpURLConnection conn = null;
        InputStream is = null;

        try {
            URL url = new URL(params[0]);
            conn = (HttpURLConnection) url.openConnection();


            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                int ch;
                StringBuffer sb = new StringBuffer();
                while ((ch = is.read()) != -1) {
                    sb.append((char) ch);
                }
                result = sb.toString();
            }


        } catch (Exception e) {
            e.printStackTrace();
            result = e.getMessage();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return result;
    }


    public HttpGetTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(String result) {
        WeatherResult moneyResult = new Gson().fromJson(result, WeatherResult.class);
        EventBus.getDefault().post(moneyResult);

    }
}
