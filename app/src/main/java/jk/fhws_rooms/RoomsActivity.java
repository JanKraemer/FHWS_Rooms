package jk.fhws_rooms;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class RoomsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle("Räume SHL");
        toolBar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolBar);
    }

}
