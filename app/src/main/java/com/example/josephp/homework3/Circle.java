package com.example.josephp.homework3;

/*
 * Created by josephp on 6/28/16.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;


public class Circle extends Shape
{
    private float radius;

    public Circle(Context context)
    {
        super(context);

        radius = 100;
    }

    public Circle(Context context, Coordinates coords, float radius, int color) {
        super(context);

        //represents the center of the circle
        this.coords.x = coords.x;
        this.coords.y = coords.y;

        this.radius = radius;
        this.color = color;
    }

    public void setRadius(float radius) {

        this.radius = radius;
    }

    public float getRadius() {

        return radius;
    }

    public void setSize(float size[]) {

        if(size != null && size.length == 1) {
            setRadius(size[0]);
        }
        else {
            throw new RuntimeException("Error: Circle Size");
        }
    }

    public Shape.ShapeType getShapeType() {

        return Shape.ShapeType.CIRCLE;
    }

    public void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(color);

        canvas.drawCircle(coords.x, coords.y, radius, paint);
    }
}
