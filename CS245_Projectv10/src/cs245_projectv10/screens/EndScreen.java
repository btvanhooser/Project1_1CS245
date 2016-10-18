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
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * EndScreen Displays the end score and a button to go back to the main menu
 *  screen
 */
public class EndScreen  extends JFrame {
    /* --- Variables --- */
    private JPanel   endGamePanel;
    private JLabel   scoreLabel;
    private JButton  endGameButton;
    
    public EndScreen(GameScreen game) {
        createEndGamePanel();
        setFrameAttributes();
        endGamePanel.add(scoreLabel);
        endGamePanel.add(new JLabel());
        endGamePanel.add(endGameButton);
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
    private void createEndGamePanel() {
        int totalscore = Globals.HANGMAN_GAME_SCORE + Globals.COLOR_GAME_SCORE;
        
        endGamePanel = new JPanel();
        endGamePanel.setLayout(new GridLayout(3,1,50,100));
        endGamePanel.setBackground(Color.WHITE);
        scoreLabel = new JLabel("Score: " + totalscore, SwingConstants.CENTER);
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
