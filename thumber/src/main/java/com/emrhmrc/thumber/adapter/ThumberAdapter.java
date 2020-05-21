package com.emrhmrc.thumber.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.emrhmrc.genericrecycler.adapters.GenericAdapter;
import com.emrhmrc.genericrecycler.interfaces.IOnItemClickListener;
import com.emrhmrc.thumber.R;
import com.emrhmrc.thumber.databinding.RcvItemThumbnailBinding;
import com.emrhmrc.thumber.model.ThumbnailModel;

/**
 * Created by hamurcuabi on 26,March,2020
 **/
public class ThumberAdapter extends GenericAdapter<ThumbnailModel,
        IOnItemClickListener<ThumbnailModel>, ThumberViewHolder> {

    public ThumberAdapter(Context context, IOnItemClickListener<ThumbnailModel> listener, @Nullable ViewGroup emptyView) {
        super(context, listener, emptyView);
    }

    @NonNull
    @Override
    public ThumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RcvItemThumbnailBinding defaultBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.rcv_item_thumbnail, parent, false);
        return new ThumberViewHolder(defaultBinding);


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
