package finalproject.ski2rent.activities;


import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

import finalproject.ski2rent.R;

import finalproject.ski2rent.adapters.Adapter_Prices;
import finalproject.ski2rent.callbacks.CallBack_AllPriceData;
import finalproject.ski2rent.callbacks.CallBack_PriceData;
import finalproject.ski2rent.objects.MockPrices;
import finalproject.ski2rent.objects.PriceRecord;
import finalproject.ski2rent.objects.Prices;
import finalproject.ski2rent.utils.FireBaseManager;

public class Activity_Prices extends Activity_Base {
 //   public static final String EXTRA_KEY_PRICE = "EXTRA_KEY_PRICE";

    private RecyclerView prices_LST_records;
 //   private Prices prices;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("PricesLifeCycle", "onCreate: Activity_Prices");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__prices);

        gson = new Gson();

//        String pTableJson = getIntent().getStringExtra(EXTRA_KEY_PRICE);
//        prices = new Gson().fromJson(pTableJson, Prices.class);

        findViews();

        ArrayList<PriceRecord> priceTable = Prices.getInstance().getPriceTable();
  //      ArrayList<PriceRecord> priceTable = prices.getPriceTable();

        prices_LST_records.setLayoutManager(new LinearLayoutManager(this));
        Adapter_Prices adapter_prices = new Adapter_Prices(this, priceTable);

        prices_LST_records.setAdapter(adapter_prices);

    } // onCreate

    private void findViews() {
        prices_LST_records = findViewById(R.id.prices_LST_records);
    }


    @Override
    protected void onStart() {
        Log.d("PricesLifeCycle", "onStart: Activity_Prices");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("PricesLifeCycle", "onResume: Activity_Prices");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("PricesLifeCycle", "onPause: Activity_Prices");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("PricesLifeCycle", "onStop: Activity_Prices");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("PricesLifeCycle", "onDestroy: Activity_Prices");
        super.onDestroy();
    }

} // Activity_Prices