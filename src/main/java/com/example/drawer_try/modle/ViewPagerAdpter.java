package com.example.drawer_try.modle;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ViewPagerAdpter extends PagerAdapter implements View.OnClickListener {

    private Context context;
    private ArrayList<String> imagesURL;


    public ViewPagerAdpter(Context context, ArrayList<String> imagesURL) {
        this.context = context;
        this.imagesURL = imagesURL;
    }

    @Override
    public int getCount() {
        return imagesURL.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull  Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull  ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);


        Glide.with(context)
                .load(imagesURL.get(position))
                .fitCenter()
                .into(imageView);

//        Picasso.get()
//                .load(imagesURL.get(position))
//                .fit()
//                .centerInside()
//                .into(imageView);
//
//        //                .centerCrop()


        container.addView(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull  ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View v) {
        Log.d("pager", "onClick: click pager");
    }
}
