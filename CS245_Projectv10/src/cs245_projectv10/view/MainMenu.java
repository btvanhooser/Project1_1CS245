/***************************************************************
* file: EndScreen.java
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
import cs245_projectv10.screens.CreditsScreen;
import cs245_projectv10.screens.GameScreen;
import cs245_projectv10.screens.HighScoreScreen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 */
public class MainMenu extends JFrame {
    
    /* --- --- */
    private JButton playButton;
    private JButton highScoresButton;
    private JButton creditsButton;
    private JPanel  logoPanel;
    private JPanel  centerPanel;
    private JPanel  buttonPanel;
    
    public MainMenu() {
        setFrameAttributes();
        createLogoPanel();
        createCenterPanel();
        addComponentsToFrame();
        addActionListeners();
        loadUpHighScoresFromFile();
    }
    
    
    /* --- Helper Methods --- */
    
    private void setFrameAttributes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setOpacity(1);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout(20,20));
    }
    
    private void createLogoPanel() {
        logoPanel = new JPanel();
        ImageIcon teamNameIcon = new ImageIcon("src//cs245_projectv10//resources//teamlogo.png");
        JLabel teamLabel       = new JLabel();
        teamLabel.setIcon(teamNameIcon);
        logoPanel.setBackground(Color.WHITE);
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
        highScoresButton = new JButton("High Scores");
        creditsButton    = new JButton("Credits");

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
            GameScreen game = new GameScreen();
            dispose();
        });
        
        highScoresButton.addActionListener((ActionEvent e)->{
            HighScoreScreen highScores = new HighScoreScreen(this);
            setVisible(false);
        });
        
        creditsButton.addActionListener((ActionEvent e)->{
            CreditsScreen credits = new CreditsScreen(this);
            setVisible(false);
        });
    }

    private void loadUpHighScoresFromFile() {
        File file = new File("src\\cs245_projectv10\\resources\\highscores.txt");
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        int player = 0;
        while (fileScanner.hasNext()){
            String tempUser = fileScanner.nextLine();
            int tempScore = Integer.parseInt(fileScanner.nextLine());
            Globals.USER_LIST[player] = tempUser;
            Globals.USER_SCORES[player] = tempScore;
            player++;
        }
    }
}
