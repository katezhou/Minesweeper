package hu.ait.android.weatherinfo.connection;

/**
 * Created by zhou_xiaoquan on 6/28/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Wind {

    @SerializedName("speed")
    @Expose
    public String speed;

    public Serializable getDeg() {
        return deg;
    }

    public String getSpeed() {
        return speed;
    }

    @SerializedName("deg")
    @Expose
    public String deg;

}