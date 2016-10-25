/***************************************************************
* file: GameScreen.java
* @author: Andrew Olaveson
* @author: Melanie Giusti
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified:
* purpose: 
****************************************************************/
package cs245_projectv10.screens;

import cs245_projectv10.view.MainMenu;
import javax.swing.Action;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


/**
 * Main Game class which creates and ties the Model, controller and View together
 *  This creates an easy way of controlling dependencies passing a common pointer
 *  (this) to the model which drives the View while utilizing the controller
 */
public class GameScreen  extends JFrame {
    /* --- Constants -- */
    private final MainMenu mainMenu;
    
    /* --- Variables --- */
    ProgramInfoScreen infoScreen;
    
    public GameScreen() {
        mainMenu = new MainMenu(this);
        infoScreen = new ProgramInfoScreen();
        setFrameAttributes();
        createKeyBindings();
    }
    
    private void setFrameAttributes() {
        add(mainMenu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setOpacity(1);
        setBackground(Color.WHITE);
    }
    
    public void swapPanel(JPanel panelToSwap){
        getContentPane().removeAll();
        add(panelToSwap);
        validate();
    } 
    
    public void backToMainMenu() {
        swapPanel(mainMenu);
        repaint();
    }
    
    public void createKeyBindings(){
        Action F1Action = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                infoScreen.setVisible(true);
            }
        };
        
        Action EscAction = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"),"F1");
        getRootPane().getActionMap().put("F1",F1Action);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"),"Esc");
        getRootPane().getActionMap().put("Esc",EscAction);
    }
}
