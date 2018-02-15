package com.vf.admin.vf_rep;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Donavan on 2017/01/16.
 */

public class ApenixListActivity extends AppCompatActivity {



   // Activity for image uploads page for reps
    //style xml files are grid_row_uploads.xml



    GridView gridView;
    AdapterStorelistApendix gridViewCustomeAdapter;
    ArrayList<AndroidStore> mystores;
    AndroidStore globalstore;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_storelist);


        TextView txtProgress = (TextView) findViewById(R.id.txtProgress);
        txtProgress.setVisibility(View.INVISIBLE);
        txtProgress.setText("Please wait...");

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mystores = getStoresData();

        gridView=(GridView)findViewById(R.id.gridViewCustom2);
        // Create the Custom Adapter Object
        gridViewCustomeAdapter = new AdapterStorelistApendix(this, mystores);
        // Set the Adapter to GridView
        gridView.setAdapter(gridViewCustomeAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            //you should be able to set the url differetnly for each position, read up about it
            //on  stackoverflow
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {

                //Declare progress dialoge
                ProgressDialog progressDialog;


                globalstore = mystores.get(pos);
                //Rather call the webservice here
                TextView txtProgress = (TextView) findViewById(R.id.txtProgress);
                txtProgress.setVisibility(View.VISIBLE);
                GetUnitListsForUserExplicit(Local.Get(getApplicationContext(), "UserName"), false, globalstore.getID());

                //set and remove prrogress bar
                progressDialog = new ProgressDialog(ApenixListActivity.this);
                progressDialog.setMessage("Loading Lists "); // Setting Message
                progressDialog.setTitle("Please Wait..."); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(10000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // progressDialog.dismiss();
                    }
                }).start();

            }

        });


    }



    private ArrayList<AndroidStore> getStoresData() {
        ArrayList<AndroidStore> stores = new ArrayList<>();
        String json = "[\n" +
                "  {\n" +
                "    \"ID\": 1041,\n" +
                "    \"StoreName\": \"TEST Daniel\",\n" +
                "    \"UserName\": \"dsouchon@gmail.com\",\n" +
                "    \"ContactPerson\": \"Daniel Souchon\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"ID\": 1042,\n" +
                "    \"StoreName\": \"Test Don1\",\n" +
                "    \"UserName\": \"dsouchon@gmail.com\",\n" +
                "    \"ContactPerson\": \"Donavan\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"ID\": 1043,\n" +
                "    \"StoreName\": \"TEST Roadhouse Grill Hurlingham\",\n" +
                "    \"UserName\": \"dsouchon@gmail.com\",\n" +
                "    \"ContactPerson\": \"Robert Kingori\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"ID\": 1044,\n" +
                "    \"StoreName\": \"TEST Soggybottom INN\",\n" +
                "    \"UserName\": \"dsouchon@gmail.com\",\n" +
                "    \"ContactPerson\": \"Saggy Sogbottom\"\n" +
                "  }]";


        json = Local.Get(getApplicationContext(), "AndroidStores");

        try {
            stores = JsonUtil.parseJsonArrayAndroidStore(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return stores;
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

                    Toast.makeText(getApplicationContext(), "Getting data timed out. Please try again.", Toast.LENGTH_LONG).show();
                }
                else {
                    Local.Set(getApplicationContext(), "AndroidStoreUnitsExplicit", result);
                    TextView txtProgress = (TextView) findViewById(R.id.txtProgress);
                    txtProgress.setVisibility(View.VISIBLE);

                    //CAll the next screen
                    AndroidStore store = globalstore;
                    Intent intent = new  Intent(getApplicationContext(),ApendixActivity.class);


                    intent.putExtra("ID", store.getID());
                    intent.putExtra("StoreName", store.getStoreName());
                    intent.putExtra("URN", store.getURN());
                    intent.putExtra("StoreNameURN", store.getStoreNameURN());

                    intent.putExtra("CurrentPhase", store.getCurrentPhase());
                    intent.putExtra("RegionName", store.getRegionName());
                    intent.putExtra("BrandName", store.getBrandName());
                    intent.putExtra("TierTypeName", store.getTierTypeName());
                    intent.putExtra("OutletTypeName", store.getOutletTypeName());
                    intent.putExtra("ContactPerson", store.getContactPerson());
                    intent.putExtra("ContactEmail", store.getContactEmail());
                    intent.putExtra("ContactPhone", store.getContactPhone());
                    intent.putExtra("OpeningTime", store.getOpeningTime());
                    intent.putExtra("ClosingTime", store.getClosingTime());
                    intent.putExtra("TotalUnitCount", store.getTotalUnitCount());
                    intent.putExtra("AddressLine1", store.getAddressLine1());
                    intent.putExtra("AddressLine2", store.getAddressLine2());
                    intent.putExtra("TownCity", store.getTownCity());
                    intent.putExtra("RepFirstNameSurname", store.getRepFirstNameSurname());
                    intent.putExtra("RepJobTitle", store.getRepJobTitle());
                    intent.putExtra("RepCellNo", store.getRepCellNo());
                    intent.putExtra("TssFirstNameSurname", store.getTssFirstNameSurname());
                    intent.putExtra("TssJobTitle", store.getTssJobTitle());
                    intent.putExtra("TssCellNo", store.getTssCellNo());
                    intent.putExtra("InsFirstNameSurname", store.getInsFirstNameSurname());
                    intent.putExtra("InsJobTitle", store.getInsJobTitle());
                    intent.putExtra("InsCellNo", store.getInsCellNo());
                    intent.putExtra("DateRecordChanged", store.getDateRecordChanged());
                    intent.putExtra("GpsLat", store.getGpsLat());
                    intent.putExtra("GpsLng", store.getGpsLng());



                    startActivity(intent);
                    finish();



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












