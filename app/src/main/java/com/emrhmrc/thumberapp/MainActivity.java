package com.emrhmrc.thumberapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.emrhmrc.thumber.ThumberActivity;
import com.emrhmrc.thumber.ThumberData;
import com.emrhmrc.thumber.VideoFrom;
import com.emrhmrc.thumber.util.ImageHelper;
import com.emrhmrc.thumberapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnCamera.setOnClickListener(view -> {
            Intent i = new Intent(this, ThumberActivity.class);
            i.putExtra(ThumberData.ThumberCount.name(), 5);
            i.putExtra(ThumberData.Duration.name(), 9);
            i.putExtra(ThumberData.VideoFrom.name(), VideoFrom.CAPTURE);
            startActivityForResult(i, ThumberActivity.THUMBER_ACTIVTY);
        });
        binding.btnGalery.setOnClickListener(view -> {
            Intent i = new Intent(this, ThumberActivity.class);
            i.putExtra(ThumberData.ThumberCount.name(), 5);
            i.putExtra(ThumberData.Duration.name(), 9);
            i.putExtra(ThumberData.VideoFrom.name(), VideoFrom.GALLERY);
            startActivityForResult(i, ThumberActivity.THUMBER_ACTIVTY);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ThumberActivity.THUMBER_ACTIVTY) {
            if (resultCode == Activity.RESULT_OK) {
                Log.d(TAG, "onActivityResult: " + data.getStringExtra(ThumberData.VideoUri.name()));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
