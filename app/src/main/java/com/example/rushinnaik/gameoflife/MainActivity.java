package com.example.rushinnaik.gameoflife;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Locate the button in activity_main.xml
        Button startButton = (Button) findViewById(R.id.start_button);
        Button exitButton = (Button)findViewById(R.id.exit_button);

        // Exit Button click
        exitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //Close the app
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        // Capture button clicks
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        GamePlay.class);
                startActivity(myIntent);
            }
        });
    }
}
