package jk.fhws_rooms.Activitiy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import jk.fhws_rooms.Model.DataManager;
import jk.fhws_rooms.Model.Room;
import jk.fhws_rooms.R;


public class LectureDetailView extends AppCompatActivity {

    public static String OBJECTID = "POSTION";
    private Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_detail_view);
        if (getIntent().getExtras() != null) {
            int position = Integer.valueOf(getIntent().getExtras().getString(OBJECTID));
            room = DataManager.getInstance().getRoom(position);
        }
        initToolBarWithTitle( );
    }

    private void initToolBarWithTitle( ) {
        Toolbar toolbar = ( Toolbar ) findViewById( R.id.toolbar );
        setSupportActionBar(toolbar);
        getSupportActionBar( ).setTitle( room.getLabel( ) );
        getSupportActionBar( ).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
