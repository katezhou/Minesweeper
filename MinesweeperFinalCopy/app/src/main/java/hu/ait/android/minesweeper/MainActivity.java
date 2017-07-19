package hu.ait.android.minesweeper;

import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import hu.ait.android.minesweeper.View.MinesweeperView;
import hu.ait.android.minesweeper.model.model;


public class MainActivity extends AppCompatActivity {
    private Switch mySwitch;
    public static final String SIZE = "SIZE";
    public static final String NUMBER = "NUMBER";
    private Chronometer chronometer;
    public static ToggleButton toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout layoutRoot = (LinearLayout) findViewById(R.id.layoutRoot);
        final MinesweeperView gameView = (MinesweeperView) findViewById(R.id.gameView);
        button(layoutRoot, gameView);
        toggle();
        model.getInstance().mode = model.OFF;
        if (getIntent().hasExtra(WelcomeActivity.NUMBER) && getIntent().hasExtra(WelcomeActivity.SIZE)) {
            setMainVariables();
        }
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        startTime();
    }

    private void toggle() {
        toggle = (ToggleButton) findViewById(R.id.toggle);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    model.getInstance().mode = model.ON;
                    toggle.setTextOn(getString(R.string.insert_remove));
                } else {
                    model.getInstance().mode = model.OFF;
                    toggle.setTextOff(getString(R.string.flags_off));
                }
            }
        });
    }

    private void button(final LinearLayout layoutRoot, final MinesweeperView gameView) {
        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // clear the game board
                Snackbar.make(layoutRoot, R.string.are_you_sure, Snackbar.LENGTH_LONG).setAction(R.string.yes,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gameView.clearGameArea();
                                startTime();
                            }
                        }).show();
            }
        });
    }

    private void setMainVariables() {
        model.DIM = (short) Integer.parseInt(getIntent().getStringExtra(SIZE));
        model.MINENUM = (short) Integer.parseInt(getIntent().getStringExtra(NUMBER));
        MinesweeperView.DIM = (short) Integer.parseInt(getIntent().getStringExtra(SIZE));
        model.mines = new Pair[model.MINENUM];
        model.model = new short[model.DIM][model.DIM];
        model.getInstance().resetModel();
    }

    public void showToastMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        super.onStop();
    }


    public void startTime() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    public void stopTime() {
        chronometer.stop();
    }

}
