package hu.ait.android.weatherinfo.connection;

/**
 * Created by zhou_xiaoquan on 6/28/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    @Expose
    public String all;

    public String getAll() {
        return all;
    }
}