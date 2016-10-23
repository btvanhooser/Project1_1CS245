/***************************************************************
* file: SudokuGridInner.java
* @author: Brian Van Hooser
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified: 10/23/2016
* purpose: Sub grids that contain each individual square. Used 
* three classes instead of two to provide a better look to the 
* board.
****************************************************************/
package customComponents;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Brian
 */
class SudokuGridInner extends JPanel {

    private SudokuPanel[][] panelList;
    
    //SudokuGridInner
    //purpose: constructor
    public SudokuGridInner() {
        super(new GridBagLayout());
        
        panelList = new SudokuPanel[3][3];

        GridBagConstraints c = new GridBagConstraints();
        /** construct the grid */
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                c.weightx = 1.0;
                c.weighty = 1.0;
                c.fill = GridBagConstraints.BOTH;
                c.gridx = i;
                c.gridy = j;
                SudokuPanel temp = new SudokuPanel();
                panelList[i][j] = temp;
                add(temp, c);
            }
        }

        /** create a black border */ 
        setBorder(BorderFactory.createLineBorder(Color.black)); 

    }

    //getLabelOfPanel
    //helper method to retrieve the label of a panel in the panelList array.
    public JLabel getLabelOfPanel(int a, int b) { return panelList[a%3][b%3].getLabel();}
    
}