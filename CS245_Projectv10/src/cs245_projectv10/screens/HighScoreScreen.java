/***************************************************************
* file: HighScoreScreen.java
* @author: Brian Van Hooser
* @author: Melanie Giusti
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified:
* purpose: Displays top 5 player's high scores
****************************************************************/
package cs245_projectv10.screens;

import cs245_projectv10.Globals;
import cs245_projectv10.view.MainMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/*TODO: Add functionality and remove dummy placeholder scores*/
public class HighScoreScreen extends JFrame {
    
    /*TODO: Replace with actual scores from file*/
    private final String DUMMY_SCORES [] = {"AAA...1000","BBB...900","CCC...800",
                                            "DDD...700","EEE...600"};
    
    private MainMenu mainMenu;
    private JButton  backButton;
    private JPanel   centerPanel;
    private JPanel   footerPanel;
    
    /*Constructs all elements for highscore screen and displays the screen*/
    public HighScoreScreen(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        
        setFrameAttributes();
        createCenterPanel();
        createFooterPanel();
        addComponentsToFrame();
        addActionListeners();
    }
    
    /*Sets the attributes of the main frame*/
    private void setFrameAttributes(){
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setOpacity(1);
        getContentPane().setBackground(Color.WHITE);
    }
    
    /*Creates center panel to hold dummy high score values*/
    private void createCenterPanel(){
        centerPanel            = new JPanel();
        JLabel highScoresLabel = new JLabel("--- HIGH SCORES ---",SwingConstants.CENTER);
        
        centerPanel.setLayout(new GridLayout(0,1));
        centerPanel.setBackground(Color.WHITE);
        
        centerPanel.add(highScoresLabel);
        
        for(int ii = 0; ii < DUMMY_SCORES.length; ii++){
            JLabel scoreLabel = new JLabel(DUMMY_SCORES[ii],SwingConstants.CENTER);
            centerPanel.add(scoreLabel);
        }
        
    }
    
    /*Creates panel to contain "back" button*/
    private void createFooterPanel(){
        footerPanel = new JPanel();
        backButton  = new JButton("Back");
        
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
            mainMenu.setVisible(true);
            dispose();
        });
    }
}
