package com.huo.tank.model;


import com.huo.tank.Window;
import com.huo.tank.mgr.ResourceMgr;

import java.awt.*;

/**
 * @author huoyun
 * @date 2019/5/26-16:26
 */
public class Bullet extends BaseObject {
    private boolean living = true;
    private Dir dir;
    private int speed;
    private boolean good;
    private Rectangle bulletRect = new Rectangle();
    private Window window;


    Bullet(int positX, int positY, Dir dir, boolean good, Window window) {
        this.positX = positX;
        this.positY = positY;
        this.dir = dir;
        this.good = good;
        this.window = window;
        // 可以从配置文件拿
        this.speed = 20;
    }

    private void move() {
        // 子弹飞出屏幕了
        if (positY < 0 || positX < 0 || positY > Window.GAME_HEIGHT || positX > Window.GAME_WIDTH) {
            living = false;
            return;
        }
        switch (dir) {
            case DOWN:
                positY += speed;
                break;
            case UP:
                positY -= speed;
                break;
            case LEFT:
                positX -= speed;
                break;
            case RIGHT:
                positX += speed;
                break;
            default:
                break;
        }
        bulletRect.x = positX;
        bulletRect.y = positY;
        bulletRect.height = bulletRect.width = 5;

        // 敌人打我
//        collideWith(Window.getTank());
        // 需要和每一个敌人的坦克进行碰撞检测
        for (Tank t : Window.getEnemies()) {
            collideWith(t);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.magenta);
        g.fillOval(positX, positY, 5, 5);
        move();
    }


    /**
     * 碰撞检测，和谁碰撞需要传进来
     * 这里每次都new对象，有优化空间
     */
    private void collideWith(Tank tank) {
        if (tank.isGood() == this.good) {
            return;
        }
        // 不开启队友伤害
        if (bulletRect.intersects(tank.getTankRect())) {
            // 在爆炸处new出一个爆炸
            Window.getExplodes().add(new Explode(positX - ResourceMgr.explodes[0].getWidth() / 2,
                    positY - ResourceMgr.explodes[0].getHeight() / 2));
            this.die();
            tank.die();
        }

//         开启队友伤害
//        if (bulletRect.intersects(tank.getTankRect())) {
//            // 在爆炸处new出一个爆炸
//            Window.getExplodes().add(new Explode(positX - ResourceMgr.explodes[0].getWidth() / 2,
//                    positY - ResourceMgr.explodes[0].getHeight() / 2));
//            this.die();
//            tank.die();
//        }
    }

    private void die() {
        living = false;
    }

    public boolean isLiving() {
        return living;
    }
}
