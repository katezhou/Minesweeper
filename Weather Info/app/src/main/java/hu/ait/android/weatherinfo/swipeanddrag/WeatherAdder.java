package hu.ait.android.weatherinfo.swipeanddrag;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import hu.ait.android.weatherinfo.R;
import hu.ait.android.weatherinfo.model.weatherInfo;

/**
 * Created by zhou_xiaoquan on 6/27/16.
 */
public class WeatherAdder extends AppCompatActivity {


    public static final String NEW_WEATHER_INFO = "newWeatherInfo";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weatheradder);
        this.setTitle(getString(R.string.new_city));
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(0xffffff);

        Button saveButton = (Button) findViewById(R.id.btnSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newTodoTitleEdittext = (EditText) findViewById(R.id.etCityName);
                String newCityName = newTodoTitleEdittext.getText().toString();
                weatherInfo newWeatherInfo = new weatherInfo(newCityName);
                Intent result = new Intent();
                result.putExtra(NEW_WEATHER_INFO, newWeatherInfo);
                setResult(Activity.RESULT_OK, result);
                if (newTodoTitleEdittext.getText().equals("")) {
                    Toast.makeText(WeatherAdder.this, R.string.enter_city_name, Toast.LENGTH_LONG);
                }
                finish();
            }
        });

        Button cancelButton = (Button) findViewById(R.id.btnCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
