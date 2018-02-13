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


public class SurveyUploadAdapter extends  ArrayAdapter {

    Context context;




    public SurveyUploadAdapter(Context context)
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
            row = inflater.inflate(R.layout.new_grid_upload1, parent, false);


            TextView text1 = (TextView) row.findViewById(R.id.textView1);
            TextView text2 = (TextView) row.findViewById(R.id.textView2);


            ImageView imageViewIte = (ImageView) row.findViewById(R.id.imageView4);





            text1.setText("product details  product details  product details ");
            text2.setText("Qty");
            imageViewIte.setImageResource(R.drawable.dis1);




        }





        return row;

    }

}
