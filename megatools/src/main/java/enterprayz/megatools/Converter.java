package enterprayz.megatools;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> fc76bc5... change copyright
 * Copyright 2016 U.Prayzner
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
<<<<<<< HEAD
=======
 * com.timeweb.app.utils Created by urec on 29.09.15 TimeWeb.
>>>>>>> c4311c8... Init commit
=======
>>>>>>> fc76bc5... change copyright
 */
public class Converter {
    public static final String PUB_DATE_PATTERN = "EEE, dd MMM yyyy HH:mm:ss zzz";
    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    public static String convertSecondsToMmSs(long milliseconds) {
        return String.format("%d:%d",
                TimeUnit.MILLISECONDS.toMinutes(milliseconds),
                TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds))
        );
    }

    public static int getRandomInteger(int aStart, int aEnd, Random aRandom) {
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long) aEnd - (long) aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long) (range * aRandom.nextDouble());
        return (int) (fraction + aStart);
    }


    public static String getMoneyFormat(int source) {
        DecimalFormatSymbols custom = new DecimalFormatSymbols();
        custom.setGroupingSeparator(' ');
        custom.setDecimalSeparator(' ');

        DecimalFormat decimal_formatter = new DecimalFormat("#,##0.###", custom);
        decimal_formatter.setGroupingSize(3);

        return decimal_formatter.format(source);
    }

    public static String getMoneyFormat(double source) {
        DecimalFormatSymbols custom = new DecimalFormatSymbols();
        custom.setGroupingSeparator(' ');
        custom.setDecimalSeparator(',');

        DecimalFormat decimal_formatter = new DecimalFormat("#,##0.##", custom);
        decimal_formatter.setGroupingSize(3);

        return decimal_formatter.format(source);
    }

    public static int daysBetween(Calendar day1, Calendar day2) {
        Calendar dayOne = (Calendar) day1.clone(),
                dayTwo = (Calendar) day2.clone();

        if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)) {
            return Math.abs(dayOne.get(Calendar.DAY_OF_YEAR) - dayTwo.get(Calendar.DAY_OF_YEAR));
        } else {
            if (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
                //swap them
                Calendar temp = dayOne;
                dayOne = dayTwo;
                dayTwo = temp;
            }
            int extraDays = 0;

            int dayOneOriginalYearDays = dayOne.get(Calendar.DAY_OF_YEAR);

            while (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
                dayOne.add(Calendar.YEAR, -1);
                // getActualMaximum() important for leap years
                extraDays += dayOne.getActualMaximum(Calendar.DAY_OF_YEAR);
            }

            return extraDays - dayTwo.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays;
        }
    }


    public static int[] milisecondToTimeUnits(long ms, TimeUnit[] units) {
        int second;
        int minute;
        int hour;
        int day;
        if (ms > DAY) {
            day = (int) (ms / (DAY));
            ms %= DAY;
        } else {
            day = 0;
        }
        if (ms > HOUR) {
            hour = (int) (ms / (HOUR));
            ms %= HOUR;
        } else {
            hour = 0;
        }
        if (ms > MINUTE) {
            minute = (int) (ms / (MINUTE));
            ms %= MINUTE;
        } else {
            minute = 0;
        }
        if (ms > SECOND) {
            second = (int) (ms / (SECOND));
        } else {
            second = 0;
        }
        int[] res = new int[units.length];
        int index = 0;
        for (TimeUnit unit : units) {
            int valueToSet = 0;
            switch (unit) {
                case SECONDS: {
                    valueToSet = second;
                    break;
                }
                case MINUTES: {
                    valueToSet = minute;
                    break;
                }
                case HOURS: {
                    valueToSet = hour;
                    break;
                }
                case DAYS: {
                    valueToSet = day;
                    break;
                }
            }
            res[index] = valueToSet;
            index++;
        }
        return res;
    }

    public static String getFormatDate(long milliSeconds, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static Date getDate(int y, int m, int d) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, y);
        calendar.set(Calendar.MONTH, m);
        calendar.set(Calendar.DAY_OF_MONTH, d);
        return calendar.getTime();
    }


    public static Date getDate(String stringDate, String pattern) {
        DateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        try {
            return formatter.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFormatDate(Date date, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.getDefault());
        return formatter.format(date);
    }


    public static String readableFileSize(long size) {
        if (size <= 0) return "0 MB";
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static int getMediumInt(int[] numArray) {
        Arrays.sort(numArray);
        int median;
        if (numArray.length % 2 == 0)
            median = (numArray[numArray.length / 2] + numArray[numArray.length / 2 - 1]) / 2;
        else
            median = numArray[numArray.length / 2];
        return median;
    }

    public static double getMediumDouble(double[] doubleArray) {
        Arrays.sort(doubleArray);
        double median;
        if (doubleArray.length % 2 == 0)
            median = (doubleArray[doubleArray.length / 2] + doubleArray[doubleArray.length / 2 - 1]) / 2;
        else
            median = doubleArray[doubleArray.length / 2];
        return median;
    }

    public static int dpToPx(int dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static int pxToDp(int px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return (int) dp;
    }
}
