package com.vf.admin.vf_rep;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

public class RepValidatedActivity extends AppCompatActivity {


    GridView gridView;
    RepGridViewCustomImages grisViewCustomeAdapter;

    @Override
    public void onBackPressed() {     }      @Override  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rep_validated_layout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        gridView=(GridView)findViewById(R.id.gridViewCustom4);
        // Create the Custom Adapter Object
        grisViewCustomeAdapter = new RepGridViewCustomImages(this);
        // Set the Adapter to GridView
        gridView.setAdapter(grisViewCustomeAdapter);

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

            Intent intent = new Intent(RepValidatedActivity.this, MainActivity.class );

            startActivity(intent); finish();

            // return true;
        }




        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,Login.class));
        }

        if (id == R.id.backbutton){
            startActivity(new Intent(this,RepItemsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


    public void continuebtn(View view) {
        Intent intent = new Intent(RepValidatedActivity.this, RepHomeActivity.class );
        startActivity(intent); finish();


    }

    public void editbtn(View view) {

        Intent intent = new Intent(RepValidatedActivity.this, RepStoreDetailsActivity.class );
        startActivity(intent); finish();

    }
}
