package finalproject.ski2rent.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import finalproject.ski2rent.R;

public class Activity_RentSkis extends Activity_Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("RentSkisLifeCycle", "onCreate: Activity_RentSkis");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__rent_skis);
    }

    @Override
    protected void onStart() {
        Log.d("RentSkisLifeCycle", "onStart: Activity_RentSkis");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("RentSkisLifeCycle", "onResume: Activity_RentSkis");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("RentSkisLifeCycle", "onPause: Activity_RentSkis");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("RentSkisLifeCycle", "onStop: Activity_RentSkis");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("RentSkisLifeCycle", "onDestroy: Activity_RentSkis");
        super.onDestroy();
    }



} // Activity_RentSkis