package com.vf.admin.vf_rep;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Double.parseDouble;
import com.vf.admin.vf_rep.R;
/**
 * Created by Donavan on 2017/01/16.
 */

public class RepStoreDetailsActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";

    private Spinner  spinner2;

    private TextView tvDisplayTime;
    private TimePicker timePicker1;
    private Button btnChangeTime;
    private TextView tvDisplayTime2;
    private TimePicker timePicker2;
    private Button btnChangeTime2;

    private int hour;
    private int minute;
    private int hour2;
    private int minute2;

    static final int TIME_DIALOG_ID = 999;
    static final int TIME_DIALOG_ID2 = 888;



    GsonBuilder gsonb = new GsonBuilder();
    Gson mGson = gsonb.create();



    public boolean saveJsonLocalStore(String jsonStore, String yourSettingName)
    {
        SharedPreferences mSettings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor mEditor = mSettings.edit();
        try {

            mEditor.putString(yourSettingName, jsonStore);
            mEditor.commit();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public Store readJsonLocalStore(String yourSettingName)
    {
        SharedPreferences mSettings = getSharedPreferences(PREFS_NAME, 0);
        String loadValue = mSettings.getString(yourSettingName, "");
        Store c = mGson.fromJson(loadValue, Store.class);

        return c;
    }


    @Override
    public void onBackPressed() {     }      @Override  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rep_storedetails_layout);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        addItemsOnSpinner2();

        setCurrentTimeOnView();
        addListenerOnButton();
        setCurrentTimeOnView2();
        addListenerOnButton2();




    }

    public void  setCurrentTimeOnView2() {
        tvDisplayTime2 = (TextView) findViewById(R.id.tvTime2);
        timePicker2 = (TimePicker) findViewById(R.id.timePicker2);

        final Calendar B = Calendar.getInstance();
        hour2 = B.get(Calendar.HOUR_OF_DAY);
        minute2 = B.get(Calendar.MINUTE);

        // set current time into textview;
        tvDisplayTime2.setText(
                new StringBuilder().append(pad2(hour2))
                .append(":").append(pad2(minute2)));

        // set current time into timepicker
        timePicker2.setCurrentHour(hour2);
        timePicker2.setCurrentMinute(minute2);


    }

     //date and time picket open time starts here
    // display current time
    public void setCurrentTimeOnView() {

        tvDisplayTime = (TextView) findViewById(R.id.tvTime);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);



        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        // set current time into textview
        tvDisplayTime.setText(
                new StringBuilder().append(pad(hour))
                        .append(":").append(pad(minute)));

        // set current time into timepicker
        timePicker1.setCurrentHour(hour);
        timePicker1.setCurrentMinute(minute);


    }

    public void addListenerOnButton2() {

        btnChangeTime2 = (Button) findViewById(R.id.btnChangeTime2);
        btnChangeTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID2);
            }
        });
    }

    public void addListenerOnButton() {

        btnChangeTime = (Button) findViewById(R.id.btnChangeTime);

        btnChangeTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }

        });

    }



    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(this,
                        timePickerListener, hour, minute,false);



            case TIME_DIALOG_ID2:
                // set time picker as current time
                return new TimePickerDialog(this, timePickerListener2,hour2,minute2,false);



        }
        return null;
    }


    private TimePickerDialog.OnTimeSetListener timePickerListener2 =
            new TimePickerDialog.OnTimeSetListener()

            {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute)

                {


                    hour2 = selectedHour;
                    minute2 = selectedMinute;

                    // set current time into textview
                    tvDisplayTime2.setText(new StringBuilder().append(pad2(hour2))
                            .append(":" ).append(pad2(minute2)));

                    // set current time into timepicker
                    timePicker2.setCurrentHour(hour2);
                    timePicker2.setCurrentMinute(minute2);




                }
            };





    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener()

            {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute)

                {


                    hour = selectedHour;
                    minute = selectedMinute;

                    // set current time into textview
                    tvDisplayTime.setText(new StringBuilder().append(pad(hour))
                            .append(":").append(pad(minute)));

                    // set current time into timepicker
                    timePicker1.setCurrentHour(hour);
                    timePicker1.setCurrentMinute(minute);




                }
            };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    private static String pad2(int B) {
        if (B >= 10)
            return String.valueOf(B);
        else
            return "0" + String.valueOf(B);
    }

    //date and time picket open time ends here

    //date and time picket close time starts here




    // add items into spinner dynamically
    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add(0, "Choose Territory");
        list.add(1, "Central Rift");
        list.add(2, "Coast Mainland");
        list.add(3, "Island and South Coast");
        list.add(4, "Kitui");
        list.add(5, "Lake");
        list.add(6, "Machakos");
        list.add(7, "Meru");
        list.add(8, "Nairobi Central");
        list.add(9, "Nairobi East");
        list.add(10, "Nairobi Metro");
        list.add(11, "Nairobi North");
        list.add(12, "Nairobi Outer");
        list.add(13, "Nairobi South");
        list.add(14, "Nairobi West");
        list.add(15, "North Coast");
        list.add(16, "North Rift");
        list.add(17, "Nyanza");
        list.add(18, "Nyeri");
        list.add(19, "South Nyanza");
        list.add(20, "South Rift");
        list.add(21, "Thika");
        list.add(22, "Western");
        spinner2.setPrompt("Choose Territory");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
               R.layout.spinnerstyle, list);
        dataAdapter.setDropDownViewResource(R.layout.spinnerstyledrop);
        spinner2.setAdapter(dataAdapter);
    }

    // add items into spinner dynamically




