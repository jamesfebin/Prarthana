package com.bangalorediocese.mgocsm.lentapp.activities;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bangalorediocese.mgocsm.lentapp.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

public class PrayerActivity extends ActionBarActivity {

    private ListView prayerList;
    private TextView currentPrayer;
    private ArrayAdapter<String> listAdapter ;
    private MediaPlayer mediaPlayer;
    private String prayerfile;
    private final static String EXP_PATH = "/Android/obb/";

    Map<String, Integer> map = new HashMap<String, Integer>();

    static String[] getAPKExpansionFiles(Context ctx, int mainVersion,
                                         int patchVersion) {
        String packageName = ctx.getPackageName();
        Vector<String> ret = new Vector<String>();
        if (Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED)) {
            // Build the full path to the app's expansion files
            File root = Environment.getExternalStorageDirectory();
            File expPath = new File(root.toString() + EXP_PATH + packageName);

            // Check that expansion file path exists
            if (expPath.exists()) {
                if ( mainVersion > 0 ) {
                    String strMainPath = expPath + File.separator + "main." +
                            mainVersion + "." + packageName + ".obb";
                    File main = new File(strMainPath);
                    if ( main.isFile() ) {
                        ret.add(strMainPath);
                    }
                }
                if ( patchVersion > 0 ) {
                    String strPatchPath = expPath + File.separator + "patch." +
                            mainVersion + "." + packageName + ".obb";
                    File main = new File(strPatchPath);
                    if ( main.isFile() ) {
                        ret.add(strPatchPath);
                    }
                }
            }
        }
        String[] retArray = new String[ret.size()];
        ret.toArray(retArray);
        return retArray;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prayerList = (ListView) findViewById(R.id.prayerlist);
        currentPrayer = (TextView) findViewById(R.id.currentPrayer);
        String[] planets = new String[] { "MONDAY MORNING", "MONDAY EVENING", "TUESDAY MORNING", "TUESDAY EVENING",
                "WEDNESDAY MORNING", "WEDNESDAY EVENING", "THURSDAY MORNING", "THURSDAY EVENING","FRIDAY MORNING","FRIDAY EVENING"};

/*
        map.put("monmor",R.raw.monmor);
        map.put("moneve",R.raw.moneve);
        map.put("tuemor",R.raw.tuemor);
        map.put("tueeve",R.raw.tueeve);
        map.put("wedmor",R.raw.wedmor);
        map.put("wedeve",R.raw.wedeve);
        map.put("thumor",R.raw.thumor);
        map.put("thueve",R.raw.thueve);
        map.put("frimor",R.raw.frimor);
        map.put("frieve",R.raw.frieve);
        */

        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll( Arrays.asList(planets) );

        listAdapter = new ArrayAdapter<String>(this, R.layout.list_view_item, planetList);

        prayerList.setAdapter( listAdapter );

        //  mediaPlayer = MediaPlayer.create(this,R.raw.monmor);

        String weekDay;
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        Calendar calendar = Calendar.getInstance();
        weekDay = dayFormat.format(calendar.getTime());

        String timeString="";
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour == 0) {
            timeString =  "Morning";
        } else if (hour < 12) {
            timeString =  "Morning";
        } else if (hour == 12) {
            timeString = "Evening";
        } else {
            timeString = "Evening";
        }
        weekDay = weekDay.toUpperCase();

        if (weekDay.contentEquals("SATURDAY")|| weekDay.contentEquals("SUNDAY"))
        {
            currentPrayer.setText("MONDAY MORNING PRAYER");
        }
        else {
            currentPrayer.setText(weekDay + " " + timeString.toUpperCase() + " PRAYER");
            weekDay = weekDay.toLowerCase();
            prayerfile = weekDay.substring(0,2);
            timeString = timeString.toLowerCase();
            prayerfile = prayerfile +  timeString.substring(0,2);
            Log.i("P", prayerfile);
            mediaPlayer = MediaPlayer.create(this, map.get(prayerfile));
            mediaPlayer.start();

        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prayer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
