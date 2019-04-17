package com.example.recyclerx.Activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recyclerx.Adapter.SliderAdapter;
import com.example.recyclerx.R;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private int[] image;
    //private int;
    private String productName, Price;

    //Item Name and Price TextView
    private  TextView name,price;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        init();

    }

    private void init()
    {

        viewPager = findViewById(R.id.viewPage);
        mDotLayout = findViewById(R.id.linearLayout);

        name = findViewById(R.id.product_name_detais_TVId);
        price  = findViewById(R.id.product_price_detais_TVId);

        // Theme_one = findViewById(R.id.singInBTNId);
        // Theme_two = findViewById(R.id.singUpBTNId);


        // todo:  Collect Product Price ,Name, image
        int imageId = getIntent().getIntExtra("ImageId", 0);
        Price = getIntent().getStringExtra("Price");
        productName = getIntent().getStringExtra("ProductName");

        // todo: Product Name & Price Add
        price.setText(Price);
        name.setText(productName);


        image = new int[]{imageId,  R.drawable.product_icon_5, R.drawable.product_icon_3, R.drawable.product_icon_4};



        //todo: image slider adapter
        sliderAdapter = new SliderAdapter(this, image);
        viewPager.setAdapter(sliderAdapter);
        // todo: dot position select
        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);
    }


    public void addDotsIndicator(int postion) {
        // mDots =null;
        mDots = new TextView[image.length];
        mDotLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorBlack));
            mDotLayout.addView(mDots[i]);
            //    }

            if (mDots.length > 0) {
                try {

                    mDots[postion].setTextColor(getResources().getColor(R.color.colorGreen));
                }catch (Exception e)
                {

                }
            }

        }

    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
