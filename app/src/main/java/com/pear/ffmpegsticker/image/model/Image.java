package com.pear.ffmpegsticker.image.model;

import com.pear.ffmpegsticker.image.custom.StickerImageView;

public class Image {
    private StickerImageView mSticker;
    private String path;

    public Image() {
    }

    public Image(StickerImageView mSticker, String path) {
        this.mSticker = mSticker;
        this.path = path;
    }

    public StickerImageView getmSticker() {
        return mSticker;
    }

    public void setmSticker(StickerImageView mSticker) {
        this.mSticker = mSticker;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
