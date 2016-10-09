/***************************************************************
* file: CreditsScreen.java
* @author: Alfredo Ceballos
* @author: Andrew Olaveson
* 
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified: 10/05/16 12:52 a.m.  
*  purpose: This is the JPanel that is used to handle the format
*  and content of the Credits Screen. This is able to switch back
*  and forth between the MainScreen and this page through the 
*  MainFrame.
****************************************************************/
package cs245_projectv10.screens;

import cs245_projectv10.view.MainMenu;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author andrew
 */
public class CreditsScreen extends JFrame {
    
    /* --- Cosntants ---*/
    private String [] names = {"Alfredo Ceballos 010270728",
                               "Andrew Olaveson 010481549",
                               "Brian Van Hooser 009344504",
                               "Melanie Giusti 010829117"};
    private LinkedList<JLabel> nameLabels;
    
    public CreditsScreen(MainMenu mainMenu) {
        setFrameAttributes();
        addComponentsToFrame();
    }
    
    
    /* --- Helper Methods --- */
    
    private void setFrameAttributes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void addComponentsToFrame() {
        for (int ii = 0; ii < names.length; ++ii) {
            nameLabels.add(new JLabel(names[ii]));
            nameLabels.get(ii).setText(names[ii]);
        }
    }
    
}
