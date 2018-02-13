/**
 * @Author: Avery Guillermo
 * Created by Avery Guillermo on 2/11/18.
 *
 */
package edu.guillera19up.face_maker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class Face_Maker extends AppCompatActivity {

    //declare an array of Strings to use for the Spinner Contents
    private String[] HairStyle = {"Afro", "Military Cut", "Buzz Cut"};

    @Override //override the onCreateMethod
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face__maker);

        //fill the contents of the spinner with the different hair styles
        ArrayAdapter<String> hairAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, HairStyle);
        Spinner hairSpinner = (Spinner) findViewById(R.id.spinner_Hair_Style);
        hairSpinner.setAdapter(hairAdapter);

        //initialize the RadioGroup so that Hair is always checked when the App first runs
        RadioGroup radGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        radGroup.check(R.id.radioButton_Hair);

        //Create an instance of a Face object that can be used when declaring an instance of
        //an Event_Listener Object.
        Face face = findViewById(R.id.surfaceView_Face);
        Event_Listener listener = new Event_Listener(face);

        //set the hairSpinner to listen to OnItemSelected Events
        hairSpinner.setOnItemSelectedListener(listener);

        //declare the red, green, and blue seekbars and set them to listen to SeekBarChange events
        SeekBar redBar = (SeekBar) findViewById(R.id.seekBar_Red);
        redBar.setOnSeekBarChangeListener(listener);
        SeekBar greenBar = (SeekBar) findViewById(R.id.seekBar_Green);
        greenBar.setOnSeekBarChangeListener(listener);
        SeekBar blueBar = (SeekBar) findViewById(R.id.seekBar_Blue);
        blueBar.setOnSeekBarChangeListener(listener);

        //Add the seekBars to the Event_Listener class so that their progress can be set when
        // switching from the hair, skin, and eye features.
        listener.addSeekBar(redBar);
        listener.addSeekBar(greenBar);
        listener.addSeekBar(blueBar);

        //Add the Spinner to the Event_Listener class so that its position can be set when
        //the random face class is called
        listener.addSpinner(hairSpinner);

        //declare the hair, eye, and skin radioButtons and set them to listen to OnClick Events
        RadioButton hairButton = (RadioButton) findViewById(R.id.radioButton_Hair);
        hairButton.setOnClickListener(listener);
        RadioButton eyeButton = (RadioButton) findViewById(R.id.radioButton_Eyes);
        eyeButton.setOnClickListener(listener);
        RadioButton skinButton = (RadioButton) findViewById(R.id.radioButton_Skin);
        skinButton.setOnClickListener(listener);

        //declare the randomButton button and set it to listen to OnClick Events.
        Button randomButton = (Button) findViewById(R.id.Random_Face);
        randomButton.setOnClickListener(listener);
    }
}
