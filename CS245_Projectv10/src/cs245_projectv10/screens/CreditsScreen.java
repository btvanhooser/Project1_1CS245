/***************************************************************
* file: CreditsScreen.java
* @author: Alfredo Ceballos
* @author: Andrew Olaveson
* 
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified: 10/22/16 7:27PM  
*  purpose: This is the JPanel that is used to handle the format
*  and content of the Credits Screen. This is able to switch back
*  and forth between the MainScreen and this page through the 
*  MainFrame. Added tool tips for components
****************************************************************/
package cs245_projectv10.screens;

import cs245_projectv10.Globals;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.SwingConstants.*;

public class CreditsScreen extends JPanel {
    
    /* --- Constants ---*/
    private final GameScreen game;
    
    /* --- Variables --- */
    private LinkedList<JLabel> nameLabels;
    private JPanel creditsPanel;
    private JPanel backButtonPanel;
    private JLabel creditsLabel;
    private JButton backButton;
    
    
    public CreditsScreen(GameScreen game) {
        this.game = game;
        setFrameAttributes();
        addActionListenerToButton();
        addComponentsToFrame();
    }
    
    /* --- Helper Methods --- */
    
    private void setFrameAttributes() {
        backButton = new JButton("Back");
        backButton.setBackground(Globals.BUTTON_COLOR);
        backButton.setToolTipText("Return to main menu n00b");
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }
    
    private void addComponentsToFrame() {
        nameLabels      = new LinkedList<>();
        creditsLabel    = new JLabel("--- CREDITS ---",CENTER);
        creditsLabel.setToolTipText("--- CREDITS ---");
        creditsPanel    = new JPanel(new GridLayout(0,1));
        backButtonPanel = new JPanel(new BorderLayout());
        
        creditsPanel.add(creditsLabel);
        for (int ii = 0; ii < Globals.TEAM_MEMBERS.length; ++ii) {
            JLabel temp = new JLabel(Globals.TEAM_MEMBERS[ii],CENTER);
            temp.setToolTipText(Globals.TEAM_MEMBERS[ii]);
            nameLabels.add(temp);
            nameLabels.get(ii).setText(Globals.TEAM_MEMBERS[ii]);
            creditsPanel.add(nameLabels.get(ii));
        }
        
        backButtonPanel.add(backButton,BorderLayout.WEST);
        creditsPanel.setBackground(Color.WHITE);
        backButtonPanel.setBackground(Color.WHITE);
        add(new JLabel(),BorderLayout.NORTH);
        add(creditsPanel,BorderLayout.CENTER);
        add(backButtonPanel,BorderLayout.SOUTH);
        
    }
    
    private void addActionListenerToButton() {
        backButton.addActionListener((ActionEvent e)-> {
            game.backToMainMenu();
        });
    }
}
