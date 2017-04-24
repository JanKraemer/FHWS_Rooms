package jk.fhws_rooms.Helper;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import jk.fhws_rooms.Model.DataManager;
import jk.fhws_rooms.Model.Lecture;


/**
 * Created by Jan on 11.04.2017.
 */

public class TimeManager {

    private static long REDUCE_CALC_HOUR = 3600000;

    public static long now(){
        return getInstance().getTimeInMillis();
    }

    private static Calendar getInstance( ){
        return Calendar.getInstance( );
    }


    private static long midnight( ){

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

    public static boolean isCurrentRunning( Lecture lecture ){
        return lecture.getFullLecture( ).getStartdate( ) <= TimeManager.now( ) &&
                TimeManager.now( ) <= lecture.getFullLecture( ).getEnddate( );
    }

    public static String getTimeTillEndOfLecture( Lecture lecture ){
        long time =  lecture.getFullLecture( ).getEnddate( ) - now( ) - REDUCE_CALC_HOUR;

        return timeAsString(time);
    }

    public static String getStringFromTimeTillLectureOrMidnight(Lecture lecture ){
        long time = getCalenderTillLectureOrMidnight(lecture) - now( ) - REDUCE_CALC_HOUR;

        return timeAsString(time);
    }

    public static long getTimeTillLectureOrMidnight(Lecture lecture ) {
        return getCalenderTillLectureOrMidnight(lecture);
    }

    private static long getCalenderTillLectureOrMidnight( Lecture lecture ){
        long calendar = getLectureStartDate( lecture );

        long midnight = midnight( );

        if ( calendar == 0 || midnight < calendar )
            return midnight( );

        return lecture.getFullLecture( ).getStartdate( );
    }

    private static long getLectureStartDate( Lecture lecture ) {
        if ( lecture != null && lecture.hasFullLecture( ) )
            return lecture.getFullLecture( ).getStartdate( );

        return 0;
    }

    private static Date getDate( long sec ){

        Date date = new Date( sec );

        return date;
    }

    public static String timeAsString( long time ){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.GERMANY);

        return format.format( getDate( time ) );
    }

    public static String getDayFromTime( long time ){
        Calendar calendar = getInstance( );

        calendar.setTimeInMillis( time );

        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.GERMANY);
    }

    public static long today( ){

        Calendar cal = getInstance();

        cal.set(Calendar.HOUR_OF_DAY, 8);

        cal.set(Calendar.MINUTE, 0);

        cal.set(Calendar.SECOND, 0);

        return cal.getTimeInMillis();
    }

    public static boolean isTimeToday( long time ){
        return time <= midnight();
    }

    public static boolean isTimeNotExpired( long time ){
        return  now( ) < time;
    }

}
