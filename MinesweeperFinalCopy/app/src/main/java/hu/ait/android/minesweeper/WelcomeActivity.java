package hu.ait.android.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    public static final String SIZE = "SIZE";
    public static final String NUMBER = "NUMBER";
    Intent myIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button btnDisplay=(Button)findViewById(R.id.button);
        final RadioGroup sizeGrid = (RadioGroup) findViewById(R.id.sizeGrid);
        final RadioGroup numMines = (RadioGroup) findViewById(R.id.numMines);
        myIntent.setClass(WelcomeActivity.this, MainActivity.class);
        button(btnDisplay, sizeGrid, numMines);
    }

    private void button(Button btnDisplay, final RadioGroup sizeGrid, final RadioGroup numMines) {
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedIdSize = sizeGrid.getCheckedRadioButtonId();
                Button selectedSize =(RadioButton)findViewById(selectedIdSize);
                int selectedIdMines = numMines.getCheckedRadioButtonId();
                Button selectedMines =(RadioButton)findViewById(selectedIdMines);
                if (selectedMines == null || selectedSize == null) {
                    Toast.makeText(WelcomeActivity.this, R.string.warning, Toast.LENGTH_LONG).show();
                } else {
                    myIntent.putExtra(SIZE, selectedSize.getTag().toString());
                    myIntent.putExtra(NUMBER, selectedMines.getTag().toString());
                    startActivity(myIntent);
                }
            }
        });
    }
}
