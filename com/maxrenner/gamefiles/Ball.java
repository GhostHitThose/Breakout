package com.maxrenner.gamefiles;

import com.maxrenner.FrameVariables;

import java.awt.*;
import java.util.ArrayList;

public class Ball extends GameObject implements BallVariables, FrameVariables {

    private int vx = (int)((Math.pow(-1,(int)(Math.random()*2)))*2), vy = 1;

    Ball(){
        this.width = W;
        this.height = H;
        this.x = X;
        this.y = Y;
    }

    public void move(){
        this.x += this.vx;
        this.y -= this.vy;
    }

    public void checkCollision(int sliderX, int sliderY, int sliderW, int sliderH, ArrayList<Block[]> blocks){
        if(x <= 0 || x >= WIDTH-width){
            vx = -vx;
        }
        if(y <= 0){
            vy = -vy;
        }
        if(y >= HEIGHT-height){
            System.out.println("Game over");
            Game.gameState = GameState.ENDING;
        }
        if(sliderY-sliderH == y && (x <= sliderX+sliderW && x >= sliderX)) {
            vy = -vy;
        }

        for (Block[] b : blocks) {
            for (Block block : b) {
                if (block.isDrawn) {
                    if(y == 195){
                        System.out.println("TTT");
                    }
                    if ((((y+height <= block.y+5) && (y+height >= block.y-5)) || ((y <= block.y+block.height+5))&&(y >= block.y+block.height-5)) && (x >= block.x-10 && x + width <= block.x + width+10)) {
                        System.out.println(y);
                        vy = -vy;
                        block.isDestroyed = true;
                    }
                    if ((((x + width <= block.x+5)&&(x + width >= block.x-5)) || ((x <= block.x + block.width+5)&&(x >= block.x + block.width-5))) && (y+height <= block.y+block.height && y >= block.y)) {
                        vx = -vx;
                        block.isDestroyed = true;
                    }
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.yellow);
        g.fillOval(x,y,width,height);
    }
}
