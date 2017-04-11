package jk.fhws_rooms.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import jk.fhws_rooms.Model.Lectures;
import jk.fhws_rooms.Model.Room;
import jk.fhws_rooms.R;

import static jk.fhws_rooms.R.color.colorAccent;
import static jk.fhws_rooms.R.color.colorPrimaryDark;
import static jk.fhws_rooms.R.mipmap.ic_closed;
import static jk.fhws_rooms.R.mipmap.ic_room;

/**
 * Created by Jan on 11.04.2017.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomViewholder>{

    private static int GREEN = 0x689f38;

    private static int RED = 0xff6e40;

    private List<Room> rooms;
    private Context context;
    private IOnItemClickListener listener;

    public RoomAdapter(Context context, IOnItemClickListener listener){
        this(context,new LinkedList<Room>(),listener);
    }

    public RoomAdapter(Context context, List<Room> rooms, IOnItemClickListener listener){
        this.context = context;
        this.rooms = rooms;
        this.listener = listener;
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
        return new RoomViewholder(listener,view);
    }

    @Override
    public void onBindViewHolder(RoomViewholder holder, int position) {
        holder.roomTitle.setText(rooms.get(position).getLabel());
        setLectureTitleAndInformation(holder, position);

        holder.setOnClickListener(position);
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    private void setLectureTitleAndInformation(RoomViewholder holder, int position){

        if(rooms.get(position).getLectures() != null
                && rooms.get(position).getLectures().size() > 0){
            roomTaken(holder, position);
        }else{
            roomFree(holder);
        }
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
}
