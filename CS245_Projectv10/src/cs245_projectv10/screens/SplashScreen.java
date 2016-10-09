/***************************************************************
* file: GameScreen.java
* @author: Melanie Giusti
* @author: Brian Van Hooser
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified:
* purpose: Simply displays the introduction screen for the game 
*          program.
****************************************************************/
package cs245_projectv10.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout; 
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SplashScreen extends JFrame {
    
    private JPanel mainPanel;
    
    public SplashScreen(){
        setMainPanelAttributes();
        setFrameAttributes();
    }
    
    /*Adds main content panel to frame, sets attributes of root frame and displays*/
    private void setFrameAttributes(){
        
        add(mainPanel);
        pack();
        setSize(600,400);
        setTitle("CS 245 Project");
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /*Adds components to main content panel*/
    private void setMainPanelAttributes(){
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5,1));
        
        ImageIcon projectNameIcon = new ImageIcon("src//cs245_projectv10//resources//projectname.png");
        JLabel projectLabel       = new JLabel();
        projectLabel.setIcon(projectNameIcon);
        
        JLabel byLineLabel  = new JLabel("By:",SwingConstants.CENTER);
        Font font = new Font("Arial",Font.PLAIN,20);
        byLineLabel.setFont(font);
        
        ImageIcon teamNameIcon = new ImageIcon("src//cs245_projectv10//resources//teamlogo.png");
        JLabel teamLabel       = new JLabel();
        teamLabel.setIcon(teamNameIcon);
        
        JLabel topBufferLabel = new JLabel();
        topBufferLabel.setBackground(Color.WHITE);
        
        JLabel bottomBufferLabel = new JLabel();
        bottomBufferLabel.setBackground(Color.WHITE);
        
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(topBufferLabel);
        mainPanel.add(projectLabel);
        mainPanel.add(byLineLabel);
        mainPanel.add(teamLabel);
        mainPanel.add(bottomBufferLabel);
    }
}
