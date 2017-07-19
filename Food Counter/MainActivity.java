package hu.ait.android.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import hu.ait.android.finalproject.items.FoodAdapter;
import hu.ait.android.finalproject.items.calendarItem;
import hu.ait.android.finalproject.items.foodInfo;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_ITEM_REQUEST_CODE = 1;
    private FoodAdapter foodAdapter;
    public static calendarItem object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button overallData = (Button) findViewById(R.id.dateSummary);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                object = null;
            } else {
                object = (calendarItem) extras.getSerializable("Calendar Object");
            }
        } else {
            object = (calendarItem) savedInstanceState.getSerializable("Calendar Object");
        }
        overallData.setText(object.getMonth() + " " + object.getDate() + ", " + object.getYear());
        initTodosList();
        startItemAdderActivityWhenAddButtonClicked();
    }

    private void initTodosList() {
        RecyclerView foodRecylcerView = (RecyclerView) findViewById(R.id.foodInfo);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        foodRecylcerView.setLayoutManager(layoutManager);
        foodAdapter = new FoodAdapter();
        foodRecylcerView.setAdapter(foodAdapter);
    }

    private void startItemAdderActivityWhenAddButtonClicked() {
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.manualAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, foodAdder.class), NEW_ITEM_REQUEST_CODE);
            }
        });

        FloatingActionButton cameraButton = (FloatingActionButton) findViewById(R.id.cameraAdd);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, QRActivity.class), NEW_ITEM_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                foodInfo foodInfo = (foodInfo) data.getSerializableExtra(foodAdder.FOOD_INFO);
                foodInfo.setDateID(object.getDateID());
                foodAdapter.addItem(foodInfo);
            }
        }
    }
}
