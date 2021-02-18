package finalproject.ski2rent.activities;

import androidx.core.util.Pair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import finalproject.ski2rent.R;

public class Activity_RentDates extends Activity_Base {
    public static final String EXTRA_KEY_BOARD_TYPE = "EXTRA_KEY_BOARD_TYPE";
    public static final String EXTRA_KEY_ALL_BOARDS = "EXTRA_KEY_ALL_BOARDS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__rent_dates);

        String boardType = getIntent().getStringExtra(EXTRA_KEY_BOARD_TYPE);
        String boardsForRentJson = getIntent().getStringExtra(EXTRA_KEY_ALL_BOARDS);
        // now create instance of the material date picker
        // builder make sure to add the "dateRangePicker"
        // which is material date range picker which is the
        // second type of the date picker in material design
        // date picker we need to pass the pair of Long
        // Long, because the start date and end date is
        // store as "Long" type value
        MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker();

        // create the calendar constraint builder
        CalendarConstraints.Builder calendarConstraintBuilder = new CalendarConstraints.Builder();

        // all dates before current date are blocked
        calendarConstraintBuilder.setValidator(DateValidatorPointForward.now());

        // now define the properties of the
        // materialDateBuilder
        materialDateBuilder.setTitleText("SELECT RENT DATES RANGE");

        // now pass the constrained calender builder to
        // material date picker Calendar constraints
        materialDateBuilder.setCalendarConstraints(calendarConstraintBuilder.build());

        // now create the instance of the material date picker
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

        materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override public void onPositiveButtonClick(Pair<Long,Long> selection) {
                Long startDate = selection.first;
                Long endDate = selection.second;
                String dateFormat1 = DateFormat.format("dd.MM.yy", startDate).toString();
                String dateFormat2 = DateFormat.format("dd.MM.yy", endDate).toString();
                Log.d("rrr", dateFormat1);
                Log.d("rrr", dateFormat2);

                openRentBoardsActivity(Activity_RentDates.this, boardType, startDate, endDate, boardsForRentJson);

            }
        });

    } // onCreate

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