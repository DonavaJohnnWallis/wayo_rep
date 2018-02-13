package com.vf.admin.vf_rep;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RepGridViewUnitary extends  ArrayAdapter <AndroidStoreUnit> {

    Context context;
    private ArrayList<AndroidStoreUnit> data = new ArrayList<AndroidStoreUnit>();



    public RepGridViewUnitary(Context context, ArrayList<AndroidStoreUnit> data )
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
            row = inflater.inflate(R.layout.rep_row_unitary, parent, false);
        }
        else{
            row = (View) convertView;
        }




            TextView ItemTypeName = (TextView) row.findViewById(R.id.textView);
            ImageView ImageBase64 = (ImageView) row.findViewById(R.id.imageView);
            TextView MaxQuantityFromMatrix = (TextView) row.findViewById(R.id.textViewsub);

            //TextView textUnitsSet = (TextView) row.findViewById(R.id.amount);

            AndroidStoreUnit item = data.get(position);


            ItemTypeName.setText(item.getItemTypeName());//"Display Item 1002");
            ImageBase64.setImageResource(getImageFromDrawables(item.getImagePath()));//R.drawable.dis1);//TODO

            Integer max = item.getMaxQuantityFromMatrix();
            MaxQuantityFromMatrix.setText(max.toString());
            //textUnitsSet.setText("2");




        return row;

    }

    public int getImageFromDrawables(String imageName)
    {
        int returnThis = 0;
        switch(imageName)
        {
            case "Illuminated_Modrail.jpg" : returnThis = R.drawable.illuminated_modrail; break;
            case "Modular_Illuminated_Wall_Panel_pack_of_3.jpg" : returnThis = R.drawable.modular_illuminated_wall_panel_pack_of_3; break;
            case "Brand_Pillars.jpg" : returnThis = R.drawable.brand_pillars; break;
            case "Table_Defenders.jpg" : returnThis = R.drawable.table_defenders; break;
            case "TV_Wall_Essential.jpg" : returnThis = R.drawable.tv_wall_essential; break;
            case "TV_Wall_Premium.jpg" : returnThis = R.drawable.tv_wall_premium; break;
            case "Back_Of_Bar_T1.jpg" : returnThis = R.drawable.back_of_bar_t1; break;
            case "Back_Of_Bar_T2.jpg" : returnThis = R.drawable.back_of_bar_t2; break;
            case "Back_Of_Bar_ESS.jpg" : returnThis = R.drawable.back_of_bar_ess; break;
            case "Entrance_Canopy.jpg" : returnThis = R.drawable.entrance_canopy; break;
            case "Mini_Pylon.jpg" : returnThis = R.drawable.mini_pylon; break;
            case "Outlet_Sign.jpg" : returnThis = R.drawable.outlet_sign; break;
            case "Conversation_Table.jpg" : returnThis = R.drawable.conversation_table; break;
            case "Wall_Branding_Graphic_Panels.jpg" : returnThis = R.drawable.wall_branding_graphic_panels; break;
            case "guinness_Canopy.jpg" : returnThis = R.drawable.guinness_canopy; break;
            case "guinness_Outside_Signage.jpg" : returnThis = R.drawable.guinness_outside_signage; break;
            case "guinness_Solar_Light_Box.jpg" : returnThis = R.drawable.guinness_solar_light_box; break;
            case "guinness_UHI_Board.jpg" : returnThis = R.drawable.guinness_uhi_board; break;
            case "guinness_wall_branding_modrail.jpg" : returnThis = R.drawable.guinness_wall_branding_modrail; break;
            case "guinness_Wall_Branding_Graphic_Panels.jpg" : returnThis = R.drawable.guinness_wall_branding_graphic_panels; break;
            case "guinness_Front_Bar_Signage.jpg" : returnThis = R.drawable.guinness_front_bar_signage; break;
            case "guinness_Modular_Back_of_bar_Tier_1.jpg" : returnThis = R.drawable.guinness_modular_back_of_bar_tier_1; break;
            case "guinness_Modular_Back_of_bar_Tier_2.jpg" : returnThis = R.drawable.guinness_modular_back_of_bar_tier_2; break;
            case "guinness_Modular_Back_of_bar_Essentials.jpg" : returnThis = R.drawable.guinness_modular_back_of_bar_tier_essentials; break;
            case "guinness_Table_Talker.jpg" : returnThis = R.drawable.guinness_table_defenders; break;
            case "guinness_TV_Cladding.jpg" : returnThis = R.drawable.guinness_tv_cladding; break;
            case "guinness_Projector_Wall.jpg" : returnThis = R.drawable.guinness_projector_wall; break;
            case "guinness_Couch.jpg" : returnThis = R.drawable.guinness_couch; break;
            case "guinness_Bench_1.jpg" : returnThis = R.drawable.guinness_bench_1; break;
            case "guinness_Bench_2.jpg" : returnThis = R.drawable.guinness_bench2; break;
            case "kenya_cane_uhi_board_chalkboard.jpg" : returnThis = R.drawable.kenya_cane_uhi_board_chalkboard; break;
            case "kenya_cane_Wall_Mirrors.jpg" : returnThis = R.drawable.kenya_cane_wall_mirrors; break;
            case "kenya_cane_Light_Box.jpg" : returnThis = R.drawable.kenya_cane_light_box; break;
            case "kenya_cane_Wall_Branding_Graphic_Panels.jpg" : returnThis = R.drawable.kenya_cane_wall_branding_graphic_panels; break;
            case "kenya_cane_Table_Talkers.jpg" : returnThis = R.drawable.kenya_cane_table_defender; break;
            case "kenya_cane_TV_Cladding.jpg" : returnThis = R.drawable.kenya_cane_tv_cladding; break;
            case "kenya_cane_modular_BOB_lightbox.jpg" : returnThis = R.drawable.kenya_cane_modular_bob_lightbox; break;
            case "kenya_cane_Café_Table_With_Seating.jpg" : returnThis = R.drawable.kenya_cane_cafe_table_with_seating; break;
            case "kenya_cane_Perfect_Serve.jpg" : returnThis = R.drawable.kenya_cane_perfect_serve; break;
            case "kenya_cane_Outlet_Name.jpg" : returnThis = R.drawable.kenya_cane_outlet_name; break;
            case "kenya_cane_walll_counter.jpg" : returnThis = R.drawable.kenya_cane_walll_counter; break;
            case "kenya_cane_Bench.jpg" : returnThis = R.drawable.kenya_cane_bench; break;
            case "kenya_cane_Cocktail_Table_With_Bar_Stool.jpg" : returnThis = R.drawable.kenya_cane_cocktail_table_with_bar_stool; break;
            default: break;
        }

        return returnThis;
    }

}
