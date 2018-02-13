package com.vf.admin.vf_rep;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;

import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;


public class InstallSetAppointment extends AppCompatActivity  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private static final String TAG = "GpsGet";
    private TextView mLatitudeTextView;
    private TextView mLongitudeTextView;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private LocationManager mLocationManager;

    private LocationRequest mLocationRequest;
    private com.google.android.gms.location.LocationListener listener;
    private long UPDATE_INTERVAL = 1000 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */

    private LocationManager locationManager;

    private int hour;
    private int minute;

    public boolean mIsSurvey = false;

    static final int TIME_DIALOG_ID = 999;
    static final int DATE_DIALOG_ID = 0;




    protected String parseTimeForDisplay(String dateTimeWithT)
    {

        dateTimeWithT = dateTimeWithT.replace('T',' ');

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");

        try {
            calendar.setTime(simpleDateFormat.parse(dateTimeWithT));

            String dateToShow = simpleTimeFormat.format(calendar.getTime());
            return dateToShow;

        } catch (ParseException e) {
            e.printStackTrace();
            return dateTimeWithT;
        }


    }
    @Override
    public void onBackPressed() {     }

    @Override  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.install_set_appointment_layout);

        //new check for is survey
        String primaryRole = Local.Get( getApplicationContext(), "PrimaryRole");
        if (primaryRole.equals("Srv")){
            mIsSurvey = true;
        }



        setContentView(R.layout.install_set_appointment_layout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(mIsSurvey){
            Button btnSurvey =  (Button) findViewById(R.id.btnSurvey);
            btnSurvey.setVisibility(View.VISIBLE);
           // getActionBar().setTitle("Survey Appointment");
            getSupportActionBar().setTitle("Survey Appointment");  //
        }

        String Today = Local.Get(getApplicationContext(), "Today");
        String CheckedIn = getIntent().getStringExtra("URN")+":CheckedIn:"+Today;
        Button btnCheckin =  (Button) findViewById(R.id.btnCheckin);
        if(Local.Get(getApplicationContext(),CheckedIn).equals("True") && btnCheckin.getVisibility()== View.VISIBLE){
            //Toast.makeText(this, "Only one checkin allowed per store per day.", Toast.LENGTH_LONG).show();

            btnCheckin.setVisibility(View.GONE);
        }
        else {

            btnCheckin.setVisibility(View.VISIBLE);
        }

        GetSpinnerDataFromWS();


        Button btnCheckout =  (Button) findViewById(R.id.btnCheckout);
        String CheckedOut = getIntent().getStringExtra("URN")+":CheckedOut:"+Today;
        if(Local.Get(getApplicationContext(),CheckedOut).equals("True")&& btnCheckout.getVisibility()== View.VISIBLE){
            //Toast.makeText(this, "Only one checkout allowed per store per day.", Toast.LENGTH_LONG).show();

            btnCheckout.setVisibility(View.GONE);
        }
        else {

            btnCheckout.setVisibility(View.VISIBLE);
        }



        Intent me = getIntent();

        String ToastMessage = me.getStringExtra("Toast");
        if(!TextUtils.isEmpty(ToastMessage)) {
            if(ToastMessage.contains("Thank"))
            {
                Toast.makeText(this, ToastMessage, Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, ToastMessage, Toast.LENGTH_LONG).show();
            }
        }


        TextView ID =  (TextView)findViewById(R.id.txtID);
        Integer id = me.getIntExtra("ID", -1);
        ID.setText(id.toString());

        Integer storeid = me.getIntExtra("StoreID", -1);
        GetUnitListsForUserExplicit(Local.Get(getApplicationContext(), "UserName"), mIsSurvey, storeid);//installation only - for survey true)


        TextView StoreName =  (TextView)findViewById(R.id.txtStoreName);
        StoreName.setText(me.getStringExtra("StoreName"));
        TextView URN =  (TextView)findViewById(R.id.txtURN);
        URN.setText(me.getStringExtra("URN"));
        //TextView CurrentPhase =  (TextView)findViewById(R.id.txtProgress);
        //CurrentPhase.setText(me.getStringExtra("CurrentPhase"));
        TextView txtCurrentPhase =  (TextView)findViewById(R.id.txtCurrentPhase);
        txtCurrentPhase.setText(me.getStringExtra("CurrentPhase"));

        TextView ContactPerson =  (TextView)findViewById(R.id.txtContactPerson);
        ContactPerson.setText(me.getStringExtra("ContactPerson"));
        TextView ContactEmail =  (TextView)findViewById(R.id.txtContactEmail);
        ContactEmail.setText(me.getStringExtra("ContactEmail"));
        TextView ContactPhone =  (TextView)findViewById(R.id.txtContactPhone);
        ContactPhone.setText(me.getStringExtra("ContactPhone"));
        TextView OpeningTime =  (TextView)findViewById(R.id.txtOpeningTime);
        OpeningTime.setText(parseTimeForDisplay(me.getStringExtra("OpeningTime")));
        TextView ClosingTime =  (TextView)findViewById(R.id.txtClosingTime);
        ClosingTime.setText(parseTimeForDisplay(me.getStringExtra("ClosingTime")));
        TextView TotalUnitCount =  (TextView)findViewById(R.id.txtTotalUnitCount);
        Integer totalUnitCount = me.getIntExtra("TotalUnitCount", -1);
        TotalUnitCount.setText(totalUnitCount.toString());

        TextView txtRegion =  (TextView)findViewById(R.id.txtRegion);
        txtRegion.setText(me.getStringExtra("Region"));

        TextView txtBrand =  (TextView)findViewById(R.id.txtBrand);
        txtBrand.setText(me.getStringExtra("BrandName"));
        TextView txtOutletType =  (TextView)findViewById(R.id.txtOutletType);
        txtOutletType.setText(me.getStringExtra("OutletTypeName"));
        TextView txtTierTypeName =  (TextView)findViewById(R.id.txtTierTypeName);
        txtTierTypeName.setText(me.getStringExtra("TierTypeName"));

        TextView txtAddress =  (TextView)findViewById(R.id.txtAddress);
        txtAddress.setText(me.getStringExtra("Address"));


      //  EditText editMileage = (EditText)findViewById(R.id.editMileage);
       // Integer mileage = me.getIntExtra("Mileage",0);
       // editMileage.setText(mileage.toString());

        String appointmentDateTime = me.getStringExtra("AppointmentDateTime");
        appointmentDateTime = appointmentDateTime.replace('T',' ');

        TextView AppointmentDateTime = (TextView)findViewById(R.id.txtAppointmentDateTime);
        AppointmentDateTime.setText(appointmentDateTime);

//GPS STuff
     //   mLatitudeTextView = (TextView) findViewById((R.id.latitude_textview));
     //   mLongitudeTextView = (TextView) findViewById((R.id.longitude_textview));

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);



        checkLocation(); //check whether location service is enable or not in your  phone


    }


    //GPS Stuff

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
            return;
        }

        //startLocationUpdates();

        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if(mLocation == null){
            startLocationUpdates();
        }
        if (mLocation != null) {

            // mLatitudeTextView.setText(String.valueOf(mLocation.getLatitude()));
            //mLongitudeTextView.setText(String.valueOf(mLocation.getLongitude()));
        } else {
            Toast.makeText(this, "Location not Detected", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }


    public void Survey(View view) {

        Intent me = getIntent();
        Intent intent = new Intent(InstallSetAppointment.this, SurveyActivity.class );
        //Sending data to another Activity

        intent.putExtra("StoreName", me.getStringExtra("StoreName"));
        intent.putExtra("StoreNameURN", me.getStringExtra("StoreNameURN"));
        intent.putExtra("URN", me.getStringExtra("URN"));

        Integer storeID = me.getIntExtra("StoreID", 0);
        intent.putExtra("StoreID", storeID);
        intent.putExtra("RequestID", me.getIntExtra("ID",0));


        startActivity(intent); finish();
    }


    public void Uploads(View view) {

        Intent me = getIntent();
        Intent intent = new Intent(InstallSetAppointment.this, InstallUnitaryList.class );
        //Sending data to another Activity

        intent.putExtra("StoreName", me.getStringExtra("StoreName"));
        intent.putExtra("StoreNameURN", me.getStringExtra("StoreNameURN"));
        intent.putExtra("URN", me.getStringExtra("URN"));

        Integer storeID = me.getIntExtra("StoreID", 0);
        intent.putExtra("StoreID", storeID);
        intent.putExtra("RequestID", me.getIntExtra("ID",0));


        startActivity(intent); finish();
    }

    public void CheckIn(View view) {

        startLocationUpdates();

    }


    protected void startLocationUpdates() {
        // Create the location request
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);
        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this);
        Log.d("reque", "--->>>>");
    }

    @Override
    public void onLocationChanged(Location location) {

        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        //mLatitudeTextView.setText(String.valueOf(location.getLatitude()));
        //mLongitudeTextView.setText(String.valueOf(location.getLongitude() ));
        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        // You can now create a LatLng Object for use with maps
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        String strLatLng = String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude() );
        Boolean IsCheckin = Boolean.parseBoolean(Local.Get(getApplicationContext(), "IsCheckin"));

        Intent me = getIntent();
        Integer RequestID = me.getIntExtra("ID",0);

        //HandleGpsCheckIn(IsCheckin, strLatLng, RequestID);
    }

    private boolean checkLocation() {
        if(!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }



    //GPS stuff End



    public void HowDatesWork()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse("2014/11/20 8:10:00 AM"));

            Log.e("date", "" + calendar.get(Calendar.DAY_OF_MONTH));

            Log.e("month", ""+calendar.get(Calendar.MONTH));

            Log.e("year", ""+calendar.get(Calendar.YEAR));

            Log.e("hour", ""+calendar.get(Calendar.HOUR));

            Log.e("minutes", ""+calendar.get(Calendar.MINUTE));

            Log.e("seconds", ""+calendar.get(Calendar.SECOND));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    /** Create a new dialog for date picker */




    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public AndroidAppointment findAppointmentByID(int id, ArrayList<AndroidAppointment> requests){
        for (AndroidAppointment request : requests) {
            if (request.getID() == id) {
                return request;
            }
        }
        return null;
    }




    public void GpsCheckin(View view)
    {
        Intent intent = new Intent(InstallSetAppointment.this, InstallGpsCheckin.class );
        Intent me = getIntent();
        //String storeNameURN = me.getStringExtra("StoreName") + " " + me.getStringExtra("URN");
        //intent.putExtra("StoreNameURN", storeNameURN);
        intent.putExtra("StoreNameURN", me.getStringExtra("StoreNameURN"));
        intent.putExtra("StoreName", me.getStringExtra("StoreName"));
        intent.putExtra("URN",  me.getStringExtra("URN"));
        intent.putExtra("RequestID",  me.getIntExtra("ID",0));

        startActivity(intent); finish();

    }
    public void GpsCheckOut(View view)
    {
        //First check install status
        final AlertDialog ad=new AlertDialog.Builder(this).create();
        String isWebsiteAvailable = Local.Get(getApplicationContext(), "AmIOnline");

        if(isWebsiteAvailable.equals("True")) {
           
            if(!mIsSurvey) {
                MySOAPCallActivity cs1 = new MySOAPCallActivity();
                try {

                    Intent me = getIntent();
                    Integer StoreID = me.getIntExtra("StoreID", 0);

                    IsInstallCompleteParams params = new IsInstallCompleteParams(cs1, StoreID);

                    new CallSoapIsInstallComplete().execute(params);
                } catch (Exception ex) {
                    ad.setTitle("Error!");
                    ad.setMessage(ex.toString());
                }
                ad.show();
            }
            else{
                MySOAPCallActivity cs1 = new MySOAPCallActivity();
                try {

                    Intent me = getIntent();
                    Integer StoreID = me.getIntExtra("StoreID", 0);

                    IsSurveyCompleteParams params = new IsSurveyCompleteParams(cs1, StoreID);

                    new CallSoapIsSurveyComplete().execute(params);
                } catch (Exception ex) {
                    ad.setTitle("Error!");
                    ad.setMessage(ex.toString());
                }
                ad.show();
            }
        }

    }

    public void GoToCheckout(String isComplete)
    {
        Intent intent = new Intent(InstallSetAppointment.this, InstallGpsCheckOut.class );

        Intent me = getIntent();

        intent.putExtra("StoreNameURN", me.getStringExtra("StoreNameURN"));
        intent.putExtra("StoreName", me.getStringExtra("StoreName"));
        intent.putExtra("URN",  me.getStringExtra("URN"));
        intent.putExtra("RequestID",  me.getIntExtra("ID",0));

        if(!mIsSurvey) {
            intent.putExtra("IsInstallComplete", isComplete);
        }
        else {
            intent.putExtra("IsSurveyComplete", isComplete);
        }
        startActivity(intent); finish();


    }


    public void ConfirmAppointment(View view) throws JSONException {

        TextView txtID =  (TextView)findViewById(R.id.txtID);
        Integer ID = Integer.parseInt(txtID.getText().toString());

        //Save Appointment changes
        AndroidAppointment s = new AndroidAppointment();

        TextView storeName = (TextView) findViewById(R.id.txtStoreName);
        s.StoreName = storeName.getText().toString();


        TextView ContactPerson = (TextView) findViewById(R.id.txtContactPerson);
        s.ContactPerson = ContactPerson.getText().toString();
        TextView ContactEmail = (TextView) findViewById(R.id.txtContactEmail);
        s.ContactEmail = ContactEmail.getText().toString();
        TextView ContactPhone = (TextView) findViewById(R.id.txtContactPhone);
        s.ContactPhone = ContactPhone.getText().toString();

        //EditText editMileage = (EditText)findViewById(R.id.editMileage);
        //s.setMileage(Integer.parseInt( editMileage.getText().toString()));

        s.setUserName(Local.Get(getApplicationContext(), "UserName"));

        SaveAppointmentToArray(s, ID);

        SaveIfOnline();
        // startActivity(new Intent(this,MainLogin.class));
        Intent intent = new Intent(InstallSetAppointment.this, MainActivity.class );

        startActivity(intent); finish();

    }


    public void SaveAppointmentToArray(AndroidAppointment appointment, Integer ID) throws JSONException {

        try {

            String storesString = Local.Get(getApplicationContext(), "AndroidInsAppointments");

            ArrayList<AndroidAppointment> appointments = JsonUtil.parseJsonArrayAndroidAppointment(storesString);


            AndroidAppointment findappointment = findAppointmentByID(ID, appointments);
            appointment.setRequestTypeName( findappointment.getRequestTypeName());
            appointment.setID( findappointment.getID());
            //appointment.setMileage( findappointment.getMileage());
            appointment.setStoreID( findappointment.getStoreID());
            appointment.setDateRequested( findappointment.getDateRequested());
            appointment.setDateAccepted( findappointment.getDateAccepted());
            appointment.setStoreID( findappointment.getStoreID());
            appointment.setAppointmentDateTime(findappointment.getAppointmentDateTime());
            appointments.remove(findappointment);

            Date recordChangedNow = new Date();

            appointment.setDateRecordChanged((String) android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss a", recordChangedNow));

            appointments.add(appointment);

            //Save Requests back to local
            Local.Set(getApplicationContext(), "AndroidInsAppointments", JsonUtil.convertAppointmentArrayToJson(appointments));


        } catch (Exception ex) {
            //ad.setTitle("Error!");
            //ad.setMessage(ex.toString());
        }
        //ad.show();

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

            Intent intent = new Intent(InstallSetAppointment.this, MainActivity.class );

            startActivity(intent); finish();

            // return true;
        }



        if (id == R.id.logoutbutton){
            startActivity(new Intent(this,LoginActivity.class));
        }

        if (id == R.id.backbutton){
            startActivity(new Intent(this,InstallAppointments.class));
        }

        return super.onOptionsItemSelected(item);
    }


    public void SaveIfOnline()
    {
        final AlertDialog ad=new AlertDialog.Builder(this).create();
        String isWebsiteAvailable = Local.Get(getApplicationContext(), "AmIOnline");

        if(isWebsiteAvailable.equals("True")) {
            //Save My Appointments - this will also 'delete' the request online
            MySOAPCallActivity cs1 = new MySOAPCallActivity();
            try {


                String myappointments = Local.Get(getApplicationContext(), "AndroidInsAppointments");
                SaveMyAppointmentsParams params = new SaveMyAppointmentsParams(cs1, myappointments);

                new CallSoapSaveMyAppointments().execute(params);
            } catch (Exception ex) {
                ad.setTitle("Error!");
                ad.setMessage(ex.toString());
            }
            ad.show();
        }

    }
    public class CallSoapSaveMyAppointments extends AsyncTask<SaveMyAppointmentsParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(SaveMyAppointmentsParams... params) {
            return params[0].foo.SaveMyAppointments(params[0].jsonRequests);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {


                Local.Set(getApplicationContext(), "AndroidInsAppointments", "");


            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class SaveMyAppointmentsParams {
        MySOAPCallActivity foo;
        String jsonRequests;



        SaveMyAppointmentsParams(MySOAPCallActivity foo, String jsonRequests) {
            this.foo = foo;
            this.jsonRequests = jsonRequests;


        }
    }

    public class CallSoapIsInstallComplete extends AsyncTask<IsInstallCompleteParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(IsInstallCompleteParams... params) {
            return params[0].foo.IsInstallComplete(params[0].StoreID);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {


               //now go to new intent
                GoToCheckout(result);

            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class IsInstallCompleteParams {
        MySOAPCallActivity foo;
        Integer StoreID;



        IsInstallCompleteParams(MySOAPCallActivity foo, Integer StoreID) {
            this.foo = foo;
            this.StoreID = StoreID;


        }
    }

    public void GetSpinnerDataFromWS(){
        final AlertDialog ad=new AlertDialog.Builder(this).create();
        MySOAPCallActivity cs1 = new MySOAPCallActivity();
        try {
            String UserName;

            InstallStatusListParams params = new InstallStatusListParams(cs1);

            new CallSoapInstallStatusList().execute(params);
        } catch (Exception ex) {
            ad.setTitle("Error!");
            ad.setMessage(ex.toString());
        }
        ad.show();


    }



    public class CallSoapInstallStatusList extends AsyncTask<InstallStatusListParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(InstallStatusListParams... params) {
            return params[0].foo.InstallStatusList();
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {


                //TODO: return to Home?
                Local.Set(getApplicationContext(), "InstallStatusList", result);




            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class InstallStatusListParams {
        MySOAPCallActivity foo;

        InstallStatusListParams(MySOAPCallActivity foo) {
            this.foo = foo;


        }
    }

    public class CallSoapIsSurveyComplete extends AsyncTask<IsSurveyCompleteParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(IsSurveyCompleteParams... params) {
            return params[0].foo.IsSurveyComplete(params[0].StoreID);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {


                //now go to new intent
                GoToCheckout(result);

            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class IsSurveyCompleteParams {
        MySOAPCallActivity foo;
        Integer StoreID;



        IsSurveyCompleteParams(MySOAPCallActivity foo, Integer StoreID) {
            this.foo = foo;
            this.StoreID = StoreID;


        }
    }

    public void GetUnitListsForUserExplicit(String UserName, Boolean IsSurvey, Integer StoreID) {

        String isWebsiteAvailable = Local.Get(getApplicationContext(), "AmIOnline");

        if(isWebsiteAvailable.equals("True")) {

            final AlertDialog ad = new AlertDialog.Builder(this).create();
            MySOAPCallActivity cs = new MySOAPCallActivity();
            try {

                GetUnitListsForUserExplicitParams params = new GetUnitListsForUserExplicitParams(cs, UserName, IsSurvey, StoreID);

                new CallSoapGetUnitListsForUserExplicit().execute(params);


            } catch (Exception ex) {
                ad.setTitle("Error!");
                ad.setMessage(ex.toString());
            }
            ad.show();

        }
        else {
            //I am OFFLINE
            //Do Nothing - you'll just use the stores you have :)


        }
    }


    public class CallSoapGetUnitListsForUserExplicit extends AsyncTask<GetUnitListsForUserExplicitParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(GetUnitListsForUserExplicitParams... params) {
            return params[0].foo.GetUnitListsForUserExplicit(params[0].username, params[0].issurvey, params[0].storeid);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {
                //process Json Result
                //Save results in local storage
                if(result.toLowerCase().contains("error") || result.toLowerCase().contains("exception")){
                    Button btnUnitaryLists = (Button) findViewById(R.id.UnitaryLists);
                    btnUnitaryLists.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Getting data timed out. Please try again.", Toast.LENGTH_LONG).show();
                }
                else {
                    Local.Set(getApplicationContext(), "AndroidStoreUnitsExplicit", result);
                    //Button btnUnitaryLists = (Button) findViewById(R.id.UnitaryLists);
                    //btnUnitaryLists.setVisibility(View.VISIBLE);
                }

            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class GetUnitListsForUserExplicitParams {
        MySOAPCallActivity foo;
        String username;
        Boolean issurvey;
        Integer storeid;


        GetUnitListsForUserExplicitParams(MySOAPCallActivity foo, String username, Boolean issurvey, Integer storeid) {
            this.foo = foo;
            this.username = username;
            this.issurvey = issurvey;
            this.storeid=storeid;
        }
    }


}

