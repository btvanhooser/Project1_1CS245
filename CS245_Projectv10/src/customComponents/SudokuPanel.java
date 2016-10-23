/***************************************************************
* file: SudokuPanel.java
* @author: Brian Van Hooser
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified: 10/23/2016
* purpose: the class for each individual square. This class 
* contains the label that holds the square's current value and 
* state.
****************************************************************/
package customComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Brian
 */
class SudokuPanel extends JPanel {

    JLabel label;

    //SudokuPanel
    //purpose: constructor
    SudokuPanel() {
        super();
        
        setLayout(new BorderLayout());
        
        label = new JLabel("");
        label.setFont(new Font("Ariel Black", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        
        
        this.add(label, BorderLayout.CENTER);
        
        /** create a black border */
        setBorder(BorderFactory.createLineBorder(Color.black));

        /** set size to 30x30 pixel for one square */
        this.setPreferredSize(new Dimension(30,30));
    }
    
    //getLabel
    //purpose: getter method for the label of this SudokuPanel
    public JLabel getLabel() {return this.label;}
    
}
