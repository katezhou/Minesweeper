package hu.ait.android.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hu.ait.android.finalproject.items.foodInfo;

/**
 * Created by zhou_xiaoquan on 7/5/16.
 */
public class foodAdder extends AppCompatActivity {


    public static final String FOOD_INFO = "foodInfo";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_adder);
        this.setTitle("Food Item");
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(0xffffff);

        Button saveButton = (Button) findViewById(R.id.btnSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newTodoTitleEdittext = (EditText) findViewById(R.id.etCityName);
                String foodName = newTodoTitleEdittext.getText().toString();
                foodInfo foodInfo = new foodInfo(foodName, MainActivity.object);
                Intent result = new Intent();
                result.putExtra(FOOD_INFO, foodInfo);
                setResult(Activity.RESULT_OK, result);
                if (newTodoTitleEdittext.getText().equals("")) {
                    Toast.makeText(foodAdder.this, "Enter food item", Toast.LENGTH_LONG);
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
