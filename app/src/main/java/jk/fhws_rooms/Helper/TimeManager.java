package jk.fhws_rooms.Helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


/**
 * Created by Jan on 11.04.2017.
 */

public class TimeManager {

    public static long now(){

        return System.currentTimeMillis();
    }

    private static Calendar getInstance( ){
        return Calendar.getInstance();
    }


    public static long midnight(){

        Calendar cal = getInstance();

        cal.add(Calendar.DAY_OF_MONTH,1);

        cal.set(Calendar.HOUR_OF_DAY, 0);

        cal.set(Calendar.MINUTE, 0);

        cal.set(Calendar.SECOND, 0);

        return cal.getTimeInMillis();
    }

    public static long nextDays( int days ){

        Calendar cal = getInstance();

        cal.add(Calendar.DAY_OF_MONTH,days);

        cal.set(Calendar.HOUR_OF_DAY, 0);

        cal.set(Calendar.MINUTE, 0);

        cal.set(Calendar.SECOND, 0);

        return cal.getTimeInMillis();
    }
}
