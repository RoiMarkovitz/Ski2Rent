package finalproject.ski2rent.adapters;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import finalproject.ski2rent.R;
import finalproject.ski2rent.objects.PriceRecord;

public class Adapter_Prices extends RecyclerView.Adapter<Adapter_Prices.MyViewHolder> {

    private ArrayList<PriceRecord> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public Adapter_Prices(Context context, ArrayList<PriceRecord> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_prices, parent, false);
        return new MyViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("pttt", "Binding " + position);
        PriceRecord price = mData.get(position);

        if (price.getKey().equals("extraDays")) {
            holder.price_LBL_days.setText("extra days");
        } else {
            holder.price_LBL_days.setText(price.getDays()+"");
        }
        holder.price_LBL_bronze.setText("€ " + price.getBronzePrice());
        holder.price_LBL_silver.setText("€ " + price.getSilverPrice());
        holder.price_LBL_gold.setText("€ " + price.getGoldPrice());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // convenience method for getting data at click position
    public PriceRecord getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onReportClick(int position);
    }

    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView price_LBL_days;
        TextView price_LBL_bronze;
        TextView price_LBL_silver;
        TextView price_LBL_gold;

        MyViewHolder(View itemView) {
            super(itemView);
            price_LBL_days = itemView.findViewById(R.id.price_LBL_days);
            price_LBL_bronze = itemView.findViewById(R.id.price_LBL_bronze);
            price_LBL_silver = itemView.findViewById(R.id.price_LBL_silver);
            price_LBL_gold = itemView.findViewById(R.id.price_LBL_gold);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mClickListener != null) {
//                        mClickListener.onItemClick(v, getAdapterPosition());
//                    }
//                }
//            });
        }
    }
}
