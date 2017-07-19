package hu.ait.android.weatherinfo.swipeanddrag;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import hu.ait.android.weatherinfo.R;
import hu.ait.android.weatherinfo.connection.WeatherResult;
import hu.ait.android.weatherinfo.connection.WeatherService;
import hu.ait.android.weatherinfo.items.WeatherAdapter;
import hu.ait.android.weatherinfo.model.weatherInfo;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String NEW_WEATHER_INFO = "newWeatherInfo";
    public static final int NEW_ITEM_REQUEST_CODE = 1;
    private WeatherAdapter weatherAdapter;
    private TextView currTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTodosList();
        startItemAdderActivityWhenAddButtonClicked();

    }


    private void initTodosList() {
        RecyclerView weatherRecyclerView = (RecyclerView) findViewById(R.id.weatherInfo);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        weatherRecyclerView.setLayoutManager(layoutManager);
        weatherAdapter = new WeatherAdapter();
        weatherRecyclerView.setAdapter(weatherAdapter);
    }

    private void startItemAdderActivityWhenAddButtonClicked() {
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, WeatherAdder.class), NEW_ITEM_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                weatherInfo weatherInfo = (weatherInfo) data.getSerializableExtra(WeatherAdder.NEW_WEATHER_INFO);
                weatherAdapter.addItem(weatherInfo);
            }
        }
    }
}
