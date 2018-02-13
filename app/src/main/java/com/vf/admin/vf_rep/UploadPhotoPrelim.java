package com.vf.admin.vf_rep;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class UploadPhotoPrelim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo_prelim);

        int Enumerate = getIntent().getIntExtra("Enumerate", 0);
    if(Enumerate ==0){
    Button btn = (Button)findViewById(R.id.btnBarcode);
        btn.setVisibility(View.GONE);
    }


    }

    public void UploadBarcode(View view) {

        Intent intent = new Intent(UploadPhotoPrelim.this, UploadPhoto.class );


        intent.putExtra("Enumerate", getIntent().getStringExtra("Enumerate"));

        Integer storeItemID =  getIntent().getIntExtra("StoreItemID",0);
        intent.putExtra("StoreItemID", storeItemID);

        intent.putExtra("StoreName", getIntent().getStringExtra("StoreName"));
        intent.putExtra("StoreNameURN", getIntent().getStringExtra("StoreNameURN"));
        intent.putExtra("URN", getIntent().getStringExtra("URN"));

        intent.putExtra("StoreID", getIntent().getStringExtra("StoreID"));

        intent.putExtra("ImageType", "BarcodeOnUnit");
        intent.putExtra("Barcode",getIntent().getStringExtra("Barcode"));

        intent.putExtra("RequestID", getIntent().getIntExtra("RequestID",0));

        startActivity(intent); finish();
    }

    public void UploadUnitImage(View view) {

        Intent intent = new Intent(UploadPhotoPrelim.this, UploadPhoto.class );


        intent.putExtra("Enumerate", getIntent().getStringExtra("Enumerate"));

        Integer storeItemID =  getIntent().getIntExtra("StoreItemID",0);
        intent.putExtra("StoreItemID", storeItemID);

        intent.putExtra("StoreName", getIntent().getStringExtra("StoreName"));
        intent.putExtra("StoreNameURN", getIntent().getStringExtra("StoreNameURN"));
        intent.putExtra("URN", getIntent().getStringExtra("URN"));

        intent.putExtra("StoreID", getIntent().getStringExtra("StoreID"));

        intent.putExtra("ImageType", "StoreItemInStore");
        intent.putExtra("Barcode",getIntent().getStringExtra("Barcode"));

        intent.putExtra("RequestID", getIntent().getIntExtra("RequestID",0));

        startActivity(intent); finish();
    }
}
