package jk.fhws_rooms.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import jk.fhws_rooms.adapters.IOnItemClickListener;
import jk.fhws_rooms.adapters.RoomAdapter;
import jk.fhws_rooms.dialog.SettingsDialog;
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

        initToolbar();

        initRecyclerView();

        initRefreshLayout();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initToolbar() {
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);

        toolBar.setTitle(getString(R.string.toolbar_title));

        setSupportActionBar(toolBar);
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_rooms);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        adapter = new RoomAdapter(this, new IOnItemClickListener() {
            @Override
            public void OnClick(int position) {
                startRoomDetailActivity(position);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void initRefreshLayout() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_to_refresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.refreshData();

                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_name) {
            SettingsDialog.Builder(this)
                    .refreshAdapter(adapter)
                    .withOldTimeSlot(adapter.getTimeSlot())
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.room_menu, menu);

        return true;
    }

    private void startRoomDetailActivity(int position) {
        Intent intent = new Intent(RoomsActivity.this, RoomDetailView.class);

        intent.putExtra(RoomDetailView.OBJECTID, String.valueOf(position));

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

}
