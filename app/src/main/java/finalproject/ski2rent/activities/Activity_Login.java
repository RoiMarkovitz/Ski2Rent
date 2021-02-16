package finalproject.ski2rent.activities;


import android.os.Bundle;
import android.util.Log;

import finalproject.ski2rent.R;

public class Activity_Login extends Activity_Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("loginLifeCycle", "onCreate: Activity_Login");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
    }

    @Override
    protected void onStart() {
        Log.d("loginLifeCycle", "onStart: Activity_Login");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("loginLifeCycle", "onResume: Activity_Login");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("loginLifeCycle", "onPause: Activity_Login");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("loginLifeCycle", "onStop: Activity_Login");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("loginLifeCycle", "onDestroy: Activity_Login");
        super.onDestroy();
    }


} // Activity_Login