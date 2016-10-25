/***************************************************************
* file: SudokuGrid.java
* @author: Brian Van Hooser
* @author: Andrew Olaveson
* @author: Melanie Giusti
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified: 10/23/2016
* purpose: Overall grid to contain the inner grids that hold the
* squares with data. This also contains the methods to check for 
* game completion status.
****************************************************************/
package cs245_projectv10.sudoku;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import cs245_projectv10.Globals;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextPane;

/**
 *
 * @author Brian
 */
public class SudokuGrid extends JPanel {

    private SudokuGridInner[][] innerGrid;
    private boolean alreadySubmitted;
    
    //SudokuGrid
    //Purpose: Constructor
    public SudokuGrid() {
        super(new GridBagLayout());
        innerGrid = new SudokuGridInner[3][3];
        alreadySubmitted = false;
        
        GridBagConstraints c = new GridBagConstraints();
        /** construct the grid */
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                c.weightx = 1.0;
                c.weighty = 1.0;
                c.fill = GridBagConstraints.BOTH;
                c.gridx = i;
                c.gridy = j;
                SudokuGridInner temp = new SudokuGridInner();
                innerGrid[i][j] = temp;
                add(temp, c);
            }
        }

        /** create a black border */ 
        setBorder(BorderFactory.createLineBorder(Color.black)); 
        setUpLabels();
    }
    
    //SetUpLabels
    //Purpose: helper method to add all data to the sudoku grid. Adds listeners
    //for data changes as well.
    public void setUpLabels(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                JTextPane temp = innerGrid[i/3][j/3].getTextPane(i, j);
                temp.setToolTipText("Click this square to edit the value in Column " + (i+1) + " and Row " + (j+1));
                if (Globals.EDITABLE_SUDOKU_SQUARES[j][i]) {
                    temp.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            temp.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            temp.setBorder(null);
                        }
                    });
                } else { 
                    temp.setText(Globals.INITIAL_BOARD_FOR_SUDOKU[j][i]);
                    temp.setBackground(Color.LIGHT_GRAY);
                    temp.setEditable(false);
                }
            }
        }
    }
    
    //getCurrentState
    //purpose: converts the multi-object structure into a simple 2D array
    public String[][] getCurrentState() {
        String[][] grid = new String[9][9];
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                grid[j][i] = innerGrid[i/3][j/3].getTextPane(i, j).getText();
            }
        }
        return grid;
    }
    
    //checkForBlanks
    //purpose: returns true if any squares do not contain a number
    public boolean checkForBlanks(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (innerGrid[i/3][j/3].getTextPane(i, j).getText().length() == 0) return true;
            }
        }
        return false;
    }

    //checkWin
    //purpose: checks all conditions on the grid to see if a win 
    //scenario has been met or not.
    public boolean checkWin(){
        int wrongAnswers = 0;
        String[][] currentState;
        JFrame frame = new JFrame();

        currentState = getCurrentState();
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (!currentState[i][j].equals(Globals.WIN_STATE_FOR_SUDOKU[i][j])){
                    wrongAnswers++;
                    innerGrid[j/3][i/3].getTextPane(j,i).setBorder(BorderFactory.createLineBorder(Color.red));
                } else if (Globals.EDITABLE_SUDOKU_SQUARES[i][j]) {
                    innerGrid[j/3][i/3].getTextPane(j,i).setForeground(Color.BLUE);
                    innerGrid[j/3][i/3].getTextPane(j,i).setBackground(Color.LIGHT_GRAY);
                    innerGrid[j/3][i/3].getTextPane(j,i).setEditable(false);
                }
            }
        }
        
        Globals.SUDOKU_GAME_SCORE = 540;
        Globals.SUDOKU_GAME_SCORE -= (wrongAnswers*10);
        if (Globals.SUDOKU_GAME_SCORE < 0) {       
            Globals.SUDOKU_GAME_SCORE = 0;
        }

        if (!alreadySubmitted) {
            JOptionPane.showMessageDialog(frame, "Some of your Answers are incorrect!"
                + "\nHit Submit Again to continue without Fixing.\n"
                + "Otherwise make your corrections",
                "Warning", JOptionPane.WARNING_MESSAGE);
            alreadySubmitted = true;
            return false;
        } else {
            return true;
        }
    }
}
