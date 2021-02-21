package finalproject.ski2rent.activities;

import androidx.core.util.Pair;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import finalproject.ski2rent.R;
import finalproject.ski2rent.objects.ShoppingCart;
import finalproject.ski2rent.utils.FireBaseManager;
import finalproject.ski2rent.utils.MySignals;

public class Activity_RentDates extends Activity_Base {
    public static final String EXTRA_KEY_BOARD_TYPE = "EXTRA_KEY_BOARD_TYPE";
    public static final String EXTRA_KEY_ALL_BOARDS = "EXTRA_KEY_ALL_BOARDS";

    private MaterialButton rentDates_BTN_pickDate;
    private ImageView rentDates_IMG_background;
    private long pickupDateInCart;
    private long returnDateInCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__rent_dates);

        findViews();

        String boardType = getIntent().getStringExtra(EXTRA_KEY_BOARD_TYPE);
        String boardsForRentJson = getIntent().getStringExtra(EXTRA_KEY_ALL_BOARDS);

        Glide
                .with(this)
                .load(R.drawable.img_dates_background)
                .into(rentDates_IMG_background);

        ShoppingCart shoppingCart = fireBaseManager.getShoppingCart();
        if (shoppingCart != null) {
            pickupDateInCart = shoppingCart.getPickupDate();
            returnDateInCart = shoppingCart.getReturnDate();
            if (pickupDateInCart != 0 || returnDateInCart != 0) {
                MySignals.getInstance().toast("Clear cart first to pick a different date.");
                openRentBoardsActivity(Activity_RentDates.this, boardType, pickupDateInCart, returnDateInCart, boardsForRentJson);
                return;
            }
        }

        MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker();

        // create the calendar constraint builder
        CalendarConstraints.Builder calendarConstraintBuilder = new CalendarConstraints.Builder();

        // all dates before current date are blocked
        calendarConstraintBuilder.setValidator(DateValidatorPointForward.now());

        // now define the properties of the materialDateBuilder
        materialDateBuilder.setTitleText("SELECT RENT DATES RANGE");

        // now pass the constrained calender builder to material date picker Calendar constraints
        materialDateBuilder.setCalendarConstraints(calendarConstraintBuilder.build());

        // now create the instance of the material date picker
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

        rentDates_BTN_pickDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                    }
                });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override public void onPositiveButtonClick(Pair<Long,Long> selection) {
                Long startDate = selection.first;
                Long endDate = selection.second;

                openRentBoardsActivity(Activity_RentDates.this, boardType, startDate, endDate, boardsForRentJson);
            }
        });

    } // onCreate

    private void findViews() {
        rentDates_BTN_pickDate = findViewById(R.id.rentDates_BTN_pickDate);
        rentDates_IMG_background = findViewById(R.id.rentDates_IMG_background);
    }

    private void openRentBoardsActivity(Activity activity, String boardType, long startDate, long endDate, String boardsForRentJson) {
        Intent myIntent = new Intent(activity, Activity_RentBoards.class);
        myIntent.putExtra(Activity_RentBoards.EXTRA_KEY_BOARD_TYPE, boardType);
        myIntent.putExtra(Activity_RentBoards.EXTRA_KEY_START_DATE, startDate);
        myIntent.putExtra(Activity_RentBoards.EXTRA_KEY_END_DATE, endDate);
        myIntent.putExtra(Activity_RentBoards.EXTRA_KEY_ALL_BOARDS, boardsForRentJson);

        startActivity(myIntent);
        finish();
    }
}