package com.example.sjastrzebski.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class MySurfaceView extends GLSurfaceView {

    protected MyRenderer theRenderer;

    public MySurfaceView(Context aContext) {
        //super(aContext);
        this(aContext, null);
    }

    public MySurfaceView(Context aContext, AttributeSet attrs) {
        super(aContext, attrs);

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        // set the theRenderer member
        theRenderer = new MyRenderer(aContext);
        setRenderer(theRenderer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                //convert touch coords to opengl <-1,1>; flip y
                theRenderer.touchOn(2.0f * e.getX() / getWidth() - 1.0f, 1.0f - 2.0f * e.getY() / getHeight());
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // finish handling touch events
                theRenderer.touchOff();
                break;
            default:
                break;
        }
        return false;
    }
}


