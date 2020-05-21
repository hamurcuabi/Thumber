package com.emrhmrc.thumber.adapter;

import androidx.annotation.Nullable;

import com.emrhmrc.genericrecycler.adapters.BaseViewHolder;
import com.emrhmrc.genericrecycler.interfaces.IOnItemClickListener;
import com.emrhmrc.thumber.databinding.RcvItemThumbnailBinding;
import com.emrhmrc.thumber.model.ThumbnailModel;


/**
 * Created by hamurcuabi on 16,April,2020
 **/
public class ThumberViewHolder extends BaseViewHolder<ThumbnailModel,
        IOnItemClickListener<ThumbnailModel>> {
    private RcvItemThumbnailBinding binding;


    public ThumberViewHolder(com.emrhmrc.thumber.databinding.RcvItemThumbnailBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    @Override
    public void onBind(ThumbnailModel item, @Nullable IOnItemClickListener<ThumbnailModel> listener, int position) {
        binding.setThumbnail(item);
        binding.getRoot().setOnClickListener(view -> {
            listener.onItemClicked(item, position);
        });

    }
}
