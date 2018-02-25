package com.vf.admin.vf_rep;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DeclineContractActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decline_contract);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView text = (TextView) findViewById(R.id.textView1);
        text.setText("The Store has been Declined as all agreement requirements have not been accepted. Please update Visual Fusion with replacement outlet information on kenya@visualfusionglobal.com");//TODO get text from Scott Email
        //7. We need a decline loop if the store selects NO on one of the Contract pages,
        // it should redirect the user to a page that states that the Store has been
        // Declined as all agreement requirements have not been accepted. Please update Visual Fusion with replacement outlet information on kenya@visualfusionglobal.com.



    }

    public void declineContract(View view) {
    //TODO: Process declining of Contract

        Intent intent = new Intent(DeclineContractActivity.this, MainActivity.class);

        startActivity(intent); finish();

    }
}
