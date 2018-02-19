package com.vf.admin.vf_rep;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by Donavan on 2017/01/16.
 */

public class StoreListActivity extends AppCompatActivity {



   // Activity for image uploads page for reps
    //style xml files are grid_row_uploads.xml



    GridView gridView;
    AdapterStorelist gridViewCustomeAdapter;
    int mglobalStoreID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_storelist);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);




        TextView txtHeader = (TextView)findViewById(R.id.txtHeader);
        txtHeader.setText(String.format("Store list for %s", Local.Get(getApplicationContext(), "UserName")));

        gridView=(GridView)findViewById(R.id.gridViewCustom2);
        // Create the Custom Adapter Object
        gridViewCustomeAdapter = new AdapterStorelist(this);
        // Set the Adapter to GridView
        gridView.setAdapter(gridViewCustomeAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            //you should be able to set the url differetnly for each position, read up about it
            //on  stackoverflow
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
               // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://testingwayo.winninginontrade.miid.co.za/Stores/RepStoreInfo/")));


            }

        });


    }










}












