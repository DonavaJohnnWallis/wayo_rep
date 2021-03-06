package com.vf.admin.vf_rep;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {


    ViewFlipper viewFlipper;
    String mGlobalRoles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        String isWebsiteAvailable = Local.Get(getApplicationContext(), "AmIOnline");
        //Get Data from webservice
        if (isWebsiteAvailable.equals("True")) {



            //GetUnitListsForUserExplicit(Local.Get(this.getApplication(), "UserName"), false, 0);

        }
        else{
            //I am Offline
            //Use locally saved lists
           // btnUnitaryLists.setVisibility(View.VISIBLE);
        }

        mGlobalRoles = Local.Get(this.getApplication(), "Roles");

        if(mGlobalRoles.contains("Dvh")) //DTM
        {
           Button home2 = (Button)this.findViewById(R.id.home3);
            home2.setVisibility(View.GONE);
        }



    }

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

            Intent intent = new Intent(MainActivity.this, Login.class );

            startActivity(intent); finish();

            // return true;
        }



        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,Login.class));
        }

        if (id == R.id.backbutton){
            startActivity(new Intent(this,Login.class));
        }

        return super.onOptionsItemSelected(item);
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

//links child button to parent flipper layout and contains animations
    public void ClickHome1(View view) {
        viewFlipper.setDisplayedChild(1);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.homescreen1)));

        //pop animation for layout
        LinearLayout Layoutpop1 = (LinearLayout)this.findViewById(R.id.homescreen1);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop1.startAnimation(expandIn);
    }



    public void ClickHome2(View view) {
        viewFlipper.setDisplayedChild(2);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.homescreen2)));

        //pop animation for layout
        LinearLayout Layoutpop2 = (LinearLayout)this.findViewById(R.id.homescreen2);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop2.startAnimation(expandIn);
    }


    public void ClickHome3(View view) {
        viewFlipper.setDisplayedChild(3);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.homescreen3)));

        //pop animation for layout
        LinearLayout Layoutpop2 = (LinearLayout)this.findViewById(R.id.homescreen3);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop2.startAnimation(expandIn);
    }


    public void ClickHome4(View view) {
        viewFlipper.setDisplayedChild(4);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.homescreen4)));

        //pop animation for layout
        LinearLayout Layoutpop2 = (LinearLayout)this.findViewById(R.id.homescreen4);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop2.startAnimation(expandIn);
    }



    //go to barcode scanner

    public void GoToStoreList(View view) {

       //Intent intent = new Intent(MainActivity.this, StoreListActivity.class);

        Intent intent = new Intent(MainActivity.this, RepProgressActivity.class);

       startActivity(intent);
    }

    public void GoToContracts(View view) {


        Intent intent = new Intent(MainActivity.this, StoreListContractsActivity.class);

        startActivity(intent);
        finish();
    }

    public void GoToApendix(View view) {


        Intent intent = new Intent(MainActivity.this, ApenixListActivity.class);

        startActivity(intent);
        finish();
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
