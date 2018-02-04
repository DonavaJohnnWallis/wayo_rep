package com.vf.admin.vf_rep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {


    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
    }
    //links child button to parent flipper layout and contains animations
    public void ClickHome1(View view) {
        viewFlipper.setDisplayedChild(1);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.homescreen1)));

        //pop animation for layout
        LinearLayout Layoutpop1 = (LinearLayout)this.findViewById(R.id.homescreen1);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop1.startAnimation(expandIn);
    }



    public void ClickHome2(View view) {
        viewFlipper.setDisplayedChild(2);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.homescreen2)));

        //pop animation for layout
        LinearLayout Layoutpop2 = (LinearLayout)this.findViewById(R.id.homescreen2);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop2.startAnimation(expandIn);
    }


    public void ClickHome3(View view) {
        viewFlipper.setDisplayedChild(3);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.homescreen3)));

        //pop animation for layout
        LinearLayout Layoutpop2 = (LinearLayout)this.findViewById(R.id.homescreen3);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop2.startAnimation(expandIn);
    }


    public void ClickHome4(View view) {
        viewFlipper.setDisplayedChild(4);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.homescreen4)));

        //pop animation for layout
        LinearLayout Layoutpop2 = (LinearLayout)this.findViewById(R.id.homescreen4);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop2.startAnimation(expandIn);
    }



    //go to barcode scanner

    public void GoToScanbarcode(View view) {

       Intent intent = new Intent(MainActivity.this, StoreListActivity.class);

       startActivity(intent);
    }

    public void GoToContracts(View view) {


        Intent intent = new Intent(MainActivity.this, StoreListContractsActivity.class);

        startActivity(intent);
    }
}
