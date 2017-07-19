package hu.ait.android.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import hu.ait.android.finalproject.items.calendarItem;

public class CalendarActivity extends AppCompatActivity {

    Button checkDate;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        checkDate = (Button) findViewById(R.id.btnCheckDate);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        checkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarItem object = new calendarItem(datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear());
                Intent myIntent = new Intent(CalendarActivity.this, MainActivity.class);
                myIntent.putExtra("Calendar Object", object);
                startActivity(myIntent);
            }
        });
    }
}
