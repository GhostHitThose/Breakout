package com.maxrenner;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Breakout extends JFrame {

    private final int windowW = 600, windowH = 600;
    private final Game GAME = new Game(windowW,windowH);

    Breakout(){
        setTitle("Snake Game - Max Renner");
        setSize(windowW, windowH);
        setLocationRelativeTo(null);
        setResizable(false);


        add(GAME);
        addKeyListener(GAME);
        WindowListener listener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GAME.stop();
            }
        };
        addWindowListener(listener);

        setVisible(true);

        Thread thread = new Thread(GAME);
        thread.start();
    }

    public static void main(String[] args){
        new Breakout();
    }
}
