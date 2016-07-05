package com.example.josephp.homework3;

/**
 * Created by josephp on 6/28/16.
 */


import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;

import android.view.Display;
import android.view.WindowManager;


import java.util.Random;
public class ShapeFactory {

    public Shape getShape(Context context, Shape.ShapeType shape) {

        if(shape == null) {
            return null;
        }

        //get max screen size
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        Point displaySize = new Point();
        display.getSize(displaySize);

        int screenWidth = displaySize.x;
        int screenHeight = displaySize.y;


        int offsetTop = screenHeight/10;
        int offsetBot = screenHeight/10;

        Random rand = new Random();

        switch(shape) {
            case CIRCLE: {

                float maxRadius = screenWidth/4;
                float minRadius = screenWidth/8;


                float radius = rand.nextFloat()*(maxRadius-minRadius) + minRadius;

                //make coordinates stay inside the image view
                float xCoord = rand.nextFloat()*(screenWidth-radius) + radius;
                float yCoord = rand.nextFloat()*(screenHeight-radius) + radius;
                Coordinates coords = new Coordinates(xCoord, yCoord);

                //colors
                int alpha = 255;//rand.nextInt(256);
                int red = rand.nextInt(256);
                int green = rand.nextInt(256);
                int blue = rand.nextInt(256);
                int color = Color.argb(alpha, red, green, blue);

                return new Circle(context, coords, radius, color);
            }
            case RECTANGLE: {


                float maxSize = screenWidth/2;
                float minSize = screenWidth/4;


                float length = rand.nextFloat()*(maxSize - minSize) + minSize;
                float width = rand.nextFloat()*(maxSize - minSize) + minSize;

                float xCoord = rand.nextFloat()*(screenWidth-width) + width;
                float yCoord = rand.nextFloat()*((screenHeight-offsetBot-length)-(length+offsetTop)) +
                        length+offsetTop;
                Coordinates coords = new Coordinates(xCoord, yCoord);

                int alpha = 255;//rand.nextInt(256);
                int red = rand.nextInt(256);
                int green = rand.nextInt(256);
                int blue = rand.nextInt(256);
                int color = Color.argb(alpha, red, green , blue);

                return new Rectangle(context, coords, length, width, color);
            }
        }

        return null;
    }
}