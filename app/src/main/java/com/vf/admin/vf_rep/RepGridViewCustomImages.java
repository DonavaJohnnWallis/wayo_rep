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

public class RepGridViewCustomImages extends  ArrayAdapter {

    Context context;



    public RepGridViewCustomImages(Context context)
    {
        super(context, 0);
        this.context=context;

    }

    public int getCount()
    {
        return 12;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.rep_row_images, parent, false);



            ImageView imageViewIte = (ImageView) row.findViewById(R.id.imageView4);


            imageViewIte.setImageResource(R.drawable.dis1);




        }





        return row;

    }

}
