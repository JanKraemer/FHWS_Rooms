package jk.fhws_rooms.Network;

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

    public static UpdateLectureServiceBuilder getInstance( ){
        return new UpdateLectureServiceBuilder( );
    }

    private UpdateLectureService( ){ }

    public static class UpdateLectureServiceBuilder{

        protected IFhwsApi supportApiAdapter;
        protected List<Lecture> lectures;
        protected LectureAdapter adapter;

        public UpdateLectureServiceBuilder( ){ }

        public UpdateLectureServiceBuilder withNetworkInterface( IFhwsApi supportApiAdapter ){
            this.supportApiAdapter = supportApiAdapter;

            return this;
        }

        public UpdateLectureServiceBuilder updateLectures(List<Lecture> lectures ){
            this.lectures = lectures;

            return this;
        }

        public UpdateLectureServiceBuilder withAdapter( LectureAdapter adapter ){
            this.adapter = adapter;

            return this;
        }


        public void start( ){
            if( lectures != null){

                for(int index = 0;index < lectures.size();index++ ){

                    if(lectures.get(index).getFullLecture() == null ){

                        updateLecture( lectures.get(index) , index);
                    }
                }
            }
        }

        private void updateLecture(final Lecture lecture , final int index){
            Call<FullLecture> call = supportApiAdapter
                    .getLecture(lecture.getYear(),lecture.getStudiengang(),lecture.getSemester(),
                            lecture.getKursnummer(),lecture.getEvents().get(0).getStartTimeAsString());

            call.enqueue(new Callback<FullLecture>( ) {
                @Override
                public void onResponse(Call<FullLecture> call, Response<FullLecture> response) {
                    if ( response.isSuccessful( ) ){
                        lecture.setFullLecture( response.body( ) );
                        adapter.updateData(lecture,index );
                    }
                }

                @Override
                public void onFailure(Call<FullLecture> call, Throwable t) {

                }
            });
        }
    }
}
