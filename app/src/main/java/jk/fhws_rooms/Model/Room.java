package jk.fhws_rooms.Model;


import java.util.List;

/**
 * Created by Jan on 11.04.2017.
 */

public class Room {

    private String label;
    private String url;
    private List<Lectures> lectures;

    public Room( ){ }

    public String getLabel( ) { return label; }

    public String getUrl( ) { return url; }

    public void setLabel(String label) { this.label = label; }

    public void setUrl(String url) { this.url = url; }

    public List<Lectures> getLectures( ) { return lectures; }

    public void setLectures(List<Lectures> lectures) { this.lectures = lectures; }
}
