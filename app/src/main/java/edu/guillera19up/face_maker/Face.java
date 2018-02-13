/**
 * @Author: Avery Guillermo
 * Created by Avery Guillermo on 2/10/18.
 */

package edu.guillera19up.face_maker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Random;

public class Face extends SurfaceView {

    //declare instance  variables
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;
    private int redColor;
    private int greenColor;
    private int blueColor;
    //use this to make a random number
    protected Random gen = new Random();
    //This Control_Variable is used to provide control for the helper methods.
    //The Control_Variable is used to decide if it should edit the hair, nose, or skin colors.
    int Control_Variable;


    //implement all the constructors for SurfaceView since I extended SurfaceView
    public Face(Context context) {
        super(context);
        generalInit();
    }

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        generalInit();
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        generalInit();
    }

    /**
     * generalInit
     * Initialization used for the constructors.
     */
    private void generalInit() {
        setWillNotDraw(false); //include this so that the surface view will show the graphics
        randomize();
        Control_Variable = 0;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////

    /*
    * Shown below are all the helper and get methods that can be called in Event_Listener.
    * These methods are used to update the hairStyle, hairColor, skinColor, and eyeColor.
    * In addition, these methods call invalidate() to force the surfaceView to redraw itself
    * once the instance variables have been changed/updated.
    */
    public void updateHair(int hairValue){
        this.hairStyle = hairValue;
        this.invalidate();
    }

    public void updateSkinColor(int color, int value){
        if (color == Color.RED ) {
            this.redColor = value;
        }
        else if (color == Color.GREEN){
            this.greenColor = value;
        }
        else if (color == Color.BLUE){
            this.blueColor = value;
        }
        /**
         External Citation #1
         Date:     12 February 2018
         Problem:  Needed a way to combine seperate rgb values into one int variable
         Resource:
         https://stackoverflow.com/questions/10567758/how-do-i-change-4-separate-int-
         values-for-alpha-red-green-blue-into-type-int
         Solution: I used the example code from this post.
         */
        this.skinColor = (0xFF << 24) | (redColor << 16 ) | (greenColor<<8) | blueColor;
        this.invalidate();
    }

    public void updateHairColor(int color, int value){
        if (color == Color.RED ) {
            this.redColor = value;
        }
        else if (color == Color.GREEN){
            this.greenColor = value;
        }
        else if (color == Color.BLUE){
            this.blueColor = value;
        }
        //External Citation #1-> this is the same code from above.
        this.hairColor = (0xFF << 24) | (redColor << 16 ) | (greenColor<<8) | blueColor;
        this.invalidate();
    }

    public void updateEyeColor(int color, int value){
        if (color == Color.RED ) {
            this.redColor = value;
        }
        else if (color == Color.GREEN){
            this.greenColor = value;
        }
        else if (color == Color.BLUE){
            this.blueColor = value;
        }
        //External Citation #1-> this is the same code from above.
        this.eyeColor = (0xFF << 24) | (redColor << 16 ) | (greenColor<<8) | blueColor;
        this.invalidate();
    }

    public void updateControl_Variable(int value){
        this.Control_Variable = value;
    }


    public void updateSeekBars(ArrayList<SeekBar> seekBars) {
        //declare variables for Control_Variable for User Reference
        int Hair = 0;
        int Eyes = 1;
        int Skin = 2;

        if(Control_Variable == Hair) {
            /**
             External Citation #2
             Date:     12 February 2018
             Problem:  Needed a way to seperate an int Color into its respective RGB values
             Resource:
             https://stackoverflow.com/questions/10567758/how-do-i-change-4-separate-int-
             values-for-alpha-red-green-blue-into-type-int
             Solution: I used the example code from this post.
             */
            int red = 0xFF & ( hairColor >> 16);
            int green = 0xFF & (hairColor >> 8 );
            int blue = 0xFF & (hairColor >> 0 );

            //update each seekBar to the current Color
            seekBars.get(0).setProgress(red);
            seekBars.get(1).setProgress(green);
            seekBars.get(2).setProgress(blue);
        }
        else if (Control_Variable == Eyes){
            //External Citation #2 -> this is the same code from above.
            int red = 0xFF & ( eyeColor >> 16);
            int green = 0xFF & (eyeColor >> 8 );
            int blue = 0xFF & (eyeColor >> 0 );
            //update each seekBar to the current Color
            seekBars.get(0).setProgress(red);
            seekBars.get(1).setProgress(green);
            seekBars.get(2).setProgress(blue);

        }
        else if (Control_Variable == Skin){
            //External Citation #2 -> this is the same code from above.
            int red = 0xFF & ( skinColor >> 16);
            int green = 0xFF & (skinColor >> 8 );
            int blue = 0xFF & (skinColor >> 0 );
            //update each seekBar to the current Color
            seekBars.get(0).setProgress(red);
            seekBars.get(1).setProgress(green);
            seekBars.get(2).setProgress(blue);

        }

    }

    public int getControl_Variable(){
        return this.Control_Variable;
    }

    public int getHairStyle(){
        return this.hairStyle;
    }
    //end of helper methods
    ///////////////////////////////////////////////////////////////////////////////////////////////

    /*
     * This method initializes all the variables to randomly selected valid values. This method is
     * called in the constructor.
     */
    public void randomize() {
        hairStyle = 0 + gen.nextInt(3);
        //External Citation #1 -> this is the same format obtained from External Citation #1
        this.skinColor = (0xFF << 24) | ((0 + gen.nextInt(256)) << 16 ) | ((0 + gen.nextInt(256))<<8) | (0 + gen.nextInt(256));
        this.eyeColor = (0xFF << 24) | ((0 + gen.nextInt(256)) << 16 ) | ((0 + gen.nextInt(256))<<8) | (0 + gen.nextInt(256));
        this.hairColor = (0xFF << 24) | ((0 + gen.nextInt(256)) << 16 ) | ((0 + gen.nextInt(256))<<8) | (0 + gen.nextInt(256));
        this.invalidate();
    }

    /*
     * This method overrides the onDraw method and draws this Face object upon a given Canvas.
     */
    @Override
    public void onDraw(Canvas canvas) {

        //paint background
        Paint background = new Paint();
        background.setColor(Color.rgb(211,211,211));
        canvas.drawRect(0f, 0f, 1800f,1200f, background);

        //paint the hair
        Paint hairPaint = new Paint();
        hairPaint.setColor(hairColor);
        //determine which hairStyle to Paint on the canvas
        if(hairStyle == 0){
            afroHair(canvas, hairPaint);
        }
        else if (hairStyle == 1){
            militaryCut(canvas, hairPaint);
        }
        else if (hairStyle == 2){
            buzzHair(canvas, hairPaint);
        }

        //paint the skin
        Paint skinPaint = new Paint();
        skinPaint.setColor(skinColor);
        canvas.drawOval(380f, 250f, 1070f, 1100f, skinPaint);


        //paint the eyes
        Paint eyePaint = new Paint();
        eyePaint.setColor(eyeColor);
        drawEyes(canvas, eyePaint);

        //paint the nose and mouth
        drawNose_and_Mouth(canvas);
    }

    /*
     * Shown below are all the helper methods for onDraw.
     * What each method draws is self-explanatory just by looking at the name
     * of the method. (e.g. afro, militarycut, etc.)
     * Also, the numerical values used in these helper methods were obtained by
     * using math, geometry, and guess and check procedures.
     */
    public void afroHair (Canvas canvas, Paint paint){
        canvas.drawCircle(725f, 420f, 415f, paint);
    }

    public void militaryCut(Canvas canvas, Paint paint){
        canvas.drawRect(400f,150f, 1050f, 600f, paint);
    }

    public void buzzHair(Canvas canvas, Paint paint){
        canvas.drawCircle(725f, 552f, 340f, paint);
    }

    public void drawEyes(Canvas canvas, Paint paint){
        canvas.drawOval(540f, 460f, 660f, 620f, paint);
        canvas.drawOval(800f, 460f, 920f, 620f, paint);
    }

    public void drawNose_and_Mouth (Canvas canvas){
        //draw Mouth
        Paint black = new Paint();
        black.setColor(Color.rgb(00,00,00));
        canvas.drawArc(570f, 870f, 890f, 990f, -10f, 200f, true, black);

        //draw Nose
        black.setStyle(Paint.Style.STROKE);
        black.setStrokeWidth(5.0f);
        Path triangle = new Path();
        triangle.moveTo(730f, 640f);
        triangle.lineTo(810f,800f);
        triangle.lineTo(670, 800f);
        triangle.lineTo(730f, 640f);
        canvas.drawPath(triangle, black);
    }

}
