/***************************************************************
* file: Keyboard.java
* @author: Andrew Olaveson
* @author: Melanie Giusti
* 
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified:
* purpose: 
****************************************************************/
package cs245_projectv10.controller;

import cs245_projectv10.Globals;
import java.awt.Color;
import java.awt.Insets;
import java.util.LinkedList;
import javax.swing.JButton;

/**
 * "Controller" which creates buttons for the user to use. Buttons are given action
 *  listeners by the model who can then act based on the event changes each button 
 *  outputs.
 */
public class Keyboard {

    /* --- Constants--- */
    private final int FIRST_LETTER = 65;
    private final int LAST_LETTER = 90;
    private final Color[] COLORS = {Color.RED, Color.YELLOW,Color.GREEN, Color.BLUE, Color.MAGENTA};
    
    /* --- Variables --- */
    private LinkedList<JButton> keyList;
    private LinkedList<JButton> colorList;
    private JButton skipButton;
    private JButton nextButton;
    
    
    public Keyboard() {
        populateControllerButtons();
    }
    
    /* --- Getters ---*/ 
    public int getSize() { return keyList.size(); }
    public JButton getSkipButton(){ return skipButton; }
    public JButton getNextButton(){ return nextButton; }
    public LinkedList<JButton> getKeyboardList() { return keyList; }
    public LinkedList<JButton> getColorList() { return colorList; }
        
    
    
    /* --- Helper Methods --- */
    // Create new buttons to be given action listeners later
    private void populateControllerButtons() {
        skipButton = new JButton("Skip");
        skipButton.setBackground(Globals.BUTTON_COLOR);
        skipButton.setMargin(new Insets(1,1,1,1));
        
        nextButton = new JButton("Next");
        nextButton.setBackground(Globals.BUTTON_COLOR);
        nextButton.setMargin(new Insets(1,1,1,1));
        
        keyList = new LinkedList<>();
        for (int ii = FIRST_LETTER; ii <= LAST_LETTER; ++ii) {
            keyList.add(new JButton(Character.toString((char)ii)));
            keyList.get(ii - FIRST_LETTER).setMargin(new Insets(1,1,1,1));
            keyList.get(ii- FIRST_LETTER).setBackground(Globals.BUTTON_COLOR);
        }
        
        colorList = new LinkedList<>();
        for (int ii = 0; ii < 5; ++ii) {
            colorList.add(new JButton());
            colorList.get(ii).setBackground(COLORS[ii]);
        }
    }
}
