package finalproject.ski2rent.utils;

import android.text.format.DateUtils;

import java.util.concurrent.TimeUnit;

public class MyDateUtil {

    public static int calculateDaysDifferenceWithLastDay(long startDate, long endDate) {
        long msDiffDays = endDate - startDate;
        int daysDiff = (int) TimeUnit.MILLISECONDS.toDays(msDiffDays) + 1;
        return daysDiff;
    }

    public static int calculateDaysDifferenceWithoutLastDay(long startDate, long endDate) {
        long msDiffDays = endDate - startDate;
        int daysDiff = (int) TimeUnit.MILLISECONDS.toDays(msDiffDays);
        return daysDiff;
    }

    public static boolean isToday(long date) {
        return DateUtils.isToday(date);
    }


} // MyDateUtil
