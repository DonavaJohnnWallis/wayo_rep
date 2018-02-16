package com.vf.admin.vf_rep;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.vf.admin.vf_rep.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.R.attr.x;

public class RepInfoActivity extends AppCompatActivity
{



    GridView gridView;
    RepGridViewCustomImages grisViewCustomeAdapter;
Integer mGlobalStoreID;

    protected String parseTimeForDisplay(String dateTimeWithT)
    {

        dateTimeWithT = dateTimeWithT.replace('T',' ');

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");

        try {
            calendar.setTime(simpleDateFormat.parse(dateTimeWithT));

            String dateToShow = simpleTimeFormat.format(calendar.getTime());
            return dateToShow;

        } catch (ParseException e) {
            e.printStackTrace();
            return dateTimeWithT;
        }


    }
    @Override
    public void onBackPressed() {     }      @Override  protected void onCreate(Bundle savedInstanceState)
    {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.rep_storeinfo_layout);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


       /* gridView=(GridView)findViewById(R.id.gridViewCustom4);
        // Create the Custom Adapter Object
        grisViewCustomeAdapter = new RepGridViewCustomImages(this);
        // Set the Adapter to GridView
        gridView.setAdapter(grisViewCustomeAdapter);*/

            Intent me = getIntent();

            mGlobalStoreID = me.getIntExtra("ID",0);

            String strStoreName = me.getStringExtra("StoreNameURN");
            if(strStoreName != null)
            {
                TextView StoreName = (TextView) findViewById(R.id.txtStoreName);
                StoreName.setText(me.getStringExtra("StoreNameURN"));
            }
//            String strURN = me.getStringExtra("URN");
//            if(strURN != null)
//            {
//                TextView URN = (TextView) findViewById(R.id.txtURN);
//                URN.setText(me.getStringExtra("URN"));
//            }
            String strCurrentPhase = me.getStringExtra("CurrentPhase");
            if(strCurrentPhase != null)
            {
                TextView CurrentPhase = (TextView) findViewById(R.id.txtCurrentPhase);
                CurrentPhase.setText(me.getStringExtra("CurrentPhase"));
            }
            String strRegionName = me.getStringExtra("RegionName");
            if(strRegionName != null)
            {
                TextView RegionName = (TextView) findViewById(R.id.txtRegionName);
                RegionName.setText(me.getStringExtra("RegionName"));
            }
            String strBrandName = me.getStringExtra("BrandName");
            if(strBrandName != null)
            {
                TextView BrandName = (TextView) findViewById(R.id.txtBrandName);
                BrandName.setText(me.getStringExtra("BrandName"));
            }
            String strTierTypeName = me.getStringExtra("TierTypeName");
            if(strTierTypeName != null)
            {
                TextView TierTypeName = (TextView) findViewById(R.id.txtTierTypeName);
                TierTypeName.setText(me.getStringExtra("TierTypeName"));
            }
            String strOutletTypeName = me.getStringExtra("OutletTypeName");
            if(strOutletTypeName != null)
            {
                TextView OutletTypeName = (TextView) findViewById(R.id.txtOutletTypeName);
                OutletTypeName.setText(me.getStringExtra("OutletTypeName"));
            }
            String strContactPerson = me.getStringExtra("ContactPerson");
            if(strContactPerson != null)
            {
                TextView ContactPerson = (TextView) findViewById(R.id.txtContactPerson);
                ContactPerson.setText(me.getStringExtra("ContactPerson"));
            }
            String strContactEmail = me.getStringExtra("ContactEmail");
            if(strContactEmail != null)
            {
                TextView ContactEmail = (TextView) findViewById(R.id.txtContactEmail);
                ContactEmail.setText(me.getStringExtra("ContactEmail"));
            }
            String strContactPhone = me.getStringExtra("ContactPhone");
            if(strContactPhone != null)
            {
                TextView ContactPhone = (TextView) findViewById(R.id.txtContactPhone);
                ContactPhone.setText(me.getStringExtra("ContactPhone"));
            }
            String strOpeningTime = me.getStringExtra("OpeningTime");
            if(strOpeningTime != null)
            {
                TextView OpeningTime = (TextView) findViewById(R.id.txtOpeningTime);
                OpeningTime.setText(parseTimeForDisplay(me.getStringExtra("OpeningTime")));
            }
            String strClosingTime = me.getStringExtra("ClosingTime");
            if(strClosingTime != null)
            {
                TextView ClosingTime = (TextView) findViewById(R.id.txtClosingTime);
                ClosingTime.setText(parseTimeForDisplay(me.getStringExtra("ClosingTime")));
            }




