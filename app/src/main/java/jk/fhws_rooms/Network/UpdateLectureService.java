package jk.fhws_rooms.Network;

import java.util.List;

import jk.fhws_rooms.Adapter.RoomAdapter;
import jk.fhws_rooms.Model.DataManager;
import jk.fhws_rooms.Model.FullLecture;
import jk.fhws_rooms.Model.Lecture;
import jk.fhws_rooms.Model.Room;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jan on 23.04.2017.
 */

public class UpdateLectureService {

    public static UpdateLectureServiceBuilder getInstance( ){
        return new UpdateLectureServiceBuilder( );
    }

    private UpdateLectureService( ){ }

    public static class UpdateLectureServiceBuilder{

        protected IFhwsApi supportApiAdapter;
        protected Room room;

        public UpdateLectureServiceBuilder( ){ }

        public UpdateLectureServiceBuilder withNetworkInterface( IFhwsApi supportApiAdapter ){
            this.supportApiAdapter = supportApiAdapter;

            return this;
        }

        public UpdateLectureServiceBuilder updateRoom( Room room ){
            this.room = room;

            return this;
        }

        public void start( ){
            for(Lecture lecture: room.getLectures())
                updateLecture( lecture );
        }

        private void updateLecture(final Lecture lecture ){
            Call<FullLecture> call = supportApiAdapter
                    .getLecture(lecture.getYear(),lecture.getStudiengang(),lecture.getSemester(),
                            lecture.getKursnummer(),lecture.getEvents().get(0).getStartTimeAsString());

            call.enqueue(new Callback<FullLecture>( ) {
                @Override
                public void onResponse(Call<FullLecture> call, Response<FullLecture> response) {
                    if ( response.isSuccessful( ) ){
                        lecture.setFullLecture( response.body( ) );
                    }
                }

                @Override
                public void onFailure(Call<FullLecture> call, Throwable t) {

                }
            });
        }
    }
}
