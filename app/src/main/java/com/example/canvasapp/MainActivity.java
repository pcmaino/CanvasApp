package com.example.canvasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    //goal: Place two balls on the screen and draw a line between them

    //notes for later:
    //SplashScreen with fade in/out
    //Toasts

    private Button graphButton = null;
    private ToggleButton redButton = null;
    private ToggleButton blueButton = null;
    private LinearLayout linLayout = null;
    private CustomGraph customGraph = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linLayout = findViewById(R.id.linLayout);
        graphButton = findViewById(R.id.graphButton);
        redButton = findViewById(R.id.redButton);
        blueButton = findViewById(R.id.blueButton);
        customGraph = new CustomGraph(getApplicationContext());
        linLayout.addView(customGraph);

        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }
        );

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //when this is clicked, unclick blue, and vice versa
            }
        }
        );

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }
        );
    }
}
