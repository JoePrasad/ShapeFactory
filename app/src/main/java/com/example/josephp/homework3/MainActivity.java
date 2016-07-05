package com.example.josephp.homework3;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.Point;
import android.graphics.PorterDuff;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;



public class MainActivity extends AppCompatActivity {


    private final ShapeFactory shapeFactory = new ShapeFactory();

    //drawing surface info
    private ImageView mImageView;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    boolean myAnimation = false;
    private int animationAlpha;

    //Screen info
    private int screenWidth;
    private int screenHeight;

    //Game objects
    private ArrayList<Shape> shapes;


    // buttons and text
    private TextView mTextViewCircleCount;
    private TextView mTextViewRectCount;
    private Button rectButton;
    private Button circButton;
    private Button clearButton;

    //Game info
    private boolean finished;


    //count the shapes
    private int currentCircleCount;
    private int currentRectCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //hide action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();



        //setup screen info
        Display display = getWindowManager().getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);
        screenWidth = displaySize.x;
        screenHeight = displaySize.y;



        //setup the drawing surface
        mImageView = (ImageView) findViewById(R.id.imageView_main);


        View.OnTouchListener imageViewOnTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getAction();

                if (v == mImageView) {
                    if (action == MotionEvent.ACTION_DOWN) {


                        for (Iterator<Shape> iterator = shapes.iterator(); iterator.hasNext(); ) {

                            Shape s = iterator.next();

                            if (s.getShapeType() == Shape.ShapeType.CIRCLE) {
                                Circle circle = (Circle) s;


                            } else if (s.getShapeType() == Shape.ShapeType.RECTANGLE) {

                                Rectangle rect = (Rectangle) s;


                            }
                        }
                    }
                }

                return false;
            }
        };
        mImageView.setOnTouchListener(imageViewOnTouchListener);
        mBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        //initialize game text
        finished = false;


        currentCircleCount = 0;

        currentRectCount = 0;


        //initialize game objects
        shapes = new ArrayList<Shape>();


        mTextViewRectCount = (TextView) findViewById(R.id.textView_rectCount);
        mTextViewRectCount.setTextColor(Color.argb(255, 164, 192, 39));

        mTextViewCircleCount = (TextView) findViewById(R.id.textView_circCount);
        mTextViewCircleCount.setTextColor(Color.argb(255, 164, 192, 39));


        clearButton = (Button) findViewById(R.id.button_clear);
        clearButton.getBackground().setColorFilter(0xffffC639, PorterDuff.Mode.MULTIPLY);

        rectButton = (Button) findViewById(R.id.button_rect);
        rectButton.getBackground().setColorFilter(0xffA4C639, PorterDuff.Mode.MULTIPLY);

        circButton = (Button) findViewById(R.id.button_circ);
        circButton.getBackground().setColorFilter(0xffA4C639, PorterDuff.Mode.MULTIPLY);


        if (!finished) {

            update();
            buttons();

        }
    }


    public void buttons() {

        //generate rectangles
        rectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 1; i++) {

                    Shape rect = shapeFactory.getShape(v.getContext(), Shape.ShapeType.RECTANGLE);
                    shapes.add(rect);
                }

                draw();
                update();

            }
        });

        //generate circles
        circButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                for (int i = 0; i < 1; i++) {

                    Shape circ = shapeFactory.getShape(v.getContext(), Shape.ShapeType.CIRCLE);
                    shapes.add(circ);
                }

                draw();
                update();

            }
        });

        // deletes all shapes
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (Iterator<Shape> iterator = shapes.iterator(); iterator.hasNext(); ) {

                    Shape s = iterator.next();

                    if (s.getShapeType() == Shape.ShapeType.CIRCLE) {


                    } else if (s.getShapeType() == Shape.ShapeType.RECTANGLE) {


                    }

                    shapes.remove(shapes);
                    iterator.remove();
                }

                update();
                draw();
                

            }
        });
    }

    public void draw() {

        if (myAnimation) {
            runAnimation();
        } else {
            //clear canvas
            mCanvas.drawColor(Color.WHITE);
        }

        //draw  screen
        for (Shape s : shapes) {

            s.onDraw(mCanvas);
        }
        mImageView.setImageBitmap(mBitmap);
    }


    public void update() {


        mTextViewCircleCount.setText("Circle: " + currentCircleCount);
        mTextViewRectCount.setText("Rectangle: " + currentRectCount);


        updateShapeCount();
        adjustShapeAlpha();


    }


    public void adjustShapeAlpha() {

        for (Iterator<Shape> iterator = shapes.iterator(); iterator.hasNext(); ) {

            Shape shape = iterator.next();

            int alpha = shape.getColorAlpha();
            float alphaDecrease = .90f; //

            int threshold = 10;


            alpha = (int) (alpha * alphaDecrease);

            if (alpha <= threshold) {

                shape.removeShape(); // fades away shape
                iterator.remove(); // removes the count when shape is gone
            } else {
                shape.setColorAlpha(alpha);
            }

        }
    }


    public void updateShapeCount() {

        currentCircleCount = 0;
        currentRectCount = 0;


        for (Shape shape : shapes) {

            if (shape.getShapeType() == Shape.ShapeType.CIRCLE) {
                currentCircleCount++;
            } else if (shape.getShapeType() == Shape.ShapeType.RECTANGLE) {
                currentRectCount++;
            }

        }
    }



    public void runAnimation() {

        float alphaDecrease = .01f; //
        int threshold = 25;


        animationAlpha = (int) (animationAlpha * alphaDecrease);
        if (animationAlpha < threshold) {

            myAnimation = false;
            animationAlpha = 25;
        }

        mCanvas.drawColor(Color.argb(animationAlpha, 164, 192, 39));
        mImageView.setImageBitmap(mBitmap);
    }


}




