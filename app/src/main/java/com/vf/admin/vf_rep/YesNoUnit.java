package com.vf.admin.vf_rep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class YesNoUnit extends AppCompatActivity {

    public int mAcceptedByStore;
    public int mItemTypeID;
    public int mStoreItemID;
    public String mStoreNameURN;
    public String mWhyNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_no_unit);
        mItemTypeID = getIntent().getIntExtra("ItemTypeID",0);
        mStoreItemID = getIntent().getIntExtra("StoreItemID",0);
        mStoreNameURN = getIntent().getStringExtra("StoreNameURN");
        mWhyNo = getIntent().getStringExtra("WhyNo");

        RadioButton radioYes = (RadioButton)findViewById(R.id.radioYes);
        RadioButton radioNo = (RadioButton)findViewById(R.id.radioNo);

        int acceptedByStore = getIntent().getIntExtra("AcceptedByStore",-1);

        TextView txtWhy = (TextView)  findViewById(R.id.txtWhyNo);
        TextView txtWhat = (TextView)  findViewById(R.id.txtWhat);
        Button btnUnitLocationUpload = (Button) findViewById(R.id.btnUnitLocationUpload);
        EditText editWhyNo = (EditText)  findViewById(R.id.editWhyNo);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        editWhyNo.setText(mWhyNo);

        switch(acceptedByStore)
        {
            case -1:  mAcceptedByStore = -1;
                //Hide
                btnUnitLocationUpload.setVisibility(View.INVISIBLE);
                txtWhat.setVisibility(View.INVISIBLE);

                //Hide
                txtWhy.setVisibility(View.INVISIBLE);
                editWhyNo.setVisibility(View.INVISIBLE);
                btnSubmit.setVisibility(View.INVISIBLE);


                break;
            case 1: radioYes.setChecked(true);
                radioNo.setChecked(false);
                mAcceptedByStore = 1;

                //Show
                btnUnitLocationUpload.setVisibility(View.VISIBLE);
                txtWhat.setVisibility(View.VISIBLE);

                //Hide
                txtWhy.setVisibility(View.INVISIBLE);
                editWhyNo.setVisibility(View.INVISIBLE);
                btnSubmit.setVisibility(View.INVISIBLE);
                break;
            case 0:  radioYes.setChecked(false);
                radioNo.setChecked(true);
                mAcceptedByStore = 0;
                //Show
                txtWhy.setVisibility(View.VISIBLE);
                editWhyNo.setVisibility(View.VISIBLE);
                btnSubmit.setVisibility(View.VISIBLE);
                //Hide
                btnUnitLocationUpload.setVisibility(View.INVISIBLE);
                txtWhat.setVisibility(View.INVISIBLE);
                break;

            default: break;
        }


    }

    public void onRadioButtonClicked(View view) throws JSONException {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        TextView txtWhy = (TextView)  findViewById(R.id.txtWhyNo);
        TextView txtWhat = (TextView)  findViewById(R.id.txtWhat);
        Button btnUnitLocationUpload = (Button) findViewById(R.id.btnUnitLocationUpload);
        EditText editWhyNo = (EditText)  findViewById(R.id.editWhyNo);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioYes:
                if (checked)
                    mAcceptedByStore = 1;
                //Show
                btnUnitLocationUpload.setVisibility(View.VISIBLE);
                txtWhat.setVisibility(View.VISIBLE);

                //Hide
                txtWhy.setVisibility(View.INVISIBLE);
                editWhyNo.setVisibility(View.INVISIBLE);
                btnSubmit.setVisibility(View.INVISIBLE);
                break;
            case R.id.radioNo:
                if (checked)
                    mAcceptedByStore = 0;
                //Show
                txtWhy.setVisibility(View.VISIBLE);
                editWhyNo.setVisibility(View.VISIBLE);
                btnSubmit.setVisibility(View.VISIBLE);
                //Hide
                btnUnitLocationUpload.setVisibility(View.INVISIBLE);
                txtWhat.setVisibility(View.INVISIBLE);
                break;
            default: mAcceptedByStore = -1;
                break;
        }

      //  FindUnitAndChange(mStoreItemID, mStoreNameURN);

    }

    public void ClearRadio(View view) {


        RadioGroup radioCommitted = (RadioGroup)findViewById(R.id.radioCommitted);
        radioCommitted.clearCheck();

        TextView txtWhy = (TextView)  findViewById(R.id.txtWhyNo);
        TextView txtWhat = (TextView)  findViewById(R.id.txtWhat);
        Button btnUnitLocationUpload = (Button) findViewById(R.id.btnUnitLocationUpload);
        EditText editWhyNo = (EditText)  findViewById(R.id.editWhyNo);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        //Hide
        btnUnitLocationUpload.setVisibility(View.INVISIBLE);
        txtWhat.setVisibility(View.INVISIBLE);

        //Hide
        txtWhy.setVisibility(View.INVISIBLE);
        editWhyNo.setVisibility(View.INVISIBLE);
        btnSubmit.setVisibility(View.INVISIBLE);

        mAcceptedByStore = -1;
    }

    public void UploadUnitImage(View view) {



        Intent intent = new Intent(YesNoUnit.this, UploadPhoto.class );


        intent.putExtra("Enumerate", getIntent().getStringExtra("Enumerate"));

        Integer storeItemID =  getIntent().getIntExtra("StoreItemID",0);
        intent.putExtra("StoreItemID", storeItemID);

        intent.putExtra("StoreName", getIntent().getStringExtra("StoreName"));
        intent.putExtra("StoreNameURN", getIntent().getStringExtra("StoreNameURN"));
        intent.putExtra("URN", getIntent().getStringExtra("URN"));

        intent.putExtra("StoreID", getIntent().getStringExtra("StoreID"));

        intent.putExtra("ImageType", "SurveyStoreItemLocation");
        intent.putExtra("Barcode",getIntent().getStringExtra("Barcode"));

        intent.putExtra("RequestID", getIntent().getIntExtra("RequestID",0));

        intent.putExtra("AcceptedByStore", mAcceptedByStore);
        EditText editWhyNo = (EditText)  findViewById(R.id.editWhyNo);
        mWhyNo = editWhyNo.getText().toString();
        try {
            FindUnitAndChange(mStoreItemID, mStoreNameURN);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        startActivity(intent); finish();
    }

    public void Uploads(View view) {



        Intent me = getIntent();
        Intent intent = new Intent(YesNoUnit.this, InstallUnitaryList.class );
        //Sending data to another Activity

        intent.putExtra("StoreName", me.getStringExtra("StoreName"));
        intent.putExtra("StoreNameURN", me.getStringExtra("StoreNameURN"));
        intent.putExtra("URN", me.getStringExtra("URN"));

        Integer storeID = me.getIntExtra("StoreID", 0);
        intent.putExtra("StoreID", storeID);
        intent.putExtra("RequestID", me.getIntExtra("ID",0));


        intent.putExtra("AcceptedByStore", mAcceptedByStore);
        EditText editWhyNo = (EditText)  findViewById(R.id.editWhyNo);
        intent.putExtra("WhyNo", editWhyNo.getText().toString());
        mWhyNo = editWhyNo.getText().toString();

        if(mWhyNo.isEmpty() && mAcceptedByStore == 0)
        {
            Toast.makeText(getApplicationContext(), "Please enter the reason.", Toast.LENGTH_LONG).show();

        }
        else {
            Integer storeItemID = getIntent().getIntExtra("StoreItemID", 0);
            intent.putExtra("StoreItemID", storeItemID);

            try {
                FindUnitAndChange(mStoreItemID, mStoreNameURN);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            startActivity(intent); finish();
        }
    }

    public void Cancel(View view) {



        Intent me = getIntent();
        Intent intent = new Intent(YesNoUnit.this, InstallUnitaryList.class );
        //Sending data to another Activity

        intent.putExtra("StoreName", me.getStringExtra("StoreName"));
        intent.putExtra("StoreNameURN", me.getStringExtra("StoreNameURN"));
        intent.putExtra("URN", me.getStringExtra("URN"));

        Integer storeID = me.getIntExtra("StoreID", 0);
        intent.putExtra("StoreID", storeID);
        intent.putExtra("RequestID", me.getIntExtra("ID",0));

        intent.putExtra("AcceptedByStore", mAcceptedByStore);

        Integer storeItemID =  getIntent().getIntExtra("StoreItemID",0);
        intent.putExtra("StoreItemID", storeItemID);

        try {
            FindUnitAndChange(mStoreItemID, mStoreNameURN);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        startActivity(intent); finish();
    }





    public AndroidStoreUnitExplicit findStoreUnitByID(int id, ArrayList<AndroidStoreUnitExplicit> requests){
        for (AndroidStoreUnitExplicit request : requests) {
            if (request.getStoreItemID() == id) {
                return request;
            }
        }
        return null;
    }
    public void FindUnitAndChange( Integer ID, String storeNameURN) throws JSONException {

        try {

            //get them all
            String storesString = Local.Get(getApplicationContext(), "AndroidStoreUnitsExplicit");
            ArrayList<AndroidStoreUnitExplicit> androidStoreUnitExplicits = JsonUtil.parseJsonArrayAndroidStoreUnitExplicit(storesString, storeNameURN);
            //Get this one
            AndroidStoreUnitExplicit unitExplicit = findStoreUnitByID(ID, androidStoreUnitExplicits);

            //get pos
            Integer position = 0;
            Integer foundposition = 0;

            Iterator<AndroidStoreUnitExplicit> it = androidStoreUnitExplicits.iterator();
            while (it.hasNext()) {
                AndroidStoreUnitExplicit user = it.next();
                Integer findstoreItemID = unitExplicit.getStoreItemID();
                Integer nextStoreItemID = user.getStoreItemID();
                position++;
                if (findstoreItemID.equals(nextStoreItemID)) {
                    it.remove();
                    foundposition=position-1;
                }
            }

            //Clone

            AndroidStoreUnitExplicit storeUnitExplicit = new AndroidStoreUnitExplicit();
            storeUnitExplicit.setStoreName(unitExplicit.getStoreName());
            storeUnitExplicit.setStoreID( unitExplicit.getStoreID());
            storeUnitExplicit.setMaxQuantityFromMatrix( unitExplicit.getMaxQuantityFromMatrix());
            storeUnitExplicit.setImagePath( unitExplicit.getImagePath());
            storeUnitExplicit.setStoreItemID( unitExplicit.getStoreItemID());
            storeUnitExplicit.setIsSurvey(unitExplicit.getIsSurvey());
            storeUnitExplicit.setBarcode(unitExplicit.getBarcode());
            storeUnitExplicit.setEnumerate(unitExplicit.getEnumerate());
            storeUnitExplicit.setImageCount(unitExplicit.getImageCount());
            storeUnitExplicit.setItemTypeName(unitExplicit.getItemTypeName());
            storeUnitExplicit.setURN(unitExplicit.getURN());
            //storeUnitExplicit.setWhyNo(unitExplicit.getWhyNo());
            //delete original
            androidStoreUnitExplicits.remove(unitExplicit);
            //make changes to clone
            storeUnitExplicit.setAcceptedByStore(mAcceptedByStore);
            storeUnitExplicit.setWhyNo(mWhyNo);
            //add to list

            if(foundposition == position) {foundposition = position -1;}

            androidStoreUnitExplicits.add(foundposition, storeUnitExplicit);
            //Save back to local
            Local.Set(getApplicationContext(), "AndroidStoreUnitsExplicit", JsonUtil.convertAndroidStoreUnitExplicitArrayToJson(androidStoreUnitExplicits));


        } catch (Exception ex) {
            //ad.setTitle("Error!");
            //ad.setMessage(ex.toString());
        }
        //ad.show();

    }


}
