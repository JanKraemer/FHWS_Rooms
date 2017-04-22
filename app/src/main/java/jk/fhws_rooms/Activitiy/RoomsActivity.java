package jk.fhws_rooms.Activitiy;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import jk.fhws_rooms.Adapter.IOnItemClickListener;
import jk.fhws_rooms.Adapter.RoomAdapter;
import jk.fhws_rooms.Dialog.SettingsDialog;
import jk.fhws_rooms.R;

public class RoomsActivity extends AppCompatActivity {

    public static String TIMESLOT = "TIMESLOT";

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager layoutManager;
    private RoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rooms);

        initToolbar( );

        initRecyclerView( );

        initRefreshLayout( );
    }


    private void initToolbar( ) {
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);

        toolBar.setTitle(getString(R.string.toolbar_title));

        toolBar.setTitleTextColor( ContextCompat.getColor(this, R.color.colorBlack));

        setSupportActionBar(toolBar);
    }

    private void initRecyclerView( ){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_rooms);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        adapter =new RoomAdapter(getApplicationContext(), new IOnItemClickListener() {
            @Override
            public void OnClick(int position) {
                Log.d("tag","Bild"+position);
            }
        });

        adapter.insertData( );

        recyclerView.setAdapter(adapter);
    }

    private void initRefreshLayout( ) {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_to_refresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear( );

                adapter.insertData( );

                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_name) {
            SettingsDialog.Builder(this).show();
            adapter.insertData( );
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.room_menu, menu);

        return true;
    }

}
