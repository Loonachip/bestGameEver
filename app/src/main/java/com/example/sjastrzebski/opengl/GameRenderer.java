package com.example.sjastrzebski.opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GameRenderer  implements GLSurfaceView.Renderer  {
    private Context context;
    private GameController gameController;
    private Game game;

    public GameRenderer(Context c){
        this.context = c;
        this.gameController = new GameController(context, game);
    }

    public GameRenderer(Context c, Game g){
        this.context = c;
        this.game = g;
        this.gameController = new GameController(context, game);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        this.clearBuffers();

        // TODO: initialise game objects here

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        this.clearBuffers();

        // TODO: tutaj jebnij setowanie kąta kamery
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
}
