package jk.fhws_rooms.Network;

import java.util.List;

import jk.fhws_rooms.Adapter.RoomAdapter;
import jk.fhws_rooms.Helper.TimeManager;
import jk.fhws_rooms.Model.DataManager;
import jk.fhws_rooms.Model.FullLecture;
import jk.fhws_rooms.Model.Lecture;
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
        private DataManager dataManager;
        private int timeSlot;

        protected RoomManagerBuilder ( ) { }


        public RoomManagerBuilder withNetworkInterface( IFhwsApi supportApiAdapter ) {
            this.supportApiAdapter = supportApiAdapter;

            return this;
        }

        public RoomManagerBuilder timeperiod( int timeSlot ){
            this.timeSlot = timeSlot;

            return this;
        }

        public RoomManagerBuilder withDataManager( DataManager roomManager) {
            this.dataManager = roomManager;

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
                        adapter.updateData(response.body());
                        for (int i = 0; i < dataManager.getAllRooms().size(); i++)
                            getAllLecturesForRoom(dataManager.getRoom(i), i);
                    }
                }

                @Override
                public void onFailure(Call<List<Room>> call, Throwable t) {
                }
            });
        }

        private void getAllLecturesForRoom(final Room room, final int index) {
            Call<List<Lecture>> call = supportApiAdapter
                    .getLecturesInTime(room.getLabel(), TimeManager.now(), TimeManager.nextDays( timeSlot ));

            call.enqueue(new Callback<List<Lecture>>() {

                @Override
                public void onResponse(Call<List<Lecture>> call, Response<List<Lecture>> response) {
                    if ( response.isSuccessful( ) ) {

                        if ( !response.body().isEmpty( ) ){
                            room.setLectures( response.body( ) );

                            getFullLecture( room.getLectures().get(0) , index );
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Lecture>> call, Throwable t) {
                }
            });
        }

        public void getFullLecture(final Lecture lecture, final int index){

            Call<FullLecture> call = supportApiAdapter
                    .getLecture(lecture.getYear(),lecture.getStudiengang(),lecture.getSemester(),
                            lecture.getKursnummer(),lecture.getEvents().get(0).getStartTimeAsString());

            call.enqueue(new Callback<FullLecture>( ) {
                @Override
                public void onResponse(Call<FullLecture> call, Response<FullLecture> response) {
                    if ( response.isSuccessful( ) ){
                        lecture.setFullLecture( response.body( ) );

                        adapter.updateRoom( index );
                    }
                }

                @Override
                public void onFailure(Call<FullLecture> call, Throwable t) {

                }
            });
        }
    }
}

