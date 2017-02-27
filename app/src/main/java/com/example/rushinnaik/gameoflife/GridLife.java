package com.example.rushinnaik.gameoflife;

import android.util.Log;

/**
 * Created by Rushin Naik on 2/24/2017.
 */

public class GridLife {
    public boolean[][] array;
    public int gridrows;
    public int gridcols;



    public GridLife(int rows,int cols){
        gridrows = rows;
        gridcols = cols;
        array = new boolean[rows][cols];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                array[i][j] = Boolean.FALSE;
            }
        }


    }

    public void makeLIfe(int x, int y) {
        if (x < gridrows && y < gridcols) {
            if (this.array[x][y] == Boolean.TRUE)
                this.array[x][y] = Boolean.FALSE;
            else
                this.array[x][y] = Boolean.TRUE;
            Log.w("makeLife:", "" + x + "," + y + " set true");
        }
    }

    public void makeDead(int x, int y){
        if (x < gridrows && y < gridcols) {
            this.array[x][y] = Boolean.FALSE;
        }
    }

    public GridLife getCopy(){
        GridLife copy = new GridLife(gridrows,gridcols);
        for(int i = 0; i < gridrows; i++)
        {
            for(int j = 0; j < gridcols; j++)
            {
                copy.array[i][j] = array[i][j];
            }
        }
        return copy;
    }

}
