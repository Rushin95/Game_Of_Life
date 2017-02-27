package com.example.rushinnaik.gameoflife;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.R.attr.tag;

public class GamePlay extends AppCompatActivity {
    private GridView customGrid;
    private Button resetb;
    private Button nextb;
    private Button homeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        customGrid = (GridView) findViewById(R.id.grid_view);
        resetb = (Button) findViewById(R.id.reset_button);
        nextb = (Button) findViewById(R.id.next_button);
        homeb = (Button) findViewById(R.id.home_button);


        homeb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Start NewActivity.class
                AlertDialog.Builder builder = new AlertDialog.Builder(GamePlay.this);

                builder.setTitle("Go to Home Page?");
                builder.setMessage("This will clear your progress.");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        dialog.dismiss();
                        Intent back_to_home = new Intent(GamePlay.this,
                                MainActivity.class);
                        startActivity(back_to_home);
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();



            }
        });

        // Capture Reset button clicks
        resetb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GamePlay.this);

                builder.setTitle("Are you sure?");
                builder.setMessage("This will clear your progress.");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        customGrid.clearCanvas();
                        dialog.dismiss();
                        Toast.makeText(GamePlay.this, "The grid is reset. You may start with a fresh grid.",
                        Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });


        nextb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                GridLife newLife = customGrid.gridlife.getCopy();


                Log.w("row,col:",""+newLife.gridrows+","+newLife.gridcols);
                for(int i = 0; i < newLife.gridrows; i++)
                {
                    for(int j = 0; j < newLife.gridcols; j++)
                    {
                        int countLife = 0;
                        if(i!=0 && j!=0)
                            if(newLife.array[i-1][j-1] == Boolean.TRUE)
                                countLife++;
                        if(i!=0)
                            if(newLife.array[i-1][j] == Boolean.TRUE)
                                countLife++;
                        if(i!=0 && j< newLife.gridcols-1)
                            if(newLife.array[i-1][j+1] == Boolean.TRUE)
                                countLife++;



                        if(j < newLife.gridcols-1)
                            if(newLife.array[i][j+1] == Boolean.TRUE)
                                countLife++;
                        if(j!=0)
                            if(newLife.array[i][j-1] == Boolean.TRUE)
                                countLife++;



                        if(j!=0 && i < newLife.gridrows-1)
                            if(newLife.array[i+1][j-1] == Boolean.TRUE)
                                countLife++;
                        if(i < newLife.gridrows-1)
                            if(newLife.array[i+1][j] == Boolean.TRUE)
                                countLife++;
                        if(j < newLife.gridcols-1 && i < newLife.gridrows-1)
                            if(newLife.array[i+1][j+1] == Boolean.TRUE)
                                countLife++;


                        if(countLife > 3) {
                            Log.w(" > 3 .... Made Dead:", "" + i + "," + j+"count:"+countLife);
                            customGrid.gridlife.array[i][j] = Boolean.FALSE;
                        }
                        else if(countLife < 2) {
                            customGrid.gridlife.array[i][j] = Boolean.FALSE;
                        }
                        else if(newLife.array[i][j] == Boolean.TRUE) {
                            if (countLife == 3 || countLife == 2) {
                                customGrid.gridlife.array[i][j] = Boolean.TRUE;
                                Log.w("LIfe given: ", "" + i + "," + j+"count:"+countLife);
                            }
                        }
                        else if(newLife.array[i][j] == Boolean.FALSE) {
                            if (countLife == 3) {
                                customGrid.gridlife.array[i][j] = Boolean.TRUE;
                                Log.w("Life given: ", "" + i + "," + j+"count:"+countLife);
                            }else if(countLife == 2){
                                customGrid.gridlife.array[i][j] = Boolean.FALSE;

                            }
                        }
                    }
                }
                for(int i = 0; i < newLife.gridrows; i++)
                {
                    for(int j = 0; j < newLife.gridcols; j++) {
                        Log.w(""+"array("+i+","+j+")",""+ newLife.array[i][j]);
                        Log.w(""+"CUstom grid array("+i+","+j+")",""+ customGrid.gridlife.array[i][j]);
                    }

                }

                customGrid.invalidate();


            }

        });


    }

        public void clearCanvas(View v)
    {
        customGrid.clearCanvas();
    }
}
