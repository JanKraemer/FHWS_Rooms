package jk.fhws_rooms.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import jk.fhws_rooms.Activitiy.RoomsActivity;
import jk.fhws_rooms.Helper.TimeManager;
import jk.fhws_rooms.Model.DataManager;
import jk.fhws_rooms.Model.Lecture;
import jk.fhws_rooms.Model.Room;
import jk.fhws_rooms.Network.RoomManager;
import jk.fhws_rooms.Network.SupportApiAdapter;
import jk.fhws_rooms.R;

import static jk.fhws_rooms.R.mipmap.ic_closed;
import static jk.fhws_rooms.R.mipmap.ic_room;

/**
 * Created by Jan on 11.04.2017.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomViewholder>{

    private DataManager rooms;
    private Context context;
    private IOnItemClickListener listener;

    public RoomAdapter(Context context, IOnItemClickListener listener){
        this(context,DataManager.getInstance(),listener);
    }


    public RoomAdapter(Context context, DataManager rooms, IOnItemClickListener listener){
        this.context = context;
        this.rooms = rooms;
        this.listener = listener;
        insertData();
    }

    public void updateData(List<Room> data){
        DiffUtil.DiffResult result = DiffUtil.calculateDiff( new UpdateCallback(rooms.getAllRooms(),data));

        rooms.addRooms(data);

        result.dispatchUpdatesTo(this);
    }

    public void updateRoom(int index)
    {
        notifyDataSetChanged();
    }

    @Override
    public RoomViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_layout,parent,false);

        return new RoomViewholder(view, listener);
    }

    @Override
    public void onBindViewHolder(final RoomViewholder holder,final int position) {
        if ( rooms.getAllRooms( ).size( ) != 0 ) {

            holder.roomTitle.setText(rooms.getRoom(position).getLabel());

            setLectureTitleAndInformation(holder, position);

            holder.setOnItemClickListener(position);
        }
    }

    @Override
    public int getItemCount( ) {
        return rooms.getAllRooms( ).size( );
    }

    private void setLectureTitleAndInformation(RoomViewholder holder, int position){

        if( rooms.getRoom( position ).hasCurrentLecture( ) )
        {
            roomTaken(holder, position);
        }
        else
        {
            roomFree(holder,position);
        }
    }

    private void roomTaken(RoomViewholder holder, int position){

        holder.lectureTitle.setText(rooms.getRoom(position).getFirstLecture( ).getTitle());

        holder.roomInformation.setText(context.getResources()
                .getString(R.string.taken,TimeManager.getTimeTillEndOfLecture(rooms.getRoom(position).getFirstLecture( ))));

        holder.circleImageView.setImageResource(ic_closed);
    }

    private void roomFree(RoomViewholder holder, int position){

        holder.lectureTitle.setText("");

        holder.roomInformation.setText(context.getResources().getString(R.string.free,TimeManager.getTimeTillLectureOrMidnight(rooms.getRoom(position).getFirstLecture( ))));

        holder.circleImageView.setImageResource(ic_room);
    }

    private void clear( ){
        rooms.getAllRooms().clear();

        notifyDataSetChanged();
    }

    private void insertData( ) {
        RoomManager.getRoomManager()
                .withNetworkInterface( SupportApiAdapter.getSupportApiAdapter( ) )
                .withDataManager( DataManager.getInstance() )
                .timeperiod( getTimeSlot( ) )
                .update( this );
    }

    public int getTimeSlot( ) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences( context );

        return preferences.getInt( RoomsActivity.TIMESLOT , 1) ;
    }

    public void refreshData( ){
        clear();

        insertData();

    }
}
