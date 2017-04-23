package jk.fhws_rooms.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jk.fhws_rooms.Helper.TimeManager;
import jk.fhws_rooms.Model.FullLecture;
import jk.fhws_rooms.Model.Lecture;
import jk.fhws_rooms.R;

public class LectureAdapter extends RecyclerView.Adapter<LectureViewholder> {

    private List<Lecture> lectures;
    private Context context;

    public LectureAdapter(Context context, List<Lecture> lectures){
        this.context = context;

        this.lectures = lectures;
    }

    @Override
    public LectureViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout,parent,false);

        return new LectureViewholder(view);
    }

    @Override
    public void onBindViewHolder(LectureViewholder holder, int position) {
        if ( hasLectures( ) ){
            holder.lectureTitle.setText(lectures.get( position ).getTitle( ));

            holder.lectureTime.setText(getTimeStringForLecture(lectures.get(position).getFullLecture()));

            holder.circleImageView.setImageResource(R.mipmap.ic_star);
        }
    }

    @Override
    public int getItemCount() {
        return lectures != null ? lectures.size() : 0;
    }

    private boolean hasLectures(  ){
        return lectures != null && lectures.size() > 0;

    }

    private String getTimeStringForLecture( FullLecture lecture ){
        String start = TimeManager.timeAsString(lecture.getStartdate());

        String end = TimeManager.timeAsString(lecture.getEnddate());

        String day = TimeManager.getDayFromTime( lecture.getStartdate());

        return context.getString(R.string.vorlesungszeit,day,start,end);
    }
}
