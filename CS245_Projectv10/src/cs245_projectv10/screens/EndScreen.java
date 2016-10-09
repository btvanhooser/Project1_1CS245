/***************************************************************
* file: EndScreen.java
* @author: Andrew Olaveson
* @author: Melanie Giusti
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified:
* purpose: 
****************************************************************/
package cs245_projectv10.screens;

import cs245_projectv10.Globals;
import cs245_projectv10.view.MainMenu;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * EndScreen Displays the end score and a button to go back to the main menu
 *  screen
 */
public class EndScreen  extends JFrame {
    /* --- Variables --- */
    JPanel   endGamePanel;
    JPanel   headerPanel;
    JPanel   footerPanel;
    JPanel   manPanel;
    JPanel   wordPanel;
    JLabel   scoreLabel;
    JButton  endGameButton;
    
    public EndScreen(int score, GameScreen game) {
        createEndGamePanel(score);
        setFrameAttributes();
        endGamePanel.add(new JLabel());
        endGamePanel.add(scoreLabel);
        endGamePanel.add(new JLabel());
        endGamePanel.add(new JLabel());
        endGamePanel.add(endGameButton);
        endGamePanel.add(new JLabel());
        addActionListenersToEndButton();
    }
       
    
    /* --- Helper Methods --- */
    
    // Set the visual properties of the EndScreen
    private void setFrameAttributes() {
        add(endGamePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }    
    
    // Create Endgame Panel ontop of the Frame
    private void createEndGamePanel(int score) {
        endGamePanel = new JPanel();
        endGamePanel.setLayout(new GridLayout(2,3,100,300));
        scoreLabel = new JLabel("Score: " + score);
        Font font = new Font("MONOSPACED",Font.PLAIN,24);
        scoreLabel.setFont(font);
        endGameButton = new JButton("End");
        endGameButton.setBackground(Globals.BUTTON_COLOR);
    }
    
    
    // Self Descriptive function name. No need for comment.
    private void addActionListenersToEndButton() {
        endGameButton.addActionListener((ActionEvent e) ->{
            MainMenu mainMenu = new MainMenu();
            dispose();
        });
    }
}
