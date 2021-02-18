package finalproject.ski2rent.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import finalproject.ski2rent.R;
import finalproject.ski2rent.adapters.Adapter_ShoppingCart;
import finalproject.ski2rent.objects.MockShoppingCart;
import finalproject.ski2rent.objects.RentedBoard;
import finalproject.ski2rent.objects.ShoppingCart;
import finalproject.ski2rent.utils.MySignals;

public class Activity_ShoppingCart extends Activity_Base {

    private ShoppingCart shoppingCart = new ShoppingCart();
    private ArrayList<RentedBoard> boardsInCart = new ArrayList<>();

    private RecyclerView shoppingCart_LST_records;
    private TextView shoppingCart_LBL_totalPrice;
    private MaterialButton shoppingCart_BTN_checkOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ShoppingCartLifeCycle", "onCreate: Activity_ShoppingCart");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__shopping_cart);

        findViews();

        boardsInCart = MockShoppingCart.generateSnowboards();
        shoppingCart.setBoardsInCart(boardsInCart);

        setTextTotalPrice();

        shoppingCart_LST_records.setLayoutManager(new LinearLayoutManager(this));
        Adapter_ShoppingCart adapter_boards = new Adapter_ShoppingCart(this, boardsInCart);

        adapter_boards.setClickListener(new Adapter_ShoppingCart.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onCancelItemClick(int position) {
                // TODO update in fire base shopping cart with the removal
                setTextTotalPrice();
            }
        });

        shoppingCart_LST_records.setAdapter(adapter_boards);

        shoppingCart_BTN_checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySignals.getInstance().toast("Thanks for buying!");
                // TODO update in fire base shopping cart
                finish();
            }
        });

    }

    private void setTextTotalPrice() {
        shoppingCart_LBL_totalPrice.setText("Total price: € " + shoppingCart.calculateTotalPrice());
    }

    private void findViews() {
        shoppingCart_LST_records = findViewById(R.id.shoppingCart_LST_records);
        shoppingCart_LBL_totalPrice = findViewById(R.id.shoppingCart_LBL_totalPrice);
        shoppingCart_BTN_checkOut = findViewById(R.id.shoppingCart_BTN_checkOut);
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