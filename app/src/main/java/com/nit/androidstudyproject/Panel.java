package com.nit.androidstudyproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

class Panel extends View {
    public Panel(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /* Make the background of the canvas black */
        canvas.drawColor(Color.BLACK);
        /* Create an object of the Paint class */
        Paint paint = new Paint();
        /* Set the color, stroke width, and style attributes of the Paint object */
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        /* Draw a circle with radius 30 and center at (50, 50) */
        canvas.drawCircle(50, 50, 30, paint);
        /* Change the style and color of attributes of the Paint object */
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
/* Draw a rectangle with its top-left corner at
(150, 150) and bottom-right corner at (200, 200) */
        canvas.drawRect(150, 150, 200, 200, paint);
        /* Change the color attribute of the Paint object */
        paint.setColor(Color.RED);
        /* Draw a line from point (50, 50) to point (150, 150) */
        canvas.drawLine(50, 50, 150, 150, paint);

    }
}
