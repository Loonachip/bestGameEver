package com.example.sjastrzebski.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class GameSurfaceView  extends GLSurfaceView {
    private Context context;
    private GameRenderer renderer;


    public GameSurfaceView(Context context) {
        //super(context);
        this(context, null);
    }

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        // set the theRenderer member
        renderer = new GameRenderer(this.context, new Game());

        setRenderer(renderer);
    }
}
