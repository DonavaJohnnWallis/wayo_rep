package com.vf.admin.vf_rep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class StoreDetailsActivity extends AppCompatActivity {
    Integer globalStoreID;
    String globalStoreNameURN;
    String globalStoreName;
    String globalURN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_store_details);
        TextView storename = findViewById(R.id.storename);
        TextView address = findViewById(R.id.address);
        //EditText pobox = findViewById(R.id.pobox);

        Intent me = getIntent();
        String fullAddress;
        String strStoreName = me.getStringExtra("StoreName");
        if(strStoreName != null)
        {
            storename.setText(me.getStringExtra("StoreName"));
        }
        String strAddress = me.getStringExtra("AddressLine1");
        if(strAddress != null)
        {
            fullAddress = String.format("%s",strAddress);
            String strAddress2 = me.getStringExtra("AddressLine2");
            if(strAddress2 != null)
            {
                fullAddress = String.format("%s %s",strAddress, strAddress2);
            }
            String strAddress3 = me.getStringExtra("TownCity");
            if(strAddress3 != null)
            {
                fullAddress = String.format("%s %s %s",strAddress, strAddress2, strAddress3);
            }

            address.setText(fullAddress);
        }

        globalStoreID = me.getIntExtra("ID",0);
        globalStoreNameURN = me.getStringExtra("StoreNameURN");
        globalURN = me.getStringExtra("URN");
        globalStoreName = me.getStringExtra("StoreName");
    }

    public void OpenContract(View view) {
        TextView storename = findViewById(R.id.storename);
        TextView address = findViewById(R.id.address);
       // EditText pobox = findViewById(R.id.pobox);



        Intent intent = new Intent(StoreDetailsActivity.this, ContractOneActivity.class);

        //intent.putExtra("storename",storename.getText().toString());
        intent.putExtra("address",address.getText().toString());
       // intent.putExtra("pobox",pobox.getText().toString());
        intent.putExtra("ID", globalStoreID);
        intent.putExtra("StoreNameURN",      globalStoreNameURN );
        intent.putExtra("StoreName",      globalStoreName );
        intent.putExtra("URN",      globalURN );
        startActivity(intent);
        finish();
    }
}
