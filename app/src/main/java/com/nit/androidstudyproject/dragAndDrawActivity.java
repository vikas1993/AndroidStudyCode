package com.nit.androidstudyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class dragAndDrawActivity extends AppCompatActivity {
    Paint paint;
    Point point1, point2;
    /* Declare a variable of type path that can store the (x,y)
    coordinates of multiple points along a path */
    Path path;
    /* Declare a list of paths */
    List<Path> paths = new ArrayList<Path>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Panel(this));
    }


    class Panel extends View implements View.OnTouchListener {

        public Panel(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.GREEN); paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            /* Specify the onTouchListener callback */
            this.setOnTouchListener(this);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            /* Set the background color to black */
            canvas.drawColor(Color.BLACK);
            /* For each path in the paths list, draw the path on the canvas */
            for (Path path : paths)
            {
                canvas.drawPath(path, paint);
            }

        }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            /* If a pressed gesture has started */
            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                /* Obtain the current x and y coordinates and store them in an object of type, Point */
                point1 = new Point();
                point1.x = (int) event.getX();
                point1.y = (int) event.getY();
                /* Initialize a new Path variable */
                path = new Path();
                /* Add the current x and y coordinates to the path to indicate the starting point of the path. */
                path.moveTo(point1.x, point1.y);

            }
            /* If a move action has happened after initiating a press gesture and before ending it */
            else if (event.getAction() == MotionEvent.ACTION_MOVE)
            {
                /* Obtain the current x and y coordinates */
                point2 = new Point();
                point2.x = (int) event.getX();
                point2.y = (int) event.getY();

                /* Add a line in the path from the last point to point2 */
                path.lineTo(point2.x, point2.y);

                /* Add the path to the paths list */
                paths.add(path);

                /* Invalidate the view */
                invalidate();
            }
            return true;
        }
    }

}