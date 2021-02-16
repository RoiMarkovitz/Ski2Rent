package finalproject.ski2rent.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import finalproject.ski2rent.R;

// TODO remember class related logic in view

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

        validateUser();
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
                openRentDatesActivity(Activity_MainMenu.this, "Ski");
            }
        });

        menu_BTN_snowboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRentDatesActivity(Activity_MainMenu.this, "Snowboard");
            }
        });

        menu_BTN_ordersStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrdersStatusActivity(Activity_MainMenu.this);
            }
        });

    } // onCreate

    private void validateUser() {
        // these are singletons, can be called from anywhere no need context
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser == null) {
            Intent myIntent = new Intent(this, Activity_Login.class);
            startActivity(myIntent);
            finish();
            return;
        }

        Log.d("pttt", "Uid = " + firebaseUser.getUid());
        Log.d("pttt", "DisplayName = " + firebaseUser.getDisplayName());
        Log.d("pttt", "Email = " + firebaseUser.getEmail());
        Log.d("pttt", "PhoneNumber = " + firebaseUser.getPhoneNumber());
        Log.d("pttt", "PhotoUrl = " + firebaseUser.getPhotoUrl());

//        firebaseAuth.signOut();
//        FirebaseAuth.getInstance().signOut();

//         We wont do it like that...
//        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                .setDisplayName("Aliyahu Cohen")
//                .build();
//
//        firebaseUser.updateProfile(profileUpdates);
//        firebaseAuth.updateCurrentUser(firebaseUser);

    }

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

    private void openRentDatesActivity(Activity activity, String boardType) {
        Intent myIntent = new Intent(activity, Activity_RentDates.class);
        myIntent.putExtra(Activity_RentBoards.EXTRA_KEY_BOARD_TYPE, boardType);
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