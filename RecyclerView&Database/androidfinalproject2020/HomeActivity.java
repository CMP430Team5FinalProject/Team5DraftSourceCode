package com.example.androidfinalproject2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.RotateDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.Objects;


public class HomeActivity extends AppCompatActivity {

    private Button logoutBtn, addBtn;
    private TextView textConsumedCals;
    private String stringConsumedCals;
    private Float floatCalories = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
        setTitle("Go Back");

        // get intent and set text of total calories
        textConsumedCals = (TextView)findViewById(R.id.text_consumed_calories);
        stringConsumedCals = getIntent().getStringExtra(MainRecyclerActivity.CALORIES_RESULT);
        textConsumedCals.setText(stringConsumedCals);

        // set circular progress bar
        setCircularProgressBar();

        // click button exit
        logoutBtn = (Button)findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        // click button add
        addBtn = (Button)findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add = new Intent(HomeActivity.this, MainRecyclerActivity.class);
                startActivity(intent_add);

            }
        });
    }

    private void setCircularProgressBar() {
        CircularProgressBar circularProgressBar = findViewById(R.id.circularProgressBar);

        // Set Width
        circularProgressBar.setProgressBarWidth(30); // in DP
        circularProgressBar.setBackgroundProgressBarWidth(30); // in DP

        // Set Progress Max
        circularProgressBar.setProgressMax(3000f);

        if (stringConsumedCals != null){
            floatCalories = Float.valueOf(stringConsumedCals);
            if (floatCalories > 3000f){
                floatCalories = 3000f;
                Toast.makeText(HomeActivity.this,
                               "You consumed too many calories today.", Toast.LENGTH_SHORT).show();;
            }
        }
        // Set progress
        circularProgressBar.setProgress(floatCalories);

        // Set ProgressBar Color
        circularProgressBar.setProgressBarColor(Color.BLUE);
        circularProgressBar.setRoundBorder(true);

    }
}



