package com.vf.admin.vf_rep;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

// Custom Adapters for RepUpload Activity
//style xml files are grid_row_uploads.xml


public class AdapterStorelistApendix extends ArrayAdapter {

    Context context;
    private ArrayList<AndroidStore> data = new ArrayList<AndroidStore>();



    public AdapterStorelistApendix(Context context, ArrayList<AndroidStore> data)
    {
        super(context, 0);
        this.context=context;
        this.data = data;

    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.grid_storelist, parent, false);


            TextView textViewTitle = (TextView) row.findViewById(R.id.textView1);
            TextView imageViewIte = (TextView) row.findViewById(R.id.textView2);
            TextView url = (TextView) row.findViewById(R.id.textView3);


         /*   textViewTitle.setText("Store Name");
            imageViewIte.setText("Store URN: 00 000 000");
            url.setText("View Appendix");*/

            //Get class from data
            AndroidStore item = data.get(position);

            //Set control values
            textViewTitle.setText(item.getStoreName());
            imageViewIte.setText(item.getURN());
            url.setText("View Appendix");


        }
        else{
            row = (View) convertView;
        }




        return row;



    }





}
