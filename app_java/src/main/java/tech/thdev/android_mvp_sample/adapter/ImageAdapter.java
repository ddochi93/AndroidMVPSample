
package tech.thdev.android_mvp_sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import tech.thdev.android_mvp_sample.adapter.contract.ImageAdapterContract;
import tech.thdev.android_mvp_sample.adapter.holder.ImageViewHolder;
import tech.thdev.android_mvp_sample.data.ImageItem;
import tech.thdev.android_mvp_sample.listener.OnItemClickListener;

/**
 * Created by tae-hwan on 10/23/16.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> implements ImageAdapterContract.Model, ImageAdapterContract.View {

    private Context context;
    private OnItemClickListener onItemClickListener;

    private ArrayList<ImageItem> imageItems;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void addItems(ArrayList<ImageItem> imageItems) {
        this.imageItems = imageItems;
    }

    @Override
    public void clearItem() {
        if (imageItems != null) {
            imageItems.clear();
        }
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return imageItems != null ? imageItems.size() : 0;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(context, parent, onItemClickListener);
    }

    @Override
    public void setOnClickListener(OnItemClickListener clickListener) {
        this.onItemClickListener = clickListener;
    }

    @Override
    public ImageItem getItem(int position) {
        return imageItems.get(position);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        if (holder == null) return;
        holder.onBind(getItem(position), position);
    }
}