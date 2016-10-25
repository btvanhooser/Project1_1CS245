/***************************************************************
* file: SudokuGrid.java
* @author: Brian Van Hooser
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import cs245_projectv10.Globals;
import javax.swing.JTextArea;
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
        
        alreadySubmitted = false;
        
        innerGrid = new SudokuGridInner[3][3];

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
                JTextPane temp = innerGrid[i/3][j/3].getLabelOfPanel(i, j);
                temp.setToolTipText("Click this square to edit the value in Column " + (i+1) + " and Row " + (j+1));
                if (Globals.EDITABLE_SUDOKU_SQUARES[j][i]) {
                    temp.addMouseListener(new MouseListener(){
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            temp.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//                            String[] options = {"Clear","1","2","3","4","5","6","7","8","9"};
//                            JFrame frame = new JFrame();
//                            String input = (String)JOptionPane.showInputDialog(frame,
//                                    "What number would you like to input?",
//                                    "Number input",
//                                    JOptionPane.PLAIN_MESSAGE,
//                                    null,
//                                    options,
//                                    options[0]);
//                            if (input == null){}
//                            else if (input.length() != 1){
//                                temp.setText("");
//                            }
//                            else {
//                                temp.setText(input);
//                            }
                            temp.setForeground(Color.BLUE);
//                            temp.setBorder(null);
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {}

                        @Override
                        public void mouseReleased(MouseEvent e) {}

                        @Override
                        public void mouseEntered(MouseEvent e) {}

                        @Override
                        public void mouseExited(MouseEvent e) {}

                    });
                }
                else { 
                    temp.setText(Globals.INITIAL_BOARD_FOR_SUDOKU[j][i]);
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
                grid[j][i] = innerGrid[i/3][j/3].getLabelOfPanel(i, j).getText();
            }
        }
        return grid;
    }
    
    //checkForBlanks
    //purpose: returns true if any squares do not contain a number
    public boolean checkForBlanks(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (innerGrid[i/3][j/3].getLabelOfPanel(i, j).getText().length() == 0) return true;
            }
        }
        return false;
    }

    //checkWin
    //purpose: checks all conditions on the grid to see if a win 
    //scenario has been met or not.
    public boolean checkWin(){
        if (checkForBlanks()){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Not all squares are filled!", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        String[][] currentState = getCurrentState();
        int wrongAnswers = 0;
        
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (!currentState[i][j].equals(Globals.WIN_STATE_FOR_SUDOKU[i][j])){
                    wrongAnswers++;
                    innerGrid[j/3][i/3].getLabelOfPanel(j,i).setBorder(BorderFactory.createLineBorder(Color.red));
                }
            }
        }
        if (!alreadySubmitted){
            Globals.SUDOKU_GAME_SCORE -= (wrongAnswers*10);
            alreadySubmitted = true;
        }
        return (!(wrongAnswers > 0));
    }
    
}
