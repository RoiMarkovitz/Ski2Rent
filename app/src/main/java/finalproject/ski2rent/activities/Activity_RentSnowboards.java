package finalproject.ski2rent.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import finalproject.ski2rent.R;

public class Activity_RentSnowboards extends Activity_Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("RentSnowboardsLifeCycle", "onCreate: Activity_RentSnowboards");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__rent_snowboards);
    }

    @Override
    protected void onStart() {
        Log.d("RentSnowboardsLifeCycle", "onStart: Activity_RentSnowboards");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("RentSnowboardsLifeCycle", "onResume: Activity_RentSnowboards");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("RentSnowboardsLifeCycle", "onPause: Activity_RentSnowboards");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("RentSnowboardsLifeCycle", "onStop: Activity_RentSnowboards");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("RentSnowboardsLifeCycle", "onDestroy: Activity_RentSnowboards");
        super.onDestroy();
    }

} // Activity_RentSnowboards