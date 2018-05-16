package com.example.sjastrzebski.opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GameRenderer  implements GLSurfaceView.Renderer  {
    /////
    protected Context theContext;

    protected long thelStartTime;
    protected double thedLastTimestep;

    protected MyBackground theMyBackground;
    protected MySpaceship theMySpaceship;
    /////

    private Context context;
    private GameController gameController;
    private Game game;

    public GameRenderer(Context c){
        this.context = c;
        theMyBackground = new MyBackground(theContext);
        theMySpaceship = new MySpaceship(theContext);
        this.gameController = new GameController(context, theMySpaceship);
    }

    public GameRenderer(Context c, Game g){
        this.context = c;
        this.game = g;
        theMyBackground = new MyBackground(theContext);
        theMySpaceship = new MySpaceship(theContext);
        this.gameController = new GameController(context, theMySpaceship);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        // Set the background frame color
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        thelStartTime = System.nanoTime();
        thedLastTimestep = 0.0;
        //this.clearBuffers();

        // TODO: initialise game objects here

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        long alCurrentTime = System.nanoTime() - thelStartTime;
        double adCurrentTime = (1e-9) * alCurrentTime;  //ns to sec
        double adElapsedTime = adCurrentTime - thedLastTimestep;


        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        theMyBackground.update(adCurrentTime, adElapsedTime);
        theMySpaceship.update(adCurrentTime, adElapsedTime, gameController.xvalue.x);

        theMyBackground.drawShape();

        GLES20.glEnable(GLES20.GL_BLEND);
        theMySpaceship.drawShape();
        GLES20.glDisable(GLES20.GL_BLEND);


        thedLastTimestep = adCurrentTime;
        //this.clearBuffers();

        //game.cam.setCameraAngle(game.cam.getCameraAngle() + 1);
        //game.cam.setCameraAngle(gameController.xvalue.x);
        // TODO: draw objects here

    }

    private void clearBuffers(){
        // Set the background frame color
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        // clear depth buffer and colour buffer
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
    }

    public void touchOn(float x, float y) {
        theMySpaceship.touchOn(x, y);
    }

    public void touchOff() {
        theMySpaceship.touchOff();
    }
}
