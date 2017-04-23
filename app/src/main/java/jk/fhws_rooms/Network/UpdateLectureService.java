package jk.fhws_rooms.Network;

import android.content.Context;
import android.util.Log;

import java.util.List;

import jk.fhws_rooms.Adapter.LectureAdapter;
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

    public static UpdateLectureServiceBuilder getInstance( RoomAdapter adapter ){
        return new UpdateLectureServiceBuilder( adapter );
    }

    private UpdateLectureService( ){ }

    public static class UpdateLectureServiceBuilder{

        protected IFhwsApi supportApiAdapter;
        protected Room room;
        protected RoomAdapter adapter;
        protected int roomIndex;
        protected Context context;

        public UpdateLectureServiceBuilder( RoomAdapter adapter){ this.adapter = adapter; }

        public UpdateLectureServiceBuilder withNetworkInterface( IFhwsApi supportApiAdapter ){
            this.supportApiAdapter = supportApiAdapter;

            return this;
        }

        public UpdateLectureServiceBuilder fromRoom (Room room ){
            this.room = room;

            return this;
        }

        public UpdateLectureServiceBuilder onIndex( int roomIndex ){
            this.roomIndex = roomIndex;

            return this;
        }


        public void start( ){
            if( room.getLectures( ) != null){

                for( Lecture lecture : room.getLectures( ) )
                    updateLecture( lecture );

            }
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
                        if( room.AreLecturesUpdated( ) ){
                            room.sortLectures( );

                            adapter.updateRoom( roomIndex );
                        }
                    }
                }

                @Override
                public void onFailure(Call<FullLecture> call, Throwable t) {

                }
            });
        }
    }
}
