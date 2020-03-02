package tech.thdev.android_mvp_sample.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.thdev.android_mvp_sample.R;
import tech.thdev.android_mvp_sample.adapter.contract.ImageAdapterContract;
import tech.thdev.android_mvp_sample.data.ImageItem;
import tech.thdev.android_mvp_sample.listener.OnItemClickListener;

/**
 * Created by tae-hwan on 10/23/16.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> implements ImageAdapterContract.View, ImageAdapterContract.Model {

    private Context mContext;

    private ArrayList<ImageItem> imageItems;

    public ImageAdapter(Context context) {
        mContext = context;
    }

    public void setImageItems(ArrayList<ImageItem> imageItems) {
        this.imageItems= imageItems;
    }

//    public void clear() {
//        if (mImageItems != null) {
//            mImageItems.clear();
//            mImageItems = null;
//        }
//    }

    @Override
    public int getItemCount() {
        return imageItems != null ? imageItems.size() : 0;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        if (holder == null) return;

        final ImageItem imageItem = imageItems.get(position);
        new ImageAsync(holder.imageView).execute(imageItem.getImageRes());
    }

    @Override
    public void setOnClickListener(OnItemClickListener clickListener) {

    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void addItems(ArrayList<ImageItem> imageItems) {
        this.imageItems = imageItems;
    }

    @Override
    public void clearItem() {
        if(imageItems != null) {
            imageItems.clear();
        }


    }

    @Override
    public ImageItem getItem(int position) {
        return null;
    }

    private class ImageAsync extends AsyncTask<Integer, Void, Bitmap> {

        private final WeakReference<ImageView> imageViewReference;

        ImageAsync(ImageView imageView) {
            imageViewReference = new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(Integer... params) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            return BitmapFactory.decodeResource(mContext.getResources(), params[0], options);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            imageViewReference.get().setImageResource(0);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageViewReference.get().setImageBitmap(bitmap);
        }
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_view)
        ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}