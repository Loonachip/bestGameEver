package com.example.sjastrzebski.opengl;


import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRenderer implements GLSurfaceView.Renderer {

    private Game game;
    private GameController gc;

    protected Context theContext;

    protected long thelStartTime;
    protected double thedLastTimestep;

    protected MyBackground theMyBackground;
    protected MySpaceship theMySpaceship;

    public MyRenderer(Context aContext) {
        theContext = aContext;
    }

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig eglConfig) {
        // Set the background frame color
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        gc = new GameController(theContext, theMySpaceship);

        theMyBackground = new MyBackground(theContext);
        theMySpaceship = new MySpaceship(theContext);

        thelStartTime = System.nanoTime();
        thedLastTimestep = 0.0;
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int anWidth, int anHeight) {
        GLES20.glViewport(0, 0, anWidth, anHeight);
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        long alCurrentTime = System.nanoTime() - thelStartTime;
        double adCurrentTime = (1e-9) * alCurrentTime;  //ns to sec
        double adElapsedTime = adCurrentTime - thedLastTimestep;


        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        theMyBackground.update(adCurrentTime, adElapsedTime);
        //theMySpaceship.update(adCurrentTime, adElapsedTime, game.cam.getCameraAngle());

        theMyBackground.drawShape();

        GLES20.glEnable(GLES20.GL_BLEND);
        theMySpaceship.drawShape();
        GLES20.glDisable(GLES20.GL_BLEND);


        thedLastTimestep = adCurrentTime;
    }

    public void touchOn(float x, float y) {
        theMySpaceship.touchOn(x, y);
    }

    public void touchOff() {
        theMySpaceship.touchOff();
    }
}