            String strAddressLine1 = me.getStringExtra("AddressLine1");
            if(strAddressLine1 != null)
            {
                TextView AddressLine1 = (TextView) findViewById(R.id.txtAddressLine1);
                AddressLine1.setText(me.getStringExtra("AddressLine1"));
            }
            String strAddressLine2 = me.getStringExtra("AddressLine2");
            if(strAddressLine2 != null)
            {
                TextView AddressLine2 = (TextView) findViewById(R.id.txtAddressLine2);
                AddressLine2.setText(me.getStringExtra("AddressLine2"));
            }
            String strTownCity = me.getStringExtra("TownCity");
            if(strTownCity != null)
            {
                TextView TownCity = (TextView) findViewById(R.id.txtTownCity);
                TownCity.setText(me.getStringExtra("TownCity"));
            }
            String strRepFirstNameSurname = me.getStringExtra("RepFirstNameSurname");
            if(strRepFirstNameSurname != null)
            {
                TextView RepFirstNameSurname = (TextView) findViewById(R.id.txtRepFirstNameSurname);
                RepFirstNameSurname.setText(me.getStringExtra("RepFirstNameSurname"));
            }
            String strRepJobTitle = me.getStringExtra("RepJobTitle");
            if(strRepJobTitle != null)
            {
                TextView RepJobTitle = (TextView) findViewById(R.id.txtRepJobTitle);
                RepJobTitle.setText(me.getStringExtra("RepJobTitle"));
            }
            String strRepCellNo = me.getStringExtra("RepCellNo");
            if(strRepCellNo != null)
            {
                TextView RepCellNo = (TextView) findViewById(R.id.txtRepCellNo);
                RepCellNo.setText(me.getStringExtra("RepCellNo"));
            }
            String strTssFirstNameSurname = me.getStringExtra("TssFirstNameSurname");
            if(strTssFirstNameSurname != null)
            {
                TextView TssFirstNameSurname = (TextView) findViewById(R.id.txtTssFirstNameSurname);
                TssFirstNameSurname.setText(me.getStringExtra("TssFirstNameSurname"));
            }
            String strTssJobTitle = me.getStringExtra("TssJobTitle");
            if(strTssJobTitle != null)
            {
                TextView TssJobTitle = (TextView) findViewById(R.id.txtTssJobTitle);
                TssJobTitle.setText(me.getStringExtra("TssJobTitle"));
            }
            String strTssCellNo = me.getStringExtra("TssCellNo");
            if(strTssCellNo != null)
            {
                TextView TssCellNo = (TextView) findViewById(R.id.txtTssCellNo);
                TssCellNo.setText(me.getStringExtra("TssCellNo"));
            }
            String strInsFirstNameSurname = me.getStringExtra("InsFirstNameSurname");
            if(strInsFirstNameSurname != null)
            {
                TextView InsFirstNameSurname = (TextView) findViewById(R.id.txtInsFirstNameSurname);
                InsFirstNameSurname.setText(me.getStringExtra("InsFirstNameSurname"));
            }
            String strInsJobTitle = me.getStringExtra("InsJobTitle");
            if(strInsJobTitle != null)
            {
                TextView InsJobTitle = (TextView) findViewById(R.id.txtInsJobTitle);
                InsJobTitle.setText(me.getStringExtra("InsJobTitle"));
            }
            String strInsCellNo = me.getStringExtra("InsCellNo");
            if(strInsCellNo != null)
            {
                TextView InsCellNo = (TextView) findViewById(R.id.txtInsCellNo);
                InsCellNo.setText(me.getStringExtra("InsCellNo"));
            }
//            String strDateRecordChanged = me.getStringExtra("DateRecordChanged");
//            if(strDateRecordChanged != null)
//            {
//                TextView DateRecordChanged = (TextView) findViewById(R.id.txtDateRecordChanged);
//                DateRecordChanged.setText(me.getStringExtra("DateRecordChanged"));
//            }



///////////////////////////////////////////
            //numeric fields
//            TextView ID = (TextView) findViewById(R.id.txtID);
//            Integer id = me.getIntExtra("ID", -1);
//            ID.setText(id.toString());
            TextView TotalUnitCount = (TextView) findViewById(R.id.txtTotalUnitCount);
            Integer totalUnitCount = me.getIntExtra("TotalUnitCount", -1);
            TotalUnitCount.setText(totalUnitCount.toString());

            Double dGpsLat = me.getDoubleExtra("GpsLat", 0.00);
            TextView GpsLat = (TextView) findViewById(R.id.txtGpsLat);
            GpsLat.setText(Double.toString(dGpsLat));

            Double dGpsLng = me.getDoubleExtra("GpsLng", 0.00);
            TextView GpsLng = (TextView) findViewById(R.id.txtGpsLng);
            GpsLng.setText(Double.toString(dGpsLng));



        }
        catch   (Exception e)
        {
            String ex = e.getMessage();

        }



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

            Intent intent = new Intent(RepInfoActivity.this, MainActivity.class );

            startActivity(intent); finish();

            // return true;
        }


        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,LoginActivity.class));
        }

        if (id == R.id.backbutton){
            startActivity(new Intent(this,RepProgressActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


    public void SignOff(View view) {

        Intent intent = new Intent(RepInfoActivity.this, MainActivity.class );
        startActivity(intent); finish();

    }

    public void GoOnline(View view) {

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("http://testingwayo.winninginontrade.miid.co.za/Stores/RepStoreInfo?id=%s",mGlobalStoreID))));
        finish();

    }


}