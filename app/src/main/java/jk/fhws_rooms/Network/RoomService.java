package jk.fhws_rooms.Network;

import java.util.LinkedList;
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

public class RoomService {

    public static RoomServiceBuilder getRoomManager( RoomAdapter adapter ){

        return new RoomServiceBuilder( adapter );
    }

    private RoomService( ){  }

    public static class RoomServiceBuilder {

        protected IFhwsApi supportApiAdapter;
        protected RoomAdapter adapter;
        protected DataManager dataManager;

        protected RoomServiceBuilder( RoomAdapter adapter) { this.adapter = adapter; }

        public RoomServiceBuilder withNetworkInterface(IFhwsApi supportApiAdapter ) {
            this.supportApiAdapter = supportApiAdapter;

            return this;
        }

        public RoomServiceBuilder withDataManager(DataManager dataManager) {
            this.dataManager = dataManager;

            return this;
        }

        public void update( ) {

            if ( dataManager.isLastCommitSet( ) && TimeManager.isTimeToday( dataManager.getLastCommit( ) ) )
            {
                dataManager.updateLecturesOfEachRoom( );

                adapter.updateData( dataManager.getAllRooms( ) );
            }
            else {
                getRooms();
            }
        }

        private void getRooms( ) {
            Call<List<Room>> call = supportApiAdapter.getAllRooms();

            call.enqueue(new Callback<List<Room>>() {

                @Override
                public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {

                    if (response.isSuccessful()) {
                        dataManager.addRooms(response.body());
                        adapter.updateData(new LinkedList<>(response.body( )));

                        dataManager.setLastCommit( TimeManager.now( ) );
                        for (int i = 0; i < dataManager.getAllRooms().size(); i++)
                            adapter.getLecturesForRoom(dataManager.getRoom(i));
                    }
                }

                @Override
                public void onFailure(Call<List<Room>> call, Throwable t) {
                }
            });
        }
    }
}

