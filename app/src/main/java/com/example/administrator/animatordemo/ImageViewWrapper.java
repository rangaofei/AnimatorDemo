package com.example.administrator.animatordemo;

import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class ImageViewWrapper {

    private ImageView imageView;
    private float height;
    private float width;
    private ViewGroup.LayoutParams lp;

    public ImageViewWrapper(ImageView imageView) {
        this.imageView = imageView;
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        lp = imageView.getLayoutParams();
        this.height = lp.height;
        this.width = lp.width;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        lp.height = (int) height;
        imageView.setLayoutParams(lp);
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float width) {
        lp.width = (int) width;
        imageView.setLayoutParams(lp);
    }
}
