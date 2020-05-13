package com.example.projectcmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Splash screen timer. 3000 = 3 seconds
    private static int SPLASH_TIMER = 3000;

    ImageView iv_logo;
    TextView poweredBy, cal_counter;
    Animation sideAnim, bottomAnim, topAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //extends the whole screen. must be placed before setContentView
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        iv_logo = findViewById(R.id.logo);
        cal_counter = findViewById(R.id.calcount);
        poweredBy = findViewById(R.id.powered_by_team5);

        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim);

        iv_logo.setAnimation(sideAnim);
        cal_counter.setAnimation(sideAnim);
        poweredBy.setAnimation(bottomAnim);

        //Intent that takes you from the Slash screen to the Sign In Activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(MainActivity.this, SignIn.class);
                startActivity(i);
                finish();

            }
        },SPLASH_TIMER);


    }


}
