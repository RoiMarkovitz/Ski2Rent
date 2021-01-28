package finalproject.ski2rent.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import finalproject.ski2rent.R;

public class Activity_OrdersStatus extends Activity_Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("OrdersStatusLifeCycle", "onCreate: Activity_OrdersStatus");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__orders_status);
    }


    @Override
    protected void onStart() {
        Log.d("OrdersStatusLifeCycle", "onStart: Activity_OrdersStatus");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("OrdersStatusLifeCycle", "onResume: Activity_OrdersStatus");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("OrdersStatusLifeCycle", "onPause: Activity_OrdersStatus");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("OrdersStatusLifeCycle", "onStop: Activity_OrdersStatus");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("OrdersStatusLifeCycle", "onDestroy: Activity_OrdersStatus");
        super.onDestroy();
    }

} // Activity_OrdersStatus