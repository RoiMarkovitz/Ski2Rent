package finalproject.ski2rent.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import finalproject.ski2rent.R;
import finalproject.ski2rent.adapters.Adapter_OrdersStatus;
import finalproject.ski2rent.adapters.Adapter_ShoppingCart;
import finalproject.ski2rent.objects.MockShoppingCart;
import finalproject.ski2rent.objects.Order;
import finalproject.ski2rent.objects.RentedBoard;
import finalproject.ski2rent.objects.ShoppingCart;

public class Activity_OrdersStatus extends Activity_Base {

    private ArrayList<Order> orders = new ArrayList<>();

    private RecyclerView order_LST_records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("OrdersStatusLifeCycle", "onCreate: Activity_OrdersStatus");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__orders_status);

        findViews();

        Order order1 = new Order(MockShoppingCart.generateSnowboards());
        Order order2 = new Order(MockShoppingCart.generateSnowboards());

        orders.add(order1);
        orders.add(order2);

        order_LST_records.setLayoutManager(new LinearLayoutManager(this));
        Adapter_OrdersStatus adapter_orders = new Adapter_OrdersStatus(this, orders);

        adapter_orders.setClickListener(new Adapter_OrdersStatus.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }

            @Override
            public void onActionItemClick(int position) {
                //      boardsInCart.remove(position);
                // TODO update in fire base shopping cart

            }
        });

        order_LST_records.setAdapter(adapter_orders);

    }

    private void findViews() {
        order_LST_records = findViewById(R.id.order_LST_records);
    }

    @Override
    protected void onStart() {
        Log.d("OrdersStatusLifeCycle", "onStart: Activity_OrdersStatus");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("OrdersStatusLifeCycle", "onResume: Activity_OrdersStatus");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("OrdersStatusLifeCycle", "onPause: Activity_OrdersStatus");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("OrdersStatusLifeCycle", "onStop: Activity_OrdersStatus");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("OrdersStatusLifeCycle", "onDestroy: Activity_OrdersStatus");
        super.onDestroy();
    }

} // Activity_OrdersStatus