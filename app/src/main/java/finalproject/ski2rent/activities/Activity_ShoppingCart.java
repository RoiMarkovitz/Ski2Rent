package finalproject.ski2rent.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import finalproject.ski2rent.R;

public class Activity_ShoppingCart extends Activity_Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ShoppingCartLifeCycle", "onCreate: Activity_ShoppingCart");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__shopping_cart);
    }

    @Override
    protected void onStart() {
        Log.d("ShoppingCartLifeCycle", "onStart: Activity_ShoppingCart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("ShoppingCartLifeCycle", "onResume: Activity_ShoppingCart");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("ShoppingCartLifeCycle", "onPause: Activity_ShoppingCart");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("ShoppingCartLifeCycle", "onStop: Activity_ShoppingCart");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("ShoppingCartLifeCycle", "onDestroy: Activity_ShoppingCart");
        super.onDestroy();
    }


} // Activity_ShoppingCart