package com.vf.admin.vf_rep;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// Custom Adapters for RepUpload Activity
//style xml files are grid_row_uploads.xml

import com.vf.admin.vf_rep.R;

import java.util.ArrayList;

public class RepGridSelectStoreupload extends   ArrayAdapter <AndroidStore> {

    Context context;
    private ArrayList<AndroidStore> data = new ArrayList<AndroidStore>();



    public RepGridSelectStoreupload(Context context, ArrayList<AndroidStore> data)
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
        View row;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.rep_row_selectstore_upload, parent, false);
        }
        else{
            row = (View) convertView;
        }

            TextView textViewTitle = (TextView) row.findViewById(R.id.textView2);

            TextView urnNumber = (TextView) row.findViewById(R.id.textViewsub);
            TextView progress = (TextView) row.findViewById(R.id.textViewsub2);

            AndroidStore item = data.get(position);

            //Set control values DANIEL these must change
            urnNumber.setText(item.getURN());
            textViewTitle.setText(item.getStoreName());
            progress.setText(item.getCurrentPhase());


        return row;

    }

   /* public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(     Context.LAYOUT_INFLATER_SERVICE );
            v = inflater.inflate(R.layout.gridview_item_layout, parent, false);
        } else {
            v = (View) convertView;
        }
        TextView text = (TextView)v.findViewById(R.id.grid_item_text);
        text.setText(mTextIds[position]);
        ImageView image = (ImageView)v.findViewById(R.id.grid_item_image);
        image.setImageDrawable(mThumbIds[position]);
        return v;
    }*/

}
