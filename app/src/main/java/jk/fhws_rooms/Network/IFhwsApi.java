package jk.fhws_rooms.Network;

import java.util.List;

import jk.fhws_rooms.data.FullLecture;
import jk.fhws_rooms.data.Lecture;
import jk.fhws_rooms.data.Room;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IFhwsApi {


    @GET("rooms")
    Call<List<Room>> getAllRooms();

    @GET("rooms/{label}")
    Call<Room> getRoomByLabel(@Path("label") String label);

    @GET("lectures")
    Call<List<Lecture>> getLecturesInTime(@Query("room") String room, @Query("from") long startTime, @Query("to") long endTime);

    @GET("lectures/{year}/{studiengang}/{semester}/{kursnummer}/{date}")
    Call<FullLecture> getLecture(@Path("year") String year, @Path("studiengang") String studiengang,
                                 @Path("semester") int semester, @Path("kursnummer") int kursnummer, @Path("date") String date);
}
