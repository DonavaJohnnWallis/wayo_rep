package com.vf.admin.vf_rep;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Donavan on 2017/01/16.
 */

public class ApendixActivity extends AppCompatActivity {



   // Activity for image uploads page for reps
    //style xml files are grid_row_uploads.xml



    GridView gridView;
    AdapterApendix gridViewCustomeAdapter;
    ArrayList<AndroidStoreUnitExplicit> myunits;
    Integer globalStoreID;
    String globalStoreNameURN;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.layout_apendix);


        TextView txtUnitListHeader = (TextView) findViewById(R.id.txtUnitListHeader);
        txtUnitListHeader.setText(String.format("Unit list for store: %s", globalStoreNameURN));

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        boolean mIsSurvey = false;
        Intent me = getIntent();
        globalStoreNameURN = me.getStringExtra("StoreNameURN");
        myunits = getstoreUnitsExplicit(globalStoreNameURN);
        globalStoreID = me.getIntExtra("ID", 0);


        gridView=(GridView)findViewById(R.id.gridViewCustom2);
        // Create the Custom Adapter Object
        gridViewCustomeAdapter = new AdapterApendix(this, myunits, mIsSurvey);
        // Set the Adapter to GridView
        gridView.setAdapter(gridViewCustomeAdapter);




    }





    public void Confirm(View view) {

        Intent intent = new Intent(ApendixActivity.this, SignatureActivity.class);

        intent.putExtra("FromScreen", "Appendix");
        intent.putExtra("ID", globalStoreID);

        startActivity(intent);
        finish();

    }


    private ArrayList<AndroidStoreUnitExplicit> getstoreUnitsExplicit(String storeNameURN) {
        ArrayList<AndroidStoreUnitExplicit> storeUnitExplicits = new ArrayList<>();
        String json = "[]";
        json = Local.Get(getApplicationContext(), "AndroidStoreUnitsExplicit");
        try {
            storeUnitExplicits = JsonUtil.parseJsonArrayAndroidStoreUnitExplicit(json, storeNameURN);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return storeUnitExplicits;
    }


    public void GetUnitListsForUserExplicit(String UserName, Boolean IsSurvey, Integer StoreID) {

        String isWebsiteAvailable = Local.Get(getApplicationContext(), "AmIOnline");

        if(isWebsiteAvailable.equals("True")) {

            final AlertDialog ad = new AlertDialog.Builder(this).create();
            MySOAPCallActivity cs = new MySOAPCallActivity();
            try {

                GetUnitListsForUserExplicitParams params = new GetUnitListsForUserExplicitParams(cs, UserName, IsSurvey, StoreID);

                new CallSoapGetUnitListsForUserExplicit().execute(params);


            } catch (Exception ex) {
                ad.setTitle("Error!");
                ad.setMessage(ex.toString());
            }
            ad.show();

        }
        else {
            //I am OFFLINE
            //Do Nothing - you'll just use the stores you have :)


        }
    }


    public class CallSoapGetUnitListsForUserExplicit extends AsyncTask<GetUnitListsForUserExplicitParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(GetUnitListsForUserExplicitParams... params) {
            return params[0].foo.GetUnitListsForUserExplicit(params[0].username, params[0].issurvey, params[0].storeid);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {
                //process Json Result
                //Save results in local storage
                if(result.toLowerCase().contains("error") || result.toLowerCase().contains("exception")){
                    Button btnUnitaryLists = (Button) findViewById(R.id.UnitaryLists);
                    btnUnitaryLists.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Getting data timed out. Please try again.", Toast.LENGTH_LONG).show();
                }
                else {
                    Local.Set(getApplicationContext(), "AndroidStoreUnitsExplicit", result);
                    //Button btnUnitaryLists = (Button) findViewById(R.id.UnitaryLists);
                    //btnUnitaryLists.setVisibility(View.VISIBLE);
                }

            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class GetUnitListsForUserExplicitParams {
        MySOAPCallActivity foo;
        String username;
        Boolean issurvey;
        Integer storeid;


        GetUnitListsForUserExplicitParams(MySOAPCallActivity foo, String username, Boolean issurvey, Integer storeid) {
            this.foo = foo;
            this.username = username;
            this.issurvey = issurvey;
            this.storeid=storeid;
        }
    }
}












