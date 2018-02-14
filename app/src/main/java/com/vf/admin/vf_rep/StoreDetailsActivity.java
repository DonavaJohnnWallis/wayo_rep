package com.vf.admin.vf_rep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class StoreDetailsActivity extends AppCompatActivity {
    Integer globalStoreID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_store_details);
        EditText storename = findViewById(R.id.storename);
        EditText address = findViewById(R.id.address);
        EditText pobox = findViewById(R.id.pobox);

        Intent me = getIntent();
        String strStoreName = me.getStringExtra("StoreNameURN");
        if(strStoreName != null)
        {
            storename.setText(me.getStringExtra("StoreNameURN"));
        }
        String strAddress = me.getStringExtra("AddressLine1");
        if(strAddress != null)
        {
            address.setText(me.getStringExtra("AddressLine1"));
        }

        globalStoreID = me.getIntExtra("ID",0);
    }

    public void OpenContract(View view) {
        EditText storename = findViewById(R.id.storename);
        EditText address = findViewById(R.id.address);
        EditText pobox = findViewById(R.id.pobox);



        Intent intent = new Intent(StoreDetailsActivity.this, ContractOneActivity.class);

        intent.putExtra("storename",storename.getText().toString());
        intent.putExtra("address",address.getText().toString());
        intent.putExtra("pobox",pobox.getText().toString());
        intent.putExtra("ID", globalStoreID);

        startActivity(intent);
        finish();
    }
}
