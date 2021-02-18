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
import com.google.gson.Gson;

import java.util.ArrayList;

import finalproject.ski2rent.R;
import finalproject.ski2rent.callbacks.CallBack_AllBoardsForRent;
import finalproject.ski2rent.callbacks.CallBack_AllPriceData;
import finalproject.ski2rent.callbacks.CallBack_BoardForRent;
import finalproject.ski2rent.callbacks.CallBack_GetShoppingCartData;
import finalproject.ski2rent.callbacks.CallBack_PriceData;
import finalproject.ski2rent.objects.Board;
import finalproject.ski2rent.objects.BoardForRent;
import finalproject.ski2rent.objects.PriceRecord;
import finalproject.ski2rent.objects.Prices;
import finalproject.ski2rent.objects.ShoppingCart;
import finalproject.ski2rent.utils.FireBaseManager;

// TODO remember class related logic in view
// implements View.OnClickListener
public class Activity_MainMenu extends Activity_Base {

    private Button menu_BTN_prices;
    private Button menu_BTN_skis;
    private Button menu_BTN_snowboards;
    private Button menu_BTN_ordersStatus;
    private Button menu_BTN_signOut;
    private ImageView menu_IMG_background;
    private boolean isPricesFinished = false;
    private boolean isSnowboardsForRentFinished = false;
    private boolean isSkisForRentFinished = false;

 //   private ArrayList<PriceRecord> priceTable;
    private ArrayList<BoardForRent> snowboardsForRent;
    private ArrayList<BoardForRent> skisForRent;

 //   private BoardForRent board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("mainMenuLifeCycle", "onCreate: Activity_MainMenu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main_menu);

        FireBaseManager fireBaseManager = FireBaseManager.getInstance();
//        fireBaseManager.loadPriceRecords();
//        fireBaseManager.loadSnowboards();
//        fireBaseManager.loadSkis();
        //     fireBaseManager.setCallBack_PriceData(callBack_priceData);

    //    FirebaseAuth.getInstance().signOut();
    //    validateUser();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    //    String uid = firebaseUser.getUid();
        if (firebaseUser != null) {
            Log.d("pttt", "Uid = " + firebaseUser.getUid());
            Log.d("pttt", "DisplayName = " + firebaseUser.getDisplayName());
            //   Log.d("pttt", "Email = " + firebaseUser.getEmail());
            Log.d("pttt", "PhoneNumber = " + firebaseUser.getPhoneNumber());
        }



        fireBaseManager.readAllPricesFromServer(new CallBack_AllPriceData() {
            @Override
            public void retrieveAllPriceRecord(ArrayList<PriceRecord> pTable) {
                Prices.getInstance().setPriceTable(pTable);
     //           priceTable = pTable;
                isPricesFinished = true;
            }
        });

        fireBaseManager.readAllBoardForRentDataFromServer(Board.eType.Snowboard, new CallBack_AllBoardsForRent() {
            @Override
            public void retrieveAllBoardsForRent(ArrayList<BoardForRent> boards) {
                snowboardsForRent = boards;
                isSnowboardsForRentFinished = true;
            }
        });

        fireBaseManager.readAllBoardForRentDataFromServer(Board.eType.Ski, new CallBack_AllBoardsForRent() {
            @Override
            public void retrieveAllBoardsForRent(ArrayList<BoardForRent> boards) {
                skisForRent = boards;
                isSkisForRentFinished = true;
            }
        });





        findViews();

        Glide
                .with(this)
                .load(R.drawable.img_menu_background)
                .into(menu_IMG_background);

        menu_BTN_prices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPricesFinished) {
                    openPricesActivity(Activity_MainMenu.this);
                }
            }
        });

        menu_BTN_skis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSkisForRentFinished && isPricesFinished) {
                    openRentDatesActivity(Activity_MainMenu.this, "Ski");
                }
            }
        });

        menu_BTN_snowboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSnowboardsForRentFinished && isPricesFinished) {
                    openRentDatesActivity(Activity_MainMenu.this, "Snowboard");
                }
            }
        });

        menu_BTN_ordersStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrdersStatusActivity(Activity_MainMenu.this);
            }
        });

        menu_BTN_signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
            }
        });

    } // onCreate

    private void validateUser() {
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
     //   Log.d("pttt", "Email = " + firebaseUser.getEmail());
        Log.d("pttt", "PhoneNumber = " + firebaseUser.getPhoneNumber());
    //    Log.d("pttt", "PhotoUrl = " + firebaseUser.getPhotoUrl());

//        firebaseAuth.signOut();
//        FirebaseAuth.getInstance().signOut();

    }

    private void findViews() {
        menu_BTN_prices = findViewById(R.id.menu_BTN_prices);
        menu_BTN_skis = findViewById(R.id.menu_BTN_skis);
        menu_BTN_snowboards = findViewById(R.id.menu_BTN_snowboards);
        menu_BTN_ordersStatus = findViewById(R.id.menu_BTN_ordersStatus);
        menu_IMG_background = findViewById(R.id.menu_IMG_background);
        menu_BTN_signOut = findViewById(R.id.menu_BTN_signOut);
//        menu_BTN_prices.setOnClickListener(this);
    }

    private void openPricesActivity(Activity activity) {
//        Prices prices = Prices.getInstance();
//        prices.setPriceTable(priceTable);
 //       String pTableJson = new Gson().toJson(Prices.getInstance());
        Intent myIntent = new Intent(activity, Activity_Prices.class);
   //     myIntent.putExtra(Activity_Prices.EXTRA_KEY_PRICE, pTableJson);
        startActivity(myIntent);
    }

    private void openRentDatesActivity(Activity activity, String boardType) {
        String boardsForRentJson;
        if (boardType.equals("Snowboard")) {
            boardsForRentJson = new Gson().toJson(snowboardsForRent);
        } else {
            boardsForRentJson = new Gson().toJson(skisForRent);
        }

        Intent myIntent = new Intent(activity, Activity_RentDates.class);
        myIntent.putExtra(Activity_RentBoards.EXTRA_KEY_BOARD_TYPE, boardType);
        myIntent.putExtra(Activity_RentBoards.EXTRA_KEY_ALL_BOARDS, boardsForRentJson);
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



//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.menu_BTN_prices:
//                //things to do.
//                break;
//        }
//    }
} // Activity_MainMenu