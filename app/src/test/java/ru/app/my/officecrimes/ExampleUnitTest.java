package ru.app.my.officecrimes;

import org.junit.Test;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public  void testTimeMethod(){
//        TimeUtil.getDateAsString(new Date());
    }

    @Test
    public void testTime() {
        Date currentDate = new Date();
        Locale local = new Locale("ru");
        DateFormat timeInst = DateFormat.getTimeInstance(DateFormat.DEFAULT, local);
        DateFormat dateInst = DateFormat.getDateInstance(DateFormat.DEFAULT, local);

        String time = timeInst.format(currentDate);
        String date = dateInst.format(currentDate);

        String[] dateArray = date.split(Pattern.quote("."));

        int[] numbers = new int[3];

        for (int i = 0; i < 3; i++) {
            int number = Integer.parseInt(dateArray[i]);
            numbers[i] = number;
        }

        String finalDate =
                DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                        .withLocale(local)
                        .format(LocalDate.of(numbers[2], numbers[1], numbers[0]));

        System.out.println(finalDate);
    }
}