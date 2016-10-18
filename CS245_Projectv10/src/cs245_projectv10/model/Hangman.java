/***************************************************************
* file: Hangman.java
* @author: Andrew Olaveson
* @author: Melanie Giusti
* 
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified: 10/05/16 12:52 a.m.  
* purpose:            Defines the game rules for Hangman
****************************************************************/
package cs245_projectv10.model;

import cs245_projectv10.Globals;
import cs245_projectv10.controller.Keyboard;
import cs245_projectv10.screens.EndScreen;
import cs245_projectv10.screens.GameScreen;
import cs245_projectv10.view.ColorGameView;
import cs245_projectv10.view.HangmanGameView;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Hangman {

    /*Constants*/

    
    /*Variables*/
    GameScreen             game;
    HangmanGameView        view;
    Keyboard               controller;
    private  String        guessWord;
    private  StringBuilder wordState;
    private  int           wrongGuesses;
    private  int           score;
    
    /* Model constructor*/
    public Hangman(HangmanGameView view, Keyboard controller, GameScreen game) {
        this.game = game;
        this.controller = controller;
        this.view = view;
        addActionListenersToControllerButtons();
        
        // Set initial game state
        score        = Globals.MAX_SCORE;
        wrongGuesses = 0;
        guessWord    = getRandomWord(Globals.WORD_LIST);
        wordState    = new StringBuilder(guessWord.length());
        
        for(int i = 0; i < guessWord.length(); i++){wordState = wordState.append("_");}
        view.update(wordState.toString(),wrongGuesses,score);
        
    }
    
    /*Returns a random word from our word list*/
    private String getRandomWord(String [] wordSet){
        Math.random();
        return wordSet[(int)(Math.random()*wordSet.length)];
    }
    
    /*Updates game state based upon letter guessed*/
    public void update(String buttonText) {
        if(guessWord.contains(buttonText)){ // If the word contains the guessed letter
            int index = 0;
            while(index > -1){              // Fill in all instances of that letter 
                if((index = guessWord.indexOf(buttonText,index)) > -1){
                    wordState.replace(index,index+1,buttonText);                 
                    index++;
                }
            }
        }
        else{                               // Otherwise decrement the score
            if(wrongGuesses < Globals.MAX_TRYS){
                wrongGuesses++;
                score -= Globals.POINTS_TO_DEDUCT;
            }
        }
        
        view.update(wordState.toString(),wrongGuesses,score);
        checkWin();
    }
    
    /*Ends game and goes to "End Game" screen*/
    private void endGame(int score){
//        view.endHangman();
        //EndScreen end = new EndScreen(score, game);
        ColorGameView colorGameView  = new ColorGameView(controller);
        ColorGame     colorGameModel = new ColorGame(colorGameView,controller,game); 
        game.dispose();
    }
    
    /*Checks for a win or loss*/
    private void checkWin(){
        if(wrongGuesses >= Globals.MAX_TRYS){
            view.endHangman(guessWord, wrongGuesses, getRandomWord(Globals.LOSE_LIST));
//            endGame(score);
        } else if(!wordState.toString().contains("_")) {
            view.endHangman(guessWord, wrongGuesses, getRandomWord(Globals.WIN_LIST));
        }
    }
    
    /* Adds action listeners to our controller buttons*/
    private void addActionListenersToControllerButtons() {
        // Add action listener for skip button
        controller.getSkipButton().addActionListener((ActionEvent e) ->{
            score = 0;
            endGame(score);
        });
        
        controller.getNextButton().addActionListener((ActionEvent e) ->{
            endGame(score);
        });
        
        // Add action listeners to virtual keyboard
        for (JButton button : controller.getKeyboardList()) {
            button.addActionListener((ActionEvent e) -> {
                update(button.getText());
                button.setEnabled(false);
            });
        }
    }
}
