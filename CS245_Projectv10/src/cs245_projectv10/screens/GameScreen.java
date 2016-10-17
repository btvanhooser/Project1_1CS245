/***************************************************************
* file: GameScreen.java
* @author: Andrew Olaveson
* @author: Melanie Giusti
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified:
* purpose: 
****************************************************************/
package cs245_projectv10.screens;

import cs245_projectv10.controller.Keyboard;
import cs245_projectv10.model.Hangman;
import cs245_projectv10.view.HangmanGameView;
import javax.swing.JFrame;


/**
 * Main Game class which creates and ties the Model, controller and View together
 *  This creates an easy way of controlling dependencies passing a common pointer
 *  (this) to the model which drives the View while utilizing the controller
 */
public class GameScreen  extends JFrame {
    /* Variables */
    HangmanGameView view;
    Hangman  model;
    Keyboard controller;
    
    
    public GameScreen() {
        controller = new Keyboard();
        view = new HangmanGameView(controller);
        model = new Hangman(view, controller, this);
        
        setFrameAttributes();
    }
    
    private void setFrameAttributes() {
        add(view);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
