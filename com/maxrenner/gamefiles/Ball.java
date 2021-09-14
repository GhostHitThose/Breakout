package com.maxrenner.gamefiles;

import com.maxrenner.FrameVariables;

import java.awt.*;

public class Ball extends GameObject implements BallVariables, FrameVariables {

    private int vx = (int)((Math.pow(-1,(int)(Math.random()*2)))*2), vy = 2;

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

    public void checkCollision(int sliderX, int sliderY, int sliderW, int sliderH){
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
        if(sliderY-sliderH == y && (x <= sliderX+sliderW && x >= sliderX)){
            vy = -vy;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.yellow);
        g.fillOval(x,y,width,height);
    }
}
