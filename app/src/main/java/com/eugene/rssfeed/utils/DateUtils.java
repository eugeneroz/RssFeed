package com.eugene.rssfeed.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by eugene on 20/09/2017.
 */

public class DateUtils {

    private DateUtils() {
    }

    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final int TU_MILLISECONDS = 1;
    public static final int TU_SECONDS = 1000;
    public static final int TU_MINUTES = 60000;
    public static final int TU_HOURS = 3600000;
    public static final int TU_DAYS = 86400000;

    /**
     * Convert Date to string with long format
     *
     * @param context some context
     * @param date    date to format
     * @return {@code String} formatted date
     */
    public static String formatDateLong(Context context, Date date) {
        DateFormat dateFormat = android.text.format.DateFormat.getLongDateFormat(context);
        return dateFormat.format(date);
    }

    /**
     * Convert Date to string with short format
     *
     * @param context some context
     * @param date    date to format
     * @return {@code String} formatted date
     */
    public static String formatDateShort(Context context, Date date) {
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        return dateFormat.format(date);
    }

    /**
     * Check if the date is less than 7 days from now
     *
     * @param date date to check
     * @return {@code true} if the date is less than 7 days
     */
    private static boolean isLessThanOneWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -6);
        return date.after(calendar.getTime());
    }

    /**
     * Check if the date is less than 1 day from now
     *
     * @param date date to check
     * @return {@code true} if the date is more than 1 day ago
     */
    public static boolean isLessThanOneDayBefore(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return date.after(calendar.getTime());
    }

    /**
     * Check if the specific date is today
     *
     * @param date date to check
     * @return {@code true} if the date is today
     */
    public static boolean isToday(Date date) {
        return android.text.format.DateUtils.isToday(date.getTime());
    }

    /**
     * Is the specific date yesterday
     *
     * @param date date to check
     * @return {@code true} if the date is yesterday
     */
    public static boolean isYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = calendar.get(Calendar.MONTH);
        int nowMonthDay = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTimeInMillis(date.getTime());
        int thenYear = calendar.get(Calendar.YEAR);
        int thenMonth = calendar.get(Calendar.MONTH);
        int thenMonthDay = calendar.get(Calendar.DAY_OF_MONTH);

        return (thenYear == nowYear)
                && (thenMonth == nowMonth)
                && (thenMonthDay == nowMonthDay);
    }

    /**
     * Check for a leap year
     *
     * @param year the year to check
     * @return {@code true} for a leap year
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * Is the specific time was before today date
     * @param milliseconds Specific time to check
     * @return {@code true} if the time was before today
     */
    public static boolean isBeforeToday(long milliseconds) {
        boolean result;
        Calendar calendar = Calendar.getInstance();

        Date date = millisecondsToDate(milliseconds);
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);

        result = calendar.after(dateCalendar);

        return result;
    }
    /**
     * return date string from milliseconds
     *
     * @param milliseconds time in millis
     * @return yyyy-MM-dd HH:mm:ss formatted string
     */
    public static String millisecondsToString(long milliseconds) {
        return millisecondsToString(milliseconds, SIMPLE_DATE_FORMAT);
    }

    /**
     * return a date string from milliseconds with a specific date format
     *
     * @param milliseconds time in millis
     * @param dateFormat   the format to use
     * @return date formatted string
     */
    public static String millisecondsToString(long milliseconds, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(milliseconds));
    }

    /**
     * Create a formatted date
     *
     * @param date date to format
     * @return a yyyy-MM-dd HH:mm:ss formatted string
     */
    public static String dateToString(Date date) {
        return dateToString(date, SIMPLE_DATE_FORMAT);
    }

    /**
     * Create a formatted date with specific date format
     *
     * @param date       date to format
     * @param dateFormat the Date format to use
     * @return a date formatted string
     */
    public static String dateToString(Date date, SimpleDateFormat dateFormat) {
        return dateFormat.format(date);
    }

    /**
     * Convert date object to milliseconds
     *
     * @param date object to convert
     * @return long
     */
    public static long dateToMilliseconds(Date date) {
        return date.getTime();
    }

    /**
     * Convert milliseconds to date object
     *
     * @param milliseconds time to use
     * @return Date
     */
    public static Date millisecondsToDate(long milliseconds) {
        return new Date(milliseconds);
    }

    /**
     * Convert milliseconds to specific time unit
     *
     * @param milliseconds time in milliseconds
     * @param timeUnit     time unit to use
     * @return long in Time Unit
     */
    private static long millisecondsToTimeUnit(long milliseconds, int timeUnit) {
        switch (timeUnit) {
            case TU_MILLISECONDS:
            case TU_SECONDS:
            case TU_MINUTES:
            case TU_HOURS:
            case TU_DAYS:
                return Math.abs(milliseconds) / timeUnit;
        }
        return -1;
    }


    /**
     * Get current time in milliseconds
     *
     * @return long time in milliseconds
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * Get current time in formatted string
     *
     * @return a yyyy-MM-dd HH:mm:ss formatted string
     */
    public static String getCurrentTimeString() {
        return millisecondsToString(getCurrentTimeMillis());
    }

    /**
     * Get current time in formatted string with specific date format
     *
     * @param dateFormat the date format to use
     * @return String time in milliseconds
     */
    public static String getCurrentTimeString(SimpleDateFormat dateFormat) {
        return millisecondsToString(getCurrentTimeMillis(), dateFormat);
    }

    /**
     * Get current time in date object
     *
     * @return a Date object with the current time
     */
    public static Date getCurrentTimeDate() {
        return new Date();
    }

    /**
     * Calculate time interval from now
     *
     * @param date     date to compare
     * @param timeUnit time unit to get the result
     * @return long time in time unit
     */
    public static long getTimeIntervalFromNow(Date date, int timeUnit) {
        return getTimeIntervalBetweenDates(getCurrentTimeDate(), date, timeUnit);
    }

    /**
     * Calculate time interval between 2 dates
     *
     * @param date1    first date
     * @param date2    second date
     * @param timeUnit time unit to get the result
     * @return {@code long} the time interval between two dates
     */
    public static long getTimeIntervalBetweenDates(Date date1, Date date2, int timeUnit) {
        return millisecondsToTimeUnit(dateToMilliseconds(date2) - dateToMilliseconds(date1), timeUnit);
    }

    /**
     * Calculate time from current date after add interval of time units
     * @param timeInMillis time in milliseconds
     * @param interval time interval
     * @param timeUnit time unit for the added interval
     * @return {@code long} the time after adding the interval
     */
    public static long addTimeIntervalToDateInMillis(long timeInMillis, long interval, int timeUnit) {
        return timeInMillis + timeUnitToMillis(interval, timeUnit);
    }

    /**
     * Calculate the tomorrow date
     * @return tomorrow's date
     */
    public static Calendar getTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.add(Calendar.DATE, 1);

        return calendar;
    }

    /**
     * Calculate the date after specific amount of days
     * @param days amount of days
     * @return date after {@code days}
     */
    public static Calendar getDateAfter(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.add(Calendar.DATE, days);

        return calendar;
    }

    private static long timeUnitToMillis(long time, int timeUnit) {
        long result = -1;

        switch (timeUnit) {
            case TU_MILLISECONDS:
            case TU_SECONDS:
            case TU_MINUTES:
            case TU_HOURS:
            case TU_DAYS:
                result =  time * timeUnit;
        }

        return result;
    }
}
