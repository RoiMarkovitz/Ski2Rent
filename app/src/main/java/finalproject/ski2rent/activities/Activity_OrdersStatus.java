package finalproject.ski2rent.activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import finalproject.ski2rent.R;
import finalproject.ski2rent.adapters.Adapter_OrdersStatus;
import finalproject.ski2rent.callbacks.CallBack_UpdateOrderData;
import finalproject.ski2rent.objects.Order;
import finalproject.ski2rent.utils.MySignals;

public class Activity_OrdersStatus extends Activity_Base {
    public static final String EXTRA_KEY_ALL_ORDERS = "EXTRA_KEY_ALL_ORDERS";

    private ArrayList<Order> orders = new ArrayList<>();

    private RecyclerView order_LST_records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("OrdersStatusLifeCycle", "onCreate: Activity_OrdersStatus");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__orders_status);

        String ordersJson = getIntent().getStringExtra(EXTRA_KEY_ALL_ORDERS);
        Type listType = new TypeToken<List<Order>>(){}.getType();
        orders = new Gson().fromJson(ordersJson, listType);

        findViews();

        order_LST_records.setLayoutManager(new LinearLayoutManager(this));
        Adapter_OrdersStatus adapter_orders = new Adapter_OrdersStatus(this, orders);

        adapter_orders.setClickListener(new Adapter_OrdersStatus.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) { }


            @Override
            public void onActionItemClick(int position, Order o) {
                fireBaseManager.updateOrderStatusToServer(o);
            }
        });

        order_LST_records.setAdapter(adapter_orders);

    }

    private void findViews() {
        order_LST_records = findViewById(R.id.order_LST_records);
    }


} // Activity_OrdersStatus