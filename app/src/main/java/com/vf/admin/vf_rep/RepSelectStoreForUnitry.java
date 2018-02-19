package com.vf.admin.vf_rep;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Donavan on 2017/01/16.
 */

public class RepSelectStoreForUnitry extends AppCompatActivity {



   // Activity for image uploads page for reps
    //style xml files are grid_row_uploads.xml



    GridView gridView;
    RepGridSelectStoreupload grisViewCustomeAdapter;
    ArrayList<AndroidStore> mystores;

    @Override
    public void onBackPressed() {     }      @Override  protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rep_upload_layout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mystores = getStoresData();


        gridView=(GridView)findViewById(R.id.gridViewCustom2);
        // Create the Custom Adapter Object
        grisViewCustomeAdapter = new RepGridSelectStoreupload(this, mystores);
        // Set the Adapter to GridView
        gridView.setAdapter(grisViewCustomeAdapter);

        //sets where list item is clicking too
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {
                //Toast.makeText(getApplicationContext(), names[pos], Toast.LENGTH_LONG).show();




                Intent intent = new Intent(RepSelectStoreForUnitry.this, RepUnitaryList.class );
                //Sending data to another Activity
                AndroidStore store = mystores.get(pos);

                String storeName = store.getStoreName();
                String storeNameURN = store.getStoreNameURN();

                intent.putExtra("StoreName", storeName);
                intent.putExtra("StoreNameURN", storeNameURN);


                startActivity(intent); finish();
            }

        } );


    }
    public void Close(View view) {

        Intent intent = new Intent(RepSelectStoreForUnitry.this, RepHomeActivity.class );

        startActivity(intent); finish();



    }

    public void stockinput(View view) {

        Intent intent = new Intent(RepSelectStoreForUnitry.this, RepItemsActivity.class );

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.homebutton) {

            Intent intent = new Intent(RepSelectStoreForUnitry.this, MainActivity.class );

            startActivity(intent); finish();

            // return true;
        }

        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,Login.class));
        }

        if (id == R.id.backbutton){
            startActivity(new Intent(this,RepUnitaryList.class));
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


}












