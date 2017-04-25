package jk.fhws_rooms.data;

import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("label")
    private String info;

    private String url;

    @SerializedName("timestamp")
    private long startTime;

    public Event() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getStartTime() {
        return startTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getStartTimeAsString() {
        String[] array = this.url.split("/");
        return array[array.length - 1];
    }

}
