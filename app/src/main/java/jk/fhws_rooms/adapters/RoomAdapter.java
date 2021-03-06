package jk.fhws_rooms.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import jk.fhws_rooms.activities.RoomsActivity;
import jk.fhws_rooms.helpers.TimeManager;
import jk.fhws_rooms.data.DataManager;
import jk.fhws_rooms.data.Room;
import jk.fhws_rooms.Network.LectureService;
import jk.fhws_rooms.Network.RoomService;
import jk.fhws_rooms.Network.SupportApiAdapter;
import jk.fhws_rooms.Network.UpdateLectureService;
import jk.fhws_rooms.R;

import static jk.fhws_rooms.R.mipmap.ic_closed;
import static jk.fhws_rooms.R.mipmap.ic_room;

public class RoomAdapter extends RecyclerView.Adapter<RoomViewholder> {

    private List<Room> rooms;
    private Context context;
    private IOnItemClickListener listener;

    public RoomAdapter(Context context, IOnItemClickListener listener) {
        this(context, new LinkedList<Room>(), listener);
    }


    public RoomAdapter(Context context, List<Room> rooms, IOnItemClickListener listener) {
        this.context = context;
        this.rooms = rooms;
        this.listener = listener;
        insertRooms();
    }

    public void updateData(List<Room> data) {
        rooms = new LinkedList<>(data);

        notifyDataSetChanged();
    }

    public void updateRoom(int index) {
        notifyItemChanged(index);
    }

    @Override
    public RoomViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        return new RoomViewholder(view, listener);
    }

    @Override
    public void onBindViewHolder(final RoomViewholder holder, final int position) {
        if (rooms.size() != 0) {

            holder.roomTitle.setText(rooms.get(position).getLabel());

            setLectureTitleAndInformation(holder, position);

            holder.setOnItemClickListener(position);
        }
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    private void setLectureTitleAndInformation(RoomViewholder holder, int position) {

        if (rooms.get(position).hasCurrentLecture()) {
            roomTaken(holder, position);
        } else {
            roomFree(holder, position);
        }
    }

    private void roomTaken(RoomViewholder holder, int position) {

        holder.lectureTitle.setText(rooms.get(position).getFirstLecture().getTitle());

        holder.roomInformation.setText(context.getResources()
                .getString(R.string.taken, TimeManager.getTimeTillEndOfLecture(rooms.get(position).getFirstLecture())));

        holder.circleImageView.setImageResource(ic_closed);
    }

    private void roomFree(RoomViewholder holder, int position) {

        holder.lectureTitle.setText("");

        holder.roomInformation.setText(context.getResources().getString(R.string.free, TimeManager.getStringFromTimeTillLectureOrMidnight(rooms.get(position).getFirstLecture())));

        holder.circleImageView.setImageResource(ic_room);
    }

    private void clear() {
        rooms.clear();

        notifyDataSetChanged();
    }

    private void insertRooms() {

        RoomService.getRoomManager(this)
                .withNetworkInterface(SupportApiAdapter.getSupportApiAdapter())
                .withDataManager(DataManager.getInstance())
                .update();
    }

    public int getTimeSlot() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        return preferences.getInt(RoomsActivity.TIMESLOT, 1);
    }

    public void refreshData() {
        clear();

        insertRooms();
    }

    public void getLecturesForRoom(Room room) {

        LectureService.getLectures(this)
                .withNetworkInterface(SupportApiAdapter.getSupportApiAdapter())
                .addRoom(room)
                .withPeroid(getTimeSlot())
                .start();
    }


    public void getFullLectureFromLecture(Room room, int index) {
        UpdateLectureService.getInstance(this)
                .withNetworkInterface(SupportApiAdapter.getSupportApiAdapter())
                .fromRoom(room)
                .onIndex(index)
                .start();
    }
}
