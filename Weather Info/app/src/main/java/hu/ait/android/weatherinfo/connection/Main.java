package hu.ait.android.weatherinfo.connection;

/**
 * Created by zhou_xiaoquan on 6/28/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    @Expose
    public String temp;
    @SerializedName("pressure")
    @Expose
    public String pressure;
    @SerializedName("humidity")
    @Expose
    public String humidity;
    @SerializedName("temp_min")
    @Expose
    public String tempMin;
    @SerializedName("temp_max")
    @Expose
    public String tempMax;

    public String getTemp() {
        return temp;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getTempMin() {
        return tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }
}