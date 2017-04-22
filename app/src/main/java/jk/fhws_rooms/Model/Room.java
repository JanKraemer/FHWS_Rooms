package jk.fhws_rooms.Model;


import java.util.List;

import jk.fhws_rooms.Helper.TimeManager;

/**
 * Created by Jan on 11.04.2017.
 */

public class Room {

    private String label;
    private String url;
    private List<Lecture> lectures;

    public Room( ){ }

    public String getLabel( ) { return label; }

    public String getUrl( ) { return url; }

    public void setLabel(String label) { this.label = label; }

    public void setUrl(String url) { this.url = url; }

    public List<Lecture> getLectures( ) { return lectures; }

    public void setLectures(List<Lecture> lectures) { this.lectures = lectures; }

    public boolean hasCurrentLecture( ) {

        if ( hasLectures( ) && hasFullLecture( ) ){
            return TimeManager.isCurrentRunning( getFirstLecture( ) );
        }
         return false;
    }

    public Lecture getFirstLecture( ){
        return lectures == null ? null :lectures.get(0);
    }

    private boolean hasLectures( ){
        return lectures != null && !lectures.isEmpty( );
    }

    private boolean hasFullLecture ( ){
        return lectures.get(0).hasFullLecture( );
    }
}
