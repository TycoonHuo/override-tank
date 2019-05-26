package com.huo.tank.model;

import java.awt.*;

/**
 * 先分开写，最后再抽象。一步一步来
 * 游戏物体的抽象类
 *
 * @author huo
 */
public abstract class BaseObject {
    public int positX;
    public int positY;
    public int width;
    public int height;

    public abstract void paint(Graphics g);
}
