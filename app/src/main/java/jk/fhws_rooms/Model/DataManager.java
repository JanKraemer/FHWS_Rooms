package jk.fhws_rooms.Model;

import java.util.LinkedList;
import java.util.List;

import jk.fhws_rooms.Network.SupportApiAdapter;
import jk.fhws_rooms.Network.UpdateLectureService;

/**
 * Created by Jan on 22.04.2017.
 */

public class DataManager {

    private static DataManager instance;


    public static DataManager getInstance( ) {
        if (instance == null ) {
            instance = new DataManager( );
        }

        return instance;
    }

    private List<Room> listOfRooms;

    private long lastCommit;

    private DataManager( ) {
        listOfRooms = new LinkedList<>();
    }

    public long getLastCommit() {
        return lastCommit;
    }

    public void setLastCommit(long lastCommit) {
        this.lastCommit = lastCommit;
    }

    public List<Room> getAllRooms( ) {
        return listOfRooms;
    }

    public void addRooms(List<Room> room) {
        listOfRooms = room;
    }

    public Room getRoom(int position) {
        return listOfRooms.get(position);
    }

    public Room getRoomByLabel (String roomName) {
        for (Room room : listOfRooms) {
            if (room.getLabel().equals(roomName)) {
                return room;
            }
        }

        return null;
    }

    public boolean isLastCommitSet( ){
        return lastCommit != 0;
    }

    public void updateLecturesOfEachRoom( ){
        for ( Room current :listOfRooms )
            current.deleteExpiredLectures( );
    }
}
