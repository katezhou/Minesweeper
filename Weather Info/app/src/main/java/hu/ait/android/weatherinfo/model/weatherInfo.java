package hu.ait.android.weatherinfo.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by zhou_xiaoquan on 6/27/16.
 */
public class weatherInfo extends SugarRecord implements Serializable {

    private String cityName;
    private double currentTemp;
    private double minTemp;
    private double maxTemp;
    private String description;

    public weatherInfo() {
    }

    public weatherInfo(String cityName) {
        this.cityName = cityName;
    }

    public weatherInfo(String cityName, double currentTemp, double minTemp, double maxTemp, String description) {
        this.cityName = cityName;
        this.currentTemp = currentTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.description = description;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCityName() {
        return cityName;
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public String getDescription() {
        return description;
    }
}
