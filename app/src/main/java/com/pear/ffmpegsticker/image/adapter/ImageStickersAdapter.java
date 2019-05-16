package com.pear.ffmpegsticker.image.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pear.ffmpegsticker.R;
import com.pear.ffmpegsticker.image.ImageStickerFragment;

import java.util.List;

public class ImageStickersAdapter extends RecyclerView.Adapter<ImageStickersAdapter.ViewHolder>{
    int selected_position=0;
    private Fragment context;
    private List<String> list;
    int height = 0;
    private String firstItem="";
    public ImageStickersAdapter(Fragment context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_image, viewGroup,
                false);
        height = viewGroup.getHeight()/3;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (!firstItem.equals(list.get(0))){
            selected_position=0;
            firstItem=list.get(0);
        }
        if (height != 0) {
            viewHolder.layout.setLayoutParams(new FrameLayout.LayoutParams(height,height));
            if (selected_position==i){
                viewHolder.item.setBackgroundResource(R.drawable.circle_shape_button_pressed);
            }else {
                viewHolder.item.setBackground(null);
            }
            Glide.with(context).asBitmap().load(list.get(i))
                    .apply(new RequestOptions().override(height*7/10,height*7/10))
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(viewHolder.item);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView item;
        private FrameLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = (FrameLayout) itemView.findViewById(R.id.itemLayout);
            item=(ImageView)itemView.findViewById(R.id.item);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
            // Updating old as well as new positions
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            notifyItemChanged(selected_position);

            ImageStickerFragment fragment= (ImageStickerFragment)context;
            if (fragment!=null&&fragment.isVisible()){
                fragment.OnImageSelected(list.get(selected_position));
            }

        }
    }
}
