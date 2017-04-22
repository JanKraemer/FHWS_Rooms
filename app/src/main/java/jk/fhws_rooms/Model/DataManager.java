package jk.fhws_rooms.Model;

import java.util.LinkedList;
import java.util.List;

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

    private DataManager( ) {
        listOfRooms = new LinkedList<>();
    }


    public List<Room> getAllRooms( ) {
        return listOfRooms;
    }

    public void addRooms(List<Room> room) {
        listOfRooms.addAll(room);
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
}
