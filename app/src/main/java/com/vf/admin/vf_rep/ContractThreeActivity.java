package com.vf.admin.vf_rep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class ContractThreeActivity extends AppCompatActivity {
    Integer globalStoreID;
    String globalStoreNameURN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contract_three);
        Intent me = getIntent();
        globalStoreID = me.getIntExtra("ID", 0);
        //Get widgets reference from XML layout
        globalStoreNameURN = me.getStringExtra("StoreNameURN");


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

            Intent intent = new Intent(ContractThreeActivity.this, MainActivity.class );

            startActivity(intent); finish();

            // return true;
        }



        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,Login.class));
        }

        if (id == R.id.backbutton){
            startActivity(new Intent(this,ContractTwoActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void next(View view) {

        Intent intent = new Intent(ContractThreeActivity.this, ContractFourActivity.class);
        intent.putExtra("ID", globalStoreID);
        intent.putExtra("StoreNameURN",      globalStoreNameURN );
        startActivity(intent);
        finish();
    }
}
