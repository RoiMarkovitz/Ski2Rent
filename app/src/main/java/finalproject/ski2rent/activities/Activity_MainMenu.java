package finalproject.ski2rent.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

import finalproject.ski2rent.R;
import finalproject.ski2rent.callbacks.CallBack_AllBoardsForRent;
import finalproject.ski2rent.callbacks.CallBack_AllPriceData;
import finalproject.ski2rent.callbacks.CallBack_GetAllOrdersData;
import finalproject.ski2rent.callbacks.CallBack_GetLatestOrderIdNumber;
import finalproject.ski2rent.callbacks.CallBack_GetShoppingCartData;
import finalproject.ski2rent.objects.Board;
import finalproject.ski2rent.objects.BoardForRent;
import finalproject.ski2rent.objects.Order;
import finalproject.ski2rent.objects.PriceRecord;
import finalproject.ski2rent.objects.Prices;
import finalproject.ski2rent.objects.ShoppingCart;

public class Activity_MainMenu extends Activity_Base  {

    private Button menu_BTN_prices;
    private Button menu_BTN_skis;
    private Button menu_BTN_snowboards;
    private Button menu_BTN_ordersStatus;
    private ImageView menu_IMG_background;
    private boolean isPricesFinished = false;
    private boolean isBoardsForRentFinished = false;
    private boolean isOrdersFinished = false;
    private boolean isLatestOrderIdReturned = false;

    private ArrayList<BoardForRent> snowboardsForRent;
    private ArrayList<BoardForRent> skisForRent;
    private ArrayList<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("mainMenuLifeCycle", "onCreate: Activity_MainMenu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main_menu);
        
        initDataFromFireBase();
        findViews();
        initViews();

        Glide
                .with(this)
                .load(R.drawable.img_menu_background)
                .into(menu_IMG_background);

    } // onCreate

    private void initDataFromFireBase() {
        readAllPricesFromServer();
        readAllBoardForRentDataFromServer(Board.eType.Snowboard);
        readAllBoardForRentDataFromServer(Board.eType.Ski);
    }

    private void readAllPricesFromServer() {
        fireBaseManager.readAllPricesFromServer(new CallBack_AllPriceData() {
            @Override
            public void retrieveAllPriceRecord(ArrayList<PriceRecord> pTable) {
                Prices.getInstance().setPriceTable(pTable);
                isPricesFinished = true;
            }
        });
    }

    private void readAllBoardForRentDataFromServer(Board.eType type) {
        fireBaseManager.readAllBoardForRentDataFromServer(type, new CallBack_AllBoardsForRent() {
            @Override
            public void retrieveAllBoardsForRent(ArrayList<BoardForRent> boards) {
                if (type == Board.eType.Snowboard) {
                    snowboardsForRent = boards;
                } else {
                    skisForRent = boards;
                }
                isBoardsForRentFinished = true;
            }
        });
    }

    private void readAllOrdersFromServer() {
        if (fireBaseManager.isCurrentUserLoggedIn()) {
            fireBaseManager.readAllOrdersFromServer(new CallBack_GetAllOrdersData() {
                @Override
                public void retrieveAllOrders(ArrayList<Order> o) {
                    orders = o;
                    isOrdersFinished = true;
                }
            });
        }
    }

    private void readShoppingCartDataFromServer() {
        if (fireBaseManager.isCurrentUserLoggedIn()) {
            String uid = fireBaseManager.getUidCurrentUser();
            fireBaseManager.readShoppingCartDataFromServer(uid, new CallBack_GetShoppingCartData() {
                @Override
                public void retrieveShoppingCart(ShoppingCart sc) {
                    fireBaseManager.setShoppingCart(sc);
                    fireBaseManager.setShoppingCartReturned(true);
                }
            });
        }
    }

    private void readOrderLatestIdNumberFromServer() {
        fireBaseManager.readOrderLatestIdNumberFromServer(new CallBack_GetLatestOrderIdNumber() {
            @Override
            public void retrieveLatestOrderIdNumber(int latestOrderId) {
                Order.idGenerator = latestOrderId;
                isLatestOrderIdReturned = true;
            }
        });
    }

    private void initViews() {
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
                if (isBoardsForRentFinished && isLatestOrderIdReturned) {
                    openRentDatesActivity(Activity_MainMenu.this, Board.eType.Ski);
                }
            }
        });

        menu_BTN_snowboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoardsForRentFinished && isLatestOrderIdReturned) {
                    openRentDatesActivity(Activity_MainMenu.this, Board.eType.Snowboard);
                }
            }
        });

        menu_BTN_ordersStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fireBaseManager.isCurrentUserLoggedIn()) {
                    if (isOrdersFinished) {
                        openOrdersStatusActivity(Activity_MainMenu.this);
                    }
                }
                else {
                    openLoginActivity(Activity_MainMenu.this);
                }
            }
        });

    }

    private void openPricesActivity(Activity activity) {
        Intent myIntent = new Intent(activity, Activity_Prices.class);
        startActivity(myIntent);
    }

    private void openRentDatesActivity(Activity activity, Board.eType type) {
        String boardsForRentJson;
        if (type == Board.eType.Snowboard) {
            boardsForRentJson = new Gson().toJson(snowboardsForRent);
        } else {
            boardsForRentJson = new Gson().toJson(skisForRent);
        }

        Intent myIntent = new Intent(activity, Activity_RentDates.class);
        myIntent.putExtra(Activity_RentBoards.EXTRA_KEY_BOARD_TYPE, type.name());
        myIntent.putExtra(Activity_RentBoards.EXTRA_KEY_ALL_BOARDS, boardsForRentJson);
        startActivity(myIntent);
    }

    private void openOrdersStatusActivity(Activity activity) {
        String ordersJson = new Gson().toJson(orders);

        Intent myIntent = new Intent(activity, Activity_OrdersStatus.class);
        myIntent.putExtra(Activity_OrdersStatus.EXTRA_KEY_ALL_ORDERS, ordersJson);
        startActivity(myIntent);
    }

    private void findViews() {
        menu_BTN_prices = findViewById(R.id.menu_BTN_prices);
        menu_BTN_skis = findViewById(R.id.menu_BTN_skis);
        menu_BTN_snowboards = findViewById(R.id.menu_BTN_snowboards);
        menu_BTN_ordersStatus = findViewById(R.id.menu_BTN_ordersStatus);
        menu_IMG_background = findViewById(R.id.menu_IMG_background);
    }

    @Override
    protected void onStart() {
        Log.d("mainMenuLifeCycle", "onStart: Activity_MainMenu");

        super.onPrepareOptionsMenu(mymMenu);

        readAllOrdersFromServer();
        readOrderLatestIdNumberFromServer();
        readShoppingCartDataFromServer();
        
        super.onStart();
    }


} // Activity_MainMenu