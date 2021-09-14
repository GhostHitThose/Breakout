package com.maxrenner.gamefiles;

import java.awt.*;

public abstract class GameObject {
    int x, y, width, height;
    public abstract void draw(Graphics2D g);
}
