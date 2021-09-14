package com.maxrenner;

import com.maxrenner.gamefiles.Game;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Application extends JFrame implements FrameVariables {
    Application(){
        setTitle(TITLE);
        Game game = new Game();
        game.getPreferredSize();
        add(game);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char direction = e.getKeyChar();
                if(direction == 'a' || direction == 'd')
                    game.changeDirection(direction);
            }

            @Override
            public void keyReleased(KeyEvent e){
                game.keyReleased();
            }
        });

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        Thread thread = new Thread(game);
        thread.start();
    }
}
