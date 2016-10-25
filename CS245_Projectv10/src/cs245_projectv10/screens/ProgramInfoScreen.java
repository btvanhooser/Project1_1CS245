/***************************************************************
* file: EndScreen.java
* @author: Melanie Giusti
* class: CS 245.01 – Programming Graphical User Interfaces
*
* date last modified:
* purpose:            Displays program information when F1 is
*                     pressed. Info includes team member names,
*                     IDs, project name and school term
****************************************************************/
package cs245_projectv10.screens;

import cs245_projectv10.Globals;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Action;
import static javax.swing.SwingConstants.CENTER;


public class ProgramInfoScreen extends JFrame {
    
    private LinkedList<JLabel> nameLabels;
    private JLabel             projectLabel;
    private JPanel             mainPanel;
    
    public ProgramInfoScreen(){
        setFrameAttributes();
        addComponentsToFrame();
        createKeyBinding();
    }
    
    private void setFrameAttributes(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,300);
        setLocationRelativeTo(null);
        setVisible(false);
        setOpacity(1);
        getContentPane().setBackground(Color.WHITE);
    }
    
    private void addComponentsToFrame() {
        nameLabels      = new LinkedList<>();
        projectLabel    = new JLabel("--- CS 245 Project: Fall 2016 ---",CENTER);
        mainPanel       = new JPanel(new GridLayout(0,1));
        
        mainPanel.add(projectLabel);
        
        for (int ii = 0; ii < Globals.TEAM_MEMBERS.length; ++ii) {
            nameLabels.add(new JLabel(Globals.TEAM_MEMBERS[ii],CENTER));
            nameLabels.get(ii).setText(Globals.TEAM_MEMBERS[ii]);
            mainPanel.add(nameLabels.get(ii));
        }
        
        mainPanel.setBackground(Color.WHITE);
        add(new JLabel(),BorderLayout.NORTH);
        add(mainPanel,BorderLayout.CENTER);
    }
    
    public void createKeyBinding(){
        Action EscAction = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };
        getRootPane().getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),"Esc");
        getRootPane().getActionMap().put("Esc",EscAction);
    }
}