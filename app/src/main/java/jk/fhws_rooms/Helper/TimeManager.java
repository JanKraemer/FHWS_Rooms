package jk.fhws_rooms.Helper;

import android.provider.ContactsContract;
import android.text.format.Time;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by Jan on 11.04.2017.
 */

public class TimeManager {

    public static long now(){

        return System.currentTimeMillis();
    }


    public static long midnight(){

        Calendar cal = new GregorianCalendar();

        cal.add(Calendar.DAY_OF_MONTH,2);

        cal.set(Calendar.HOUR_OF_DAY, 0);

        cal.set(Calendar.MINUTE, 0);

        cal.set(Calendar.SECOND, 0);

        return cal.getTimeInMillis();
    }
}
