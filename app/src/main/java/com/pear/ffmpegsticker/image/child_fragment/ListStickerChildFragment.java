package com.pear.ffmpegsticker.image.child_fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.pear.ffmpegsticker.R;
import com.pear.ffmpegsticker.image.adapter.ImageStickersAdapter;

import java.io.IOException;
import java.util.ArrayList;

public class ListStickerChildFragment extends Fragment {
    private LinearLayout llSticker, llSetTime;
    private ArrayList<String> listItem;
    private ProgressBar progressBar;
    private RecyclerView rvList;
    private ImageStickersAdapter adapter;
    private ImageView btnAnimal, btnEmotion, btnFish, btnFruit, btnLove;


    public static ListStickerChildFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("Type", type);
        ListStickerChildFragment fragment = new ListStickerChildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_sticker_fragment_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        llSticker = view.findViewById(R.id.layout_sticker);
        llSetTime = view.findViewById(R.id.layout_setTime);
        progressBar = view.findViewById(R.id.progress);
        rvList = view.findViewById(R.id.rvSticker);
        btnAnimal = view.findViewById(R.id.btnAnimal);
        btnEmotion = view.findViewById(R.id.btnEmotion);
        btnFish = view.findViewById(R.id.btnFish);
        btnFruit = view.findViewById(R.id.btnFruit);
        btnLove = view.findViewById(R.id.btnLove);
        new GetSticker().execute(0);
        btnAnimal.setBackgroundResource(R.drawable.linearlayout_gradient_background);
        addEvents();
    }

    private void addEvents() {
        btnAnimal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        btnAnimal.setBackgroundResource(R.drawable.linearlayout_gradient_background);
                        btnEmotion.setBackground(null);
                        btnFish.setBackground(null);
                        btnFruit.setBackground(null);
                        btnLove.setBackground(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        new GetSticker().execute(0);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        btnAnimal.setBackground(null);
                        break;
                }
                return true;
            }
        });
        btnEmotion.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        btnEmotion.setBackgroundResource(R.drawable.linearlayout_gradient_background);
                        btnAnimal.setBackground(null);
                        btnFish.setBackground(null);
                        btnFruit.setBackground(null);
                        btnLove.setBackground(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        new GetSticker().execute(1);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        btnEmotion.setBackground(null);
                        break;
                }
                return true;
            }
        });
        btnFish.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        btnFish.setBackgroundResource(R.drawable.linearlayout_gradient_background);
                        btnEmotion.setBackground(null);
                        btnAnimal.setBackground(null);
                        btnFruit.setBackground(null);
                        btnLove.setBackground(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        new GetSticker().execute(2);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        btnFish.setBackground(null);
                        break;
                }
                return true;
            }
        });
        btnFruit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        btnFruit.setBackgroundResource(R.drawable.linearlayout_gradient_background);
                        btnEmotion.setBackground(null);
                        btnFish.setBackground(null);
                        btnAnimal.setBackground(null);
                        btnLove.setBackground(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        new GetSticker().execute(3);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        btnFruit.setBackground(null);
                        break;
                }
                return true;
            }
        });
        btnLove.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        btnLove.setBackgroundResource(R.drawable.linearlayout_gradient_background);
                        btnEmotion.setBackground(null);
                        btnFish.setBackground(null);
                        btnFruit.setBackground(null);
                        btnAnimal.setBackground(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        new GetSticker().execute(4);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        btnLove.setBackground(null);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Kiem tra check = true => thay layout co sticker thanh layout set duration sticker
     *
     * @param check
     */
    public void changeLayoutSetTime(boolean check) {
        if (check) {
            llSticker.post(new Runnable() {
                @Override
                public void run() {
                    llSticker.animate()
                            .translationY(llSticker.getHeight())
                            .alpha(0.0f)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    llSticker.setVisibility(View.GONE);
                                    llSetTime.setVisibility(View.VISIBLE);
                                }
                            });
                }
            });
        }else {
            llSetTime.post(new Runnable() {
                @Override
                public void run() {
                    llSetTime.animate()
                            .translationY(llSetTime.getHeight())
                            .alpha(0.0f)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    llSetTime.setVisibility(View.GONE);
                                    llSticker.setVisibility(View.VISIBLE);
                                }
                            });
                }
            });
        }

    }


    private class GetSticker extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            String s;
            String[] sticker;
            try {
                switch (integers[0]) {
                    case 0:
                        s = "file:///android_asset/sticker/animal/";
                        sticker = getActivity().getAssets().list("sticker/animal");
                        assert sticker != null;
                        for (String str : sticker) {
                            listItem.add(s + str);
                        }
                        break;
                    case 1:
                        s = "file:///android_asset/sticker/emotion/";
                        sticker = getActivity().getAssets().list("sticker/emotion");
                        assert sticker != null;
                        for (String str : sticker) {
                            listItem.add(s + str);
                        }
                        break;
                    case 2:
                        s = "file:///android_asset/sticker/fish/";
                        sticker = getActivity().getAssets().list("sticker/fish");
                        assert sticker != null;
                        for (String str : sticker) {
                            listItem.add(s + str);
                        }
                        break;
                    case 3:
                        s = "file:///android_asset/sticker/fruit/";
                        sticker = getActivity().getAssets().list("sticker/fruit");
                        assert sticker != null;
                        for (String str : sticker) {
                            listItem.add(s + str);
                        }
                        break;
                    case 4:
                        s = "file:///android_asset/sticker/love/";
                        sticker = getActivity().getAssets().list("sticker/love");
                        assert sticker != null;
                        for (String str : sticker) {
                            listItem.add(s + str);
                        }
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            if (listItem != null) {
                listItem.clear();
            } else {
                listItem = new ArrayList<>();
            }
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setVisibility(View.GONE);
            adapter = new ImageStickersAdapter(getParentFragment(), listItem);
            rvList.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4,
                    GridLayoutManager.VERTICAL, false);
            rvList.setLayoutManager(gridLayoutManager);
            rvList.setAdapter(adapter);
        }
    }
}
