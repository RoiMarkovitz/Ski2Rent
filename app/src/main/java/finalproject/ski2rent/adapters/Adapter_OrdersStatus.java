package finalproject.ski2rent.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import finalproject.ski2rent.R;
import finalproject.ski2rent.objects.Order;

public class Adapter_OrdersStatus extends RecyclerView.Adapter<Adapter_OrdersStatus.MyViewHolder> {

    private ArrayList<Order> mData;
    private Context context;
    private LayoutInflater mInflater;
    private Adapter_OrdersStatus.ItemClickListener mClickListener;
    private boolean isCancelClickable;

    // data is passed into the constructor
    public Adapter_OrdersStatus(Context context, ArrayList<Order> data) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public Adapter_OrdersStatus.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_orders, parent, false);
        return new Adapter_OrdersStatus.MyViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(Adapter_OrdersStatus.MyViewHolder holder, int position) {
        Log.d("pttt", "Binding " + position);
        Order order = mData.get(position);
        holder.order_LBL_details.setText(order.description());
        holder.order_LBL_price.setText("€ "+order.calculateTotalPrice());
        order.updateStatus();
        setOrderStatusAndImage(order, holder);

    }

    private void setOrderStatusAndImage(Order order, MyViewHolder holder) {
        if (order.getStatus() != Order.eStatus.Returned && order.getStatus() != Order.eStatus.Cancelled) {
            if (order.isCancelAllowed()) {
                // only possible to cancel order up until 24 hours before pickup date
                holder.order_IMG_action.setImageResource(R.drawable.ic_cancel);
                isCancelClickable = true;
            } else if (order.isOrderReturnable()) {
                // only possible to return order after pickup date
                holder.order_IMG_action.setImageResource(R.drawable.ic_return);
                isCancelClickable = false;
            } else {
                // 24 hours before order pickup date you cant cancel or pickup the order
                holder.order_IMG_action.setVisibility(View.INVISIBLE);
            }
        } else {
            holder.order_IMG_action.setVisibility(View.INVISIBLE);
        }
        holder.order_LBL_status.setText("" +order.getStatus().name());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // convenience method for getting data at click position
    Order getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(Adapter_OrdersStatus.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onActionItemClick(int position);
    }

    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView order_LBL_details;
        TextView order_LBL_price;
        TextView order_LBL_status;
        ImageView order_IMG_action;

        MyViewHolder(View itemView) {
            super(itemView);

            order_LBL_details = itemView.findViewById(R.id.order_LBL_details);
            order_LBL_price = itemView.findViewById(R.id.order_LBL_price);
            order_LBL_status = itemView.findViewById(R.id.order_LBL_status);
            order_IMG_action = itemView.findViewById(R.id.order_IMG_action);

            order_IMG_action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) {
                        Order order = getItem(getAdapterPosition());

                        if (isCancelClickable) {
                            order.setStatus(Order.eStatus.Cancelled);
                            // only possible to cancel order up until 24 hours before pickup date
                        } else if (!isCancelClickable) {
                            // only possible to return order after pickup date
                            order.setStatus(Order.eStatus.Returned);
                        } else {
                            // 24 hours before order pickup date you cant cancel or pickup the order
                        }

                        notifyDataSetChanged();

                        mClickListener.onActionItemClick(getAdapterPosition());
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
