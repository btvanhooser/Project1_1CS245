/***************************************************************
* file: main.java
* @author: Andrew Olaveson
* @author: Melanie Giusti
* @author: Brian Van Hooser
* @author: Alfredo Ceballos
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified: 10/6/2016
* purpose: This is the file that contains the 'main' method which
* handles the usage of the SplashScreen, and then launches the 
* MainFrame after a set time.
****************************************************************/
package cs245_projectv10;

import cs245_projectv10.screens.SplashScreen;
import cs245_projectv10.view.MainMenu;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class main {
    
    static MainMenu mainMenu;
    // Method: main
    // Purpose: This is the starting point for this project.
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            SplashScreen splash = new SplashScreen();
            
            Timer timer = new Timer(3000, (ActionEvent e) -> {
                splash.dispose();
                mainMenu = new MainMenu();
            });
            timer.setRepeats(false);
            timer.start();
            
        });
    }
}
