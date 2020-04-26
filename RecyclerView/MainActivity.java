package com.example.caloriecalculatorrecyclerview;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<String> mImages = new ArrayList<>();
    private final ArrayList<String> mNameList = new ArrayList<>();
    private final ArrayList<String> mCalorieList = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private CalorieListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                int listSize = mNameList.size();
//                // Add a new word to the wordList.
//                mNameList.add("+ Word " + listSize);
//                mCalorieList.add(" ");
//                // Notify the adapter, that the data has changed.
//                mRecyclerView.getAdapter().notifyItemInserted(listSize);
//                // Scroll to the bottom.
//                mRecyclerView.smoothScrollToPosition(listSize);
            }
        });

        loadFoodsData();

        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new CalorieListAdapter(this, mImages, mNameList, mCalorieList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    private void loadFoodsData() {
        mImages.clear();
        mNameList.clear();
        mCalorieList.clear();

        //load arrays from strings
        String[] images = getResources().getStringArray(R.array.food_images);
        String[] foodTitle = getResources().getStringArray(R.array.food_titles);
        String[] foodCalories = getResources().getStringArray(R.array.food_calories);

//        for(int i=0; i<images.length; i++){
//            String dataD = images[i];
//            byte[] bytes = dataD.getBytes();
//            dataD = new String(android.util.Base64.encode(bytes, Base64.DEFAULT));
//            mImages.add(images[i]);
//        }

        for(int i=0; i<foodTitle.length; i++){
            mImages.add(images[i]);
            mNameList.add(foodTitle[i]);
            mCalorieList.add(foodCalories[i]);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
