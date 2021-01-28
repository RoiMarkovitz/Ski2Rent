package finalproject.ski2rent.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import finalproject.ski2rent.R;

public class Activity_Prices extends Activity_Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("PricesLifeCycle", "onCreate: Activity_Prices");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__prices);
    }

    @Override
    protected void onStart() {
        Log.d("PricesLifeCycle", "onStart: Activity_Prices");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("PricesLifeCycle", "onResume: Activity_Prices");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("PricesLifeCycle", "onPause: Activity_Prices");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("PricesLifeCycle", "onStop: Activity_Prices");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("PricesLifeCycle", "onDestroy: Activity_Prices");
        super.onDestroy();
    }

} // Activity_Prices