package jk.fhws_rooms.Network;

import java.util.List;

import jk.fhws_rooms.Adapter.RoomAdapter;
import jk.fhws_rooms.Model.FullLecture;
import jk.fhws_rooms.Model.Lecture;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jan on 23.04.2017.
 */

public class FirstLectureService {

    public static FirstLectureServiceBuilder getInstance(RoomAdapter adapter ){
        return new FirstLectureServiceBuilder( adapter );
    }

    private FirstLectureService( ){ }

    public static class FirstLectureServiceBuilder {

        protected RoomAdapter adapter;
        protected IFhwsApi supportApiAdapter;
        protected int indexOfRoom;
        protected Lecture lecture;
        protected List<Lecture> lectures;

        public FirstLectureServiceBuilder(RoomAdapter adapter ){
            this.adapter = adapter;
        }

        public FirstLectureServiceBuilder withNetworkInterface(IFhwsApi supportApiAdapter ){
            this.supportApiAdapter = supportApiAdapter;

            return this;
        }

        public FirstLectureServiceBuilder onRoomIndex(int indexOfRoom ){
            this.indexOfRoom = indexOfRoom;

            return this;
        }

        public FirstLectureServiceBuilder updateLecture( Lecture lecture ){
            this.lecture = lecture;

            return this;
        }

        public void start( ){
            updateLectureWithFullLecture();
        }


        private void updateLectureWithFullLecture( ){
            Call<FullLecture> call = supportApiAdapter
                    .getLecture(lecture.getYear(),lecture.getStudiengang(),lecture.getSemester(),
                            lecture.getKursnummer(),lecture.getEvents().get(0).getStartTimeAsString());

            call.enqueue(new Callback<FullLecture>( ) {
                @Override
                public void onResponse(Call<FullLecture> call, Response<FullLecture> response) {
                    if ( response.isSuccessful( ) ){
                        lecture.setFullLecture( response.body( ) );

                        adapter.updateRoom( indexOfRoom );
                    }
                }

                @Override
                public void onFailure(Call<FullLecture> call, Throwable t) {

                }
            });
        }
    }
}
