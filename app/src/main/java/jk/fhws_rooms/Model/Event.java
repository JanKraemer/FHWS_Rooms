package jk.fhws_rooms.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.regex.Pattern;

import jk.fhws_rooms.Helper.TimeManager;

/**
 * Created by Jan on 11.04.2017.
 */

public class Event {

    @SerializedName("label")
    private String info;

    private String url;

    @SerializedName("timestamp")
    private long startTime;

    public Event( ){ }

    public String getUrl( ) { return url; }

    public void setUrl(String url) { this.url = url; }

    public long getStartTime( ) { return startTime; }

    public String getInfo( ) { return info; }

    public void setInfo(String info) { this.info = info; }

    public void setStartTime(long startTime) { this.startTime = startTime; }

    public String getStartTimeAsString( ){
        String[] array = info.replaceAll("\\p{P}", " ").split(" ");
        return array[3].replace("20","")+array[2]+array[1]+array[5]+array[6];
    }

}
