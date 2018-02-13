package com.vf.admin.vf_rep;

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
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vf.admin.vf_rep.R;

import org.json.JSONException;

/**
 * Created by Donavan on 2017/01/16.
 */

public class RepExtraDetailsActivity extends AppCompatActivity {

    private Spinner  spinner2;
    private Spinner  spinner1;
    private Spinner  spinner3;

    private AndroidStore store = new AndroidStore();

    public static final String PREFS_NAME = "MyPrefsFile";
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

    public AndroidStore readJsonLocalStore(String yourSettingName)
    {
        SharedPreferences mSettings = getSharedPreferences(PREFS_NAME, 0);
        String loadValue = mSettings.getString(yourSettingName, "");
        AndroidStore c = mGson.fromJson(loadValue, AndroidStore.class);

        return c;
    }

    @Override
    public void onBackPressed() {     }

    @Override  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rep_extradetails_layout);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        addItemsOnSpinner2();
        addItemsOnSpinner1();
        addItemsOnSpinner3();



    }


    // add items into spinner
    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinnerBrandID);
        List<String> list = new ArrayList<String>();
        list.add(0, "Choose Brand");
        list.add(1, "Tusker");
        list.add(2, "Guinness");
        list.add(3, "Kenya Cane");

        spinner2.setPrompt("Choose Brand");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
               R.layout.spinnerstyle, list);
        dataAdapter.setDropDownViewResource(R.layout.spinnerstyledrop);
        spinner2.setAdapter(dataAdapter);
    }

    // add items into spinner
    public void addItemsOnSpinner1() {

        spinner1 = (Spinner) findViewById(R.id.spinnerOutletTypeID);
        List<String> list = new ArrayList<String>();
        list.add(0, "Outlet Type");
        list.add(1, "Kutana");
        list.add(2, "Mzalendo");
        spinner1.setPrompt("Outlet Type");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinnerstyle, list);
        dataAdapter.setDropDownViewResource(R.layout.spinnerstyledrop);
        spinner1.setAdapter(dataAdapter);
    }

    // add items into spinner
    public void addItemsOnSpinner3() {

        spinner3 = (Spinner) findViewById(R.id.spinnerTierTypeID);
        List<String> list = new ArrayList<String>();
        list.add(0, "Tier");
        list.add(1, "Tier 1");
        list.add(2, "Tier 2");
        list.add(3, "Essentials");

        spinner3.setPrompt("Tier Type");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinnerstyle, list);
        dataAdapter.setDropDownViewResource(R.layout.spinnerstyledrop);
        spinner3.setAdapter(dataAdapter);







    }


    // add items into spinner



    public void stockinput(View view) throws JSONException {

        //save more to store
        store = readJsonLocalStore("Store1");

        Spinner BrandID = (Spinner) findViewById(R.id.spinnerBrandID);
        //store.BrandID  = BrandID .getSelectedItemPosition();
        store.BrandName = BrandID.getSelectedItem().toString();
        Spinner OutletTypeID = (Spinner) findViewById(R.id.spinnerOutletTypeID);
        store.OutletTypeName = OutletTypeID.getSelectedItem().toString();
        //store.OutletTypeID  = OutletTypeID .getSelectedItemPosition();
        Spinner TierTypeID = (Spinner) findViewById(R.id.spinnerTierTypeID);
        store.TierTypeName = TierTypeID.getSelectedItem().toString();
        //store.TierTypeID  = TierTypeID .getSelectedItemPosition();

        String jsonStore = JsonUtil.toJSonAndroidStore(store);
        saveJsonLocalStore(jsonStore, "Store1");

        SaveStoreToArray(store);

        //Toast store saved locally - will sync when back in range

        //Go back to Rep Home
        Intent intent = new Intent(RepExtraDetailsActivity.this, RepHomeActivity.class );
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

            Intent intent = new Intent(RepExtraDetailsActivity.this, MainActivity.class );

            startActivity(intent); finish();

            // return true;
        }



        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,LoginActivity.class));
        }


        if (id == R.id.backbutton){
            startActivity(new Intent(this,RepStoreDetailsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void  SaveStoreToArray(AndroidStore store) throws JSONException {

        try {

            String storesString = Local.Get(getApplicationContext(), "AndroidStores");

            ArrayList<AndroidStore> stores = JsonUtil.parseJsonArrayAndroidStore(storesString);

            //Convert request to appointment


            Date recordChangedNow = new Date();

            store.setDateRecordChanged((String) android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss a", recordChangedNow));

            stores.add(store);

            //Save Requests back to local
            Local.Set(getApplicationContext(), "AndroidStores", JsonUtil.convertStoreArrayToJson(stores));


        } catch (Exception ex) {
            //ad.setTitle("Error!");
            //ad.setMessage(ex.toString());
        }
        //ad.show();

    }




}












