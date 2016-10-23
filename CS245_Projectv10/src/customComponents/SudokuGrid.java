/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customComponents;

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

/**
 *
 * @author Brian
 */
class SudokuGrid extends JPanel {

    private SudokuGridInner[][] innerGrid;
    private boolean alreadySubmitted;
    
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
    
    public void setUpLabels(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                JLabel temp = innerGrid[i/3][j/3].getLabelOfPanel(i, j);
                temp.setToolTipText("Click this square to edit the value in Column " + (i+1) + " and Row " + (j+1));
                if (Globals.EDITABLE_SUDOKU_SQUARES[j][i]){
                    temp.addMouseListener(new MouseListener(){
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            temp.setBorder(BorderFactory.createLineBorder(Color.blue));
                            String[] options = {"Clear","1","2","3","4","5","6","7","8","9"};
                            JFrame frame = new JFrame();
                            String input = (String)JOptionPane.showInputDialog(frame,
                                    "What number would you like to input?",
                                    "Number input",
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    options,
                                    options[0]);
                            if (input == null){}
                            else if (input.length() != 1){
                                temp.setText("");
                            }
                            else {
                                temp.setText(input);
                            }
                            temp.setForeground(Color.blue);
                            temp.setBorder(null);
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
                }
            }
        }
    }
    
    public String[][] getCurrentState() {
        String[][] grid = new String[9][9];
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                grid[j][i] = innerGrid[i/3][j/3].getLabelOfPanel(i, j).getText();
            }
        }
        return grid;
    }
    
    public boolean checkForBlanks(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (innerGrid[i/3][j/3].getLabelOfPanel(i, j).getText().length() == 0) return true;
            }
        }
        return false;
    }

    public void checkWin(){
        if (checkForBlanks()){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Not all squares are filled!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
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
    }
    
}
