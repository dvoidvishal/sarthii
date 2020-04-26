package com.example.sarthiithetuitionfinder;


import android.app.Activity;
//import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

public class Slider_Adaptor extends PagerAdapter {

    Activity activity;
    int slide_images[];
    int slide_image[];
    String slide_headings[];

    public Slider_Adaptor(Activity activity,int slide_images[],int slide_image[],String slide_headings[]){
        this.activity=activity;
        this.slide_images=slide_images;
        this.slide_image=slide_image;
        this.slide_headings=slide_headings;
    }


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater obj=activity.getLayoutInflater();
        View view=obj.inflate(R.layout.slide_layout,container,false);

        ImageView slideImagesView= (ImageView) view.findViewById(R.id.nearby_image);
        TextView slideHeading= (TextView) view.findViewById(R.id.slide_heading);
        ImageView slideImageView= view.findViewById(R.id.nearbytext_image);

       slideImagesView.setImageResource(slide_images[position]);
       slideHeading.setText(slide_headings[position]);
       slideImageView.setImageResource(slide_image[position]);

        container.addView(view);
        Log.d("position", Integer.toString(position));
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
