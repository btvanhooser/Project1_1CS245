/***************************************************************
* file: Hangman.java
* @author: Andrew Olaveson
* @author: Melanie Giusti
* @author: Alfredo Ceballos
* @author: Brian Van Hooser
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified: 10/05/16 12:52 a.m.  
* purpose: Added color game max tries and color list
****************************************************************/
package cs245_projectv10;

import java.awt.Color;
import java.io.File;

/**
 *
 * @author andrew, brian
 */
public class Globals {
    public static final File   HIGH_SCORES_FILE   = new File("src\\cs245_projectv10\\resources\\highscores.txt");
    public static final Color  BUTTON_COLOR       = new Color(204, 204, 204);
    public static final int    MAX_SCORE          = 100;
    public static final int    MAX_TRYS           = 6;
    public static final int    POINTS_TO_DEDUCT   = 10;
    public static final int    MAX_COLOR_TRIES    = 5;
    public static int          HANGMAN_GAME_SCORE = 100;
    public static int          COLOR_GAME_SCORE   = 0;
    
    public static final String WORD_LIST [] = {"ABSTRACT","CEMETERY","NURSE",
                                               "PHARMACY","CLIMBING"};
    
    public static final String WIN_LIST [] = {"WIN!", "Nice Save", "He Lives...",
                                              "Ur so Pro"};
    
    public static final String LOSE_LIST [] = {"How Could You Do This?",
                                               "Jerk!", "Ohh Come On.",
                                               "LOSER","Try Harder",
                                               "Lives Are At Stake Here!",
                                               "You MONSTER!" };
    public static final String COLOR_LIST[] = {"Red", "Yellow", "Green", "Blue", "Purple"};
    public static final String COLOR_LIST_RGB[] = {"java.awt.Color[r=255,g=0,b=0]"/*Red*/,
                                               "java.awt.Color[r=255,g=255,b=0]"/*Yellow*/,
                                               "java.awt.Color[r=0,g=255,b=0]"/*Green*/, 
                                               "java.awt.Color[r=0,g=0,b=255]"/*Blue*/,
                                               "java.awt.Color[r=255,g=0,b=255]"/*Purple AKA Magenta*/};

    public static HighScoreEntry [] HIGH_SCORES = new HighScoreEntry[5];
}
