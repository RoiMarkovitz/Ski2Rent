package finalproject.ski2rent.adapters;

import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import finalproject.ski2rent.R;
import finalproject.ski2rent.objects.RentedBoard;

public class Adapter_ShoppingCart extends RecyclerView.Adapter<Adapter_ShoppingCart.MyViewHolder> {

    private ArrayList<RentedBoard> mData;
    private Context context;
    private LayoutInflater mInflater;
    private Adapter_ShoppingCart.ItemClickListener mClickListener;
    private boolean isShoppingCartUpdated = true;
    private long mLastClickTime = 0;

    // data is passed into the constructor
    public Adapter_ShoppingCart(Context context, ArrayList<RentedBoard> data) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public Adapter_ShoppingCart.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_shopping_cart, parent, false);
        return new Adapter_ShoppingCart.MyViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(Adapter_ShoppingCart.MyViewHolder holder, int position) {
     //   Log.d("pttt", "Binding " + position);
        RentedBoard board = mData.get(position);
        holder.shoppingCart_LBL_details.setText(board.description());
        holder.shoppingCart_LBL_quantity.setText(""+board.getQuantity());
        holder.shoppingCart_LBL_price.setText("€ " + new DecimalFormat("##.##").format(board.getPrice()));

    }

    public ArrayList<RentedBoard> getRentedBoards() {
        return mData;
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
    public void setClickListener(Adapter_ShoppingCart.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public void shoppingCartIsUpdated() {
        isShoppingCartUpdated = true;
    }


    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onCancelItemClick(int position);
    }

    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView shoppingCart_LBL_details;
        TextView shoppingCart_LBL_quantity;
        TextView shoppingCart_LBL_price;
        ImageView shoppingCart_IMG_cancel;

        MyViewHolder(View itemView) {
            super(itemView);

            shoppingCart_LBL_details = itemView.findViewById(R.id.shoppingCart_LBL_details);
            shoppingCart_LBL_quantity = itemView.findViewById(R.id.shoppingCart_LBL_quantity);
            shoppingCart_LBL_price = itemView.findViewById(R.id.shoppingCart_LBL_price);
            shoppingCart_IMG_cancel = itemView.findViewById(R.id.shoppingCart_IMG_cancel);

            shoppingCart_IMG_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null && isShoppingCartUpdated) {
                        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                            return;
                        }
                        mLastClickTime = SystemClock.elapsedRealtime();

                        isShoppingCartUpdated = false;

                        int position = getAdapterPosition();
                        mData.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(),mData.size());

                        mClickListener.onCancelItemClick(position);
                    }
                }
            });

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




