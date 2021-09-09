package com.maxrenner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener, Runnable {

    private boolean running = false;
    private final Ball ball;
    private final Slider slider;
    private boolean slide = false;
    private Block[] blocks = new Block[0];
    private final int BLOCK_AMOUNT = 25;

    Game(int windowW, int windowH){
        ball = new Ball();
        ball.setWidth(10);
        ball.setHeight(10);
        ball.setX(windowW/2-ball.getWidth());
        ball.setY(windowH/2-ball.getHeight());
        ball.setVx((Math.random()*20 >= 10) ? 2 : -2);
        ball.setVy(-2);

        slider = new Slider();
        slider.setWidth(50);
        slider.setHeight(10);
        slider.setX(275);
        slider.setY(490);
        slider.setVx(5);

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < BLOCK_AMOUNT; j++){
                int x = 50+j*20;
                Block block = new Block();
                block.setWidth(20);
                block.setHeight(20);
                block.setX(x);
                block.setY(50+i*20);
                blocks = addElement(blocks, block);
            }
        }
    }

    public Block[] addElement(Block[] blocks, Block block){
        Block[] newBlocks = new Block[blocks.length+1];
        for(int i = 0; i < newBlocks.length; i++){
            if(i < blocks.length){
                newBlocks[i] = blocks[i];
            } else {
                newBlocks[i] = block;
            }
        }
        return newBlocks;
    }

    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D g = (Graphics2D) graphics;
        drawFrame(g);
        ball.draw(g);
        slider.draw(g);

        for(Block block : blocks){
            block.draw(g);
        }

        g.dispose();
    }

    public void drawFrame(Graphics2D g){
        g.setColor(Color.darkGray);
        g.fillRect(0,0,getWidth(),getHeight());
    }

    @Override
    public void run() {
        running = true;

        long last = System.nanoTime();
        double delta = 0;
        final double FPS = 60.0, NS = 1000000000/FPS;

        while(running){
            long now = System.nanoTime();
            delta+=(now-last)/NS;
            last = now;

            while(delta >= 1){
                update();
                delta--;
            }

            render();
        }
    }

    public void update(){
        ball.move();
        ball.checkCollision(getWidth(), getHeight(), slider.getX(), slider.getY(), slider.getWidth(), slider.getHeight(), blocks);
        if(slide) {
            slider.move();
        }
        slider.clamp(getWidth());
    }

    public void render(){
        repaint();
    }

    public void stop(){
        running = false;
        System.out.println("Exiting");
        System.exit(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private boolean flipped = false;

    @Override
    public void keyPressed(KeyEvent e) {
        char direction = e.getKeyChar();
        if(direction == 'a'){
            slide = true;
            if(!flipped){
                slider.flipVx();
                flipped = true;
            }
        } else if(direction == 'd'){
            slide = true;
            if(flipped){
                slider.flipVx();
                flipped = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char direction = e.getKeyChar();
        if(direction == 'a'){
            slide = false;
        } else if(direction == 'd'){
            slide = false;
        }
    }
}
