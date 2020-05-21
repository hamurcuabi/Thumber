package com.emrhmrc.thumber.model;

import android.graphics.Bitmap;

import com.emrhmrc.genericrecycler.models.BaseModel;

/**
 * Created by hamurcuabi on 17,May,2020
 **/
public class ThumbnailModel extends BaseModel {
    private Bitmap bitmap;
    private long duration;
    private boolean isSelected;

    public ThumbnailModel(Bitmap bitmap, long duration) {
        this.bitmap = bitmap;
        this.duration = duration;
    }

    public ThumbnailModel() {
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}
