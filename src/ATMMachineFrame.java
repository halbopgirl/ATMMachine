//File: ATMMachineFrame.java
//Author: Haleigh Jayde Doetschman
//Date: Feb 2, 2018
//Purpose: Creates GUI Frames derived from JFrame and includes methods to modify
//these frames

import javax.swing.JFrame;

public class ATMMachineFrame extends JFrame {

    static final int WIDTH = 800, HEIGHT = 200;

    //constructors for frame of interface
    public ATMMachineFrame() {
        super("ATM Machine");
        setFrame(WIDTH, HEIGHT);
    }

    public ATMMachineFrame(String frameHeader) {
        super(frameHeader);
        setFrame(WIDTH, HEIGHT);
    }

    public ATMMachineFrame(String frameHeader, int width, int height) {
        super(frameHeader);
        setFrame(width, height);
    }

    //relevant methods 
    public void display() {
        setVisible(true);
    }

    private void setFrame(int width, int height) {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
