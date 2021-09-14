package com.maxrenner.gamefiles;

import com.maxrenner.FrameVariables;

import java.awt.*;

public class Slider extends GameObject implements SliderVariables, FrameVariables {

    private int speed = 5;
    private char direction;

    Slider(){
        this.x = X;
        this.y = Y;
        this.width = W;
        this.height = H;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public int getW(){
        return width;
    }
    public int getH(){
        return height;
    }

    public void changeDirection(char direction){
        this.direction = direction;
    }

    public void move(){
        if(x <= 0){
            x = 0;
        }
        if(x >= WIDTH-width){
            x=WIDTH-width;
        }
        if(direction == 'a'){
            this.x-=speed;
        } else if(direction == 'd'){
            this.x+=speed;
        }
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.GREEN);
        g.fillRect(x,y,width,height);
    }
}
