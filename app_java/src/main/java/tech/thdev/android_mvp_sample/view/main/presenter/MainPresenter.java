package tech.thdev.android_mvp_sample.view.main.presenter;

import android.content.Context;

import java.util.ArrayList;

import tech.thdev.android_mvp_sample.adapter.contract.ImageAdapterContract;
import tech.thdev.android_mvp_sample.data.ImageItem;
import tech.thdev.android_mvp_sample.data.SampleImageData;

/**
 * Created by tae-hwan on 12/22/16.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;

    private ImageAdapterContract.View adapterView;
    private ImageAdapterContract.Model adapterModel;

    private SampleImageData sampleImageData;

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void setImageAdapterModel(ImageAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setImageAdapterView(ImageAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void setSampleImageData(SampleImageData sampleImageData) {
        this.sampleImageData = sampleImageData;
    }

    @Override
    public void loadItems(Context context, boolean isClear) {
        ArrayList<ImageItem> items = sampleImageData.getImages(context, 10);
        if(isClear) {
            adapterModel.clearItem();
        }
        adapterModel.addItems(items);
        adapterView.notifyAdapter();
//        view.addItems(items, isClear);
//        view.notifyAdapter();  //adapter 갱신
    }
}