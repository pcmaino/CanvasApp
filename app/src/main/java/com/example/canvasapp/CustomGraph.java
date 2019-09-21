package com.example.canvasapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Canvas;

public class CustomGraph extends SurfaceView implements SurfaceHolder.Callback {

    public SurfaceHolder surfaceHolder = null;
    private Paint paint = null;

    private float redBallX = 0;
    private float blueBallX = 0;

    public CustomGraph(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);

        if(surfaceHolder == null) {
            // Get surfaceHolder object.
            surfaceHolder = getHolder();
            // Add this as surfaceHolder callback object.
            surfaceHolder.addCallback(this);
        }
        draw();
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        paint = null;
    }

    public Paint getPaint() {
        return paint;
    }

    public void swapPaint() {
        if(paint.getColor() == Color.RED) {
            this.paint.setColor(Color.BLUE);
        }
        else if(paint.getColor() == Color.BLUE) {
            this.paint.setColor(Color.RED);
        }

    }

    public void draw() {
        Canvas canvas = surfaceHolder.lockCanvas();



        Paint surfaceBackground = new Paint();
        surfaceBackground.setColor(Color.GRAY);
        canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), surfaceBackground);

        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    public float getBlueBallX() {
        return blueBallX;
    }

    public void setBlueBallX(float blueBallX) {
        this.blueBallX = blueBallX;
    }

    public float getRedBallX() {
        return redBallX;
    }

    public void setRedBallX(float redBallX) {
        this.redBallX = redBallX;
    }


}
