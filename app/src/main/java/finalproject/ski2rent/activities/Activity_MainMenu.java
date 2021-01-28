package finalproject.ski2rent.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import finalproject.ski2rent.R;

public class Activity_MainMenu extends Activity_Base {

    private Button menu_BTN_prices;
    private Button menu_BTN_skis;
    private Button menu_BTN_snowboards;
    private Button menu_BTN_ordersStatus;
    private ImageView menu_IMG_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("mainMenuLifeCycle", "onCreate: Activity_MainMenu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main_menu);

        findViews();

        Glide
                .with(this)
                .load(R.drawable.img_menu_background)
                .into(menu_IMG_background);

        menu_BTN_prices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPricesActivity(Activity_MainMenu.this);
            }
        });

        menu_BTN_skis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRentSkisActivity(Activity_MainMenu.this);
            }
        });

        menu_BTN_snowboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRentSnowboardsActivity(Activity_MainMenu.this);
            }
        });

        menu_BTN_ordersStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrdersStatusActivity(Activity_MainMenu.this);
            }
        });

    } // onCreate

    private void findViews() {
        menu_BTN_prices = findViewById(R.id.menu_BTN_prices);
        menu_BTN_skis = findViewById(R.id.menu_BTN_skis);
        menu_BTN_snowboards = findViewById(R.id.menu_BTN_snowboards);
        menu_BTN_ordersStatus = findViewById(R.id.menu_BTN_ordersStatus);
        menu_IMG_background = findViewById(R.id.menu_IMG_background);
    }

    private void openPricesActivity(Activity activity) {
        Intent myIntent = new Intent(activity, Activity_Prices.class);
        startActivity(myIntent);
    }

    private void openRentSkisActivity(Activity activity) {
        Intent myIntent = new Intent(activity, Activity_RentSkis.class);
        startActivity(myIntent);
    }

    private void openRentSnowboardsActivity(Activity activity) {
        Intent myIntent = new Intent(activity, Activity_RentSnowboards.class);
        startActivity(myIntent);
    }

    private void openOrdersStatusActivity(Activity activity) {
        Intent myIntent = new Intent(activity, Activity_OrdersStatus.class);
        startActivity(myIntent);
    }

    @Override
    protected void onStart() {
        Log.d("mainMenuLifeCycle", "onStart: Activity_MainMenu");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("mainMenuLifeCycle", "onResume: Activity_MainMenu");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("mainMenuLifeCycle", "onPause: Activity_MainMenu");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("mainMenuLifeCycle", "onStop: Activity_MainMenu");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("mainMenuLifeCycle", "onDestroy: Activity_MainMenu");
        super.onDestroy();
    }



} // Activity_MainMenu