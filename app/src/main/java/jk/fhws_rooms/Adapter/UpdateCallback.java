package jk.fhws_rooms.Adapter;

import android.support.v7.util.DiffUtil;

import java.util.List;

import jk.fhws_rooms.Model.Room;

/**
 * Created by Jan on 11.04.2017.
 */

public class UpdateCallback extends DiffUtil.Callback {

    private List<Room> rooms;
    private List<Room> data;

    public UpdateCallback(List<Room> rooms, List<Room> data) {
        this.rooms = rooms;
        this.data = data;
    }

    @Override
    public int getOldListSize() {
        return rooms.size();
    }

    @Override
    public int getNewListSize() {
        return data.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return rooms.get(oldItemPosition).hashCode() == data.get(newItemPosition).hashCode();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return rooms.get(oldItemPosition).hashCode() == data.get(newItemPosition).hashCode();
    }
}
