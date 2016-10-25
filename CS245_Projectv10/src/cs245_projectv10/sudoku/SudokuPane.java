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
package cs245_projectv10.sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


public class SudokuPane extends JPanel {

    JTextPane pane;
    StyledDocument document;
    KeyListener keyListener;
    
    //SudokuPanel
    //purpose: constructor
    public SudokuPane() {
        super();
        setLayout(new BorderLayout());
        setTextPaneAttributes();
        
        pane = new JTextPane(document);
        pane.setFont(new Font("Arial Black", Font.PLAIN, 20));
        this.add(pane, BorderLayout.CENTER);
        
        keyListener = new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {
                if(pane.getText().length()>=1) {  
                    ke.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        };
        
        pane.addKeyListener(keyListener);
        /** create a black border */
        setBorder(BorderFactory.createLineBorder(Color.black));
        /** set size to 30x30 pixel for one square */
        this.setPreferredSize(new Dimension(30,30));
    }
    

    
    
    public void setTextPaneAttributes() {
        setLayout(new BorderLayout());
        StyleContext context = new StyleContext();
        Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);
        document = new DefaultStyledDocument(context);
    }
    
    //getLabel
    //purpose: getter method for the label of this SudokuPanel
    public JTextPane getLabel() {return this.pane;}
    
}
