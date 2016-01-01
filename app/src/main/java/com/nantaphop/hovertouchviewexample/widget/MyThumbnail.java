package com.nantaphop.hovertouchviewexample.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nantaphop.hovertouchview.HoverTouchAble;
import com.nantaphop.hovertouchviewexample.R;


/**
 * Created by nantaphop on 01-Jan-16.
 */
public class MyThumbnail extends ImageView implements HoverTouchAble {
    public MyThumbnail(Context context) {
        super(context);
    }

    public MyThumbnail(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyThumbnail(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public View getHoverView() {
//        ImageView imageView = new ImageView(getContext());
//        imageView.setImageDrawable(this.getDrawable());
//        return imageView;
        return new MyThumbnailExpand(getContext(), getDrawable(), "Description Text For Photo\nNoted : This is just demo view you better to make your own view.");
    }

    @Override
    public int getHoverAnimateDuration() {
        return 300;
    }

    @Override
    public void onStartHover() {
        Toast.makeText(getContext(), "Start Hover", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStopHover() {
        Toast.makeText(getContext(), "Stop Hover", Toast.LENGTH_SHORT).show();
    }

}
