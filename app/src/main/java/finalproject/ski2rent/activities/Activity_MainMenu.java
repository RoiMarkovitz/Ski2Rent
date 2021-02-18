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
import finalproject.ski2rent.callbacks.CallBack_PriceData;
import finalproject.ski2rent.objects.Board;
import finalproject.ski2rent.objects.BoardForRent;
import finalproject.ski2rent.objects.PriceRecord;
import finalproject.ski2rent.objects.Prices;
import finalproject.ski2rent.utils.FireBaseManager;

// TODO remember class related logic in view
// implements View.OnClickListener
public class Activity_MainMenu extends Activity_Base {

    private Button menu_BTN_prices;
    private Button menu_BTN_skis;
    private Button menu_BTN_snowboards;
    private Button menu_BTN_ordersStatus;
    private ImageView menu_IMG_background;
    private boolean isPricesFinished = false;
    private boolean isSnowboardsForRentFinished = false;
    private boolean isSkisForRentFinished = false;
    private ArrayList<PriceRecord> priceTable;
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
        //    validateUser();

        fireBaseManager.readAllPricesFromServer(new CallBack_AllPriceData() {
            @Override
            public void retrieveAllPriceRecord(ArrayList<PriceRecord> pTable) {
                priceTable = pTable;
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

//        fireBaseManager.readBoardForRentDataFromServer("B001", Board.eType.Snowboard, new CallBack_BoardForRent() {
//            @Override
//            public void retrieveBoardForRent(BoardForRent b) {
//                board = b;
//            }
//        });

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
                if (isSkisForRentFinished) {
                    openRentDatesActivity(Activity_MainMenu.this, "Ski");
                }
            }
        });

        menu_BTN_snowboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSnowboardsForRentFinished) {
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

    } // onCreate

//    public CallBack_PriceData callBack_priceData = new CallBack_PriceData() {
//        @Override
//        public void retrievePriceRecord(PriceRecord p) {
//
//            day = p.getDays();
//         //   menu_BTN_prices.setText("" + day);
//            pRecord.setKey(p.getKey());
//            pRecord.setDays(p.getDays());
//            pRecord.setBronzePrice(p.getBronzePrice());
//            pRecord.setSilverPrice(p.getSilverPrice());
//            pRecord.setGoldPrice(p.getGoldPrice());
//
//        }
//    };


//    private void validateUser() {
//        // these are singletons, can be called from anywhere no need context
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//
//        if (firebaseUser == null) {
//            Intent myIntent = new Intent(this, Activity_Login.class);
//            startActivity(myIntent);
//            finish();
//            return;
//        }
//
//        Log.d("pttt", "Uid = " + firebaseUser.getUid());
//        Log.d("pttt", "DisplayName = " + firebaseUser.getDisplayName());
//        Log.d("pttt", "Email = " + firebaseUser.getEmail());
//        Log.d("pttt", "PhoneNumber = " + firebaseUser.getPhoneNumber());
//        Log.d("pttt", "PhotoUrl = " + firebaseUser.getPhotoUrl());
//
////        firebaseAuth.signOut();
////        FirebaseAuth.getInstance().signOut();
//
//    }

//    private void updateDisplayName(String name) {
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//
//        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                .setDisplayName(name)
//                .build();
//        firebaseUser.updateProfile(profileUpdates);
//        firebaseAuth.updateCurrentUser(firebaseUser);
//    }

    private void findViews() {
        menu_BTN_prices = findViewById(R.id.menu_BTN_prices);
        menu_BTN_skis = findViewById(R.id.menu_BTN_skis);
        menu_BTN_snowboards = findViewById(R.id.menu_BTN_snowboards);
        menu_BTN_ordersStatus = findViewById(R.id.menu_BTN_ordersStatus);
        menu_IMG_background = findViewById(R.id.menu_IMG_background);
//        menu_BTN_prices.setOnClickListener(this);
    }

    private void openPricesActivity(Activity activity) {
        // TODO should probably no need to move object, as its singleton
        Prices prices = Prices.getInstance();
        prices.setPriceTable(priceTable);
        String pTableJson = new Gson().toJson(prices);
        Intent myIntent = new Intent(activity, Activity_Prices.class);
        myIntent.putExtra(Activity_Prices.EXTRA_KEY_PRICE, pTableJson);
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