//This is where store is saved
    public void stockinput(View view) {



        AndroidStore s = new AndroidStore();

        EditText storeName = (EditText) findViewById(R.id.storeName);
        s.StoreName = storeName.getText().toString();

        s.UserName = Local.Get(getApplicationContext(), "UserName");

        EditText StoreName = (EditText) findViewById(R.id.storeName);
        s.StoreName = StoreName.getText().toString();
        EditText ContactPerson = (EditText) findViewById(R.id.contactPerson);
        s.ContactPerson = ContactPerson.getText().toString();
        EditText ContactEmail = (EditText) findViewById(R.id.contactEmail);
        s.ContactEmail = ContactEmail.getText().toString();
        EditText ContactPhone = (EditText) findViewById(R.id.contactPhone);
        s.ContactPhone = ContactPhone.getText().toString();
        EditText AddressLine1 = (EditText) findViewById(R.id.addressLine1);
        s.AddressLine1 = AddressLine1.getText().toString();
        EditText AddressLine2 = (EditText) findViewById(R.id.addressLine2);
        s.AddressLine2 = AddressLine2.getText().toString();
        EditText TownCity = (EditText) findViewById(R.id.townCity);
        s.TownCity = TownCity.getText().toString();

        Spinner regionSpinner = (Spinner)findViewById(R.id.spinner2);
        s.setTerritoryName(regionSpinner.getSelectedItem().toString());

        TextView openingTime = (TextView) findViewById(R.id.tvTime);
        s.setOpeningTime( openingTime.getText().toString());

        TextView closingTime = (TextView) findViewById(R.id.tvTime2);
        s.setClosingTime( closingTime.getText().toString());

//        EditText TerritoryID = (EditText) findViewById(R.id.regionID);
//        s.TerritoryID = TerritoryID.getText().toString();
//        EditText OpeningTime = (EditText) findViewById(R.id.openingTime);
//        s.OpeningTime = OpeningTime.getText().toString();
//        EditText ClosingTime = (EditText) findViewById(R.id.closingTime);
//        s.ClosingTime = ClosingTime.getText().toString();
//
//        EditText BrandID = (EditText) findViewById(R.id.brandID);
//        s.BrandID = BrandID.getText().toString();
//        EditText OutletTypeID = (EditText) findViewById(R.id.outletTypeID);
//        s.OutletTypeID = OutletTypeID.getText().toString();
//        EditText TierTypeID = (EditText) findViewById(R.id.tierTypeID);
//        s.TierTypeID = TierTypeID.getText().toString();

        EditText GpsLat = (EditText) findViewById(R.id.gpsLat);
        if(GpsLat.getText().toString().equals("")){}
        else{
        s.GpsLat =  parseDouble(GpsLat.getText().toString());}
        EditText GpsLng = (EditText) findViewById(R.id.gpsLng);
        if(GpsLng.getText().toString().equals("")){}
        else{
            s.GpsLng =  parseDouble(GpsLng.getText().toString());}

        String jsonStore = JsonUtil.toJSonAndroidStore(s);
        saveJsonLocalStore(jsonStore, "Store1");

        //Move to next wizard step
        Intent intent = new Intent(RepStoreDetailsActivity.this, RepExtraDetailsActivity.class );
        startActivity(intent); finish();
    }

    //this is the main menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        if (id == R.id.homebutton) {

            Intent intent = new Intent(RepStoreDetailsActivity.this, MainActivity.class );

            startActivity(intent); finish();

            // return true;
        }



        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,Login.class));
        }

        if (id == R.id.backbutton){
            startActivity(new Intent(this,RepHomeActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }



}












