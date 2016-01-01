package com.nantaphop.hovertouchviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.nantaphop.hovertouchviewexample.widget.MyThumbnail;
import com.nantaphop.hovertouchview.HoverTouchHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout root = (FrameLayout) findViewById(R.id.root);
        MyThumbnail pic1 = (MyThumbnail) findViewById(R.id.pic1);
        MyThumbnail pic2 = (MyThumbnail) findViewById(R.id.pic2);
        MyThumbnail pic3 = (MyThumbnail) findViewById(R.id.pic3);
        MyThumbnail pic4 = (MyThumbnail) findViewById(R.id.pic4);
        HoverTouchHelper.make(root, pic1);
        HoverTouchHelper.make(root, pic2);
        HoverTouchHelper.make(root, pic3);
        HoverTouchHelper.make(root, pic4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
