package com.example.canvasapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ToggleButton;


public class CustomGraph extends SurfaceView implements SurfaceHolder.Callback, OnTouchListener {

    public SurfaceHolder surfaceHolder = null;
    private Paint ballPaint = null;
    private ToggleButton redButton = null;
    private ToggleButton blueButton = null;

    private float redBallX = 0;
    private float blueBallX = 0;
    private float redBallY = 0;

    private float blueBallY = 0;

    //Constructor. Takes in the two ToggleButtons needed to check which ball to draw.
    public CustomGraph(Context context, ToggleButton nredButton, ToggleButton nblueButton) {
        super(context);
        ballPaint = new Paint();
        ballPaint.setColor(Color.RED);
        this.redButton = nredButton;
        this.blueButton = nblueButton;

        //if(surfaceHolder == null) {
            // Get surfaceHolder object.
            surfaceHolder = getHolder();
            // Add this as surfaceHolder callback object.
            surfaceHolder.addCallback(this);
        //}
    }

    //Necessary Interface Implementations
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        ballPaint = null;
    }

    //Swaps draw color from Red to Blue and Blue to Red
    public void swapBallPaint() {
        if(ballPaint.getColor() == Color.RED) {
            this.ballPaint.setColor(Color.BLUE);
        }
        else if(ballPaint.getColor() == Color.BLUE) {
            this.ballPaint.setColor(Color.RED);
        }

    }

    //Event handler for touching the surface. Will check which ToggleBUtton is selected and set the
    //ball coords.
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            if (redButton.isChecked()) {
                redBallX = motionEvent.getX();
                redBallY = motionEvent.getY();
                if(MainActivity.drawRedBall == false) {
                    MainActivity.drawRedBall = true;
                }
            } else if (blueButton.isChecked()) {
                blueBallX = motionEvent.getX();
                blueBallY = motionEvent.getY();
                if(MainActivity.drawBlueBall == false) {
                    MainActivity.drawBlueBall = true;
                }
            }
            System.out.println("yeet");
            draw();
        }
        return true;
    }

    //Locks the canvas, draws the background and axis, and draws the balls if their coordinates have
    // been set.
    public void draw() {
        //lock canvas
        Canvas canvas = surfaceHolder.lockCanvas();

        //draw background
        Paint surfaceBackground = new Paint();
        surfaceBackground.setColor(Color.GRAY);
        canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), surfaceBackground);

        //draw axis
        Paint surfaceLines = new Paint();
        surfaceLines.setColor(Color.BLACK);
        canvas.drawLine(this.getWidth()/2, 0,this.getWidth()/2, this.getHeight(),surfaceLines );
        canvas.drawLine(0, this.getHeight()/2,this.getWidth(), this.getHeight()/2,surfaceLines );

        //Ball drawing handler. Swaps color if necessary.
        if(MainActivity.drawRedBall == true) {
            if(ballPaint.getColor() != Color.RED) {
                swapBallPaint();
            }
            canvas.drawCircle(redBallX, redBallY, 30, ballPaint);
        }
        if(MainActivity.drawBlueBall == true) {
            if(ballPaint.getColor() != Color.BLUE) {
                swapBallPaint();
            }
            canvas.drawCircle(blueBallX, blueBallY, 30, ballPaint);
        }

        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    //Locks the canvas, draws the background and axis, and draws the balls if their coordinates have
    // been set. Also will draw a line connecting the two balls if they exist.
    public void drawLine() {
        //lock canvas
        Canvas canvas = surfaceHolder.lockCanvas();

        //draw background
        Paint surfaceBackground = new Paint();
        surfaceBackground.setColor(Color.GRAY);
        canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), surfaceBackground);

        //draw axis
        Paint surfaceLines = new Paint();
        surfaceLines.setColor(Color.BLACK);
        canvas.drawLine(this.getWidth()/2, 0,this.getWidth()/2, this.getHeight(),surfaceLines );
        canvas.drawLine(0, this.getHeight()/2,this.getWidth(), this.getHeight()/2,surfaceLines );

        //Ball drawing handler. Swaps color if necessary.
        if(MainActivity.drawRedBall == true) {
            if(ballPaint.getColor() != Color.RED) {
                swapBallPaint();
            }
            canvas.drawCircle(redBallX, redBallY, 30, ballPaint);
        }
        if(MainActivity.drawBlueBall == true) {
            if(ballPaint.getColor() != Color.BLUE) {
                swapBallPaint();
            }
            canvas.drawCircle(blueBallX, blueBallY, 30, ballPaint);
        }

        if(MainActivity.drawRedBall == true && MainActivity.drawBlueBall == true) {
            //float[] lineCoords = findLine(redBallX,redBallY, blueBallX, blueBallY);
            surfaceLines.setColor(Color.MAGENTA);
            //canvas.drawLine(lineCoords[0], lineCoords[1], lineCoords[2], lineCoords[3], surfaceLines);
            canvas.drawLine(redBallX,redBallY, blueBallX, blueBallY, surfaceLines);
            MainActivity.ballError(false);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
        else {
            MainActivity.ballError(true);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    //Collides the two balls by bringing them closer together.
    public void drawCollide() {

        if( !(MainActivity.drawRedBall == true && MainActivity.drawBlueBall == true) ) {
            MainActivity.ballError(true);
            return;
        }
        MainActivity.ballError(false);

        while(  (redBallX > blueBallX+5 || redBallX < blueBallX-5 ) ||
                (redBallY > blueBallY+5 || redBallY < blueBallY-5 ) ) {
            //lock canvas
            Canvas canvas = surfaceHolder.lockCanvas();

            //draw background
            Paint surfaceBackground = new Paint();
            surfaceBackground.setColor(Color.GRAY);
            canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), surfaceBackground);

            //draw axis
            Paint surfaceLines = new Paint();
            surfaceLines.setColor(Color.BLACK);
            canvas.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight(), surfaceLines);
            canvas.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2, surfaceLines);

            //Ball drawing handler. Swaps color if necessary.
            if (MainActivity.drawRedBall == true) {
                if (ballPaint.getColor() != Color.RED) {
                    swapBallPaint();
                }
                canvas.drawCircle(redBallX, redBallY, 30, ballPaint);
            }
            if (MainActivity.drawBlueBall == true) {
                if (ballPaint.getColor() != Color.BLUE) {
                    swapBallPaint();
                }
                canvas.drawCircle(blueBallX, blueBallY, 30, ballPaint);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);

            //slowly moves the balls towards eachother
            if(redBallX>blueBallX) {
                redBallX-=5;
                blueBallX+=5;
            }
            else {
                redBallX+=5;
                blueBallX-=5;
            }
            if(redBallY>blueBallY) {
                redBallY-=3;
                blueBallY+=3;
            }
            else {
                redBallY+=3;
                blueBallY-=3;
            }
        }
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

    public float getRedBallY() {
        return redBallY;
    }

    public void setRedBallY(float redBallY) {
        this.redBallY = redBallY;
    }

    public float getBlueBallY() {
        return blueBallY;
    }

    public void setBlueBallY(float blueBallY) {
        this.blueBallY = blueBallY;
    }

    public Paint getPaint() {
        return ballPaint;
    }


    //Will return the start x,y and end x,y of the line that would connect two points, that also
    // hits both sides of the screen.
    //Index: 0  xStart, Index: 1    yStart, Index: 2  xEnd, Index: 3    yEnd
    public float[] findLine(float x1, float x2, float y1, float y2) {
        float[] ret = new float[4];

        float m = (y2 - y1) / (x2 - x1);
        float b = y1-(m*x1);



        ret[0] = (0+b)/m;
        ret[1] = 0;
        ret[2] = (this.getHeight()+b)/m;
        ret[3] = this.getHeight();

        return ret;
    }

}
