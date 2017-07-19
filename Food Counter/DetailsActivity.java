package hu.ait.android.finalproject;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import hu.ait.android.finalproject.items.foodInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DetailsActivity extends AppCompatActivity {
    public ProgressBar calories;
    public ProgressBar total_fat;
    public ProgressBar cholestrol;
    public ProgressBar sodium;
    public ProgressBar carbohydrate;
    public ProgressBar protein;
    public TextView one;
    public TextView two;
    public TextView three;
    public TextView four;
    public TextView five;
    public TextView six;

    public foodInfo foodInfo;
    public String foodName;
    public TextView nameTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        calories = (ProgressBar) findViewById(R.id.calories);
        total_fat = (ProgressBar) findViewById(R.id.total_fat);
        cholestrol = (ProgressBar) findViewById(R.id.cholesterol);
        sodium = (ProgressBar) findViewById(R.id.sodium);
        carbohydrate = (ProgressBar) findViewById(R.id.total_carbohydrate);
        protein = (ProgressBar) findViewById(R.id.protein);
        nameTitle = (TextView) findViewById(R.id.foodTitle);

        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (TextView) findViewById(R.id.three);
        four = (TextView) findViewById(R.id.four);
        five = (TextView) findViewById(R.id.five);
        six = (TextView) findViewById(R.id.six);

        if (!getIntent().hasExtra("foodInfo")) {
            Toast.makeText(DetailsActivity.this, "Food information not found", Toast.LENGTH_LONG);
        }
        foodInfo = (hu.ait.android.finalproject.items.foodInfo) getIntent().getSerializableExtra("foodInfo");
        foodName = foodInfo.getFoodName();
        nameTitle.setText(foodName);
        setValues(foodName);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void setValues(String foodName) {
        if (foodName.equals("apple")) {
            setValuesHelper(52, 0, 0, 1, 14, 0);
        } else if (foodName.equals("cheese")) {
            setValuesHelper(113, 9, 29, 174, 0, 7);
        } else if (foodName.equals("chocolate")) {
            setValuesHelper(546, 31, 8, 24, 61, 5);
        } else if (foodName.equals("pasta")) {
            setValuesHelper(131, 1, 44, 6, 25, 5);
        } else if (foodName.equals("ice cream")) {
            setValuesHelper(207, 11, 44, 50, 24, 4);
        } else if (foodName.equals("test1")) {
            setValuesHelper(2200, 11, 44, 50, 24, 4);
        } else if (foodName.equals("test2")) {
            setValuesHelper(1500, 11, 44, 50, 24, 4);
        }
        else {
            Toast.makeText(DetailsActivity.this, "Food information not found", Toast.LENGTH_LONG);
        }
    }

    public void setValuesHelper(int calories, int total_fat, int cholestrol, int sodium, int carbohydrate, int protein) {
        this.calories.setProgress(calories);
        this.total_fat.setProgress(total_fat);
        this.cholestrol.setProgress(cholestrol);
        this.sodium.setProgress(sodium);
        this.carbohydrate.setProgress(carbohydrate);
        this.protein.setProgress(protein);

        setColors(this.calories, calories, one);
        setColors(this.total_fat, total_fat, two);
        setColors(this.cholestrol, total_fat, three);
        setColors(this.sodium, sodium, four);
        setColors(this.carbohydrate, carbohydrate, five);
        setColors(this.protein, protein, six);
    }

    private void setColors(ProgressBar progressBar, int number, TextView tv) {

        int percent = (int) (((double) number / (double) progressBar.getMax()) * 100);
        if (percent < 25) {
            progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#4094fa"), PorterDuff.Mode.MULTIPLY);
        } else if (percent >= 25 && percent < 75) {
            progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#faad40"), PorterDuff.Mode.MULTIPLY);
        } else {
            progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#c3fc1212"), PorterDuff.Mode.MULTIPLY);
        }
        tv.setText("   " + percent + "%");
    }


}
