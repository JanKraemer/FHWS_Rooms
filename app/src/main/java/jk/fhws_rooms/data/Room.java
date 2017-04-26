package jk.fhws_rooms.data;


import java.util.Comparator;
import java.util.List;

import jk.fhws_rooms.helpers.LectureHelper;
import jk.fhws_rooms.helpers.TimeManager;

import static java.util.Collections.sort;

public class Room {

    private String label;
    private String url;
    private List<Lecture> lectures;

    public Room() {
    }

    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public boolean hasCurrentLecture() {

        if (hasLectures() && hasFullLecture()) {
            return TimeManager.isCurrentRunning(getFirstLecture());
        }
        return false;
    }

    public Lecture getFirstLecture() {
        return lectures == null || lectures.size() == 0 ? null : lectures.get(0);
    }

    private boolean hasLectures() {
        return lectures != null && !lectures.isEmpty();
    }

    private boolean hasFullLecture() {
        return lectures.get(0).hasFullLecture();
    }

    public boolean AreLecturesUpdated() {
        if (lectures != null) {
            for (Lecture lecture : lectures) {
                if (!lecture.hasFullLecture()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void sortLectures() {
        sort(lectures, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture o1, Lecture o2) {
                return Long.compare(o1.getFullLecture().getStartdate(), o2.getFullLecture().getStartdate());
            }
        });
    }

    public void deleteExpiredLectures() {
        if (hasLectures()) {
            setLectures(LectureHelper.removeExpiredLectures(lectures));

            sortLectures();
        }
    }

    public List<Lecture> getFutureLectures() {
        return LectureHelper.getNotActualRunningLectures(lectures);
    }
}
