package com.maxrenner;

import java.awt.*;

public interface GameObject {
    void draw(Graphics2D g);
    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);
    void setX(int x);
    void setY(int y);
}
