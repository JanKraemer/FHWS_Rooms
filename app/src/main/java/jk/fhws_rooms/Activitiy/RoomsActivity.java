package jk.fhws_rooms.Activitiy;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import jk.fhws_rooms.Adapter.IOnItemClickListener;
import jk.fhws_rooms.Adapter.RecyclerItemClickListener;
import jk.fhws_rooms.Adapter.RoomAdapter;
import jk.fhws_rooms.Adapter.RoomViewholder;
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

        initToolbar( );

        initRecyclerView( );

        RoomManager
                .getRoomManager( )
                .with( SupportApiAdapter.getSupportApiAdapter( ) )
                .update( ( RoomAdapter ) recyclerView.getAdapter( ) );
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

        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Log.d("Tag", "itemclick"+position);
/*                       if (adapter.getSelectedItems().get(position, true)) {

                            adapter.selectItem(( RoomViewholder) recyclerView.getChildViewHolder(view));
                        }
                        else {

                           adapter.unselectItem(( RoomViewholder) recyclerView.getChildViewHolder(view));
                        }*/
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
         /*               if (adapter.getSelectedItems().get(position, false)) {

                            adapter.selectItem(( RoomViewholder) recyclerView.getChildViewHolder(view));

                        }
                        else {

                            adapter.unselectItem(( RoomViewholder) recyclerView.getChildViewHolder(view));
                        }*/
                    }
                })
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_name) {
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

}
