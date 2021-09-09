package com.maxrenner;

import java.awt.*;

public class Block implements GameObject {
    private int width,height,x,y;

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.CYAN);
        g.fillRect(x,y,width,height);
        g.setColor(Color.darkGray);
        g.drawRect(x,y,width,height);
    }

    @Override
    public int getWidth() {return width;}

    @Override
    public int getHeight() {return height;}

    @Override
    public void setWidth(int width) {this.width = width;}

    @Override
    public void setHeight(int height) {this.height = height;}

    @Override
    public void setX(int x) {this.x = x;}

    @Override
    public void setY(int y) {this.y = y;}

    public int getX(){ return x; }
    public int getY(){ return y; }
}
