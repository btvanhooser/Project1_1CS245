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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class ColorGameView extends JPanel {
    /* --- Variables --- */
    private JPanel     mainPanel;
    private JPanel     headerPanel;
    private JLabel     headerLabel;
    private JLabel     scoreLabel;
    private JLabel     clockTextArea;
    private JLabel     currentColor;
    private JButton    colorButton1;
    private JButton    colorButton2;
    private JButton    colorButton3;
    private JButton    colorButton4;
    private JButton    colorButton5;
    private DateFormat dateFormat;
    private Timer      timer;
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
        setBackground(Color.WHITE);
    }
    
    private void createMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
    }
    
    private void createHeaderPanelItems() {
        headerLabel = new JLabel("Color Game");
        headerLabel.setToolTipText("Current game");
        scoreLabel = new JLabel(" Score: ---");
        scoreLabel.setToolTipText("This is your score");
        dateFormat = new SimpleDateFormat("MM/dd/yyy HH:mm:ss");
        clockTextArea = new JLabel(dateFormat.format(new Date()));
        clockTextArea.setToolTipText("Current date and time");
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
        
        colorButton1 = controller.getColorList().get(0);
        colorButton1.setSize(100,100);
        colorButton1.setLocation(buttonCoordinates[0],buttonCoordinates[1]);
        colorButton1.setToolTipText("Red");
        
        colorButton2 = controller.getColorList().get(1);
        colorButton2.setSize(100,100);
        colorButton2.setLocation(buttonCoordinates[2],buttonCoordinates[3]);
        colorButton2.setToolTipText("Yellow");
        
        colorButton3 = controller.getColorList().get(2);
        colorButton3.setSize(100,100);
        colorButton3.setLocation(buttonCoordinates[4],buttonCoordinates[5]);
        colorButton3.setToolTipText("Green");
        
        colorButton4 = controller.getColorList().get(3);
        colorButton4.setSize(100,100);
        colorButton4.setLocation(buttonCoordinates[6],buttonCoordinates[7]);
        colorButton4.setToolTipText("Blue");
        
        colorButton5 = controller.getColorList().get(4);
        colorButton5.setSize(100,100);
        colorButton5.setLocation(buttonCoordinates[8],buttonCoordinates[9]);
        colorButton5.setToolTipText("Purple");
        
        mainPanel.add(colorButton1);
        mainPanel.add(colorButton2);
        mainPanel.add(colorButton3);
        mainPanel.add(colorButton4);
        mainPanel.add(colorButton5);
    }
}
