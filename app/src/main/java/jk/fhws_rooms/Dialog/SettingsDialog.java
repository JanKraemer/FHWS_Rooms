package jk.fhws_rooms.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import jk.fhws_rooms.Activitiy.RoomsActivity;
import jk.fhws_rooms.Adapter.RoomAdapter;
import jk.fhws_rooms.R;

/**
 * Created by Jan on 22.04.2017.
 */

public class SettingsDialog {

    public static SettingsDialogBuilder Builder( Context context ) {

        return new SettingsDialogBuilder(context);
    }

    private SettingsDialog( ) { }

    public static class SettingsDialogBuilder {

        protected Context context;
        protected TextView title;
        protected Spinner spinner;
        protected int timeSlot;
        protected int oldTimeSlot;
        protected RoomAdapter adapter;

        protected SettingsDialogBuilder( Context context ){
            this.context = context;
        }

        public SettingsDialogBuilder refreshAdapter(RoomAdapter adapter){
            this.adapter = adapter;

            return this;
        }

        public SettingsDialogBuilder withOldTimeSlot( int oldTimeSlot ){
            this.oldTimeSlot = oldTimeSlot;

            return this;
        }

        public void show( ) {
            final Dialog dialog = new Dialog(context);

            initDialogContent( dialog );

            setSpinner( );

            setCancelButton( dialog );

            setAgreeButton( dialog );

            dialog.show( );
        }

        private void setCancelButton( final Dialog dialog ){
            Button cancel = (Button) dialog.findViewById(R.id.dialog_settings_cancel);

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        private void setAgreeButton( final Dialog dialog ) {
            Button agree = (Button) dialog.findViewById(R.id.dialog_settings_okay);

            agree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( oldTimeSlot != timeSlot ) {
                        addNewTimeSlot( );
                        adapter.refreshData( );
                    }
                    dialog.dismiss();
                }
            });
        }

        private void initDialogContent( final Dialog dialog ){
            dialog.setContentView(R.layout.settings_dialog);

            title = (TextView)dialog.findViewById(R.id.settings_title);

            spinner = (Spinner) dialog.findViewById(R.id.checkbox_normal);
        }

        private void setSpinner ( ) {

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( context,
                    R.array.planets_array, android.R.layout.simple_spinner_item);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    timeSlot = Integer.valueOf((String) parent.getItemAtPosition(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    timeSlot = 1;
                }
            });

        }

        private void addNewTimeSlot ( ) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

            SharedPreferences.Editor editor = preferences.edit();

            editor.putInt(RoomsActivity.TIMESLOT,timeSlot);

            editor.apply();
        }
    }
}
