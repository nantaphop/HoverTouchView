# HoverTouchView
Stimulate Apple's Force Touch or 3D Touch on Android App with Hover Gesture

![Hover Touch Gif](https://media.giphy.com/media/xT77Ybv4VQzCYTJJAc/giphy.gif)
[Watch in Youtube Click Here!](https://www.youtube.com/watch?v=Ov4fkrCIuk4&feature=youtu.be)

#Welcome for Pull Request 

#Features
* Allow Custom View as a Hover View
* Built-in Blur Effect on Backgroud
* Config for Animation Duration
* Provide `onStartHover()` and `onStopHover()` for flexibility

#Limitation
* Root View Must Be `FrameLayout`(Because Hover View will add as a child in `FrameLayout`)

#Setup
**Gradle (jCenter)**
```
  dependencies {
    compile 'com.nantaphop:hoverTouchView:0.1'
  }
```

#Usage
This library work as a util class that call `setOnTouchListener(...)` to the view that you want user to hold a finger to see more information.
Basically, I provided `HoverTouchAble` interface that you have to implement on your Custom View Class that act as a hover area such as Thumbnail, List Item, Cover Image
```
public interface HoverTouchAble{
    public View getHoverView(); // return view that appear when user hold touch on this view
    public int getHoverAnimateDuration(); // return duration(ms) for show/hide Hover View Animation
    public void onStartHover(); // Callback when user start hover this view
    public void onStopHover(); // Callback when user stop hover this view
}
```
Then use helper class to initial hover view
```
MyThumbnail pic1 = (MyThumbnail) findViewById(R.id.pic1);
HoverTouchHelper.make(root, pic1);
```
###Example
####Custom Image View as Thumbnail
```
public class MyThumbnail extends ImageView implements HoverTouchAble {
  ...
  
    @Override
    public View getHoverView() {
        return new MyThumbnailExpand(getContext(), getDrawable(), "Description Text For Photo");
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
```
####Create Your Custom View that show when user hover
```
public class MyThumbnailExpand extends LinearLayout {
  ...
  public MyThumbnailExpand(Context context, Drawable drawable, String text) {
        super(context);
        this.drawable = drawable;
        this.text = text;
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
```

####Your Hover Able View must place in FrameLayout
```
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/root"
    ... >
    ...
    <com.nantaphop.hovertouchviewexample.widget.MyThumbnail
            android:id="@+id/pic1"
            android:layout_width="128dp"
            android:layout_height="128dp"
            android:src="@drawable/pic1" />
    ...
</FrameLayout>
```
####Then, Use `HoverTouchHelper` to initial your hover view
```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    MyThumbnail pic1 = (MyThumbnail) findViewById(R.id.pic1);
    MyThumbnail pic2 = (MyThumbnail) findViewById(R.id.pic2);
    MyThumbnail pic3 = (MyThumbnail) findViewById(R.id.pic3);
    MyThumbnail pic4 = (MyThumbnail) findViewById(R.id.pic4);
    HoverTouchHelper.make(root, pic1);
    HoverTouchHelper.make(root, pic2);
    HoverTouchHelper.make(root, pic3);
    HoverTouchHelper.make(root, pic4);
}
```

#Acknowledgement
*Inspire by Instagram Team that reinvent their 3D Touch from iOS to something similar in Anroid
*Thank to `wasasbeef` for Blurry [https://github.com/wasabeef/Blurry](https://github.com/wasabeef/Blurry)

#License
```
Copyright 2016 Nantaphop Phuengphae

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
