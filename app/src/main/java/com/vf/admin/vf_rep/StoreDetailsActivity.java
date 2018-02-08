package com.vf.admin.vf_rep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StoreDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_store_details);
    }

    public void OpenContract(View view) {
        EditText storename = findViewById(R.id.storename);
        EditText address = findViewById(R.id.address);
        EditText pobox = findViewById(R.id.pobox);

        Intent intent = new Intent(StoreDetailsActivity.this, ContractOneActivity.class);

        intent.putExtra("storename",storename.getText().toString());
        intent.putExtra("address",address.getText().toString());
        intent.putExtra("pobox",pobox.getText().toString());

        startActivity(intent);
    }
}
