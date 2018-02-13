package com.vf.admin.vf_rep;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;

public class InstallGpsCheckOut extends AppCompatActivity {
    public boolean mIsSurvey = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_store_checkoutmenu);


        Intent me = getIntent();
        TextView txtStoreName = (TextView)findViewById(R.id.txtStoreName);
        TextView txtURN = (TextView)findViewById(R.id.txtURN);

        txtStoreName.setText(me.getStringExtra("StoreName"));
        txtURN.setText(me.getStringExtra("URN"));

        String primaryRole = Local.Get( getApplicationContext(), "PrimaryRole");
        if (primaryRole.equals("Srv")){
            mIsSurvey = true;
        }

        if(!mIsSurvey){
            String IsInstallComplete = me.getStringExtra("IsInstallComplete");
        if(IsInstallComplete.toLowerCase().equals("false")){

            Toast.makeText(this, "Installation is not complete. Barcodes still to be captured and at least one image per Unit uploaded.", Toast.LENGTH_LONG).show();
            Button button = (Button) findViewById(R.id.btnSiteComplete);
            button.setVisibility(GONE);
        }
        }
        else{
            String IsSurveyComplete = me.getStringExtra("IsSurveyComplete");
            if(IsSurveyComplete.toLowerCase().equals("false")){

                Toast.makeText(this, "Survey is not complete. Answer all the questions unless you've selected No for store committed. Also upload at least one interior, one exterior image and one image per unit where Accepted = Y.", Toast.LENGTH_LONG).show();
                Button button = (Button) findViewById(R.id.btnSiteComplete);
                button.setVisibility(GONE);
            }
        }



    }

    public void SiteIncomplete(View view)
    {
        if(!mIsSurvey){
        MoveToCheckoutScreen("Installation Incomplete");//get from spinner
        }
        else {
            MoveToCheckoutScreen("Survey Incomplete");//get from spinner
        }
    }

    public void SiteComplete(View view)
    {
        if(!mIsSurvey){
        MoveToCheckoutScreen("Installation Complete");//get from spinner
        }
        else {
            MoveToCheckoutScreen("Survey Complete");//get from spinner
        }
    }

    public void MoveToCheckoutScreen(String Status) {
        Intent intent = new Intent(InstallGpsCheckOut.this, InstallGpsCheckoutSiteComplete.class);
        //Sending data to another Activity

        Intent me = getIntent();

        if(!mIsSurvey) {
            intent.putExtra("InstallStatus", Status);//use spinner like site
        }
        if(mIsSurvey) {
            intent.putExtra("SurveyStatus", Status);//use spinner like site
        }
        intent.putExtra("StoreID", me.getIntExtra("StoreID", 0));
        intent.putExtra("RequestID", me.getIntExtra("RequestID", 0));
        intent.putExtra("StoreName", me.getStringExtra("StoreName"));
        intent.putExtra("URN",  me.getStringExtra("URN"));

        startActivity(intent); finish();
    }
}
