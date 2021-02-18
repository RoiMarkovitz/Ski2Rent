package finalproject.ski2rent.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import finalproject.ski2rent.R;
import finalproject.ski2rent.adapters.Adapter_RentBoards;
import finalproject.ski2rent.objects.BoardForRent;
import finalproject.ski2rent.objects.MockSkis;
import finalproject.ski2rent.objects.MockSnowboards;
import finalproject.ski2rent.objects.Prices;
import finalproject.ski2rent.objects.RentedBoard;
import finalproject.ski2rent.objects.ShoppingCart;
import finalproject.ski2rent.utils.MyDateUtil;
import finalproject.ski2rent.utils.MySignals;

public class Activity_RentBoards extends Activity_Base {
    public static final String EXTRA_KEY_BOARD_TYPE = "EXTRA_KEY_BOARD_TYPE";
    public static final String EXTRA_KEY_START_DATE = "EXTRA_KEY_START_DATE";
    public static final String EXTRA_KEY_END_DATE = "EXTRA_KEY_END_DATE";
    public static final String EXTRA_KEY_ALL_BOARDS = "EXTRA_KEY_ALL_BOARDS";


    private ShoppingCart shoppingCart = new ShoppingCart();
    private Gson gson;

    private RecyclerView rentBoards_LST_boards;
    private TextView rentBoards_LBL_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("RentBoardsLifeCycle", "onCreate: Activity_RentBoards");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__rent_boards);

        ArrayList<BoardForRent> boardsForRent = new ArrayList<>();
        gson = new Gson();

        findViews();

        String boardsForRentJson = getIntent().getStringExtra(EXTRA_KEY_ALL_BOARDS);
        Type listType = new TypeToken<List<BoardForRent>>(){}.getType();
        boardsForRent = new Gson().fromJson(boardsForRentJson, listType);

        String boardType = getIntent().getStringExtra(EXTRA_KEY_BOARD_TYPE);
        long startRentDate = getIntent().getLongExtra(EXTRA_KEY_START_DATE, -1);
        long endRentDate = getIntent().getLongExtra(EXTRA_KEY_END_DATE, -1);

        int rentDays = MyDateUtil.calculateDaysDifferenceWithLastDay(startRentDate, endRentDate);
        int daysBeforePickup = getDaysBeforePickup(startRentDate);

        setLayoutTitle(boardType);

        rentBoards_LST_boards.setLayoutManager(new LinearLayoutManager(this));
        Adapter_RentBoards adapter_rent_boards = new Adapter_RentBoards(this, boardsForRent, rentDays, daysBeforePickup);

        adapter_rent_boards.setClickListener(new Adapter_RentBoards.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {}

            @Override
            public void onAddToCartClick(int position, RentedBoard boardToCart) {
                // TODO probably just need to add to firebase
                shoppingCart.addToCart(boardToCart);
                MySignals.getInstance().toast("Item added to cart!");
                MySignals.getInstance().vibrate();
            }
        });

        rentBoards_LST_boards.setAdapter(adapter_rent_boards);

    } // onCreate

    private void setLayoutTitle(String boardType) {
      //  ArrayList<BoardForRent> boards = new ArrayList<>();
        if (boardType.equals("Snowboard")) {
            rentBoards_LBL_title.setText("Snowboards Rent List");
        //    boards = MockSnowboards.generateSnowboards();
        } else {
            rentBoards_LBL_title.setText("Skis Rent List");
        //    boards = MockSkis.generateSkis();
        }
     //   return boards;
    }

    private int getDaysBeforePickup(long startRentDate) {
        int daysBeforePickup;
        if (MyDateUtil.isToday(startRentDate)) {
            daysBeforePickup = MyDateUtil.calculateDaysDifferenceWithoutLastDay(System.currentTimeMillis(), startRentDate);
        } else {
            daysBeforePickup = MyDateUtil.calculateDaysDifferenceWithLastDay(System.currentTimeMillis(), startRentDate);
        }
        return daysBeforePickup;
    }

    private void findViews() {
        rentBoards_LBL_title = findViewById(R.id.rentBoards_LBL_title);
        rentBoards_LST_boards = findViewById(R.id.rentBoards_LST_boards);
    }

    @Override
    protected void onStart() {
        Log.d("RentBoardsLifeCycle", "onStart: Activity_RentBoards");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("RentBoardsLifeCycle", "onResume: Activity_RentBoards");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("RentBoardsLifeCycle", "onPause: Activity_RentBoards");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("RentBoardsLifeCycle", "onStop: Activity_RentBoards");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("RentBoardsLifeCycle", "onDestroy: Activity_RentBoards");
        super.onDestroy();
    }

} // Activity_RentBoards