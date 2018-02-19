package com.vf.admin.vf_rep;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.CompoundButton;

public class ContractOneActivity extends AppCompatActivity {
    Integer globalStoreID;
    String globalStoreNameURN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contract_one);

        TextView storetext = (TextView) findViewById(R.id.storenametext);
        TextView addresstext = (TextView) findViewById(R.id.roadtext);
        //TextView potext = (TextView) findViewById(R.id.poboxtext);
        Bundle bu;
        bu=getIntent().getExtras();
        storetext.setText(bu.getString("StoreName"));
        addresstext.setText(bu.getString("address"));
       // potext.setText(bu.getString("pobox"));

        Intent me = getIntent();
        globalStoreID = me.getIntExtra("ID", 0);

        globalStoreNameURN = me.getStringExtra("StoreNameURN");
        //Get widgets reference from XML layout

        Switch sButton = (Switch) findViewById(R.id.switch_btn);
        Button btn = (Button) findViewById(R.id.button3);
        btn.setEnabled(false);




//Set a CheckedChange Listener for Switch Button
        sButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on) {
                if (on) {
                    //Do something when Switch button is on/checked

                    Button btn = (Button) findViewById(R.id.button3);
                    btn.setEnabled(true);
                } else {
                    //Do something when Switch is off/unchecked

                    Button btn = (Button) findViewById(R.id.button3);
                    btn.setEnabled(false);
                }
            }
        });
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

            Intent intent = new Intent(ContractOneActivity.this, MainActivity.class );

            startActivity(intent); finish();

            // return true;
        }



        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,Login.class));
        }

        if (id == R.id.backbutton){
            startActivity(new Intent(this,StoreListContractsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


    public void next(View view) {




        Intent intent = new Intent(ContractOneActivity.this, ContractTwoActivity.class);

        TextView storetext = (TextView) findViewById(R.id.storenametext);
        TextView addresstext = (TextView) findViewById(R.id.roadtext);
       // TextView potext = (TextView) findViewById(R.id.poboxtext);

        intent.putExtra("storename",storetext.getText().toString());
        intent.putExtra("address",addresstext.getText().toString());
       // intent.putExtra("pobox",potext.getText().toString());
intent.putExtra("ID", globalStoreID);
intent.putExtra("StoreNameURN",      globalStoreNameURN );

        startActivity(intent);
        finish();

    }
}
