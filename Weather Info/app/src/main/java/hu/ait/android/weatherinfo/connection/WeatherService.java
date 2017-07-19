package hu.ait.android.weatherinfo.connection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhou_xiaoquan on 6/28/16.
 */
public interface WeatherService {

    //http://api.openweathermap.org/data/2.5/weather?q=Paris&units=metric&appid=f3d694bc3e1d44c1ed5a97bd1120e8fe

    @GET("weather")
    Call<WeatherResult> getWeather(@Query("q") String city,
                                   @Query("units") String units,
                                   @Query("appid") String appid);

}