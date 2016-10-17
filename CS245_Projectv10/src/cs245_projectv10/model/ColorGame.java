/***************************************************************
* file: Hangman.java
* @author: 
* @author: 
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
import cs245_projectv10.view.GameView;

/**
 *
 * @author Melanie
 */
public class ColorGame {
    private ColorGameView view;
    
    public ColorGame(ColorGameView view, Keyboard controller, GameScreen game) {
        
    }
    
    
    public void update() {
        // Pass button locations & associated color, color to guess text to game view   
        view.update();
    }
    
    public void endGame() {
        
    }
}
