package com.vf.admin.vf_rep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class ContractFourActivity extends AppCompatActivity {

    Integer globalStoreID; String globalStoreNameURN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contract_four);
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

    public void next(View view) {

        Intent intent = new Intent(ContractFourActivity.this, ContractLast.class);
        intent.putExtra("ID", globalStoreID);
        startActivity(intent);
        finish();
    }
}
