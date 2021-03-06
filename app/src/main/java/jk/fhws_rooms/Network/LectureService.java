package jk.fhws_rooms.Network;

import java.util.List;

import jk.fhws_rooms.adapters.RoomAdapter;
import jk.fhws_rooms.helpers.TimeManager;
import jk.fhws_rooms.data.DataManager;
import jk.fhws_rooms.data.Lecture;
import jk.fhws_rooms.data.Room;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LectureService {


    public static LectureServiceBuilder getLectures(RoomAdapter adapter) {
        return new LectureServiceBuilder(adapter);
    }


    private LectureService() {
    }

    public static class LectureServiceBuilder {

        protected IFhwsApi supportApiAdapter;
        protected DataManager dataManager;
        protected RoomAdapter adapter;
        protected Room room;
        protected int timeSlot;

        protected LectureServiceBuilder(RoomAdapter adapter) {
            dataManager = DataManager.getInstance();

            this.adapter = adapter;
        }


        public LectureServiceBuilder withNetworkInterface(IFhwsApi supportApiAdapter) {
            this.supportApiAdapter = supportApiAdapter;

            return this;
        }

        public LectureServiceBuilder addRoom(Room room) {
            this.room = room;

            return this;
        }

        public LectureServiceBuilder withPeroid(int timeSlot) {
            this.timeSlot = timeSlot;

            return this;
        }

        public void start() {
            getAllLecturesForRoom();

        }


        private void getAllLecturesForRoom() {
            Call<List<Lecture>> call = supportApiAdapter
                    .getLecturesInTime(room.getLabel(), TimeManager.today(), TimeManager.nextDays(timeSlot));

            call.enqueue(new Callback<List<Lecture>>() {

                @Override
                public void onResponse(Call<List<Lecture>> call, Response<List<Lecture>> response) {
                    if (response.isSuccessful()) {

                        if (!response.body().isEmpty()) {
                            room.setLectures(response.body());

                            adapter.getFullLectureFromLecture(room, dataManager.getAllRooms().indexOf(room));
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Lecture>> call, Throwable t) {
                }
            });
        }


    }


}
