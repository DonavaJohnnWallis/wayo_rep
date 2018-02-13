package com.vf.admin.vf_rep;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.vf.admin.vf_rep.R;

import java.util.ArrayList;

public class InstallGridViewAppointments extends  ArrayAdapter <AndroidAppointment> {

    Context context;
    private ArrayList<AndroidAppointment> data = new ArrayList<AndroidAppointment>();


    public InstallGridViewAppointments(Context context, ArrayList<AndroidAppointment> data)
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
            row = inflater.inflate(R.layout.installation_row_appointments, parent, false);
        }
        else{
            row = (View) convertView;
        }

            TextView txtURN = (TextView) row.findViewById(R.id.txtURN);

            TextView txtStoreName = (TextView) row.findViewById(R.id.txtStoreName);
            TextView txtProgress = (TextView) row.findViewById(R.id.txtProgress);

        TextView txtAppointmentDateTime = (TextView) row.findViewById(R.id.txtAppointmentDateTime);

            //Get class from data
            AndroidAppointment item = data.get(position);

            //Set control values DANIEL these must change
            txtURN.setText(item.getURN());
            txtStoreName.setText(item.getStoreName());
            txtProgress.setText(item.getCurrentPhase());

        String appointmentDateTime = item.getAppointmentDateTime();
        appointmentDateTime = appointmentDateTime.replace('T',' ');
        txtAppointmentDateTime.setText(appointmentDateTime);



        return row;



    }




}
