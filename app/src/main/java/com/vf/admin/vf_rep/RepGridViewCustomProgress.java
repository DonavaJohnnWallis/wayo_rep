package com.vf.admin.vf_rep;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.vf.admin.vf_rep.R;

import java.util.ArrayList;

public class RepGridViewCustomProgress extends  ArrayAdapter <AndroidStore>{

    Context context;
    private ArrayList<AndroidStore> data = new ArrayList<AndroidStore>();


    public RepGridViewCustomProgress(Context context, ArrayList<AndroidStore> data)
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
            row = inflater.inflate(R.layout.rep_row_progress, parent, false);
        }
        else{
            row = (View) convertView;
        }

            //Find controls
            TextView txtStoreName = (TextView) row.findViewById(R.id.txtStoreName);
            TextView txtURN = (TextView) row.findViewById(R.id.txtURN);
            TextView txtProgress = (TextView) row.findViewById(R.id.txtProgress);

            //Get class from data
            AndroidStore item = data.get(position);

            //Set control values
            txtStoreName.setText(item.getStoreName());
            txtURN.setText(item.getURN());
            txtProgress.setText(item.getCurrentPhase());



        return row;



    }




}
