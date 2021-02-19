package finalproject.ski2rent.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import finalproject.ski2rent.R;

import finalproject.ski2rent.objects.BoardForRent;

import finalproject.ski2rent.objects.Prices;
import finalproject.ski2rent.objects.RentedBoard;
import finalproject.ski2rent.utils.FireBaseManager;

public class Adapter_RentBoards extends RecyclerView.Adapter<Adapter_RentBoards.MyViewHolder>  {

    private ArrayList<BoardForRent> mData;
    private Context context;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Prices prices;
    private int days;
    private int daysBeforePickup;
    private double discountPercentage;

    // data is passed into the constructor
    public Adapter_RentBoards(Context context, ArrayList<BoardForRent> data, int days, int daysBeforePickup) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;
        this.days = days;
        prices = Prices.getInstance();
        this.daysBeforePickup = daysBeforePickup;
        this.discountPercentage = prices.calculateDiscount(daysBeforePickup);

    }

    // inflates the row layout from xml when needed
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_boards, parent, false);
        return new MyViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("pttt", "Binding " + position);
        BoardForRent board = mData.get(position);
        holder.boards_LBL_name.setText(board.getName());
        holder.boards_LBL_brand.setText(board.getBrand());
        holder.boards_LBL_camberProfile.setText(board.getCamberProfile().name());
        holder.boards_LBL_discount.setText((discountPercentage)+"% discount");
        ArrayList<Integer> lengths = board.getLengths();
        ArrayAdapter<Integer> length_adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, lengths);
        holder.boards_SPN_lengths.setAdapter(length_adapter);

     //   String len = holder.boards_SPN_lengths.getSelectedItem().toString();
     //   Toast.makeText(context, "len " + len, Toast.LENGTH_SHORT).show();

        double price = prices.calculatePrice(days, board, daysBeforePickup);
        holder.boards_LBL_price.setText("€ " + price);
        board.setPrice(price);

        setNameBackGroundColor(board, holder);

        int resourceId = context.getResources().getIdentifier(board.getImagePath(), "drawable", context.getPackageName());
        Glide
                .with(mInflater.getContext())
                .load(resourceId)
   //             .centerCrop()
                .into(holder.boards_IMG_image);
    }

    private void setNameBackGroundColor(BoardForRent board, MyViewHolder holder) {
        if (board.getLevel().name().equals("Bronze")) {
            holder.boards_LBL_name.setBackgroundColor(context.getResources().getColor(R.color.bronze));
        } else if (board.getLevel().name().equals("Silver")) {
            holder.boards_LBL_name.setBackgroundColor(context.getResources().getColor(R.color.silver));
        } else {
            holder.boards_LBL_name.setBackgroundColor(context.getResources().getColor(R.color.gold));
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // convenience method for getting data at click position
    BoardForRent getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onAddToCartClick(int position, RentedBoard boardToCart);
    }

    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView boards_LBL_name;
        TextView boards_LBL_brand;
        TextView boards_LBL_camberProfile;
        TextView boards_LBL_discount;
        TextView boards_LBL_price;
        Spinner boards_SPN_lengths;
        ImageView boards_IMG_image;
        MaterialButton boards_BTN_addToCart;

        MyViewHolder(View itemView) {
            super(itemView);
            boards_LBL_name = itemView.findViewById(R.id.boards_LBL_name);
            boards_LBL_brand = itemView.findViewById(R.id.boards_LBL_brand);
            boards_LBL_camberProfile = itemView.findViewById(R.id.boards_LBL_camberProfile);
            boards_LBL_discount = itemView.findViewById(R.id.boards_LBL_discount);
            boards_LBL_price = itemView.findViewById(R.id.boards_LBL_price);
            boards_SPN_lengths = itemView.findViewById(R.id.boards_SPN_lengths);
            boards_IMG_image = itemView.findViewById(R.id.boards_IMG_image);
            boards_BTN_addToCart = itemView.findViewById(R.id.boards_BTN_addToCart);


            boards_BTN_addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) {
                        BoardForRent board = getItem(getAdapterPosition());
                        RentedBoard boardToCart = new RentedBoard();
                        boardToCart.setKey(board.getKey());
                        boardToCart.setName(board.getName());
                        boardToCart.setBrand(board.getBrand());
                        boardToCart.setLevel(board.getLevel());
                        boardToCart.setType(board.getType());
                        boardToCart.setCamberProfile(board.getCamberProfile());
                        boardToCart.setImagePath(board.getImagePath());
                        boardToCart.setPrice(board.getPrice());

//                        boardToCart.setLength()
                        FireBaseManager fireBaseManager = FireBaseManager.getInstance();
                      //  fireBaseManager.updateShoppingCartToServer();

                        mClickListener.onAddToCartClick(getAdapterPosition(), boardToCart);
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





