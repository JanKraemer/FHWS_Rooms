package jk.fhws_rooms.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
    CardView cardView;
    TextView roomTitle;
    TextView lectureTitle;
    TextView roomInformation;
    IOnItemClickListener listener;

    public RoomViewholder(IOnItemClickListener listener, View itemView ) {
        super(itemView);
        this.listener = listener;
        this.cardView = (CardView) itemView.findViewById(R.id.room_cardview);
        this.circleImageView = (CircleImageView) itemView.findViewById(R.id.room_image);
        this.lectureTitle = (TextView) itemView.findViewById(R.id.room_subtitle);
        this.roomTitle = (TextView) itemView.findViewById(R.id.room_title);
        this.roomInformation = (TextView) itemView.findViewById(R.id.room_information);
    }

    void setOnClickListener(final int position){
       final View.OnClickListener onClickListener= new View.OnClickListener(){
            @Override
            public void onClick(final View v){
            listener.OnClick(position);
            }
        };
        cardView.setOnClickListener(onClickListener);
    }
}
