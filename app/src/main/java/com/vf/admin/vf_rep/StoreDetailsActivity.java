package com.vf.admin.vf_rep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StoreDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_store_details);
    }

    public void OpenContract(View view) {

        Intent intent = new Intent(StoreDetailsActivity.this, ContractOneActivity.class);

        startActivity(intent);
    }
}
