package jk.fhws_rooms.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jk.fhws_rooms.Activitiy.RoomsActivity;
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

    private List<Room> rooms;
    private Context context;
    private IOnItemClickListener listener;
    private SparseBooleanArray selectedItems;

    public RoomAdapter(Context context, IOnItemClickListener listener){
        this(context,new LinkedList<Room>(),listener);
    }


    public RoomAdapter(Context context, List<Room> rooms, IOnItemClickListener listener){
        this.context = context;
        this.rooms = rooms;
        this.listener = listener;
        selectedItems = new SparseBooleanArray();
    }

    public void updateData(List<Room> data){
        DiffUtil.DiffResult result = DiffUtil.calculateDiff( new UpdateCallback(rooms,data));

        rooms = data;

        result.dispatchUpdatesTo(this);
    }

    public void updateRoom(int index)
    {
        notifyItemChanged(index);
    }

    @Override
    public RoomViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_layout,parent,false);

        return new RoomViewholder(view, listener);
    }

    @Override
    public void onBindViewHolder(final RoomViewholder holder,final int position) {
        if ( rooms.size() != 0 ) {

            holder.roomTitle.setText(rooms.get(position).getLabel());

            setLectureTitleAndInformation(holder, position);

            holder.setOnItemClickListener(position);
        }
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    private void setLectureTitleAndInformation(RoomViewholder holder, int position){

        if(rooms.get(position).getLectures() != null && rooms.get(position).getLectures().size() > 0)

            roomTaken(holder, position);

        else

            roomFree(holder);

    }

    private void roomTaken(RoomViewholder holder, int position){

        holder.lectureTitle.setText(rooms.get(position).getLectures().get(0).getTitle());

        holder.roomInformation.setText(context.getResources().getString(R.string.free,"hallo"));

        holder.circleImageView.setImageResource(ic_closed);
    }

    private void roomFree(RoomViewholder holder){

        holder.lectureTitle.setText("");

        holder.roomInformation.setText(context.getResources().getString(R.string.taken,"hallo"));

        holder.circleImageView.setImageResource(ic_room);
    }

    public void clear( ){
        rooms.clear();

        notifyDataSetChanged();
    }

    public void insertData( ) {
        RoomManager.getRoomManager()
                .with( SupportApiAdapter.getSupportApiAdapter( ) )
                .timeperiod( getTimeSlot( ) )
                .update( this );
    }

    private int getTimeSlot( ) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences( context );

        return preferences.getInt( RoomsActivity.TIMESLOT , 1) ;
    }
}
