package com.huo.tank.model;

import com.huo.tank.Window;
import com.huo.tank.mgr.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * tank类
 *
 * @author huoyun
 * @date 2019/5/26-14:23
 */
public class Tank extends BaseObject {
    /**
     * 默认坦克朝右(图片)
     */
    private boolean good;
    private int speed;
    private Dir tDir;
    private static Random random = new Random();
    private BufferedImage tankImg;
    private boolean moving;
    private boolean living = true;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private Rectangle tankRect = new Rectangle();

    /**
     * @param positX 横坐标位置
     * @param positY 纵坐标位置
     * @param good   好坦克还是坏的
     */
    public Tank(int positX, int positY, boolean good, int speed) {
        this.positX = positX;
        this.positY = positY;
        this.good = good;
        tankImg = good ? ResourceMgr.goodTank1U : ResourceMgr.badTank1D;
        tDir = good ? Dir.UP : Dir.DOWN;
        this.speed = speed;
    }

    public void settDir(Dir tDir) {
        this.tDir = tDir;
    }

    public void move() {
        if (!moving) {
            return;
        }
        if (good) {
            switch (tDir) {
                case DOWN:
                    tankImg = random.nextInt(10) > 5 ? ResourceMgr.goodTank1D : ResourceMgr.goodTank2D;
                    positY += speed;
                    break;
                case UP:
                    tankImg = random.nextInt(10) > 5 ? ResourceMgr.goodTank1U : ResourceMgr.goodTank2U;
                    positY -= speed;
                    break;
                case LEFT:
                    tankImg = random.nextInt(10) > 5 ? ResourceMgr.goodTank1L : ResourceMgr.goodTank2L;
                    positX -= speed;
                    break;
                case RIGHT:
                    tankImg = random.nextInt(10) > 5 ? ResourceMgr.goodTank1R : ResourceMgr.goodTank2R;
                    positX += speed;
                    break;
                default:
                    break;
            }
        } else {
            switch (tDir) {
                case DOWN:
                    tankImg = random.nextInt(10) > 5 ? ResourceMgr.badTank1D : ResourceMgr.badTank2D;
                    positY += speed;
                    break;
                case UP:
                    tankImg = random.nextInt(10) > 5 ? ResourceMgr.badTank1U : ResourceMgr.badTank2U;
                    positY -= speed;
                    break;
                case LEFT:
                    tankImg = random.nextInt(10) > 5 ? ResourceMgr.badTank1L : ResourceMgr.badTank2L;
                    positX -= speed;
                    break;
                case RIGHT:
                    tankImg = random.nextInt(10) > 5 ? ResourceMgr.badTank1R : ResourceMgr.badTank2R;
                    positX += speed;
                    break;
                default:
                    break;
            }
        }

        tankRect.x = positX;
        tankRect.y = positY;
        tankRect.width = tankImg.getWidth();
        tankRect.height = tankImg.getHeight();
    }

    @Override
    public void paint(Graphics g) {
        // 防止坦克出界
        boundCheck();

        if (living) {
            g.drawImage(tankImg, positX, positY, null);
        }

        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (bullet.isLiving()) {
                bullet.paint(g);
            } else {
                iterator.remove();
            }
        }
        move();
    }

    public void fire() {
        bullets.add(new Bullet(positX + tankImg.getWidth() / 2, positY + tankImg.getWidth() / 2, tDir, good));
    }

    private void boundCheck() {
        if (positX < 0) {
            positX = 0;
        }

        if (positY < 20) {
            positY = 20;
        }

        if (positX > Window.GAME_WIDTH - tankImg.getWidth()) {
            positX = Window.GAME_WIDTH - tankImg.getWidth();
        }

        if (positY > Window.GAME_HEIGHT - tankImg.getHeight()) {
            positY = Window.GAME_HEIGHT - tankImg.getHeight();
        }
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public boolean isLiving() {
        return living;
    }

    public void die() {
        living = false;
    }

    public boolean isGood() {
        return good;
    }

    public Rectangle getTankRect() {
        return tankRect;
    }
}
