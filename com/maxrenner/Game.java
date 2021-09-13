package com.maxrenner;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable,FrameVariables{

    private Ball ball;
    private Slider slider;
    public static GameState gameState;
    private boolean moveSlider = false;

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(FrameVariables.WIDTH,FrameVariables.HEIGHT);
    }

    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.darkGray);
        g.fillRect(0,0, FrameVariables.WIDTH, FrameVariables.HEIGHT);

        ball.draw(g);
        slider.draw(g);
    }

    public void run(){
        gameState = GameState.STARTING;

        // init
        ball = new Ball();
        slider = new Slider();

        gameState = GameState.RUNNING;

        long last = System.nanoTime();
        double delta = 0, ns = 1000000000/60.0;

        while(gameState == GameState.RUNNING){
            long now = System.nanoTime();
            delta += (now-last)/ns;
            last = now;

            while(delta >= 1){
                update();
                delta--;
            }

            render();
        }

        System.exit(0);
    }

    public void changeDirection(char direction){
        slider.changeDirection(direction);
        moveSlider = true;
    }

    public void keyReleased(){
        moveSlider = false;
    }

    public void update(){
        ball.move();
        ball.checkCollision(slider.getX(), slider.getY(), slider.getW(), slider.getH());
        if(moveSlider)
            slider.move();
    }

    public void render(){ repaint(); }
}
