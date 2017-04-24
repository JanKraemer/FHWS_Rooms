package jk.fhws_rooms.Helper;

import java.util.ArrayList;
import java.util.List;

import jk.fhws_rooms.Model.Lecture;

/**
 * Created by Jan on 24.04.2017.
 */

public class LectureHelper {

    public static List<Lecture> removeExpiredLectures( List<Lecture> lectures ){
        ArrayList<Lecture> listeOfLectures= new ArrayList<>();

        for ( Lecture lecture: lectures ){
            if ( TimeManager.isTimeNotExpired( lecture.getFullLecture( ).getEnddate( ) ) ){
                listeOfLectures.add(lecture);
            }
        }
        return listeOfLectures;
    }

    public static List<Lecture> getNotActualRunningLectures( List<Lecture> lectures) {
        ArrayList<Lecture> listeOfLectures= new ArrayList<>();

        for ( Lecture lecture: lectures ){
            if ( !TimeManager.isCurrentRunning( lecture ) ){
                listeOfLectures.add(lecture);
            }
        }
        return listeOfLectures;
    }

}
