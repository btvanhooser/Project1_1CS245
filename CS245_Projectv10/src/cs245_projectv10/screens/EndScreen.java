/***************************************************************
* file: EndScreen.java
* @author: Andrew Olaveson
* @author: Melanie Giusti
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified:
* purpose:            Displays score and saves a new high score
*                     if one is achieved 
****************************************************************/
package cs245_projectv10.screens;

import cs245_projectv10.Globals;
import cs245_projectv10.view.MainMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EndScreen  extends JFrame {
    
    /* --- Variables --- */
    private JPanel     endGamePanel;
    private JPanel     endGamePanelWithInput;
    private JPanel     endGameFooterPanel;
    private JLabel     scoreLabel;
    private JButton    endGameButton;
    private JTextField inputName;
    
    private int      totalScore;
    private boolean  newHighScore;
    
    public EndScreen(GameScreen game) {
        totalScore = Globals.HANGMAN_GAME_SCORE + Globals.COLOR_GAME_SCORE;
        checkForHighScore();
        
        setFrameAttributes();
        add(new JLabel(""),BorderLayout.NORTH);
        
        if(newHighScore){
            createEndGamePanelWithInput();
            add(endGamePanelWithInput,BorderLayout.CENTER);
        }
        else{
            createEndGamePanel();
            add(endGamePanel,BorderLayout.CENTER);
        }
        
        createEndGameFooterPanel();
        add(endGameFooterPanel,BorderLayout.SOUTH);
        addActionListenersToEndButton();
    }
    
    /*Used to limit input into player name field to 3 characters*/
    KeyListener keyListener = new KeyListener(){

        @Override
        public void keyTyped(KeyEvent ke) {
           if(inputName.getText().length()>=3) {  
            ke.consume();
            }
        }

        @Override
        public void keyPressed(KeyEvent ke) {
        }

        @Override
        public void keyReleased(KeyEvent ke) {
        }
    };
    
    /*Set the visual properties of the EndScreen*/
    private void setFrameAttributes() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }    
    
    /*Create Endgame Panel ontop of the Frame for no highscore*/
    private void createEndGamePanel() {
        
        endGamePanel = new JPanel();
        endGamePanel.setLayout(new GridLayout(0,1));
        endGamePanel.setBackground(Color.WHITE);
        
        scoreLabel = new JLabel("Score: " + totalScore, SwingConstants.CENTER);
        Font font = new Font("MONOSPACED",Font.PLAIN,24);
        scoreLabel.setFont(font);
        
        endGamePanel.add(scoreLabel);
    }
    
    /*Create Endgame Panel for entering player info for new highscore*/
    private void createEndGamePanelWithInput(){
        endGamePanelWithInput = new JPanel();
        endGamePanelWithInput.setLayout(new GridLayout(3,1));
        endGamePanelWithInput.setBackground(Color.WHITE);
        
        scoreLabel = new JLabel("Score: " + totalScore, SwingConstants.CENTER);
        Font font = new Font("MONOSPACED",Font.PLAIN,24);
        scoreLabel.setFont(font);
        
        inputName = new JTextField(3);
        
        endGamePanelWithInput.add(new JLabel("NEW HIGH SCORE!",SwingConstants.CENTER));
        endGamePanelWithInput.add(scoreLabel);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Enter your initials: ",SwingConstants.CENTER));
        inputPanel.add(inputName);
        
        endGamePanelWithInput.add(inputPanel);
        
    }
    
    /*Create Endgame Footer Panel (Just end button)*/
    private void createEndGameFooterPanel(){
        endGameFooterPanel = new JPanel();
        endGameFooterPanel.setBackground(Color.WHITE);
        endGameButton = new JButton("End");
        endGameButton.setBackground(Globals.BUTTON_COLOR);
        endGameFooterPanel.add(endGameButton,BorderLayout.CENTER);
    }
    
    
    /*End button creates new instance of main menu, resets the scores for ea
      game and stores highscore if achieved*/
    private void addActionListenersToEndButton() {
        
        endGameButton.addActionListener((ActionEvent e) ->{
            
            MainMenu mainMenu = new MainMenu();
            
            //Reset score for games
            Globals.HANGMAN_GAME_SCORE = 100;
            Globals.COLOR_GAME_SCORE = 0;
            
            //Store new high score
            if(newHighScore){writeHighScoresToFile();};
            
            dispose();
        });
        
        if(newHighScore){
            inputName.addKeyListener(keyListener);
        }
    }
    
    /*Checks if cumulative score qualifies to be in the highscore list*/
    private void checkForHighScore(){
        newHighScore = false;
        if(totalScore > Globals.HIGH_SCORES[Globals.HIGH_SCORES.length-1].getScore()){
            newHighScore = true;
        }
    }
    
    /*Writes new highscore list to file if one was acheived*/ 
    private void writeHighScoresToFile(){
        
        //Store new highscore localy
        String playerName = inputName.getText();
        
        // Fill unused character spaces for name with -
        switch(playerName.length()){
            case(0): playerName = "---";
                break;
            case(1): playerName = playerName.concat("--");
                break;
            case(2): playerName = playerName.concat("-");  
                break;
            default: playerName = playerName.replace(" ","-");
                break;
        }
        Globals.HIGH_SCORES[Globals.HIGH_SCORES.length-1].setScore(totalScore);
        Globals.HIGH_SCORES[Globals.HIGH_SCORES.length-1].setName(playerName);
        Arrays.sort(Globals.HIGH_SCORES);
        
        //Write new highscore to file
        try {
        FileWriter fWriter  = new FileWriter(Globals.HIGH_SCORES_FILE);
        PrintWriter pWriter = new PrintWriter(fWriter);
        
        
        int player = 0;
        while (player < Globals.HIGH_SCORES.length){
            pWriter.println(Globals.HIGH_SCORES[player].getName());
            pWriter.println(Globals.HIGH_SCORES[player].getScore());
            player++;
        }
        
        pWriter.close();
        fWriter.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}

