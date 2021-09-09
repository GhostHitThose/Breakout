package com.maxrenner;

import java.awt.*;

public class Slider implements GameObject {
    private int x,y,width,height,vx;

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.GREEN);
        g.fillRect(x,y,width,height);
    }

    public int getX(){return x;}
    public int getY(){return y;}

    @Override
    public int getWidth(){return width;}
    @Override
    public int getHeight(){return height;}
    @Override
    public void setX(int x){this.x=x;}
    @Override
    public void setY(int y){this.y=y;}
    @Override
    public void setWidth(int width){this.width=width;}
    @Override
    public void setHeight(int height){this.height=height;}

    public void setVx(int vx){this.vx = vx;}
    public void flipVx(){vx = -vx;}

    public void move(){
        x += vx;
    }

    public void clamp(int wWidth){
        if(x <= 0){
            x = 0;
        }
        else if(x >= wWidth-getWidth()){
            x = wWidth-getWidth();
        }
    }
}
