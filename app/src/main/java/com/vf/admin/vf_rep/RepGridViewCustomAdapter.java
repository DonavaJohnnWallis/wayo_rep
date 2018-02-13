package com.vf.admin.vf_rep;




import android.app.Activity;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.ArrayAdapter;

import android.widget.ImageView;

import android.widget.TextView;

import com.vf.admin.vf_rep.R;

public class RepGridViewCustomAdapter extends  ArrayAdapter {

    Context context;




    public RepGridViewCustomAdapter(Context context)
    {
        super(context, 0);
        this.context=context;

    }

    public int getCount()
    {
        return 10;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.rep_row_items, parent, false);


            TextView textViewTitle = (TextView) row.findViewById(R.id.textView);
            ImageView imageViewIte = (ImageView) row.findViewById(R.id.imageView);
            TextView numberinput = (TextView) row.findViewById(R.id.textViewsub);





            textViewTitle.setText("Display Item 1");
            imageViewIte.setImageResource(R.drawable.dis1);
            numberinput.setText("Price & Other info ");



        }





        return row;

    }

}
