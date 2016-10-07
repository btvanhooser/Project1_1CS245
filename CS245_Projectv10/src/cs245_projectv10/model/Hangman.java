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

import cs245_projectv10.controller.Keyboard;
import cs245_projectv10.screens.EndScreen;
import cs245_projectv10.screens.GameScreen;
import cs245_projectv10.view.GameView;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Hangman {

    /*Constants*/
    private final int    MAX_SCORE        = 100;
    private final int    MAX_TRYS         = 6;
    private final int    POINTS_TO_DEDUCT = 10;
    private final String WORD_LIST []     = {"ABSTRACT","CEMETERY","NURSE",
                                             "PHARMACY","CLIMBING"};
    
    /*Variables*/
    GameScreen             game;
    GameView               view;
    Keyboard               controller;
    private  String        guessWord;
    private  StringBuilder wordState;
    private  int           wrongGuesses;
    private  int           score;
    
    /* Model constructor*/
    public Hangman(GameView view, Keyboard controller, GameScreen game) {
        this.game = game;
        this.controller = controller;
        this.view = view;
        addActionListenersToControllerButtons();
        
        // Set initial game state
        score        = MAX_SCORE;
        wrongGuesses = 0;
        guessWord    = getRandomWord();
        wordState    = new StringBuilder(guessWord.length());
        
        for(int i = 0; i < guessWord.length(); i++){wordState = wordState.append("_");}
        
        view.update(wordState.toString(),wrongGuesses,score);
        
    }
    
    /*Returns a random word from our word list*/
    private String getRandomWord(){
        return WORD_LIST[(int)(Math.random()*WORD_LIST.length)];
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
            if(wrongGuesses < MAX_TRYS){
                wrongGuesses++;
                score -= POINTS_TO_DEDUCT;
            }
        }
        
        view.update(wordState.toString(),wrongGuesses,score);
        checkWin();
    }
    
    /*Ends game and goes to "End Game" screen*/
    private void endGame(int score){
        EndScreen end = new EndScreen(score, game);
        game.dispose();
    }
    
    /*Checks for a win or loss*/
    private void checkWin(){
        if(wrongGuesses >= MAX_TRYS || !wordState.toString().contains("_")){
            endGame(score);
        }
    }
    
    /* Adds action listeners to our controller buttons*/
    private void addActionListenersToControllerButtons() {
        // Add action listener for skip button
        controller.getSkipButton().addActionListener((ActionEvent e) ->{
            score = 0;
            endGame(score);
        });
        
        // Add action listeners to virtual keyboard
        for (JButton button : controller.keyList) {
            button.addActionListener((ActionEvent e) -> {
                update(button.getText());
                button.setEnabled(false);
            });
        }
    }
}
