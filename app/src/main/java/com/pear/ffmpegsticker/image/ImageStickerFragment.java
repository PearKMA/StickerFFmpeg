package com.pear.ffmpegsticker.image;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.pear.ffmpegsticker.R;
import com.pear.ffmpegsticker.image.child_fragment.ListStickerChildFragment;
import com.pear.ffmpegsticker.image.custom.StickerImageView;
import com.pear.ffmpegsticker.image.custom.StickerView;
import com.pear.ffmpegsticker.image.model.Image;

import java.util.ArrayList;

public class ImageStickerFragment extends Fragment implements StickerInterface{
    //
    private boolean done=false;
    int width = 0, height = 0;
    //UI
    private FrameLayout flVideo, flBot, flSticker;
    private ImageView btnOk;
    private VideoView videoView;
    private int position = 0;
    private MediaController mediaController;
    //list
    private ArrayList<Image> createSticker=new ArrayList<>();

    public static ImageStickerFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putString("Path", path);
        ImageStickerFragment fragment = new ImageStickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_sticker, container, false);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        flVideo = view.findViewById(R.id.flContainer);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) flVideo.getLayoutParams();
        lp.height = width;
        flVideo.setLayoutParams(lp);
        flBot = view.findViewById(R.id.containerFragment);
        flSticker = view.findViewById(R.id.containerSticker);
        btnOk = view.findViewById(R.id.btnOk);
        videoView = view.findViewById(R.id.videoView);
        done=false;
        //replaceChildFrag(ListStickerChildFragment.newInstance("Image"),"List");
        addEvents();
    }

    private void replaceChildFrag(ListStickerChildFragment fragment, String s) {
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.containerFragment,fragment,s);
        ft.addToBackStack(s);
        ft.commit();
    }

    private void addEvents() {
        btnOk.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        btnOk.setBackgroundResource(R.drawable.circle_shape_button_pressed);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        btnOk.setBackground(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        btnOk.setBackground(null);
                        hideBorderSticker();
                        if (done==true){

                            //ffmpeg command
                        }else {
                            changeFeature();
                            done=true;
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void changeFeature() {
        ListStickerChildFragment fragment=(ListStickerChildFragment)getChildFragmentManager().findFragmentByTag("Child");
        fragment.changeLayoutSetTime(true);
    }

    public void OnImageSelected(String path) {
        final StickerImageView iv_sticker = new StickerImageView(getActivity());
        Glide.with(getActivity()).asBitmap().load(path)
                .apply(new RequestOptions().override(500, 500))
                .into(new Target<Bitmap>() {
                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv_sticker.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void getSize(@NonNull SizeReadyCallback cb) {

                    }

                    @Override
                    public void removeCallback(@NonNull SizeReadyCallback cb) {

                    }

                    @Override
                    public void setRequest(@Nullable Request request) {

                    }

                    @Nullable
                    @Override
                    public Request getRequest() {
                        return null;
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onStop() {

                    }

                    @Override
                    public void onDestroy() {

                    }
                });
        flSticker.addView(iv_sticker);
        createSticker.add(new Image(iv_sticker,path));
        hideBorderSticker();
        iv_sticker.setControlItemsHidden(false);
    }
    private void hideBorderSticker(){
        if (createSticker.size()>0){
            for (Image iv: createSticker){
                iv.getmSticker().setControlItemsHidden(true);
            }
        }
    }


    @Override
    public void onDeleteSticker(StickerView stickerView) {
        createSticker.remove(stickerView);
    }

    @Override
    public void onStickerClicked(StickerView stickerView) {
        hideBorderSticker();
        if (stickerView.getEdit()==false){
            stickerView.setControlItemsHidden(true);
        }else {
            stickerView.setControlItemsHidden(false);
        }
    }
}
