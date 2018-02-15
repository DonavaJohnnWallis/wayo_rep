package com.vf.admin.vf_rep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class ContractLast extends AppCompatActivity {
    Integer globalStoreID;
    String globalStoreNameURN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contract_last);
        Intent me = getIntent();
        globalStoreID = me.getIntExtra("ID", 0);
        globalStoreNameURN = me.getStringExtra("StoreNameURN");

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.date);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM  yyyy hh-mm-ss a");
                                String dateString = sdf.format(date);
                                tdate.setText(dateString);

                            }
                        });

                    }
                } catch (InterruptedException e) {
                }
            }
        };

   t.start();
    }

    public void GoToSignatureNew(View view) {

        Intent intent = new Intent(ContractLast.this, SignatureActivity.class);

        intent.putExtra("FromScreen", "Contract");
        intent.putExtra("ID", globalStoreID);
        intent.putExtra("StoreNameURN", globalStoreNameURN);


        startActivity(intent);
        finish();
    }
}
