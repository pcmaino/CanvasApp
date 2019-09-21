package com.example.canvasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    //goal: Place two balls on the screen and draw a line between them

    //notes for later:
    //SplashScreen with fade in/out
    //Toasts

    private CustomGraph customGraph = null;

    public boolean drawRedBall = false;
    public boolean drawBlueBall = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        final Button graphButton = findViewById(R.id.graphButton);
        final ToggleButton redButton = findViewById(R.id.redButton);
        final ToggleButton blueButton = findViewById(R.id.blueButton);

        customGraph = new CustomGraph(getApplicationContext());
        linLayout.addView(customGraph);

        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customGraph.draw();
            }
        }
        );

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
}
