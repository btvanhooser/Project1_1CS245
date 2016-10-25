/***************************************************************
* file: SudokuPane.java
* @author: Brian Van Hooser
* @author: Andrew Olaveson
* @author: Melanie Giusti
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
import java.awt.KeyboardFocusManager;
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
    KeyListener keyListener;
    Pattern regex;
    
    //SudokuPanel
    //purpose: constructor
    public SudokuPane() {
        setAttributes();
        initTextPane();
        initKeyListener();
        add(pane, BorderLayout.CENTER);
    }
    
    public void setAttributes() {
        setLayout(new BorderLayout());
        /** create a black border */
        setBorder(BorderFactory.createLineBorder(Color.black));
        /** set size to 30x30 pixel for one square */
        setPreferredSize(new Dimension(30,30));
        
        regex = Pattern.compile("[1-9]");
    }
    
    public void initTextPane() {
        StyleContext context = new StyleContext();
        Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);
        StyledDocument document = new DefaultStyledDocument(context);
        pane = new JTextPane(document);
        pane.setFont(new Font("Arial Black", Font.PLAIN, 20));
    }
    
    public void initKeyListener() {
        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                boolean Digit = regex.matcher(""+ ke.getKeyChar()).matches();
                
                pane.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
                pane.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
                
                if (ke.getExtendedKeyCode() == 0) {
                }
                
                if(pane.getText().length()>=1) {  
                    ke.consume();
                } else if(!Digit) {
                    pane.setBorder(BorderFactory.createLineBorder(Color.red));
                    ke.consume();
                } else {
                    pane.setBorder(null);
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) { }

            @Override
            public void keyReleased(KeyEvent ke) { }
        };
        
        pane.addKeyListener(keyListener);
    }
    
    //getLabel
    //purpose: getter method for the label of this SudokuPanel
    public JTextPane getPane() {return pane;}
    
}
