/***************************************************************
* file: Hangman.java
* @author: Brian Van Hooser
* @author: Alfredo Ceballos
* 
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified:  
* purpose:            Defines the game rules for Hangman
****************************************************************/
package cs245_projectv10.model;

import cs245_projectv10.controller.Keyboard;
import cs245_projectv10.screens.GameScreen;
import cs245_projectv10.view.ColorGameView;
import cs245_projectv10.view.HangmanGameView;
import cs245_projectv10.Globals;
import cs245_projectv10.screens.EndScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.JLabel;
/**
 *
 * @author Melanie
 */
import java.util.LinkedList;
import javax.swing.JButton;
public class ColorGame {
    
    private final int [][]coordinateArray = {
                                   {30,10,4,150,100,10,250,160,160,410,220},
                                   {130,150,270,30,300,210,30,30,460,100},
                                   {160,50,290,210,60,230,30,110,430,10},
                                   {180,160,280,40,60,70,460,220,320,190},
                                   {40,220,490,20,160,120,320,10,450,210},
                                   {230,220,220,30,20,20,100,160,420,70},
                                   {50,30,300,180,450,210,40,220,380,40},
                                   {80,80,290,20,140,210,430,230,280,170},
                                   {110,60,380,120,50,150,490,20,220,200},
                                   {260,220,470,210,60,50,370,50,220,80}};
    private ColorGameView view;
    private int iteration;
    private JLabel tempLabel;
    private Keyboard controller;
    private String correctColor;
    private String userColorPicked;
    private LinkedList<JButton> colorList;
    
    public ColorGame(ColorGameView view, Keyboard controller) {
        iteration = 1;
        this.view = view;
        this.controller = controller;
        colorList = controller.getColorList();
        addActionListenersToControllerButtons();
        update();
    }
    
    public void update() {
        // Pass button locations & associated color, color to guess text to game view 
        if(iteration >= 2  && iteration <= 6){
            if(userColorPicked.equals(correctColor)){
                Globals.COLOR_GAME_SCORE += 100;
            }
        }
        if(iteration <= 5){
            ArrayList<Integer> used = new ArrayList();
            Random num = new Random();
            int coordinateChoice, colorChoice, textChoice;
        
            coordinateChoice = num.nextInt(10);
            while(used.contains(coordinateChoice)) {
                coordinateChoice = num.nextInt(10);
            }
            used.add(coordinateChoice);
        
            colorChoice = num.nextInt(5);
            textChoice = num.nextInt(5);
            while(colorChoice == textChoice){
                textChoice = num.nextInt(5);
            }
            String textColor = null;
            String colorName = null;
            tempLabel = new JLabel("<html><font color='" + Globals.COLOR_LIST[colorChoice]+"'>"
                                   +Globals.COLOR_LIST[textChoice]+"</font></html>");
            iteration++;
            correctColor = Globals.COLOR_LIST_RGB[colorChoice];
            view.update(coordinateArray[coordinateChoice],tempLabel);   
        }
        else {
            endGame();
            view.dispose();
        }
    }
    
    private void addActionListenersToControllerButtons() {
        // Add action listeners to virtual keyboard
        for (JButton button : colorList) {
            button.addActionListener((ActionEvent e) -> {
                userColorPicked = button.getBackground().toString();
                update();
            });
        }
    }
    
    public void endGame() {
        new EndScreen(Globals.COLOR_GAME_SCORE, null);    // FIX
        view.dispose();
    }
}
