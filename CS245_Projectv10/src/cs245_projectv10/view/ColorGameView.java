/***************************************************************
* file: ColorGameView.java
* @author: Andrew Olaveson
* @author: Melanie Giusti
* 
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified:  
* purpose:            Defines the game rules for Hangman
****************************************************************/
package cs245_projectv10.view;

import cs245_projectv10.Globals;
import cs245_projectv10.controller.Keyboard;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class ColorGameView extends JFrame {
    
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JLabel headerLabel;
    private JLabel scoreLabel;
    private JLabel clockTextArea;
    private DateFormat dateFormat;
    private Timer timer;
    private JLabel currentColor;
    
    int [] buttonCoordinates;
    Keyboard controller;
    
    public ColorGameView(Keyboard controller){
        this.controller = controller;
        createMainPanel();
        createHeaderPanelItems();
        startClock();
        setFrameAttributes();
        addPanels();
    }
    
    public void update(int [] coordinates, JLabel currentColor) {
        buttonCoordinates = coordinates;
        this.currentColor = currentColor;
        updateScore();
        headerPanel.remove(6);
        headerPanel.add(currentColor, 6);
        addButtonsToMainPanel();
        validate();
    }
    
    private void setFrameAttributes() {
        setLayout(new BorderLayout());
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void createMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
    }
    
    private void createHeaderPanelItems() {
        headerLabel = new JLabel("Color Game");
        scoreLabel = new JLabel(" Score: ---");
        dateFormat = new SimpleDateFormat("MM/dd/yyy HH:mm:ss");
        clockTextArea = new JLabel(dateFormat.format(new Date()));
    }
    
    
    private void addPanels() {
        headerPanel = new JPanel();
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        headerPanel.setLayout(new GridLayout(0,4));
        headerPanel.setBackground(Color.WHITE);
        
        headerPanel.add(clockTextArea);
        headerPanel.add(new JLabel());
        headerPanel.add(new JLabel());
        headerPanel.add(headerLabel);
        headerPanel.add(scoreLabel);
        headerPanel.add(new JLabel());
        headerPanel.add(new JLabel());
        headerPanel.add(new JLabel());

        add(headerPanel,BorderLayout.NORTH);
        add(mainPanel,BorderLayout.CENTER);
    }
    
    private void updateScore() {
        scoreLabel.setText(" Score: "+ Integer.toString(Globals.COLOR_GAME_SCORE));
    }
        
    private void startClock() {
        timer = new Timer(1000, (ActionEvent e) -> {
            clockTextArea.setText(dateFormat.format(new Date()));
        });
        timer.start();
    }
    
    private void addButtonsToMainPanel() {
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton button4 = new JButton();
        JButton button5 = new JButton();
        
        button1 = controller.getColorList().get(0);
        button1.setSize(100,100);
        button1.setLocation(buttonCoordinates[0],buttonCoordinates[1]);
        
        button2 = controller.getColorList().get(1);
        button2.setSize(100,100);
        button2.setLocation(buttonCoordinates[2],buttonCoordinates[3]);
        
        button3 = controller.getColorList().get(2);
        button3.setSize(100,100);
        button3.setLocation(buttonCoordinates[4],buttonCoordinates[5]);
        
        button4 = controller.getColorList().get(3);
        button4.setSize(100,100);
        button4.setLocation(buttonCoordinates[6],buttonCoordinates[7]);
        
        button5 = controller.getColorList().get(4);
        button5.setSize(100,100);
        button5.setLocation(buttonCoordinates[8],buttonCoordinates[9]);
        
        mainPanel.add(button1);
        mainPanel.add(button2);
        mainPanel.add(button3);
        mainPanel.add(button4);
        mainPanel.add(button5);
        
    }
    
}
