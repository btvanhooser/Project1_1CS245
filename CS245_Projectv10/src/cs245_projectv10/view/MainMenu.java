/***************************************************************
* file: MainMenu.java
* @author: Andrew Olaveson
* @author: Brian Van Hooser
* @author: Melanie Giusti
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified:
* purpose: purpose: This Panel handles the display of our logo, as well as
* the three function buttons which are responsible for launching 
* the game, displaying the high scores, and presenting the credits
****************************************************************/
package cs245_projectv10.view;

import cs245_projectv10.Globals;
import cs245_projectv10.controller.Keyboard;
import cs245_projectv10.model.Hangman;
import cs245_projectv10.screens.CreditsScreen;
import cs245_projectv10.screens.GameScreen;
import cs245_projectv10.screens.HighScoreScreen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel {
    /* --- Variables --- */
    private GameScreen game;
    private JButton    playButton;
    private JButton    highScoresButton;
    private JButton    creditsButton;
    private JPanel     logoPanel;
    private JPanel     centerPanel;
    private JPanel     buttonPanel;
    
    public MainMenu(GameScreen game) {
        this.game = game;
        setPanelAttributes();
        createLogoPanel();
        createCenterPanel();
        addComponentsToFrame();
        addActionListeners();
    }
    
    
    /* --- Helper Methods --- */
    
    private void setPanelAttributes() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout(20,20));
    }
    
    private void createLogoPanel() {
        logoPanel = new JPanel();
        ImageIcon teamNameIcon = new ImageIcon("src//cs245_projectv10//resources//teamlogo.png");
        JLabel teamLabel       = new JLabel();
        teamLabel.setIcon(teamNameIcon);
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setToolTipText("The ASCIIckers");
        logoPanel.add(teamLabel);
    }
    
    private void createCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1,3,20,20));
        centerPanel.setBackground(Color.WHITE);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,1,30,30));
        buttonPanel.setBackground(Color.WHITE);
        
        playButton       = new JButton("Play");
        playButton.setToolTipText("Select to begin game");
        highScoresButton = new JButton("High Scores");
        highScoresButton.setToolTipText("View saved high scores");
        creditsButton    = new JButton("Credits");
        creditsButton.setToolTipText("Gaze at the masterminds behind these games");

        playButton.setBackground(Globals.BUTTON_COLOR);
        highScoresButton.setBackground(Globals.BUTTON_COLOR);
        creditsButton.setBackground(Globals.BUTTON_COLOR);
        
        
        buttonPanel.add(playButton);
        buttonPanel.add(highScoresButton);
        buttonPanel.add(creditsButton);
        
        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());
        centerPanel.add(buttonPanel);
    }
    
    private void addComponentsToFrame() {
        add(logoPanel,BorderLayout.NORTH);
        add(centerPanel,BorderLayout.CENTER);
    }
    
    private void addActionListeners() {
        playButton.addActionListener((ActionEvent e)->{
            Keyboard controller  = new Keyboard();
            HangmanGameView view = new HangmanGameView(controller);
            Hangman model        = new Hangman(view, controller, game);
            
            game.swapPanel(view); // Swaps current panel out for a new panel
        });
        
        highScoresButton.addActionListener((ActionEvent e)->{
            HighScoreScreen highScores = new HighScoreScreen(game);
            game.swapPanel(highScores);
        });
        
        creditsButton.addActionListener((ActionEvent e)->{
            CreditsScreen credits = new CreditsScreen(game);
            game.swapPanel(credits);
        });
    }
}
