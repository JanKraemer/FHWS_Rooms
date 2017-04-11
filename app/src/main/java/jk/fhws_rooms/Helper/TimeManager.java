package jk.fhws_rooms.Helper;

import java.util.Calendar;
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

        cal.add(Calendar.DAY_OF_MONTH,1);

        cal.set(Calendar.HOUR_OF_DAY, 0);

        cal.set(Calendar.MINUTE, 0);

        cal.set(Calendar.SECOND, 0);

        return cal.getTimeInMillis();
    }
}
