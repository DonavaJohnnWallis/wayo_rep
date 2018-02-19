package com.vf.admin.vf_rep;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class InstallGpsCheckoutSiteComplete extends AppCompatActivity   implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{

    private Spinner  spinner2;

    private static final String TAG = "GpsGet";
    private TextView mLatitudeTextView;
    private TextView mLongitudeTextView;
    private TextView txtGpsLatLng;
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

    private Boolean mIsCheckIn;
    private String mGpsLatLng;
    private Integer mRequestID;
    private String mInstallOrSurveyStatus;
    private Integer mMileage;
    private String incomingStatus;
    public boolean mIsSurvey = false;

    static final int TIME_DIALOG_ID = 999;
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_store_checkout);

        String primaryRole = Local.Get( getApplicationContext(), "PrimaryRole");
        if (primaryRole.equals("Srv")){
            mIsSurvey = true;
        }

try {
    Intent me = getIntent();
    TextView txtStoreName = (TextView)findViewById(R.id.txtStoreName);
    TextView txtURN = (TextView)findViewById(R.id.txtURN);

    txtStoreName.setText(me.getStringExtra("StoreName"));
    txtURN.setText(me.getStringExtra("URN"));




    Spinner spinnerInstallStatus = (Spinner) findViewById(R.id.spinnerInstallStatus);

    if(!mIsSurvey) {
        incomingStatus = me.getStringExtra("InstallStatus");
        if (incomingStatus.equals("Installation Complete")) {
            //Hide spinner if site complete
            spinnerInstallStatus.setVisibility(View.INVISIBLE);
        }

        populatespinner();
    }
    else {
        incomingStatus = me.getStringExtra("SurveyStatus");
        if (incomingStatus.equals("Survey Complete")) {
            //Hide spinner if site complete
            spinnerInstallStatus.setVisibility(View.INVISIBLE);
        }

        addItemsOnSurveySpinner();
    }
    mGoogleApiClient = new GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build();

    mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


    checkLocation(); //check whether location service is enable or not in your  phone

}
catch(Exception ex)
{

}
    }

    public void addItemsOnSurveySpinner() {

        spinner2 = (Spinner) findViewById(R.id.spinnerInstallStatus);
        List<String> list = new ArrayList<String>();
        list.add(0, "Reason");
        list.add(1, "Could not complete - Store");
        spinner2.setPrompt("Choose Reason");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinnerstyle, list);
        dataAdapter.setDropDownViewResource(R.layout.spinnerstyledrop);
        spinner2.setAdapter(dataAdapter);
    }

    public void CheckOut(View view) {


        Local.Set(getApplicationContext(), "IsCheckin", "false");
        startLocationUpdates();

    }


    public void CheckoutSaveAppointmentMileage(View view)
    {
        Intent me = getIntent();

        String InstallStatus = me.getStringExtra("InstallStatus");
        String StoreName = me.getStringExtra("StoreName");

        //GPS checkout stuff with webservice call including mileage and emails
        //HandleGpsCheckIn();

        Intent intent = new Intent(InstallGpsCheckoutSiteComplete.this, MainActivity.class);
        startActivity(intent); finish();
    }


    protected void startLocationUpdates() {
        // Create the location request
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);
        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 3);
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 4);


            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        Log.d("reque", "--->>>>");
    }

    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.CAMERA}, 2);
        }
    }



    //GPS Stuff

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
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
        Integer RequestID = me.getIntExtra("RequestID",0);


        //display GPS
        txtGpsLatLng =  (TextView) findViewById(R.id.txtGpsLatLng);
        txtGpsLatLng.setText(strLatLng);
        //set public variables
        mIsCheckIn = IsCheckin;
        mGpsLatLng = strLatLng;
        mRequestID = RequestID;


        Toast.makeText(this, "GPS location found. Now click Home", Toast.LENGTH_LONG).show();
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



    public void HandleGpsCheckIn(Boolean IsCheckIn, String GpsLatLng, Integer RequestID, String InstallStatus, Integer Mileage)
    {
        final AlertDialog ad=new AlertDialog.Builder(this).create();
        MySOAPCallActivity cs1 = new MySOAPCallActivity();
        try {
            String UserName;

            UserName = Local.Get(getApplicationContext(), "UserName");

            GpsCheckinParams params = new GpsCheckinParams(cs1, UserName, RequestID, GpsLatLng, IsCheckIn, InstallStatus, Mileage);

            new CallSoapGpsCheckin().execute(params);
        } catch (Exception ex) {
            ad.setTitle("Error!");
            ad.setMessage(ex.toString());
        }
        ad.show();


    }

    public void Cancel(View view) {
        Intent intent = new Intent(InstallGpsCheckoutSiteComplete.this, InstallSetAppointment.class );
        ArrayList<AndroidAppointment> myappointments;

        myappointments = getAppointmentsData();

        //Sending data to another Activity
        int ID = getIntent().getIntExtra("RequestID",0);
        AndroidAppointment appointment = findAppointmentByID(ID, myappointments);

        intent.putExtra("ID", appointment.getID());
        intent.putExtra("Mileage", appointment.getMileage());
        intent.putExtra("StoreID", appointment.getStoreID());


        intent.putExtra("RequestTypeName", appointment.getRequestTypeName());
        intent.putExtra("StoreName", appointment.getStoreName());
        intent.putExtra("StoreNameURN", appointment.getStoreNameURN());

        intent.putExtra("BrandName", appointment.getBrandName());
        intent.putExtra("OutletTypeName", appointment.getOutletTypeName());
        intent.putExtra("TierTypeName", appointment.getTierTypeName());


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

    }



    public void Home(View view) {
        if(mGpsLatLng !=null && !mGpsLatLng.isEmpty()) {

            EditText editMileage = (EditText)findViewById(R.id.editMileage1);
            String strMileage = editMileage.getText().toString();
            mMileage = Integer.parseInt(strMileage);
            Spinner spinnerInstallStatus=(Spinner) findViewById(R.id.spinnerInstallStatus);

            if(spinner2.isShown())
            {
                mInstallOrSurveyStatus = spinnerInstallStatus.getSelectedItem().toString();
            }
            else
            {
                mInstallOrSurveyStatus = incomingStatus; //complete
            }

            if(mInstallOrSurveyStatus.equals("Reason"))
            {
                if(!mIsSurvey) {//none selected when they should have
                    Toast.makeText(this, "Please select reason installation is incomplete from dropdown.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(this, "Please select reason survey is incomplete from dropdown.", Toast.LENGTH_LONG).show();
                }
            }
            else {

                String Today = Local.Get(getApplicationContext(), "Today");
                String CheckedOut = getIntent().getStringExtra("URN")+":CheckedOut:"+Today;
                if(Local.Get(getApplicationContext(),CheckedOut).equals("True")){
                    Toast.makeText(this, "Only one checkout allowed per store per day.", Toast.LENGTH_LONG).show();
                }
                else {
                    HandleGpsCheckIn(mIsCheckIn, mGpsLatLng, mRequestID, mInstallOrSurveyStatus, mMileage);
                }
            }
        }
        else
        {
            Toast.makeText(this, "Gps not collected yet. Move device and click checkout again", Toast.LENGTH_LONG).show();

        }

    }

public void populatespinner()
{
    ArrayList<AndroidInstallStatus> androidInstallStatuses;

    androidInstallStatuses = getInstallStatusData();

    spinner2 = (Spinner) findViewById(R.id.spinnerInstallStatus);
    List<String> list = new ArrayList<String>();
    list.add(0, "Reason");
    //list.add("Delivery Incomplete");
    //list.add("Units not Delivered");

    for (int i=0; i<androidInstallStatuses.size(); i++) {
        list.add(androidInstallStatuses.get(i).getStatusName());
    }


    spinner2.setPrompt("Choose Reason");
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
            R.layout.spinnerstyle, list);
    dataAdapter.setDropDownViewResource(R.layout.spinnerstyledrop);
    spinner2.setAdapter(dataAdapter);


}




    public class CallSoapGpsCheckin extends AsyncTask<GpsCheckinParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(GpsCheckinParams... params) {
            return params[0].foo.GpsCheckin(params[0].UserName,params[0].RequestID,params[0].GpsLatLng,params[0].IsCheckIn,params[0].InstallStatus,params[0].Mileage);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {



                //TODO: return to Home?
                Local.Set(getApplicationContext(), "CurrentGps", result);



                Intent me = getIntent();


                String Today = Local.Get(getApplicationContext(), "Today");
                Local.Set(getApplicationContext(),  me.getStringExtra("URN") + ":CheckedOut:" + Today, "True");
                Integer ID = me.getIntExtra("RequestID",0);

                Intent intent = new Intent(InstallGpsCheckoutSiteComplete.this, InstallSetAppointment.class );

                intent.putExtra("Toast", "Check out successful.");



                ArrayList<AndroidAppointment> myappointments;

                myappointments = getAppointmentsData();

                //Sending data to another Activity
                AndroidAppointment appointment = findAppointmentByID(ID, myappointments);

                intent.putExtra("ID", appointment.getID());
                intent.putExtra("Mileage", appointment.getMileage());
                intent.putExtra("StoreID", appointment.getStoreID());


                intent.putExtra("RequestTypeName", appointment.getRequestTypeName());
                intent.putExtra("BrandName", appointment.getBrandName());
                intent.putExtra("OutletTypeName", appointment.getOutletTypeName());
                intent.putExtra("TierTypeName", appointment.getTierTypeName());




                intent.putExtra("StoreName", appointment.getStoreName());
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
    private static class GpsCheckinParams {
        MySOAPCallActivity foo;
        String UserName; Integer RequestID; String GpsLatLng; Boolean IsCheckIn; String InstallStatus; Integer Mileage;


        GpsCheckinParams(MySOAPCallActivity foo, String UserName, Integer RequestID, String GpsLatLng, Boolean IsCheckIn, String InstallStatus, Integer Mileage) {
            this.foo = foo;
            this.UserName = UserName;
            this.RequestID = RequestID;
            this.GpsLatLng = GpsLatLng;
            this.IsCheckIn = IsCheckIn;
            this.InstallStatus = InstallStatus;
            this.Mileage = Mileage;


        }
    }

    private ArrayList<AndroidInstallStatus> getInstallStatusData() {
        ArrayList<AndroidInstallStatus> appointments = new ArrayList<>();
        String json = "";
        json = Local.Get(getApplicationContext(), "InstallStatusList");
        try {
            appointments = JsonUtil.parseJsonArrayAndroidInstallStatus(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return appointments;
    }



    private ArrayList<AndroidAppointment> getAppointmentsData() {
        ArrayList<AndroidAppointment> appointments = new ArrayList<>();
        String json = "";
        json = Local.Get(getApplicationContext(), "AndroidInsAppointments");
        try {
            appointments = JsonUtil.parseJsonArrayAndroidAppointment(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public AndroidAppointment findAppointmentByID(int id, ArrayList<AndroidAppointment> requests){
        for (AndroidAppointment request : requests) {
            if (request.getID() == id) {
                return request;
            }
        }
        return null;
    }



}
