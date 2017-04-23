package jk.fhws_rooms.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import jk.fhws_rooms.R;

/**
 * Created by Jan on 11.04.2017.
 */

public class RoomViewholder extends RecyclerView.ViewHolder {

    CardView cardView;
    CircleImageView circleImageView;
    TextView roomTitle;
    TextView lectureTitle;
    TextView roomInformation;
    IOnItemClickListener listener;

    public RoomViewholder( View itemView, IOnItemClickListener listener ) {

        super(itemView);

        this.listener = listener;

        this.cardView = (CardView) itemView.findViewById(R.id.room_cardview);

        this.circleImageView = (CircleImageView) itemView.findViewById(R.id.card_image);

        this.lectureTitle = (TextView) itemView.findViewById(R.id.card_subtitle);

        this.roomTitle = (TextView) itemView.findViewById(R.id.card_title);

        this.roomInformation = (TextView) itemView.findViewById(R.id.card_information);
    }

    void setOnItemClickListener(final int position ){
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(position);
            }
        };
        cardView.setOnClickListener(onClickListener);
    }

}
