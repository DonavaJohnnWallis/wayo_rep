package com.vf.admin.vf_rep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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



        TextView storenametext = (TextView) findViewById(R.id.storenametext);
        storenametext.setText(String.format("For Outlet: %s",globalStoreNameURN));

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contract_one, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        if (id == R.id.homebutton) {

            Intent intent = new Intent(ContractLast.this, MainActivity.class );

            startActivity(intent); finish();

            // return true;
        }



        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,Login.class));
        }

        if (id == R.id.backbutton){
            startActivity(new Intent(this,ContractFourActivity.class));
        }
        if (id == R.id.declineContract){

            Intent intent = new Intent(ContractLast.this, DeclineContractActivity.class );
            intent.putExtra("StoreID", globalStoreID);

            startActivity(intent); finish();
        }
        return super.onOptionsItemSelected(item);
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
