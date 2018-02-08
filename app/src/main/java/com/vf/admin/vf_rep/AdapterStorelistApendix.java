package com.vf.admin.vf_rep;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

// Custom Adapters for RepUpload Activity
//style xml files are grid_row_uploads.xml


public class AdapterStorelistApendix extends ArrayAdapter {

    Context context;




    public AdapterStorelistApendix(Context context)
    {
        super(context, 0);
        this.context=context;

    }

    public int getCount()
    {
        return 16;
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





            textViewTitle.setText("Store Name");
            imageViewIte.setText("Store URN: 00 000 000");
            url.setText("View Appendix");




        }





        return row;



    }





}
