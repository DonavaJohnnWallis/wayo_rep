package com.vf.admin.vf_rep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_layout);
    }

    public void GoHome(View view) {

        Intent intent = new Intent(SuccessActivity.this, MainActivity.class);

        startActivity(intent);
        finish();
    }
}
