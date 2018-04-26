package com.example.sjastrzebski.opengl;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

public class UtilsTexture {

    public static int loadTexture(Context aContext, int anBitmapID){
        final int[] avnTextureObjectIDs = new int[1];
        GLES20.glGenTextures(1, avnTextureObjectIDs, 0);
        if(0==avnTextureObjectIDs[0]){
            Log.e(CommonConstants.LOG_TAG,"glGenTextures failed");
            return 0;
        }
        //Read from resources
        final BitmapFactory.Options aOptions = new BitmapFactory.Options();
        aOptions.inScaled = false;   //get the original image data instead of a scaled version of the data
        final Bitmap aBitmap = BitmapFactory.decodeResource(aContext.getResources(), anBitmapID, aOptions);
        if(null==aBitmap){
            GLES20.glDeleteTextures(1, avnTextureObjectIDs, 0);
            Log.e(CommonConstants.LOG_TAG,"decodeResource failed");
            return 0;
        }
        //tell OpenGL that future texture calls should be applied to this texture object
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, avnTextureObjectIDs[0]);
        //Set filtering
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR_MIPMAP_LINEAR); //reduced in size
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);               //when the texture is expanded

        // Load the bitmap into the bound texture.
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, aBitmap, 0);

        // Note: Following code may cause an error to be reported in the
        // ADB log as follows: E/IMGSRV(20095): :0: HardwareMipGen:
        // Failed to generate texture mipmap levels (error=3)
        // No OpenGL error will be encountered (glGetError() will return
        // 0). If this happens, just squash the source image to be
        // square. It will look the same because of texture coordinates,
        // and mipmap generation will work.

        GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);

        // Recycle the bitmap, since its data has been loaded into OpenGL.
        aBitmap.recycle();

        // Unbind from the texture.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

        return avnTextureObjectIDs[0];
    }

}
