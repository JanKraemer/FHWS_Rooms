package jk.fhws_rooms.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;
import jk.fhws_rooms.R;

/**
 * Created by Jan on 11.04.2017.
 */

public class RoomViewholder extends RecyclerView.ViewHolder {

    CircleImageView circleImageView;
    RelativeLayout background;
    TextView roomTitle;
    TextView lectureTitle;
    TextView roomInformation;
    IOnItemClickListener listener;

    public RoomViewholder( View itemView, IOnItemClickListener listener ) {

        super(itemView);

        this.listener = listener;

        this.circleImageView = (CircleImageView) itemView.findViewById(R.id.room_image);

        this.lectureTitle = (TextView) itemView.findViewById(R.id.room_subtitle);

        this.roomTitle = (TextView) itemView.findViewById(R.id.room_title);

        this.roomInformation = (TextView) itemView.findViewById(R.id.room_information);

        this.background = (RelativeLayout) itemView.findViewById(R.id.card_background);
    }

    void setOnItemClickListener(final int position ){
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(position);
            }
        };
        circleImageView.setOnClickListener(onClickListener);
    }

}
