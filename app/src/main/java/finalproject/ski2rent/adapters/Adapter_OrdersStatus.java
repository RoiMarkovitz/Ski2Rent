package finalproject.ski2rent.adapters;

import android.content.Context;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import finalproject.ski2rent.R;
import finalproject.ski2rent.objects.Order;
import finalproject.ski2rent.objects.RentedBoard;

public class Adapter_OrdersStatus extends RecyclerView.Adapter<Adapter_OrdersStatus.MyViewHolder> {

    private ArrayList<Order> mData;
    private Context context;
    private LayoutInflater mInflater;
    private Adapter_OrdersStatus.ItemClickListener mClickListener;
    private boolean isCancelClickable;
    private long mLastClickTime = 0;

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
     //   Log.d("pttt", "Binding " + position);
        Order order = mData.get(position);
        holder.order_LBL_date.setText(DateFormat.format("dd.MM.yy", order.getOrderDate()).toString());
        holder.order_LBL_id.setText("Order ID " + order.getId());
        holder.order_LBL_pickupDate.setText("Pickup date: " + DateFormat.format("dd.MM.yy", order.getPickupDate()).toString());
        holder.order_LBL_returnDate.setText("Return date: " + DateFormat.format("dd.MM.yy", order.getReturnDate()).toString());
        holder.order_LBL_price.setText("Total price: € "+ new DecimalFormat("##.##").format(order.calculateTotalPrice()));
        order.updateStatus();
        setOrderStatusAndImage(order, holder);

        ArrayList<RentedBoard> boards;
        boards = order.getBoards();

        holder.order_LST_details.setLayoutManager(new LinearLayoutManager(context));
        Adapter_ItemDetails adapter = new Adapter_ItemDetails(context, boards);
        holder.order_LST_details.setAdapter(adapter);

    }

    private void setOrderStatusAndImage(Order order, MyViewHolder holder) {
        if (order.getStatus() != Order.eStatus.Returned && order.getStatus() != Order.eStatus.Cancelled) {
            if (order.isCancelAllowed()) {
                holder.order_IMG_action.setImageResource(R.drawable.ic_cancel);
                isCancelClickable = true;
            } else if (order.isOrderReturnable()) {
                holder.order_IMG_action.setImageResource(R.drawable.ic_return);
                isCancelClickable = false;
            } else {
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
        void onActionItemClick(int position, Order o);
    }

    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView order_LBL_date;
        TextView order_LBL_status;
        TextView order_LBL_id;
        TextView order_LBL_pickupDate;
        TextView order_LBL_returnDate;
        TextView order_LBL_price;
        RecyclerView order_LST_details;
        ImageView order_IMG_action;

        MyViewHolder(View itemView) {
            super(itemView);

            order_LBL_date = itemView.findViewById(R.id.order_LBL_date);
            order_LBL_status = itemView.findViewById(R.id.order_LBL_status);
            order_LBL_id = itemView.findViewById(R.id.order_LBL_id);
            order_LBL_pickupDate = itemView.findViewById(R.id.order_LBL_pickupDate);
            order_LBL_returnDate = itemView.findViewById(R.id.order_LBL_returnDate);
            order_LST_details = itemView.findViewById(R.id.order_LST_details);
            order_LBL_price = itemView.findViewById(R.id.order_LBL_price);
            order_IMG_action = itemView.findViewById(R.id.order_IMG_action);

            order_IMG_action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) {
                        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                            return;
                        }
                        mLastClickTime = SystemClock.elapsedRealtime();

                        Order order = getItem(getAdapterPosition());

                        if (isCancelClickable) {
                            order.setStatus(Order.eStatus.Cancelled);
                            // only possible to cancel order up until 24 hours before pickup date
                        } else {
                            // only possible to return order after pickup date
                            order.setStatus(Order.eStatus.Returned);
                        }


                        notifyDataSetChanged();

                        mClickListener.onActionItemClick(getAdapterPosition(), order);
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

