package jk.fhws_rooms.Network;

import java.util.List;

import jk.fhws_rooms.Model.FullLecture;
import jk.fhws_rooms.Model.Lecture;
import jk.fhws_rooms.Model.Room;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Jan on 11.04.2017.
 */

public interface IFhwsApi {


    @GET("rooms")
    Call<List<Room>> getAllRooms();

    @GET("rooms/{label}")
    Call<Room> getRoomByLabel(@Path("label") String label);

    @GET("lectures")
    Call<List<Lecture>> getLecturesInTime(@Query("room") String room, @Query("from") long startTime, @Query("to") long endTime);

    @GET("lectures/{year}/{studiengang}/{semester}/{kursnummer}/{date}")
    Call<FullLecture> getLecture(@Path("year") String year, @Path("studiengang") String studiengang,
                                 @Path("semester") int semester, @Path("kursnummer") int kursnummer, @Path("date") String date );
}
