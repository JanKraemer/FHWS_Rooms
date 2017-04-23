package jk.fhws_rooms.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jan on 11.04.2017.
 */

public class Lecture {

    @SerializedName("number")
    private int kursnummer;

    @SerializedName("label")
    private String title;

    private String year;

    @SerializedName("program")
    private String studiengang;

    @SerializedName("session")
    private int semester;

    private List<SimpleLecturer> lecturers;

    private List<Event> events;

    private FullLecture fullLecture;

    public Lecture( ) { }

    public int getKursnummer( ) { return kursnummer; }

    public int getSemester( ) { return semester; }

    public List<Event> getEvents( ) { return events; }

    public List<SimpleLecturer> getLecturers( ) { return lecturers; }

    public String getStudiengang( ) { return studiengang; }

    public String getTitle( ) { return title; }

    public String getYear( ) { return year; }

    public void setKursnummer(int kursnummer) { this.kursnummer = kursnummer; }

    public void setEvents(List<Event> events) { this.events = events; }

    public void setLecturers(List<SimpleLecturer> lecturers) { this.lecturers = lecturers; }

    public void setSemester(int semester) { this.semester = semester; }

    public void setStudiengang(String studiengang) { this.studiengang = studiengang; }

    public void setTitle(String title) { this.title = title; }

    public void setYear(String year) { this.year = year; }

    public FullLecture getFullLecture() {
        return fullLecture;
    }

    public void setFullLecture(FullLecture fullLecture) {
        this.fullLecture = fullLecture;
    }

    public boolean hasFullLecture( ) {
        return fullLecture != null;
    }

}
