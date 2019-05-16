package com.pear.ffmpegsticker;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pear.ffmpegsticker.image.ImageStickerFragment;
import com.pear.ffmpegsticker.image.StickerInterface;
import com.pear.ffmpegsticker.image.custom.StickerImageView;
import com.pear.ffmpegsticker.image.custom.StickerView;

public class MainActivity extends AppCompatActivity implements StickerInterface {
    private StickerImageView mSticker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.home, ImageStickerFragment.newInstance("//storage/emulated/0/FFmpeg/test/mp4"),"Child");
        ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public void onDeleteSticker(StickerView stickerView) {

    }

    @Override
    public void onStickerClicked(StickerView stickerView) {
        if (mSticker != null && mSticker == stickerView) {
            if (stickerView.getEdit() == false)
                stickerView.setControlItemsHidden(true);
            else
                stickerView.setControlItemsHidden(false);
        }
        if (mSticker == null || mSticker != stickerView) {
            mSticker = (StickerImageView) stickerView;
            stickerView.setControlItemsHidden(false);
        }
    }
}
