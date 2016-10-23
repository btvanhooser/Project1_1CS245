/***************************************************************
* file: GameView.java
* @author: Andrew Olaveson
* @author: Melanie Giusti
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified:
* purpose: 
****************************************************************/
package cs245_projectv10.view;

import cs245_projectv10.Globals;
import cs245_projectv10.controller.Keyboard;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.Timer;


/**
 * This is the "View" of the game which displays the game according to what 
 *  information is given to it from the Model. The Model can pass this data 
 *  to here by using its update method. This method contains all of the parameters 
 *  which can change during the game.
 */
public class HangmanGameView extends JPanel {
    
    /* --- Constants --- */
    private final Keyboard controller;
    private final int      HANGMAN_STATES = 7;
    
    /* --- Variables --- */
    private JPanel hangmanPanel;
    private JPanel keyboardPanel;
    private JPanel headerPanel;
    private JPanel manPanel;
    private JPanel wordPanel;
    private JPanel endGamePanel;
    private JLabel scoreLabel;
    private JTextArea manBuffer;
    private JLabel headerLabel;
    private JLabel clockTextArea;
    
    private String [] hangmanParts;
    private LinkedList <JLabel> currentWordList;
    private String currentWord;
    private DateFormat dateFormat;
    private Timer timer;
    
   
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public HangmanGameView(Keyboard controller) {
        this.controller = controller;
        createWordPanel();
        createHangmanPanel();
        createKeyboardPanel();
        createHeaderPanelItems();
        startClock();
        addPanels();
    }
 
    // Update function is driven from the Hangman Model
    //  Will update the Score, Current Word displayed with missing letters and 
    //  state of the Hang-man. 
    public void update(String wordState, int wrongGuesses) {
        currentWord = wordState;
        updateScore(Integer.toString(Globals.HANGMAN_GAME_SCORE));
        updateWord();
        drawNoose(wrongGuesses);
    }
    
    public void endHangman(String fullWord, int wrongGuesses, String message) {
        endGamePanel = new JPanel();
        Font font = new Font("MONOSPACED",Font.PLAIN,27);
        JLabel endGameLabel = new JLabel(message);
        
        headerPanel.remove(controller.getSkipButton());
        headerPanel.add(controller.getNextButton());
        remove(keyboardPanel);

        
        if (wrongGuesses < Globals.MAX_TRYS) {
            endGameLabel.setForeground(Color.GREEN);
        } else {
            endGameLabel.setForeground(Color.RED);
        }
                
        endGameLabel.setFont(font);
        endGameLabel.setHorizontalTextPosition(CENTER);
        endGamePanel.setBackground(Color.WHITE);

        fillMissedGuesses(fullWord);
        endGamePanel.add(endGameLabel);
        add(endGamePanel,BorderLayout.SOUTH);

        validate();
        repaint();
    }

    /* --- Helper Methods --- */
    
    private void addPanels() {
        setLayout(new BorderLayout());
        add(keyboardPanel,BorderLayout.SOUTH);
        add(hangmanPanel,BorderLayout.CENTER);
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
        headerPanel.add(controller.getSkipButton());

        add(headerPanel,BorderLayout.NORTH);
    }
    
    private void createHangmanPanel() {
        String hangmanFile    = "src//cs245_projectv10//resources//noose";
        String hangmanFileExt = ".txt";
        hangmanParts = new String[HANGMAN_STATES];
        for(int ii = 0; ii < HANGMAN_STATES; ii++){
            hangmanParts[ii] = "";
            String hangmanSrcFile = hangmanFile + ii + hangmanFileExt;
            try {
                BufferedReader bReader = new BufferedReader(new FileReader(hangmanSrcFile));
                String line = bReader.readLine();
                System.out.println("" + line);
                while (line != null) {
                    hangmanParts[ii] += line + "\n";
                    line = bReader.readLine();
                }
                bReader.close();
            } 
            catch (IOException e) { 
                System.out.println("Could Not Read File."); 
            }
        }
        
        hangmanPanel = new JPanel();
        manPanel     = new JPanel();
        manBuffer    = new JTextArea();
        Font font    = new Font("MONOSPACED",Font.PLAIN,5);
        
        manBuffer.setFont(font);
        manBuffer.setColumns(75);
        manBuffer.setToolTipText("DO NOT LET ME DOWN");
        
        drawNoose(0);
        
        manPanel.setBackground(Color.WHITE);
        manPanel.add(manBuffer);
        manPanel.setToolTipText("DO NOT LET ME DOWN");
        
        hangmanPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        hangmanPanel.setLayout(new BorderLayout());
        hangmanPanel.add(manPanel,BorderLayout.CENTER);
        hangmanPanel.add(wordPanel,BorderLayout.SOUTH);
        hangmanPanel.setToolTipText("Correctly guessed letters");
    }
    
    private void createKeyboardPanel() {
        keyboardPanel = new JPanel();
        keyboardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        keyboardPanel.setLayout(new GridLayout(2,13,5,5));
        keyboardPanel.setBackground(Color.WHITE);
        for (int ii = 0; ii < controller.getSize(); ++ii ) {
            keyboardPanel.add(controller.getKeyboardList().get(ii));
            keyboardPanel.setToolTipText("");
        }
    }
    
    private void createHeaderPanelItems() {
        headerLabel = new JLabel("HANGMAN");
        headerLabel.setToolTipText("Current game");
        scoreLabel = new JLabel(" Score: ---");
        scoreLabel.setToolTipText("Current score");
        dateFormat = new SimpleDateFormat("MM/dd/yyy HH:mm:ss");
        clockTextArea = new JLabel(dateFormat.format(new Date()));
        clockTextArea.setToolTipText("Current date and time");
    }
    
    // fetches the current hang-man to be drawn on the screen depending 
    //  on the number of incorrect guesses.
    private void drawNoose(int wrongGuessCount) {
        manBuffer.setText(hangmanParts[wrongGuessCount]);
    }
    
    private void createWordPanel() {
        currentWord = "---";
        wordPanel = new JPanel();
        wordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        wordPanel.setBackground(Color.WHITE);
        currentWordList = new LinkedList<>();

        updateWord();
    }
    
    // Clock which will update on 1000ms intervals to show the current time.
    private void startClock() {
        timer = new Timer(1000, (ActionEvent e) -> {
            clockTextArea.setText(dateFormat.format(new Date()));
        });
        timer.start();
    }
    
    private void updateScore(String score) {
        scoreLabel.setText(" Score: "+ score);
    }
    
    private void updateWord() {
        wordPanel.removeAll();

        for (int ii = 0; ii < currentWord.length(); ++ii) {
            currentWordList.add(new JLabel(currentWord.charAt(ii)+ " "));
            wordPanel.add(currentWordList.get(ii));
            currentWordList.get(ii).setText(currentWord.charAt(ii) + " ");
        }
    }
    
    private void fillMissedGuesses(String fullWord) {
        wordPanel.removeAll();

        for (int ii = 0; ii < currentWord.length(); ++ii) {
            if (currentWord.charAt(ii) != fullWord.charAt(ii)) {
                currentWordList.add(new JLabel());
                currentWordList.get(ii).setText(fullWord.charAt(ii)+ " ");
                currentWordList.get(ii).setForeground(Color.RED);
                wordPanel.add(currentWordList.get(ii));
            } else {
                currentWordList.add(new JLabel());
                currentWordList.get(ii).setText(currentWord.charAt(ii) + " ");
                wordPanel.add(currentWordList.get(ii));
            }
        }
    }
}
