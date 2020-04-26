package com.example.calorycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar_circular;
    private ProgressBar progressBar_bar1;
    private ProgressBar progressBar_bar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progCirc();
        prog1();
        prog2();

    }

    private void progCirc() {

        progressBar_circular = findViewById(R.id.progressBar_circular);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_circular.setVisibility(View.VISIBLE);
            }
        },6000);
    }


    private void prog1() {

        progressBar_bar1 = findViewById(R.id.progressBar_bar1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_bar1.setVisibility(View.VISIBLE);
            }
        },6000);
    }


    private void prog2() {

        progressBar_bar2 = findViewById(R.id.progressBar_bar2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_bar2.setVisibility(View.GONE);
            }
        },6000);
    }

//        Thread thread = new Thread();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<100;){
//                    try {
//                        sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    progressBar_circular.setVisibility(View.VISIBLE);
//                    i=i+10;
//                }
//
//            }
//        },1000);
//        thread.start();
//    }
}
