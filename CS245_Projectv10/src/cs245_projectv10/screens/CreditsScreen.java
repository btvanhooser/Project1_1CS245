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
import cs245_projectv10.view.MainMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.SwingConstants.*;

/**
 *
 * @author andrew
 */
public class CreditsScreen extends JFrame {
    
    /* --- Constants ---*/
    private final MainMenu mainMenu;
    private final String [] names = {"Alfredo Ceballos, 010270728",
                                     "Andrew Olaveson, 010481549",
                                     "Brian Van Hooser, 009344504",
                                     "Melanie Giusti, 010829117"};
    private LinkedList<JLabel> nameLabels;
    private JPanel creditsPanel;
    private JPanel backButtonPanel;
    private JLabel creditsLabel;
    private JButton backButton;
    
    public CreditsScreen(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void addComponentsToFrame() {
        nameLabels      = new LinkedList<>();
        creditsLabel    = new JLabel("--- CREDITS ---",CENTER);
        creditsLabel.setToolTipText("--- CREDITS ---");
        creditsPanel    = new JPanel(new GridLayout(0,1));
        backButtonPanel = new JPanel(new BorderLayout());
        
        creditsPanel.add(creditsLabel);
        for (int ii = 0; ii < names.length; ++ii) {
            JLabel temp = new JLabel(names[ii],CENTER);
            temp.setToolTipText(names[ii]);
            nameLabels.add(temp);
            nameLabels.get(ii).setText(names[ii]);
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
            mainMenu.setVisible(true);
            dispose();
        });
    }
    
}
