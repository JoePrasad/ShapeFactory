package com.example.josephp.homework3;

/**
 * Created by josephp on 6/28/16.
 */


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;


public class Rectangle extends Shape {

    private float length;
    private float width;

    public Rectangle(Context context) {
        super(context);

        length = 200;
        width = 200;
    }

    public Rectangle(Context context, Coordinates coords, float length, float width, int color) {
        super(context);

        //represents the center of the rectangle
        this.coords.x = coords.x;
        this.coords.y = coords.y;

        this.length = length;
        this.width = width;
        this.color = color;
    }

    public void setLength(float length) {

        this.length = length;
    }

    public float getRectLength() {

        return length;
    }

    public void setWidth(float width) {

        this.width = width;
    }

    public float getRectWidth() {

        return width;
    }
    public void setSize(float[] size) {

        if(size != null && size.length == 2) {

            setLength(size[0]);
            setWidth(size[1]);
        }
        else {
            throw new RuntimeException("Error: Rectangle Size");
        }
    }

    public ShapeType getShapeType() {

        return ShapeType.RECTANGLE;
    }

    public void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(color);

        float leftX = coords.x - length/2;
        float leftY = coords.y - width/2;
        float rightX = coords.x + length/2;
        float rightY = coords.y + width/2;

        canvas.drawRect(leftX, leftY, rightX, rightY, paint);
    }
}