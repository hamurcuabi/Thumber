package com.emrhmrc.thumber.adapter;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;

import com.emrhmrc.genericrecycler.adapters.BaseViewHolder;
import com.emrhmrc.genericrecycler.interfaces.IOnItemClickListener;
import com.emrhmrc.thumber.R;
import com.emrhmrc.thumber.databinding.RcvItemThumbnailBinding;
import com.emrhmrc.thumber.model.ThumbnailModel;


/**
 * Created by hamurcuabi on 16,April,2020
 **/
public class ThumberViewHolder extends BaseViewHolder<ThumbnailModel,
        IOnItemClickListener<ThumbnailModel>> {
    private RcvItemThumbnailBinding binding;
    private Animation zoom_in;

    public ThumberViewHolder(com.emrhmrc.thumber.databinding.RcvItemThumbnailBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        zoom_in = AnimationUtils.loadAnimation(binding.getRoot().getContext(), R.anim.zoom_in);
    }

    @Override
    public void onBind(ThumbnailModel item, @Nullable IOnItemClickListener<ThumbnailModel> listener, int position) {
        binding.setThumbnail(item);
        binding.getRoot().setOnClickListener(view -> {
            listener.onItemClicked(item, position);
            binding.frameRoot.startAnimation(zoom_in);
        });

    }
}
