package jk.fhws_rooms.Network;

import java.util.List;

import jk.fhws_rooms.Adapter.RoomAdapter;
import jk.fhws_rooms.Helper.TimeManager;
import jk.fhws_rooms.Model.Lectures;
import jk.fhws_rooms.Model.Room;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jan on 11.04.2017.
 */

public class RoomManager {

    public static RoomManagerBuilder getRoomManager(){

        return new RoomManagerBuilder();

    }

    private RoomManager( ){  }

    public static class RoomManagerBuilder {

        private IFhwsApi supportApiAdapter;
        private RoomAdapter adapter;
        private List<Room> rooms;
        private int timeSlot;

        protected RoomManagerBuilder ( ) { }


        public RoomManagerBuilder with( IFhwsApi supportApiAdapter ) {
            this.supportApiAdapter = supportApiAdapter;

            return this;
        }

        public RoomManagerBuilder timeperiod( int timeSlot ){
            this.timeSlot = timeSlot;

            return this;
        }

        public void update(final RoomAdapter adapter) {

            this.adapter = adapter;

            getRoomsWithLectures();

        }

        private void getRoomsWithLectures() {
            Call<List<Room>> call = supportApiAdapter.getAllRooms();

            call.enqueue(new Callback<List<Room>>() {

                @Override
                public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {

                    if (response.isSuccessful()) {
                        rooms = response.body();
                        adapter.updateData(rooms);
                        for (int i = 0; i < rooms.size(); i++)
                            getAllLecturesForRoom(rooms.get(i), i);
                    }
                }

                @Override
                public void onFailure(Call<List<Room>> call, Throwable t) {
                }
            });
        }

        private void getAllLecturesForRoom(final Room room, final int index) {
            Call<List<Lectures>> call = supportApiAdapter
                    .getLecturesInTime(room.getLabel(), TimeManager.now(), TimeManager.nextDays( timeSlot ));

            call.enqueue(new Callback<List<Lectures>>() {

                @Override
                public void onResponse(Call<List<Lectures>> call, Response<List<Lectures>> response) {

                    if (response.isSuccessful()) {
                        room.setLectures(response.body());
                        if (response.body().size() > 0)
                            adapter.updateRoom(index);
                    }
                }

                @Override
                public void onFailure(Call<List<Lectures>> call, Throwable t) {
                }
            });
        }
    }
}

