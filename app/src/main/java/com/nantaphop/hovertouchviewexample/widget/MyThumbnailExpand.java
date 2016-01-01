package com.nantaphop.hovertouchviewexample.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nantaphop.hovertouchviewexample.R;

/**
 * Created by nantaphop on 01-Jan-16.
 */
public class MyThumbnailExpand extends LinearLayout {
    private Drawable drawable;
    private String text;

    public MyThumbnailExpand(Context context, Drawable drawable, String text) {
        super(context);
        this.drawable = drawable;
        this.text = text;
        init();
    }

    public MyThumbnailExpand(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyThumbnailExpand(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.view_my_thumbnail_expand, this);
        ImageView img = (ImageView) findViewById(R.id.img);
        TextView text = (TextView) findViewById(R.id.text);
        img.setImageDrawable(this.drawable);
        text.setText(this.text);

    }


}
