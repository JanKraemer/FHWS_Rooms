package jk.fhws_rooms.Activitiy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import jk.fhws_rooms.Adapter.LectureAdapter;
import jk.fhws_rooms.Helper.TimeManager;
import jk.fhws_rooms.Model.DataManager;
import jk.fhws_rooms.Model.Lecture;
import jk.fhws_rooms.Model.Room;
import jk.fhws_rooms.Network.SupportApiAdapter;
import jk.fhws_rooms.Network.UpdateLectureService;
import jk.fhws_rooms.R;


public class RoomDetailView extends AppCompatActivity {

    public static String OBJECTID = "POSTION";
    private Room room;
    private RecyclerView recyclerView;
    private LectureAdapter adapter;
    private LinearLayoutManager layoutManager;
    private TextView subtitle;
    private TextView vorlesung;
    private TextView professor;
    private TextView time;
    private TextView studiengang;
    private TextView next_Vorlesungen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_room_detail_view);
        if ( getIntent( ).getExtras( ) != null ) {
            int position = Integer.valueOf( getIntent( ).getExtras( ).getString( OBJECTID ) );
            room = DataManager.getInstance( ).getRoom( position );
        }
        initToolBarWithTitle( );

        initRecyclerView( );

        updateDate( );

        initTextView( );

        fillTextViewsWithLectureData ( room.getFirstLecture( ) );

    }

    private void initToolBarWithTitle( ) {
        Toolbar toolbar = ( Toolbar ) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar( ).setTitle( room.getLabel( ) );
        getSupportActionBar( ).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        int id = item.getItemId( );
        if ( id == android.R.id.home ) {
            finish( );
            return true;
        }
        return super.onOptionsItemSelected( item );
    }


    public void updateDate( ){
        UpdateLectureService.getInstance( ).
                withNetworkInterface( SupportApiAdapter.getSupportApiAdapter( ) )
                .withAdapter( adapter )
                .updateLectures( room.getLectures( ) )
                .start( );
    }

    private void initTextView( ){
        subtitle = ( TextView ) findViewById(R.id.subtitle);

        vorlesung = ( TextView ) findViewById(R.id.vorlesung_title);

        professor = ( TextView ) findViewById(R.id.vorlesung_professor);

        studiengang = ( TextView ) findViewById(R.id.vorlesung_studiengang);

        time = ( TextView ) findViewById(R.id.vorlesung_time);

        next_Vorlesungen = ( TextView ) findViewById(R.id.vorlesung_next);
    }

    private void fillTextViewsWithLectureData( Lecture lecture ){
        subtitle.setText(getString(R.string.vorlesung_subtitle));

        next_Vorlesungen.setText(getString(R.string.vorlesung_next));

        if( room.hasCurrentLecture( ) ) {
            currentLecture( lecture );

        }else{
            freeRoom( lecture );

        }
    }

    private String getTimeString ( long start, long end ){
        String startTime = TimeManager.timeAsString( start );

        String endTime = TimeManager.timeAsString( end );

        return  getString( R.string.time_slot, startTime , endTime );
    }

    private void currentLecture ( Lecture lecture ){
        vorlesung.setText(lecture.getTitle());

        professor.setText(lecture.getLecturers().get(0).getFullName());

        studiengang.setText(getString(R.string.studiengang, lecture.getStudiengang(), lecture.getSemester()));

        time.setText(getTimeString( lecture.getFullLecture( ).getStartdate( ), lecture.getFullLecture( ).getEnddate( ) ) );
    }

    private void freeRoom( Lecture lecture ) {
        vorlesung.setText(getString(R.string.keine_vorlesung));

        professor.setText(getString(R.string.strich));

        studiengang.setText(getString(R.string.strich));

        time.setText(getTimeString(TimeManager.now(), TimeManager.getTimeTillLectureOrMidnight(lecture)));
    }

    private void initRecyclerView( ){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_rooms);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        adapter =new LectureAdapter(this, room.getLectures( ) );

        recyclerView.setAdapter(adapter);
    }
}
