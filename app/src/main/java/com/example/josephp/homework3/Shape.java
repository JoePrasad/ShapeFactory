package com.example.josephp.homework3;

/**
 * Created by josephp on 6/28/16.
 */


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

public abstract class Shape extends View
{

    public enum ShapeType {
        CIRCLE, RECTANGLE
    }

    protected Coordinates coords;
    protected int color;

    public Shape(Context context)
    {
        super(context);

        coords = new Coordinates(0 ,0);
        color = Color.argb(255, 0, 0, 0);
    }


    public void setShapeAlpha(float alpha)
    {

        setAlpha(alpha);
    }


    public float getShapeAlpha()
    {

        return getAlpha();
    }

    public void removeShape()
    {

        setVisibility(GONE);
    }

    public Coordinates getCoords()
    {

        return coords;
    }

    public void setCoords(Coordinates position) {

        coords.x = position.x;
        coords.y = position.y;
    }

    public int getColor()
    {

        return color;
    }

    public int getColorAlpha()
    {

        return Color.alpha(color);
    }

    public void setColor(int color)
    {

        this.color = color;
    }

    public void setColorAlpha(int alpha)
    {

        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        this.color = Color.argb(alpha, red, green, blue);
    }

    public abstract ShapeType getShapeType();

    public abstract void onDraw(Canvas canvas);
    public abstract void setSize(float[] size);
}