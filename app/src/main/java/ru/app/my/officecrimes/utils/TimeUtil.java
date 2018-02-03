package ru.app.my.officecrimes.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

// Dmitry Koltovich, Февр., 2018.
public class TimeUtil {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getDateAsString(Date mDate) {
        Log.d("timeDate", "mDate = " + mDate);

        Locale local = new Locale("ru");
        DateFormat timeInst = DateFormat.getTimeInstance(DateFormat.DEFAULT, local);
        DateFormat dateInst = DateFormat.getDateInstance(DateFormat.DEFAULT, local);

        String time = timeInst.format(mDate);
        String date = dateInst.format(mDate);

        Log.d("timeDate", "date: " + date);
        Log.d("timeDate", "time: " + time);

        String[] dateArray = date.split(Pattern.quote("."));

        if (dateArray.length == 3) {
            int[] numbers = new int[3];
            for (int i = 0; i < 3; i++) {
                int number = Integer.parseInt(dateArray[i]);
                numbers[i] = number;
            }
            String finalDate =
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                            .withLocale(local)
                            .format(LocalDate.of(numbers[2], numbers[1], numbers[0]));
            return time + ", " + finalDate;
        }
        return time + ", " + date;
    }
}
