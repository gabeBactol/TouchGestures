package edu.miracostacollege.cs134.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private GestureDetector mGestureDetector;
    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesScrollView = findViewById(R.id.gesturesScrollView);

        gesturesLogTextView = findViewById(R.id.gesturesLogTextView);
        singleTapTextView = findViewById(R.id.singleTapTextView);
        doubleTapTextView = findViewById(R.id.doubleTapTextView);
        longPressTextView = findViewById(R.id.longPressTextView);
        scrollTextView = findViewById(R.id.scrollTextView);
        flingTextView = findViewById(R.id.flingTextView);

        mGestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        gesturesLogTextView.append("onDown touch event\n");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        gesturesLogTextView.append("onShowPress touch event\n");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        gesturesLogTextView.append("onSingleTapUp touch event\n");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
       // gesturesLogTextView.append("onScroll: distanceX is " + distanceX + ", distanceY is " + distanceY + ".\n");
        scrolls++;
        scrollTextView.setText(String.valueOf(scrolls));
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        longPresses++;
        gesturesLogTextView.append("OnLongPress pressed\n");
        longPressTextView.setText(String.valueOf(longPresses));
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
       // gesturesLogTextView.append("onFling: velocityX is " + velocityX + ", velocityY is " + velocityY + ".\n");
        flings++;
        flingTextView.setText(String.valueOf(flings));
        if(velocityX > 0 && Math.abs(velocityX) > Math.abs(velocityY))
        {
            gesturesLogTextView.append("Swipe Right");
        }
        else
        {
            gesturesLogTextView.append("Swipe left");
        }
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        gesturesLogTextView.append("onSingleTapConfirmed\n\n");
        singleTaps++;
        singleTapTextView.setText(String.valueOf(singleTaps));
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        gesturesLogTextView.append("onDoubleTap touch event");
        doubleTaps++;
        doubleTapTextView.setText(String.valueOf(doubleTaps));
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        gesturesLogTextView.append("Event occurred within onDoubleTap touch event\n\n");

        return true;
    }

    public void clearAll(View v)
    {
        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings = 0;
        singleTapTextView.setText(getString(R.string.zero));
        doubleTapTextView.setText(getString(R.string.zero));
        longPressTextView.setText(getString(R.string.zero));
        scrollTextView.setText(getString(R.string.zero));
        flingTextView.setText(getString(R.string.zero));
        gesturesLogTextView.setText("");
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev);
    }
}
