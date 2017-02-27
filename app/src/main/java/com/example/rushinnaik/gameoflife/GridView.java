package com.example.rushinnaik.gameoflife;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Rushin Naik on 2/22/2017.
 */

public class GridView extends View {
    public GridLife gridlife = null;
    public int width;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    Context context;
    private Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;
    int gcolCount = 0;
    int growCount = 0;
    int gcellSize = 0;
    float xOverFlow = 0;
    float yOverFlow = 0;

    public GridView(Context c, AttributeSet attrs) {


        super(c, attrs);
        context = c;
        // we set a new Path
        mPath = new Path();
        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);
    }


    protected void drawGrid(int cellSize, int height, int width, Canvas canvas) {
        this.gcellSize = cellSize;
        int colCount = width / cellSize;
        this.gcolCount = colCount;
        int rowCount = height / cellSize;
        this.growCount = rowCount;



        float verticalOverflow = height - (rowCount * cellSize);
        this.yOverFlow = verticalOverflow;
        float horizontalOverflow = width - (colCount * cellSize);
        this.xOverFlow = horizontalOverflow;

        // Drawing vertical lines
        for(int i = 0; i <= colCount; i++) {
            canvas.drawLine(i * cellSize + horizontalOverflow / 2, verticalOverflow / 2, i * cellSize + horizontalOverflow / 2, rowCount * cellSize + verticalOverflow / 2, mPaint);
        }

        // Drawing horizontal lines
        for(int i = 0; i <= rowCount; i++) {
            canvas.drawLine(horizontalOverflow / 2, i * cellSize + verticalOverflow / 2, colCount * cellSize + horizontalOverflow / 2, i * cellSize + verticalOverflow / 2, mPaint);
        }
        Paint pcircle = new Paint();
        pcircle.setColor(Color.RED);

        if(gridlife == null) {
            gridlife = new GridLife(growCount,gcolCount);
        }
        for(int i = 0; i < growCount; i++)
        {
            for(int j = 0; j < gcolCount; j++)
            {
                if(gridlife.array[i][j] == Boolean.TRUE)
                {
                    canvas.drawCircle((j*cellSize)+(horizontalOverflow/2)+cellSize/2,(i*cellSize)+(verticalOverflow/2)+cellSize/2, (cellSize/2)- 1 ,pcircle);
                }
            }
        }


    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }
            // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGrid(90, findViewById(R.id.grid_view).getHeight(), findViewById(R.id.grid_view).getWidth(), canvas);
    }



    public void clearCanvas() {
        mPath.reset();

        gridlife = new GridLife(growCount,gcolCount);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
//                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                Log.w("Coordinates", ""+x+","+y);
                Log.w("overflow x :",""+xOverFlow);
                this.GiveCellCords(x,y);
//                upTouch();
                invalidate();
                break;
        }
        return true;
    }


    public void GiveCellCords(float x, float y)
    {
        if(x >= (xOverFlow/2) &&  y >= (yOverFlow/2)) {
            int row = (int) (y - (this.yOverFlow / 2)) / this.gcellSize;
            int col = (int) (x - (this.xOverFlow / 2)) / this.gcellSize;
            Log.w("Cell:", "[" + row + "," + col + "]");
            this.gridlife.makeLIfe(row, col);
        }

    }
}
