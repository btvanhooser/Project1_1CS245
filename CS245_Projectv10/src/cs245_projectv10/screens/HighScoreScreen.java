/***************************************************************
* file: HighScoreScreen.java
* @author: Brian Van Hooser
* @author: Melanie Giusti
* @author: Alfredo Ceballos
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified: 10/16/2016 12:26PM
* purpose: Displays top 5 player's high scores
*          Added functionality to read in high score from file
****************************************************************/
package cs245_projectv10.screens;

import cs245_projectv10.Globals;
import cs245_projectv10.view.MainMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.io.*;


public class HighScoreScreen extends JPanel {
  
    /* --- Variables --- */
    private GameScreen game;
    private JButton  backButton;
    private JPanel   centerPanel;
    private JPanel   footerPanel;
    
    /*Constructs all elements for highscore screen and displays the screen*/
    public HighScoreScreen(GameScreen game) {
        this.game = game;
        
        setFrameAttributes();
        /*A try catch block is used to handle an exception thrown if an error
        occurs when taking in scores from the text file*/
        try{
            createCenterPanel();
        }
        catch(IOException e){
            System.out.println("High scores file not found...");
        }
        createFooterPanel();
        addComponentsToFrame();
        addActionListeners();
    }
    
    /*Sets the attributes of the main frame*/
    private void setFrameAttributes(){
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }
    
    /*Creates center panel and brings in high scores from text file*/
    private void createCenterPanel() throws IOException{
        centerPanel            = new JPanel();
        JLabel highScoresLabel = new JLabel("--- HIGH SCORES ---",SwingConstants.CENTER);
        highScoresLabel.setToolTipText("--- HIGH SCORES ---");
        
        centerPanel.setLayout(new GridLayout(0,1));
        centerPanel.setBackground(Color.WHITE);
        
        /*The scores are read line by line and added to the labels
          as they are created*/
        centerPanel.add(highScoresLabel);
   
        for(int i = 0; i < Globals.HIGH_SCORES.length; i++){
            JLabel scoreLabel = new JLabel(Globals.HIGH_SCORES[i].getName() + " . . . " + Globals.HIGH_SCORES[i].getScore(), SwingConstants.CENTER);
            scoreLabel.setToolTipText(Globals.HIGH_SCORES[i].getName() + "'s score");
            centerPanel.add(scoreLabel);
        }
        
    }
    
    /*Creates panel to contain "back" button*/
    private void createFooterPanel(){
        footerPanel = new JPanel();
        backButton  = new JButton("Back");
        backButton.setToolTipText("Return to main menu n00b");
        
        backButton.setBackground(Globals.BUTTON_COLOR);
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setBackground(Color.WHITE);
        
        footerPanel.add(backButton,BorderLayout.WEST);
    }
    
    /*Adds panel components to main frame*/
    private void addComponentsToFrame(){
        add(new JLabel(),BorderLayout.NORTH);
        add(centerPanel,BorderLayout.CENTER);
        add(footerPanel,BorderLayout.SOUTH);
    }
    
    /*Adds action listener to "back" button*/
    private void addActionListeners(){
        backButton.addActionListener((ActionEvent e)->{
            game.backToMainMenu();
        });
    }
}
