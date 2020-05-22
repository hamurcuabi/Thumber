package com.emrhmrc.thumber;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;

import com.emrhmrc.thumber.model.ThumbnailModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamurcuabi on 21,May,2020
 **/
public class ThumberHelper {

    public static List<ThumbnailModel> getThumberList(Context context, Uri videoUri,
                                                      int thumberCount) {
        List<ThumbnailModel> thumbnailModelList = new ArrayList<>();
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(context, videoUri);
        int videoLength = (Integer.parseInt(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)));
        long interval = videoLength / thumberCount;

        for (int i = 0; i < thumberCount; i++) {
            long frameTime = i * interval;
            Bitmap bmp = mediaMetadataRetriever.getFrameAtTime(frameTime,
                    MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
            thumbnailModelList.add(new ThumbnailModel(bmp, frameTime));
        }
        return thumbnailModelList;
    }

    public static Bitmap getThumber(Context context, Uri videoUri, int duration) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(context, videoUri);
        return mediaMetadataRetriever.getFrameAtTime(duration, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
    }

    public static long getLongDuration(Context context, Uri videoUri) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(context, videoUri);
        int videoLength = (Integer.parseInt(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)));
        return videoLength;
    }

    public static long getIntDuration(Context context, Uri videoUri) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(context, videoUri);
        int videoLength = (Integer.parseInt(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)));
        return videoLength / 1000;
    }
}
