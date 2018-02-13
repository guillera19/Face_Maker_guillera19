/**
 * @Author: Avery Guillermo
 * Created by Avery Guillermo on 2/11/18.
 */

package edu.guillera19up.face_maker;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import java.util.ArrayList;


public class Event_Listener
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, Spinner.OnItemSelectedListener {

    //declare instance variables
    private Face face;
    private ArrayList<SeekBar> seekBars = new ArrayList<SeekBar>(); //ArrayList to store the seekBars
    private Spinner hairStyleSpinner;
    //declare variables that are used for the Control_Variable
    int Hair = 0;
    int Eyes = 1;
    int Skin = 2;

    //constructors
    public Event_Listener(Face faceObject){
        this.face = faceObject;
    }

    //use this method to add the Red, Green, and Blue seekBars so that I can edit their progress
    public void addSeekBar(SeekBar anotherSeekBar){
        seekBars.add(anotherSeekBar);
    }

    public void addSpinner(Spinner spin){
        hairStyleSpinner = spin;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    /*
     * button classes that need to be overridden since I implemented View.OnClickListener
     */
    @Override
    public void onClick(View v) {

        Button buttonWasClicked = (Button) v;
        String buttonLabel = (String) buttonWasClicked.getText();

        //Determine which button was pressed
        if (buttonLabel.equalsIgnoreCase("Hair")){
            face.updateControl_Variable(Hair); //update the Control_Variable so that the seekBars..
            //..correspond the the hair feature
            face.updateSeekBars(seekBars);
        }
        else if (buttonLabel.equalsIgnoreCase("Eyes")){
            face.updateControl_Variable(Eyes);//update the Control_Variable so that the seekBars..
            //..correspond the the eye feature
            face.updateSeekBars(seekBars);
        }
        else if (buttonLabel.equalsIgnoreCase("Skin")){
            face.updateControl_Variable(Skin);//update the Control_Variable so that the seekBars..
            //..correspond the the skin feature
            face.updateSeekBars(seekBars);
        }
        else if (buttonLabel.equalsIgnoreCase("Random Face")){
            face.randomize(); //generate a random face
            face.updateSeekBars(seekBars);

            //declare hairStyles as enumerated data for user reference
            int afro = 0;
            int militaryCut = 1;
            int buzzCut = 2;

            //update the hairStyleSpinner to show the hair style that was randomly selected
            if(face.getHairStyle() == afro) {
                hairStyleSpinner.setSelection(afro);
            }
            else if (face.getHairStyle() == militaryCut){
                hairStyleSpinner.setSelection(militaryCut);
            }
            else if (face.getHairStyle() == buzzCut){
                hairStyleSpinner.setSelection(buzzCut);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    /*
     * SeekBar classes that need to be overridden since I implemented SeekBar.OnSeekBarChangeListener
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //determine which seekbar was changed and update that specific Color
        if(face.getControl_Variable()==Hair) {
            if (seekBar.getId() == R.id.seekBar_Red) {
                face.updateHairColor(Color.RED, seekBar.getProgress());
            }
            if (seekBar.getId() == R.id.seekBar_Green) {
                face.updateHairColor(Color.GREEN, seekBar.getProgress());
            }
            if (seekBar.getId() == R.id.seekBar_Blue) {
                face.updateHairColor(Color.BLUE, seekBar.getProgress());
            }
        }
        else if(face.getControl_Variable()==Eyes) {
            if (seekBar.getId() == R.id.seekBar_Red) {
                face.updateEyeColor(Color.RED, seekBar.getProgress());
            }
            if (seekBar.getId() == R.id.seekBar_Green) {
                face.updateEyeColor(Color.GREEN, seekBar.getProgress());
            }
            if (seekBar.getId() == R.id.seekBar_Blue) {
                face.updateEyeColor(Color.BLUE, seekBar.getProgress());
            }
        }
        else if(face.getControl_Variable()==Skin) {
            if (seekBar.getId() == R.id.seekBar_Red) {
                face.updateSkinColor(Color.RED, seekBar.getProgress());
            }
            if (seekBar.getId() == R.id.seekBar_Green) {
                face.updateSkinColor(Color.GREEN, seekBar.getProgress());
            }
            if (seekBar.getId() == R.id.seekBar_Blue) {
                face.updateSkinColor(Color.BLUE, seekBar.getProgress());
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //do nothing. I don't care about this event.
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //do nothing. I don't care about this event.
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    /*
     * Spinner classes that need to be overridden since I implemented Spinner.OnItemSelectedListener
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0){
            face.updateHair(0);
        }
        else if (position == 1){
            face.updateHair(1);
        }
        else if (position == 2){
            face.updateHair(2);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing. I don't care about this event.
    }
}
