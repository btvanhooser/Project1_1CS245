/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public JLabel getLabel() {return this.label;}
    
}
