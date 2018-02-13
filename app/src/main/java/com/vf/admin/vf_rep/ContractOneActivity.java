package com.vf.admin.vf_rep;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.CompoundButton;

public class ContractOneActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contract_one);

        TextView storetext = (TextView) findViewById(R.id.storenametext);
        TextView addresstext = (TextView) findViewById(R.id.roadtext);
        TextView potext = (TextView) findViewById(R.id.poboxtext);
        Bundle bu;
        bu=getIntent().getExtras();
        storetext.setText(bu.getString("storename"));
        addresstext.setText(bu.getString("address"));
        potext.setText(bu.getString("pobox"));




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




        Intent intent = new Intent(ContractOneActivity.this, ContractTwoActivity.class);

        TextView storetext = (TextView) findViewById(R.id.storenametext);
        TextView addresstext = (TextView) findViewById(R.id.roadtext);
        TextView potext = (TextView) findViewById(R.id.poboxtext);

        intent.putExtra("storename",storetext.getText().toString());
        intent.putExtra("address",addresstext.getText().toString());
        intent.putExtra("pobox",potext.getText().toString());

        startActivity(intent);
        finish();

    }
}
