package com.example.canvasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    //goal: Place two balls on the screen and draw a line between them

    //notes for later:
    //SplashScreen with fade in/out
    //Toasts

    private CustomGraph customGraph = null;

    public static boolean drawRedBall = false;
    public static boolean drawBlueBall = false;

    private static TextView  messageText;

    //Creation method for the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("CanvasApp");

        //Initialize all our view elements
        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        final Button graphButton = findViewById(R.id.graphButton);
        final Button collideButton = findViewById(R.id.collideButton);
        final ToggleButton redButton = findViewById(R.id.redButton);
        redButton.toggle();
        final ToggleButton blueButton = findViewById(R.id.blueButton);
        messageText = findViewById(R.id.messageText);

        customGraph = new CustomGraph(getApplicationContext(), redButton, blueButton);
        customGraph.setOnTouchListener(customGraph);
        linLayout.addView(customGraph);


        //Click Listener for Graph
        graphButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               customGraph.drawLine();
                                           }
                                       }
        );

        //Click Listener for Collide
        collideButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               customGraph.drawCollide();
                                           }
                                       }
        );

        //Toggle listener for Red Button
        redButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // The toggle is enabled
                    if(blueButton.isChecked()) {
                        blueButton.toggle();
                    }
                } else {
                    // The toggle is disabled
                    if(!blueButton.isChecked()) {
                        blueButton.toggle();
                    }
                }

                //customGraph.draw();
            }
        });

        //Toggle Listener for Blue Button
        blueButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // The toggle is enabled
                    if(redButton.isChecked()) {
                        redButton.toggle();
                        //customGraph.draw();
                    }
                } else {
                    // The toggle is disabled
                    if(!redButton.isChecked()) {
                        redButton.toggle();
                    }
                }





            }
        });
    }

    //Will display an error message if true is passed, clear the error message otherwise.
    public static void ballError(boolean isError) {
        if(isError) messageText.setText("Error.");
        else messageText.setText("");
    }
}
