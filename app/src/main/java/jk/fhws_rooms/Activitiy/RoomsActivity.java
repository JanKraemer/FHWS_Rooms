package jk.fhws_rooms.Activitiy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import jk.fhws_rooms.Adapter.IOnItemClickListener;
import jk.fhws_rooms.Adapter.RoomAdapter;
import jk.fhws_rooms.Network.RoomManager;
import jk.fhws_rooms.Network.SupportApiAdapter;
import jk.fhws_rooms.R;

public class RoomsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rooms);

        initToolbar();

        initRecyclerView();

        RoomManager
                .getRoomManager( )
                .with( SupportApiAdapter.getSupportApiAdapter( ) )
                .update( ( RoomAdapter ) recyclerView.getAdapter( ) );
    }


    private void initToolbar()
    {
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);

        toolBar.setTitle("Räume SHL");

        toolBar.setNavigationIcon(R.mipmap.ic_launcher);

        setSupportActionBar(toolBar);
    }

    private void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_rooms);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        adapter =new RoomAdapter(getApplicationContext(),new IOnItemClickListener() {
            @Override
            public void OnClick(int position) {
                Log.d("TAG", "onItemClick" + position);
            }
        });
        recyclerView.setAdapter(adapter);
    }


}
