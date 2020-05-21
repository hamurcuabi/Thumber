package com.emrhmrc.thumber.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.emrhmrc.thumber.R;

/**
 * Created by hamurcuabi on 21,May,2020
 **/
public class DataBindingAdapter {

    @BindingAdapter("thumber:loadBitmap")
    public static void loadBitmap(ImageView view, Bitmap bitmap) {
        Glide.with(view.getContext())
                .load(bitmap)
                .error(R.drawable.ripple_transparent)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view);
    }
}
