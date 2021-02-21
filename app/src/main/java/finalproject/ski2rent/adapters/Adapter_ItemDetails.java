package finalproject.ski2rent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import finalproject.ski2rent.R;

import finalproject.ski2rent.objects.RentedBoard;


public class Adapter_ItemDetails extends RecyclerView.Adapter<Adapter_ItemDetails.MyViewHolder> {
    private ArrayList<RentedBoard> mData;
    private Context context;
    private LayoutInflater mInflater;
    private Adapter_RentBoards.ItemClickListener mClickListener;

    // data is passed into the constructor
    public Adapter_ItemDetails(Context context, ArrayList<RentedBoard> data) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;

    }

    // inflates the row layout from xml when needed
    @Override
    public Adapter_ItemDetails.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_details, parent, false);
        return new Adapter_ItemDetails.MyViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(Adapter_ItemDetails.MyViewHolder holder, int position) {
        //   Log.d("pttt", "Binding " + position);
        RentedBoard board = mData.get(position);
        holder.item_LBL_details.setText(board.description());
        holder.item_LBL_price.setText("" + board.getPrice());
        holder.item_LBL_quantity.setText("" + board.getQuantity());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // convenience method for getting data at click position
    RentedBoard getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(Adapter_RentBoards.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onAddToCartClick(int position, RentedBoard boardToCart);
    }

    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item_LBL_details;
        TextView item_LBL_quantity;
        TextView item_LBL_price;

        MyViewHolder(View itemView) {
            super(itemView);
            item_LBL_details = itemView.findViewById(R.id.item_LBL_details);
            item_LBL_quantity = itemView.findViewById(R.id.item_LBL_quantity);
            item_LBL_price = itemView.findViewById(R.id.item_LBL_price);

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

