package jk.fhws_rooms.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import jk.fhws_rooms.R;

public class LectureViewholder extends RecyclerView.ViewHolder {

    CardView cardView;
    CircleImageView circleImageView;
    TextView lectureTitle;
    TextView lectureTime;

    public LectureViewholder(View itemView) {
        super(itemView);

        this.cardView = (CardView) itemView.findViewById(R.id.room_cardview);

        this.circleImageView = (CircleImageView) itemView.findViewById(R.id.card_image);

        this.lectureTitle = (TextView) itemView.findViewById(R.id.card_title);

        this.lectureTime = (TextView) itemView.findViewById(R.id.card_subtitle);
    }

}
