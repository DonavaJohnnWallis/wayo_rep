package com.vf.admin.vf_rep;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;


import android.*;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Login extends AppCompatActivity {

    boolean isNetworkAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);


           AmIOnlineAtAll();

        checkCameraPermission();
        checkWritePermission();


        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        EditText userName = (EditText) findViewById(R.id.editUserName);

        if(Local.isSet(getApplicationContext(),"UserName")) {
            userName.setText(Local.Get(getApplicationContext(), "UserName"));
        }
        isNetworkAvailable = isNetworkAvailable();

    }






    private void checkWritePermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.CAMERA}, 2);
        }
    }




    @Override
    public void onBackPressed() {     }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void AmIOnlineAtAll()
    {

        MySOAPCallActivity cs1 = new MySOAPCallActivity();
        try {

            AmIOnlineParams params = new AmIOnlineParams(cs1);
            new CallSoapAmIOnline().execute(params);


        } catch (Exception ex) {

        }
    }

    public void Reset(View view)
    {
        Local.Set(getApplicationContext(), "AndroidTssOpenRequests", "");
        Local.Set(getApplicationContext(), "AndroidInsOpenRequests", "");
        Local.Set(getApplicationContext(), "AndroidWhsOpenRequests", "");
        Local.Set(getApplicationContext(), "AndroidTssAppointments", "");
        Local.Set(getApplicationContext(), "AndroidInsAppointments", "");
        Local.Set(getApplicationContext(), "AndroidWhsAppointments", "");
        Local.Set(getApplicationContext(), "AndroidStoreUnits", "");
        Local.Set(getApplicationContext(), "AndroidStoreUnitsRep", "");
        Local.Set(getApplicationContext(), "AndroidStoreUnitsIns", "");
        Local.Set(getApplicationContext(), "AndroidStoreUnitsTss", "");
        Local.Set(getApplicationContext(), "AndroidStoreUnitsWhs", "");
        Local.Set(getApplicationContext(), "UserName", "");

        Local.Set(getApplicationContext(), "AndroidStores", "");
        Local.Set(getApplicationContext(), "AndroidStoresIns", "");
        Local.Set(getApplicationContext(), "AndroidStoresWhs", "");
        Local.Set(getApplicationContext(), "AndroidStoresTss", "");

        Local.Set(getApplicationContext(), "AndroidSurveys", "");

        String Today = Local.Get(getApplicationContext(), "Today");
        //Local.Set(getApplicationContext(), "ResetAllCheckins", "False");

        String resetcheckins = Local.Get(getApplicationContext(), "ResetAllCheckins");
        if(resetcheckins.equals("True")|| TextUtils.isEmpty(resetcheckins))
        {
            Local.Set(getApplicationContext(), "ResetAllCheckins", "False");
        }
        else        {
            Local.Set(getApplicationContext(), "ResetAllCheckins", "True");
        }

        //Intent intent = new Intent(LoginActivity.this, Signature.class);

        //startActivity(intent); finish();
    }




    public void Login(View view) {



        if (isNetworkAvailable) {
            final AlertDialog ad = new AlertDialog.Builder(this).create();
            MySOAPCallActivity cs = new MySOAPCallActivity();
            try {
                EditText userName = (EditText) findViewById(R.id.editUserName);
                EditText password = (EditText) findViewById(R.id.editPassword);
                String user = userName.getText().toString();
                String pwd = password.getText().toString();
                LoginParams params = new LoginParams(cs, user, pwd);

                Local.Set(getApplicationContext(), "UserName", user);
                Local.Set(getApplicationContext(), "Password", pwd);

                new CallSoapLogin().execute(params);
                // new CallSoapGetCurrentEvents().execute(params);

            } catch (Exception ex) {
                ad.setTitle("Error!");
                ad.setMessage(ex.toString());
            }
            ad.show();
        }



    }



    public void GetStoresForUser(String UserName, String Phase) {

        String isWebsiteAvailable = Local.Get(getApplicationContext(), "AmIOnline");

        if(isWebsiteAvailable.equals("True")) {

            final AlertDialog ad = new AlertDialog.Builder(this).create();
            MySOAPCallActivity cs = new MySOAPCallActivity();
            try {

                GetStoresForUserParams params = new GetStoresForUserParams(cs, UserName, Phase);

                new CallSoapGetStoresForUser().execute(params);


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



    public class CallSoapLogin extends AsyncTask<LoginParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(LoginParams... params) {
            return params[0].foo.Login(params[0].username, params[0].password);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {

                //TextView loginResult = (TextView) findViewById(R.id.labelLoginResult);
                //loginResult.setVisibility(View.VISIBLE);
                //loginResult.setText(result);

                // Button buttonUnsetEvent = (Button)findViewById(R.id.buttonUnsetEvent);
                // buttonUnsetEvent.setEnabled(true);

                //Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
                //spinner2.setEnabled(true);

                boolean LoginSuccessful = false;
                Local.Set(getApplicationContext(), "Roles", result);

                if (result.toLowerCase().contains("success")) {
                    LoginSuccessful = true;
                }

                if (LoginSuccessful) {
                    String user = Local.Get(getApplicationContext(), "UserName");
                    Local.Set(getApplicationContext(), "LoggedIn", user);
                    //if successful:

                    ProgressDialog progressDialog;

                    //set and remove prrogress bar
                    progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMessage("Loading Stores "); // Setting Message
                    progressDialog.setTitle("Please Wait..."); // Setting Title
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                    progressDialog.show(); // Display Progress Dialog
                    progressDialog.setCancelable(true);

                    progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(10000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            // progressDialog.dismiss();
                        }
                    }).start();

                    GetStoresForUser(Local.Get(getApplicationContext(), "UserName"), "Rep");


                   // Intent intent = new Intent(Login.this, MainActivity.class);

                   // startActivity(intent); finish();


                } else {
                    //Do nothing
                    Toast.makeText(getApplicationContext(), "Invalid Login Credentials. Please check.", Toast.LENGTH_LONG).show();
                }
            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }


    }

    private static class LoginParams {
        MySOAPCallActivity foo;
        String username;
        String password;


        LoginParams(MySOAPCallActivity foo, String username, String password) {
            this.foo = foo;
            this.username = username;
            this.password = password;

        }
    }

    public class CallSoapAmIOnline extends AsyncTask<AmIOnlineParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(AmIOnlineParams... params) {
            return params[0].foo.AmIOnline();
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            boolean OnlineYes = false;

            try {


                if (result.toLowerCase().contains("success")) {
                    OnlineYes = true;

                    Local.Set(getApplicationContext(), "AmIOnline", "True");
                    //if successful:


                } else {
                    Local.Set(getApplicationContext(), "AmIOnline", "False");




                }
            } catch (Exception ex) {
                String e3 = ex.toString();
                Local.Set(getApplicationContext(), "AmIOnline", "False");
            }

        }


    }

    private static class AmIOnlineParams {
        MySOAPCallActivity foo;


        AmIOnlineParams(MySOAPCallActivity foo) {
            this.foo = foo;


        }

    }



    public class CallSoapSaveMyOpenRequests extends AsyncTask<SaveMyOpenRequestsParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(SaveMyOpenRequestsParams... params) {
            return params[0].foo.SaveMyOpenRequests(params[0].jsonRequests);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {


                //   Intent intent = new Intent(LoginActivity.this, MainActivity.class );

                //   startActivity(intent); finish();
                //


            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class SaveMyOpenRequestsParams {
        MySOAPCallActivity foo;
        String jsonRequests;



        SaveMyOpenRequestsParams(MySOAPCallActivity foo, String jsonRequests) {
            this.foo = foo;
            this.jsonRequests = jsonRequests;


        }
    }


    public class CallSoapSaveMyStores extends AsyncTask<SaveMyStoresParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(SaveMyStoresParams... params) {
            return params[0].foo.SaveMyStores(params[0].jsonStores);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {


                Local.Set(getApplicationContext(), "AnroidStores", "");


            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class SaveMyStoresParams {
        MySOAPCallActivity foo;
        String jsonStores;



        SaveMyStoresParams(MySOAPCallActivity foo, String jsonStores) {
            this.foo = foo;
            this.jsonStores = jsonStores;


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


                Local.Set(getApplicationContext(), "AnroidTssAppointments", "");


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

    public class CallSoapGetStoresForUser extends AsyncTask<GetStoresForUserParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(GetStoresForUserParams... params) {
            return params[0].foo.GetStoresForUser(params[0].username, params[0].phase);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            try {
                //process Json Result
                //Save results in local storage
                if(result.toLowerCase().contains("error")){

                }
                else {
                    Local.Set(getApplicationContext(), "AndroidStores", result);

                    //move to webservice result
                    Intent intent = new Intent(Login.this, MainActivity.class);

                    startActivity(intent); finish();
                    finish();
                    //

                }

            } catch (Exception ex) {
                String e3 = ex.toString();
            }

        }



    }
    private static class GetStoresForUserParams {
        MySOAPCallActivity foo;
        String username;
        String phase;



        GetStoresForUserParams(MySOAPCallActivity foo, String username, String phase) {
            this.foo = foo;
            this.username = username;
            this.phase = phase;


        }
    }


}

