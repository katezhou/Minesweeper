package hu.ait.android.weatherinfo.swipeanddrag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import hu.ait.android.weatherinfo.R;
import hu.ait.android.weatherinfo.connection.Coord;
import hu.ait.android.weatherinfo.connection.Weather;
import hu.ait.android.weatherinfo.connection.WeatherResult;
import hu.ait.android.weatherinfo.connection.WeatherService;
import hu.ait.android.weatherinfo.model.weatherInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static String cityName;
    weatherInfo weatherInfo;
    public TextView currTemp;
    public TextView description;
    public TextView minTemp;
    public TextView maxTemp;
    public TextView latitude;
    public TextView longitude;
    public ImageView imageView;
    public TextView cityTitle;
    private GoogleMap mMap;

    public static double aLat;
    public static double aLong;

    private WeatherService weatherService;

    private String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private String ID_KEY = "c482bd9b17f83d88b1ae0f15599aa765";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        cityName = "";
        weatherInfo = null;
        cityTitle = (TextView) findViewById(R.id.cityTitle);
        currTemp = (TextView) findViewById(R.id.currTemp);
        description = (TextView) findViewById(R.id.description);
        minTemp = (TextView) findViewById(R.id.minTemp);
        maxTemp = (TextView) findViewById(R.id.maxTemp);
        latitude = (TextView) findViewById(R.id.latitude);
        longitude = (TextView) findViewById(R.id.longitude);
        imageView = (ImageView) findViewById(R.id.image);
        if (getIntent().hasExtra("weatherInfo")) {
            weatherInfo = (hu.ait.android.weatherinfo.model.weatherInfo) getIntent().getSerializableExtra("weatherInfo");
            cityName = weatherInfo.getCityName();
        } else {
            Toast.makeText(DetailsActivity.this, R.string.enter_valid_city, Toast.LENGTH_LONG);
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherService = retrofit.create(WeatherService.class);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        weatherService.getWeather(cityName, "metric", ID_KEY).enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                if (!response.body().getCod().equals("404")) {
                    cityName = response.body().getName();
                    cityTitle.append(response.body().getName());
                    currTemp.append("" + (response.body().getMain().getTemp()) + "\u2103");
                    description.append("" + ((Weather) response.body().getWeather().get(0)).getDescription());
                    minTemp.append("" + response.body().getMain().getTempMin() + "\u2103");
                    maxTemp.append("" + response.body().getMain().getTempMax() + "\u2103");
                    latitude.append("" + ((Coord) (response.body().getCoord())).getLat() + "\u00B0");
                    longitude.append("" + ((Coord) (response.body().getCoord())).getLon() + "\u00B0");
                    aLat = ((Coord) (response.body().getCoord())).getLat();
                    aLong = ((Coord) (response.body().getCoord())).getLon();
                    String result = "http://openweathermap.org/img/w/";
                    result += ((Weather) response.body().getWeather().get(0)).getIcon();
                    result += ".png";
                    Glide.with(DetailsActivity.this).load(result).into(imageView);
                    mMap.clear();
                    onMapReady(mMap);
                } else {
                    Toast.makeText(DetailsActivity.this, R.string.error_warning, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {
                Toast.makeText(DetailsActivity.this, R.string.error_warning + t.toString(), Toast.LENGTH_LONG);

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(aLat, aLong);
        mMap.addMarker(new MarkerOptions().position(sydney).title(getString(R.string.marker) + cityName));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}

