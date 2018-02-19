package com.vf.admin.vf_rep;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.w3c.dom.DocumentType;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class InstallUnitaryList extends AppCompatActivity {
    public boolean mIsSurvey = false;
    public  Integer mDocCount =0;
    GridView gridView;
    InstallGridViewUnitary gridViewCustomAdapter;
    ArrayList<AndroidStoreUnitExplicit> myunits;
    Integer RequestID;

    @Override
    public void onBackPressed() {     }

    @Override  protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.install_unitary_list_layout); // Daniel
        //Don
        setContentView(R.layout.new_upload_layout);



        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        String primaryRole = Local.Get( getApplicationContext(), "PrimaryRole");
        if (primaryRole.equals("Srv")){
            mIsSurvey = true;
        }

        if(mIsSurvey){
           //getActionBar().setTitle("Survey Unitary List");
            getSupportActionBar().setTitle("Survey Unitry List");  //
            Button btnExteriorImage = (Button) findViewById(R.id.btnExteriorImage);
            btnExteriorImage.setVisibility(View.VISIBLE);
            Button btnInteriorImage = (Button) findViewById(R.id.btnInteriorImage);
            btnInteriorImage.setVisibility(View.VISIBLE);

            TextView txtBarcodeHeader = (TextView)  findViewById(R.id.txtBarcodeHeader);
            txtBarcodeHeader.setText("Accepted");

        }
        else{
            Button btnUploadJobCard = (Button) findViewById(R.id.btnUploadJobCard);
            btnUploadJobCard.setVisibility(View.VISIBLE);
            Button btnProductComplaint = (Button) findViewById(R.id.btnProductComplaint);
            btnProductComplaint.setVisibility(View.VISIBLE);
            int storeID = getIntent().getIntExtra("StoreID", 0);
            String strDocCount = "0";
            ArrayList<AndroidDocumentType> docs = getDocs();
            AndroidDocumentType docCount = findDocumentCountByStoreIDAndType(storeID, "JobCardIns", docs);
           if(docCount != null) {
               mDocCount = docCount.getCount();
           }
            else
           {
               mDocCount = 0;
           }
            strDocCount = mDocCount.toString();
            btnUploadJobCard.setText(btnUploadJobCard.getText().toString() + " (" + strDocCount + " pages)" );

        }


        Intent me = getIntent();

        String ToastMessage = me.getStringExtra("Toast");
        if(!TextUtils.isEmpty(ToastMessage)) {
            Toast.makeText(this, ToastMessage, Toast.LENGTH_LONG).show();
        }


        TextView txtStoreName = (TextView)findViewById(R.id.txtStoreName);
        //TextView txtURN = (TextView)findViewById(R.id.txtURN);

        txtStoreName.setText(me.getStringExtra("StoreNameURN"));


        int StoreID = me.getIntExtra("StoreID",0);
        try {
            GetExtInteriorImageCount(StoreID, "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            GetExtInteriorImageCount(StoreID, "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestID = me.getIntExtra("RequestID",0);

        myunits = getstoreUnitsExplicit(me.getStringExtra("StoreNameURN"));

        gridView=(GridView)findViewById(R.id.gridViewCustom);
        // Create the Custom Adapter Object
        gridViewCustomAdapter = new InstallGridViewUnitary(this, myunits, mIsSurvey);
        // Set the Adapter to GridView
        gridView.setAdapter(gridViewCustomAdapter);

        //sets where list item is clicking too
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {

                Intent intent = new Intent();

                if(!mIsSurvey) {
                    intent = new Intent(InstallUnitaryList.this, UploadPhotoPrelim.class);
                }
                else {
                    intent = new Intent(InstallUnitaryList.this, YesNoUnit.class );
                }



                //Sending data to another Activity
                AndroidStoreUnitExplicit storeunit = myunits.get(pos);
                ///TODo: EXCELLENT: Get all data for saving the image as document...... Daniel

                //String storeNameURN = storeunit.getStoreName() + " " + storeunit.getURN();

                Integer Enumerate = storeunit.getEnumerate();
                intent.putExtra("Enumerate", Enumerate);

                Integer storeItemID = storeunit.getStoreItemID();

                intent.putExtra("StoreName", storeunit.getStoreName());
                intent.putExtra("StoreNameURN", storeunit.getStoreNameURN());


                intent.putExtra("AcceptedByStore", storeunit.getAcceptedByStore());
                intent.putExtra("WhyNo", storeunit.getWhyNo());
                intent.putExtra("URN", storeunit.getURN());

                intent.putExtra("StoreID", storeunit.getStoreID());
                intent.putExtra("StoreItemID", storeItemID);
                intent.putExtra("ImageType", "StoreItemInStore");
                intent.putExtra("Barcode", storeunit.getBarcode());

                intent.putExtra("RequestID", RequestID);
                intent.putExtra("ID", RequestID);

                startActivity(intent); finish();

            }

        } );



    }

    public void UploadimgeNew(View view) {

        Intent intent = new Intent(InstallUnitaryList.this, UploadPhoto.class );
        startActivity(intent); finish();

    }



    //this is the main menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        if (id == R.id.homebutton) {

            Intent intent = new Intent(InstallUnitaryList.this, MainActivity.class );

            startActivity(intent); finish();

            // return true;
        }



        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,Login.class));
        }
        if (id == R.id.backbutton){
            startActivity(new Intent(this,InstallAppointments.class));
        }


        return super.onOptionsItemSelected(item);
    }

    public boolean formIsValid(LinearLayout layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View v = layout.getChildAt(i);
            if (v instanceof EditText) {
                //validate your EditText here
            } else if (v instanceof RadioButton) {
                //validate RadioButton
            } //etc. If it fails anywhere, just return false.
        }
        return true;
    }


    public void GetExtInteriorImageCount(Integer StoreID, String DocumentType) throws JSONException {
        final AlertDialog ad=new AlertDialog.Builder(this).create();
        String isWebsiteAvailable = Local.Get(getApplicationContext(), "AmIOnline");

        if(isWebsiteAvailable.equals("True")) {
            //Save My Appointments - this will also 'delete' the request online
            MySOAPCallActivity cs1 = new MySOAPCallActivity();
            try {

                GetDocumentCountParams params = new GetDocumentCountParams(cs1, StoreID, DocumentType);

                new CallSoapGetDocumentCount().execute(params);
            } catch (Exception ex) {
                ad.setTitle("Error!");
                ad.setMessage(ex.toString());
            }
            ad.show();
        }






    }


    public void Close(View view) throws JSONException {
        final AlertDialog ad=new AlertDialog.Builder(this).create();
        String isWebsiteAvailable = Local.Get(getApplicationContext(), "AmIOnline");

        if(isWebsiteAvailable.equals("True")) {
            //Save My Appointments - this will also 'delete' the request online
            MySOAPCallActivity cs1 = new MySOAPCallActivity();
            try {

                SaveAndroidUnitExplicitsParams params = new SaveAndroidUnitExplicitsParams(cs1, Local.Get(getApplicationContext(),"AndroidStoreUnitsExplicit"));

                new CallSoapSaveAndroidUnitExplicits().execute(params);
            } catch (Exception ex) {
                ad.setTitle("Error!");
                ad.setMessage(ex.toString());
            }
            ad.show();
        }






    }



    public void ProductComplaint(View view)
    {

        Intent intent = new Intent(InstallUnitaryList.this, UploadPhoto.class );
        //augment upload photo to include text box for complaint

        //Sending data to another Activity

        Intent me = getIntent();

        //String storeNameURN = me.getStringExtra("StoreName") + " " + me.getStringExtra("URN");

        intent.putExtra("StoreName", me.getStringExtra("StoreName"));
        intent.putExtra("StoreNameURN", me.getStringExtra("StoreNameURN"));
        intent.putExtra("StoreID", me.getIntExtra("StoreID", 0));
        intent.putExtra("StoreItemID", 0);
        intent.putExtra("ImageType", "ProductComplaint");
        intent.putExtra("RequestID", RequestID);
        startActivity(intent); finish();

    }

    public void SurveyStoreExterior(View view)
    {

        Intent intent = new Intent(InstallUnitaryList.this, UploadPhoto.class );
        //augment upload photo to include text box for complaint

        //Sending data to another Activity

        Intent me = getIntent();

        //String storeNameURN = me.getStringExtra("StoreName") + " " + me.getStringExtra("URN");

        intent.putExtra("StoreName", me.getStringExtra("StoreName"));
        intent.putExtra("StoreNameURN", me.getStringExtra("StoreNameURN"));
        intent.putExtra("StoreID", me.getIntExtra("StoreID", 0));
        intent.putExtra("StoreItemID", 0);
        intent.putExtra("ImageType", "SurveyStoreExterior");
        intent.putExtra("RequestID", RequestID);
        startActivity(intent); finish();

    }

    public void SurveyStoreInterior(View view)
    {

        Intent intent = new Intent(InstallUnitaryList.this, UploadPhoto.class );
        //augment upload photo to include text box for complaint

        //Sending data to another Activity

        Intent me = getIntent();

        //String storeNameURN = me.getStringExtra("StoreName") + " " + me.getStringExtra("URN");

        intent.putExtra("StoreName", me.getStringExtra("StoreName"));
        intent.putExtra("StoreNameURN", me.getStringExtra("StoreNameURN"));
        intent.putExtra("StoreID", me.getIntExtra("StoreID", 0));
        intent.putExtra("StoreItemID", 0);
        intent.putExtra("ImageType", "SurveyStoreInterior");
        intent.putExtra("RequestID", RequestID);
        startActivity(intent); finish();

    }



    public void UploadJobCard(View view)
    {

        Intent intent = new Intent(InstallUnitaryList.this, UploadPhoto.class );
        //Sending data to another Activity

        Intent me = getIntent();



        intent.putExtra("StoreName", me.getStringExtra("StoreName"));
        intent.putExtra("StoreNameURN", me.getStringExtra("StoreNameURN"));
        intent.putExtra("StoreID", me.getIntExtra("StoreID", 0));
        intent.putExtra("DocCount", mDocCount);
        intent.putExtra("StoreItemID", 0);
        intent.putExtra("ImageType", "JobCardIns");
        intent.putExtra("RequestID",RequestID);
        startActivity(intent); finish();

    }


    private ArrayList<AndroidDocumentType> getDocs() {
        ArrayList<AndroidDocumentType> androidDocumentTypes = new ArrayList<>();
        String json = "[]";
        json = Local.Get(getApplicationContext(), "AndroidDocumentTypes");
        try {
            androidDocumentTypes = JsonUtil.parseJsonArrayAndroidDocumentTypes(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return androidDocumentTypes;
    }


    private ArrayList<AndroidStoreUnitExplicit> getstoreUnitsExplicit(String storeNameURN) {
        ArrayList<AndroidStoreUnitExplicit> storeUnitExplicits = new ArrayList<>();
        String json = "[]";
        json = Local.Get(getApplicationContext(), "AndroidStoreUnitsExplicit");
        try {
            storeUnitExplicits = JsonUtil.parseJsonArrayAndroidStoreUnitExplicit(json, storeNameURN);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return storeUnitExplicits;
    }

    public AndroidAppointment findAppointmentByID(int id, ArrayList<AndroidAppointment> requests){
        for (AndroidAppointment request : requests) {
            if (request.getID() == id) {
                return request;
            }
        }
        return null;
    }

    public AndroidDocumentType findDocumentCountByStoreIDAndType(int storeid, String doctype, ArrayList<AndroidDocumentType> jobcards){
        for (AndroidDocumentType jobcard : jobcards) {
            if (jobcard.getStoreID() == storeid && jobcard.getDocumentType().equals(doctype)) {
                return jobcard;
            }
        }
        return null;
    }


    public class CallSoapSaveAndroidUnitExplicits extends AsyncTask<SaveAndroidUnitExplicitsParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(SaveAndroidUnitExplicitsParams... params) {
            return params[0].foo.SaveAndroidUnitExplicits(params[0].jsonStoreItems);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {


                Intent intent = new Intent(InstallUnitaryList.this, InstallSetAppointment.class );

                //Sending data to another Activity
                String storesString = Local.Get(getApplicationContext(), "AndroidInsAppointments");

                ArrayList<AndroidAppointment> appointments = JsonUtil.parseJsonArrayAndroidAppointment(storesString);
                Integer ID = getIntent().getIntExtra("RequestID", 0);
                //Convert request to appointment
                AndroidAppointment appointment = findAppointmentByID(ID, appointments);
                intent.putExtra("ID", appointment.getID());
                intent.putExtra("Mileage", appointment.getMileage());
                intent.putExtra("StoreID", appointment.getStoreID());


                intent.putExtra("RequestTypeName", appointment.getRequestTypeName());
                intent.putExtra("StoreName", appointment.getStoreName());
                intent.putExtra("BrandName", appointment.getBrandName());
                intent.putExtra("OutletTypeName", appointment.getOutletTypeName());
                intent.putExtra("TierTypeName", appointment.getTierTypeName());

                intent.putExtra("StoreNameURN", appointment.getStoreNameURN());

                intent.putExtra("URN", appointment.getURN());


                intent.putExtra("CurrentPhase", appointment.getCurrentPhase());
                intent.putExtra("ContactPerson", appointment.getContactPerson());
                intent.putExtra("ContactEmail", appointment.getContactEmail());
                intent.putExtra("ContactPhone", appointment.getContactPhone());
                intent.putExtra("OpeningTime", appointment.getOpeningTime());
                intent.putExtra("ClosingTime", appointment.getClosingTime());
                intent.putExtra("TotalUnitCount", appointment.getTotalUnitCount());
                intent.putExtra("StoreID", appointment.getStoreID());
                intent.putExtra("DateRequested", appointment.getDateRequested());
                intent.putExtra("DateAccepted", appointment.getDateAccepted());
                intent.putExtra("AppointmentDateTime", appointment.getAppointmentDateTime());
                intent.putExtra("AppointmentDateDay", appointment.getAppointmentDateDay());
                intent.putExtra("AppointmentDateMonth", appointment.getAppointmentDateMonth());
                intent.putExtra("AppointmentDateYear", appointment.getAppointmentDateYear());
                intent.putExtra("AppointmentDateTimeTime", appointment.getAppointmentDateTimeTime());
                intent.putExtra("DateConfirmed", appointment.getDateConfirmed());
                intent.putExtra("DateRecordChanged", appointment.getDateRecordChanged());

                intent.putExtra("Address", appointment.getAddress());
                intent.putExtra("Territory", appointment.getTerritory());

                intent.putExtra("BrandName", appointment.getBrandName());
                intent.putExtra("OutletTypeName", appointment.getOutletTypeName());
                intent.putExtra("TierTypeName", appointment.getTierTypeName());

                startActivity(intent); finish();



            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class SaveAndroidUnitExplicitsParams {
        MySOAPCallActivity foo;
        String jsonStoreItems;



        SaveAndroidUnitExplicitsParams(MySOAPCallActivity foo, String jsonStoreItems) {
            this.foo = foo;
            this.jsonStoreItems = jsonStoreItems;


        }
    }




    public class CallSoapGetDocumentCount extends AsyncTask<GetDocumentCountParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(GetDocumentCountParams... params) {
            return params[0].foo.GetDocumentCount(params[0].StoreID, params[0].DocumentType);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {
               //now go to new intent
               // GoToCheckout(result);
                String[] RowData = result.split(",");

                String mDocumentType = RowData[0];
                String mDocumentCount = RowData[1];

                Local.Set(getApplicationContext(), mDocumentType, mDocumentCount);

                Button btnExteriorImage = (Button) findViewById(R.id.btnExteriorImage);
                Button btnInteriorImage = (Button) findViewById(R.id.btnInteriorImage);
                btnExteriorImage.setText("Exterior Image ("+ Local.Get(getApplicationContext(), "SurveyStoreExterior") + ")");
                btnInteriorImage.setText("Interior Image ("+ Local.Get(getApplicationContext(), "SurveyStoreInterior") + ")");

            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class GetDocumentCountParams {
        MySOAPCallActivity foo;
        Integer StoreID;
        String  DocumentType;


        GetDocumentCountParams(MySOAPCallActivity foo, Integer StoreID, String  DocumentType) {
            this.foo = foo;
            this.StoreID = StoreID;
            this.DocumentType = DocumentType;

        }
    }


    //SurveyStoreInterior

}