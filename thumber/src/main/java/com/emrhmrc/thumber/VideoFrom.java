package com.emrhmrc.thumber;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hamurcuabi on 21,May,2020
 **/
public enum VideoFrom {
    GALLERY(1), CAPTURE(2);
    private static Map map = new HashMap<>();

    static {
        for (VideoFrom pageType : VideoFrom.values()) {
            map.put(pageType.value, pageType);
        }
    }

    private int value;

    VideoFrom(int value) {
        this.value = value;
    }

    public static VideoFrom valueOf(int pageType) {
        return (VideoFrom) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
