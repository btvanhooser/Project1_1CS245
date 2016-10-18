/***************************************************************
* file: HighScoreEntry.java
* @author: Melanie Giusti
* 
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified:  
* purpose:            A class for creating highscore entry objects
*                     and provides a comparator that compares by score
****************************************************************/
package cs245_projectv10;

import java.util.Comparator;

public class HighScoreEntry implements Comparable <HighScoreEntry>{
    
    private String name;
    private int    score;
    
    HighScoreEntry(String name, int score){
        this.name   = name;
        this.score = score;
    }
    
    @Override
    /*Descending order comparator by value (score)*/
    public int compareTo(HighScoreEntry h) {
        Integer s1 = this.score;
        Integer s2 = h.score;
        
        return s2.compareTo(s1);
    }
    
    /*Getter/setter methods*/
    public String getName(){return this.name;}
    public void   setName(String name){this.name = name;}
    public int    getScore(){return this.score;}
    public void   setScore(int score){this.score = score;}
}
