/***************************************************************
* file: ColorGame.java
* @author: Brian Van Hooser
* @author: Alfredo Ceballos
* 
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified:  
* purpose:            Defines the game rules for the ColorGame
****************************************************************/
package cs245_projectv10.model;

import cs245_projectv10.controller.Keyboard;
import cs245_projectv10.screens.GameScreen;
import cs245_projectv10.view.ColorGameView;
import cs245_projectv10.Globals;
import cs245_projectv10.screens.EndScreen;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.HashSet;
import javax.swing.JLabel;
import java.util.LinkedList;
import javax.swing.JButton;
public class ColorGame {
    
    private final int [][]coordinateArray = {
        {260,220,420,210, 60, 50,370, 50,220, 80},
        { 50,180, 20, 30,260, 90,170,220,480, 30},
        {260, 30,310,220,420,150, 40,100,190,150},
        {430,210, 70,210,150, 60,470, 20,320, 80},
        {220,170,470,210,370, 50, 30,150,140, 20},
        {270, 80,130,220,420, 20, 40, 60,340,190},
        {100,100,420,220, 40,220,230, 10,440, 40},
        {310,170, 10, 10,460,140,240, 20, 70,210},
        {230, 20,450,220,190,210,340,170, 60,100},
        {160,200,430, 20, 90, 60,410,220,280, 70}
    };
    private ColorGameView view;
    GameScreen game;
    private int iteration;
    private JLabel tempLabel;
    private Keyboard controller;
    private String correctColor;
    private String userColorPicked;
    private LinkedList<JButton> colorList;
    private HashSet<Integer> used;
    private int lastUsedColorChoice;
    
    public ColorGame(ColorGameView view, Keyboard controller, GameScreen game) {
        iteration = 1;
        used = new HashSet<>();
        this.view = view;
        this.controller = controller;
        this.game = game;
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
            Random num = new Random();
            int coordinateChoice, colorChoice, textChoice;
        
            coordinateChoice = num.nextInt(10);
            while(used.contains(coordinateChoice)) {
                coordinateChoice = num.nextInt(10);
            }
            used.add(coordinateChoice);
        
            colorChoice = num.nextInt(5);
            while(colorChoice == lastUsedColorChoice){
                colorChoice = num.nextInt(5);
            }
            lastUsedColorChoice = colorChoice;
            textChoice = num.nextInt(5);
            while(colorChoice == textChoice){
                textChoice = num.nextInt(5);
            }
            tempLabel = new JLabel("<html><font color='" + Globals.COLOR_LIST[colorChoice]+"'>"
                                   +Globals.COLOR_LIST[textChoice]+"</font></html>");
            tempLabel.setToolTipText("Selected color");
            iteration++;
            correctColor = Globals.COLOR_LIST_RGB[colorChoice];
            view.update(coordinateArray[coordinateChoice],tempLabel);
        }
        else {
            new EndScreen(game);
            view.dispose();
        }
    }
    
    private void addActionListenersToControllerButtons() {
        // Add action listeners to virtual keyboard
        for (JButton button : colorList) {
            
            Color originalColor = button.getBackground();
            
            // Need to explicitly define hover behavior because of fix for 
            // displaying button color for Mac OS (in Keyboard.java)
            button.addMouseListener(new java.awt.event.MouseAdapter(){
                
                // Highlight button while hovering 
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt){
                    button.setBackground(originalColor.darker());
                }
                
                // Reset color on exit
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt){
                    button.setBackground(originalColor);
                }
            
            });
            
            button.addActionListener((ActionEvent e) -> {
                // Reset button to unhighlighted color when pressed for score
                // proc purposes
                button.setBackground(originalColor);
                userColorPicked = button.getBackground().toString();
                update();
            });
            
        }
    }
}
