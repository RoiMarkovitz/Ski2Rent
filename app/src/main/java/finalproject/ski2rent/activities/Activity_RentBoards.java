package finalproject.ski2rent.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import finalproject.ski2rent.R;
import finalproject.ski2rent.adapters.Adapter_Boards;
import finalproject.ski2rent.objects.BoardForRent;
import finalproject.ski2rent.objects.MockSkis;
import finalproject.ski2rent.objects.MockSnowboards;
import finalproject.ski2rent.objects.RentedBoard;

public class Activity_RentBoards extends Activity_Base {
    public static final String EXTRA_KEY_BOARD_TYPE = "EXTRA_KEY_BOARD_TYPE";
    public static final String EXTRA_KEY_START_DATE = "EXTRA_KEY_START_DATE";
    public static final String EXTRA_KEY_END_DATE = "EXTRA_KEY_END_DATE";

    private ArrayList<RentedBoard> boardsInCart = new ArrayList<>();

    private RecyclerView rentBoards_LST_boards;
    private TextView rentBoards_LBL_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("RentBoardsLifeCycle", "onCreate: Activity_RentBoards");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__rent_boards);

        findViews();

        String boardType = getIntent().getStringExtra(EXTRA_KEY_BOARD_TYPE);
        long startRentDate = getIntent().getLongExtra(EXTRA_KEY_START_DATE, -1);
        long endRentDate = getIntent().getLongExtra(EXTRA_KEY_END_DATE, -1);

        long msDiffRentDays = endRentDate - startRentDate;
        int rentDays = (int)TimeUnit.MILLISECONDS.toDays(msDiffRentDays) + 1;

        long msDiffDaysBeforePickup = startRentDate - System.currentTimeMillis();
        int daysBeforePickup = (int)TimeUnit.MILLISECONDS.toDays(msDiffDaysBeforePickup) + 1;


        ArrayList<BoardForRent> boards = new ArrayList<>();
        if (boardType.equals("Snowboard")) {
            rentBoards_LBL_title.setText("Snowboards Rent List");
            boards = MockSnowboards.generateSnowboards();
        } else {
            rentBoards_LBL_title.setText("Skis Rent List");
            boards = MockSkis.generateSkis();
        }

        rentBoards_LST_boards.setLayoutManager(new LinearLayoutManager(this));
        Adapter_Boards adapter_boards = new Adapter_Boards(this, boards, rentDays, daysBeforePickup);

        adapter_boards.setClickListener(new Adapter_Boards.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onAddToCartClick(int position, RentedBoard boardToCart) {
                for (int i = 0; i < boardsInCart.size(); i++) {
                    if (boardsInCart.get(i).isSameKey(boardToCart.getKey())) {
                        boardsInCart.get(i).setQuantity(boardsInCart.get(i).getQuantity() + 1);
                        Toast.makeText(Activity_RentBoards.this, "Quantity+ " + boardsInCart.get(i).getQuantity(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                boardToCart.setQuantity(1);

                boardsInCart.add(boardToCart);
                Toast.makeText(Activity_RentBoards.this, "numOFDifBoards " + boardsInCart.size(), Toast.LENGTH_SHORT).show();

            }
        });

        rentBoards_LST_boards.setAdapter(adapter_boards);

    } // onCreate

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