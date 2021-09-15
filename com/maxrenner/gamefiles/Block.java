package com.maxrenner.gamefiles;

import java.awt.*;

public class Block extends GameObject implements BlockVariables {

    public boolean isDrawn = false;
    public boolean isDestroyed = false;

    Block(){
        this.width = W;
        this.height = H;
        this.x -= 5;
        this.y -= 5;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.cyan);
        g.fillRect(x, y, width,height);
        g.setColor(Color.black);
        g.drawRect(x, y, width,height);
    }
}
