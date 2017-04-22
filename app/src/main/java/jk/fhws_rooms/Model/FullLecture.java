package jk.fhws_rooms.Model;

import java.util.List;

/**
 * Created by Jan on 22.04.2017.
 */

public class FullLecture {

    private String name;
    private long startdate;
    private long enddate;
    private String label;
    private String type;
    private int eventid;
    private String note;
    private String Group;
    private String room;
    private Module module;
    private List<SimpleLecturer> lecturers;

    public FullLecture( ){ }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public int getEventid() {
        return eventid;
    }

    public long getEnddate() {
        return enddate;
    }

    public long getStartdate() {
        return startdate;
    }

    public Module getModule() {
        return module;
    }

    public List<SimpleLecturer> getLecturers() {
        return lecturers;
    }

    public String getGroup() {
        return Group;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public String getRoom() {
        return room;
    }

    public String getType() {
        return type;
    }

    public void setEnddate(long enddate) {
        this.enddate = enddate;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public void setLecturers(List<SimpleLecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setStartdate(long startdate) {
        this.startdate = startdate;
    }

    public void setType(String type) {
        this.type = type;
    }
}
