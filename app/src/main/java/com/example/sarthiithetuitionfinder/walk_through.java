package com.example.sarthiithetuitionfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class walk_through extends AppCompatActivity {

    ViewPager mSlideViewPager;
    LinearLayout mDotLayout;

    TextView[] mDots;

    Button next,back;

    int mCurrentPage;
    int slide_images[]={R.drawable.nearby,R.drawable.qualityeducation,R.drawable.fees,R.drawable.parent,R.drawable.parttimejob};
    int slide_image[]={R.drawable.nearbytext,R.drawable.qualitytext,R.drawable.feestext,R.drawable.parenttext,R.drawable.partjobtext};

    String slide_headings[]={"Nearby Tutors","Best Quality Of Education","Reasonable Fee","Parent Portal","Spread Education with Knowledge"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_walk_through);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_walk_through);

        PrefManager prefManager = new PrefManager(this);
        if (prefManager.isFirstTimeLaunch()) {
          Intent i=new Intent(walk_through.this,DashBoard.class);
         startActivity(i);
         finish();
        }

        //prefManager.setFirstTimeLaunch(false);

       // Intent i = new Intent(walk_through.this,DashBoard.class);
        //startActivity(i);



        mSlideViewPager= (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout= (LinearLayout) findViewById(R.id.dotsLayout);

        next= (Button) findViewById(R.id.nextBtn);
        back= (Button) findViewById(R.id.prevBtn);

        Slider_Adaptor adaptor=new Slider_Adaptor(this,slide_images,slide_image,slide_headings);
        mSlideViewPager.setAdapter(adaptor);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentPage==4){
                    Intent i=new Intent(walk_through.this, DashBoard.class);
                    startActivity(i);
                    finish();
                }
                mSlideViewPager.setCurrentItem(mCurrentPage+1);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage-1);
            }
        });

    }

    public void addDotsIndicator(int position){
        mDots=new TextView[5];
        mDotLayout.removeAllViews();

        for(int i=0;i<mDots.length;i++){
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);
        }
        if (mDots.length>0){
            mDots[position].setTextColor(Color.WHITE);
        }
    }


    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
        {

            if(position==4){


                Intent ii= new Intent(walk_through.this, DashBoard.class);
                startActivity(ii);
                finish();

            }

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage=position;

            if(position==0){
                back.setEnabled(false);
                next.setEnabled(true);
                back.setVisibility(View.INVISIBLE);
            }
            else if(position==mDots.length-1){
                back.setEnabled(true);
                next.setEnabled(true);
                back.setVisibility(View.VISIBLE);

                next.setText("Finish");
            }

            else{
                back.setEnabled(true);
                next.setEnabled(true);
                back.setVisibility(View.VISIBLE);

                next.setText("Next");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {


        }

    };

}







