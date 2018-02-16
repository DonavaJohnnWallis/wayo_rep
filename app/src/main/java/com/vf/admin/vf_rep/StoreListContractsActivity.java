package com.vf.admin.vf_rep;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONException;

import java.util.ArrayList;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.vf.admin.vf_rep.R;

import org.json.JSONException;

import java.util.ArrayList;


public class StoreListContractsActivity extends AppCompatActivity
{


    GridView gridView;
    RepGridViewCustomProgress grisViewCustomeAdapter;
    ArrayList<AndroidStore> mystores;
    AndroidStore globalstore;

    @Override
    public void onBackPressed() {     }
    @Override  protected void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.rep_progress_layout);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    TextView textHeader = (TextView) findViewById(R.id.textHeader);

    textHeader.setText(String.format("Store list for %s", Local.Get(getApplicationContext(), "UserName")));

    mystores = getStoresData();


    gridView=(GridView)findViewById(R.id.gridViewCustom3);
    // Create the Custom Adapter Object
    grisViewCustomeAdapter = new RepGridViewCustomProgress(this, mystores);
    // Set the Adapter to GridView
    gridView.setAdapter(grisViewCustomeAdapter);



    //sets where list item is clicking too
    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {
            //Toast.makeText(getApplicationContext(), names[pos], Toast.LENGTH_LONG).show();
            globalstore = mystores.get(pos);
            ProgressDialog progressDialog;

            //set and remove prrogress bar
            progressDialog = new ProgressDialog(StoreListContractsActivity.this);
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



            GetUnitListsForUserExplicit(Local.Get(getApplicationContext(), "UserName"), false, globalstore.getID());


        }

    } );

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

            Intent intent = new Intent(StoreListContractsActivity.this, MainActivity.class );

            startActivity(intent); finish();

            // return true;
        }



        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,LoginActivity.class));
        }

        if (id == R.id.backbutton){
            startActivity(new Intent(this,RepHomeActivity.class));
        }

        return super.onOptionsItemSelected(item);
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


                    //CAll the next screen
                    AndroidStore store = globalstore;
                    Intent intent = new Intent(StoreListContractsActivity.this, StoreDetailsActivity.class );

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



                    startActivity(intent); finish();



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

/*
 * Created by Donavan on 2017/01/16.

public class StoreListContractsActivity extends AppCompatActivity {



    // Activity for image uploads page for reps
    //style xml files are grid_row_uploads.xml



    GridView gridView;
    AdapterStorelistContracts gridViewCustomeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_storelist);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        gridView=(GridView)findViewById(R.id.gridViewCustom2);
        // Create the Custom Adapter Object
        gridViewCustomeAdapter = new AdapterStorelistContracts(this);
        // Set the Adapter to GridView
        gridView.setAdapter(gridViewCustomeAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            //you should be able to set the url differetnly for each position, read up about it
            //on  stackoverflow
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent i = new  Intent(getApplicationContext(),StoreDetailsActivity.class);
                startActivity(i);
                finish();

            }

        });


    }










}*/









