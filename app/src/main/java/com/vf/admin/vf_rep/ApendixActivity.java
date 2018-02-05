package com.vf.admin.vf_rep;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Switch;

/**
 * Created by Donavan on 2017/01/16.
 */

public class ApendixActivity extends AppCompatActivity {



   // Activity for image uploads page for reps
    //style xml files are grid_row_uploads.xml



    GridView gridView;
    AdapterApendix gridViewCustomeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_apendix);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);






        gridView=(GridView)findViewById(R.id.gridViewCustom2);
        // Create the Custom Adapter Object
        gridViewCustomeAdapter = new AdapterApendix(this);
        // Set the Adapter to GridView
        gridView.setAdapter(gridViewCustomeAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            //you should be able to set the url differetnly for each position, read up about it
            //on  stackoverflow
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent i = new  Intent(getApplicationContext(),ApenixListActivity.class);
                startActivity(i);




            }

        });


    }





    public void Confirm(View view) {

        Intent intent = new Intent(ApendixActivity.this, SignatureActivity.class);

        startActivity(intent);

    }




}












