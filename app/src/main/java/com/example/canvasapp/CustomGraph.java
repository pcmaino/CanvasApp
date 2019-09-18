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


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    public CustomGraph(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    public void draw() {

        
    }
}
