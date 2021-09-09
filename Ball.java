package com.maxrenner;

import java.awt.*;

public class Ball implements GameObject {
    private int x,y,width,height,vx,vy;

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.yellow);
        g.fillOval(x,y,width,height);
    }

    @Override
    public int getWidth(){return width;}
    @Override
    public int getHeight(){return height;}

    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}
    public void setVx(int vx){this.vx=vx;}
    public void setVy(int vy){this.vy=vy;}

    public void move(){
        x += vx;
        y += vy;
    }
    public void checkCollision(int width, int height, int slideX, int slideY, int slideW, int slideH, Block[] blocks){
        if(x < 0 || x > width-getWidth())
            vx = -vx;
        if(y < 0)
            vy = -vy;
        if(y > height-getHeight())
            System.exit(0);
        if((slideX <= x && slideX+slideW >= x) && (slideY-slideH == y || slideY == y))
            vy = -vy;
        if((slideX == x || slideX+slideW == x) && (slideY <= y && slideY >= y))
            vy = -vy;
        for (Block block : blocks) {
            if (block.getX() <= x && x >= block.getX() + block.getWidth())
                if (block.getY() == y || block.getY() + block.getHeight() == y)
                    vy = -vy;
            if (block.getX() == x || block.getX() + block.getWidth() == x)
                if (block.getY() - block.getHeight() >= y && block.getY() <= y)
                    vx = -vx;
        }
    }
    @Override
    public void setWidth(int width){this.width=width;}
    @Override
    public void setHeight(int height){this.height=height;}
}
