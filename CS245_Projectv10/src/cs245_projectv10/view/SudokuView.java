/***************************************************************
* file: SudokuGrid.java
* @author: Andrew Oloaveson
* @author: Melanie Giusti
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified: 10/26/2016
* purpose: Container to hold Sudoku components
****************************************************************/
package cs245_projectv10.view;

import cs245_projectv10.Globals;
import cs245_projectv10.screens.EndScreen;
import cs245_projectv10.screens.GameScreen;
import cs245_projectv10.sudoku.SudokuGrid;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SudokuView extends JPanel {
    
    /* --- VARIABLES ---*/
    private SudokuGrid       grid;
    private GameScreen       game;
    private Timer            timer;
    private SimpleDateFormat dateFormat; 
    private JPanel           headerPanel;
    private JPanel           buttonsPanel;
    private JLabel           headerLabel;
    private JLabel           scoreLabel;
    private JLabel           clockTextArea;
    private JButton          quitButton;
    private JButton          submitButton;
    
    public SudokuView(GameScreen game){
        
        startClock();
        this.game = game;
        grid = new SudokuGrid();

        createHeaderPanel();
        createButtonsPanel();
        addActionListeners();
        addPanelsToMainPanel();
    }
    
    public void addPanelsToMainPanel(){
        setLayout(new BorderLayout());
        add(headerPanel,BorderLayout.NORTH);
        add(grid,BorderLayout.CENTER);
        add(buttonsPanel,BorderLayout.SOUTH);
    }
    
    public void createHeaderPanel(){
        headerPanel = new JPanel();
        
        headerLabel = new JLabel("SUDOKU");
        headerLabel.setToolTipText("Current game");
        scoreLabel = new JLabel(" ");
        dateFormat = new SimpleDateFormat("MM/dd/yyy HH:mm:ss");
        clockTextArea = new JLabel(dateFormat.format(new Date()));
        clockTextArea.setToolTipText("Current date and time");
        
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
    }
    
    public void createButtonsPanel(){
        buttonsPanel = new JPanel();
        
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setLayout(new GridLayout(1,0,50,50));
        
        quitButton   = new JButton("Quit");
        quitButton.setBackground(Globals.BUTTON_COLOR);
        submitButton = new JButton("Submit");
        submitButton.setBackground(Globals.BUTTON_COLOR);
        
        quitButton.setToolTipText("Quit sudoku game");
        submitButton.setToolTipText("Submit answer");
       
        buttonsPanel.add(new JLabel(""));
        buttonsPanel.add(submitButton);
        buttonsPanel.add(new JLabel(""));
        buttonsPanel.add(quitButton);
        buttonsPanel.add(new JLabel(""));
    }
    
    public void addActionListeners(){
        submitButton.addActionListener((ActionEvent e) -> {
                if(grid.checkWin()) {
                    EndScreen endScreen = new EndScreen(game);
                    game.swapPanel(endScreen);
                }
        });
        
        quitButton.addActionListener((ActionEvent e) -> {
                EndScreen endScreen = new EndScreen(game);
                Globals.SUDOKU_GAME_SCORE = 0;
                game.swapPanel(endScreen);
        });
    }
    
    // Clock which will update on 1000ms intervals to show the current time.
    private void startClock() {
        timer = new Timer(1000, (ActionEvent e) -> {
            clockTextArea.setText(dateFormat.format(new Date()));
        });
        timer.start();
    }
    
}
