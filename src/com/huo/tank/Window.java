package com.huo.tank;

import com.huo.tank.mgr.PropertyMgr;
import com.huo.tank.model.Dir;
import com.huo.tank.model.Explode;
import com.huo.tank.model.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * 游戏的窗口
 *
 * @author huo
 * @date 2019-5-26 14:11:08
 */
public class Window extends Frame {
    public static final int GAME_WIDTH = 1280;
    public static final int GAME_HEIGHT = 720;
    private static Tank tank;
    private static final Random RANDOM = new Random();
    private static ArrayList<Tank> enemies;
    private static ArrayList<Explode> explodes = new ArrayList<>();

    @Override
    public void paint(Graphics g) {
        // 遍历爆炸
        Iterator<Explode> iterExplode = explodes.iterator();
        while (iterExplode.hasNext()) {
            Explode next = iterExplode.next();
            if (!next.isLiving()) {
                iterExplode.remove();
            } else {
                next.paint(g);
            }
        }

        // 左上角显示内存信息
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量" + tank.getBullets().size(), 10, 40);
        g.drawString("敌人坦克的数量" + enemies.size(), 10, 60);
        g.drawString("爆炸的数量" + explodes.size(), 10, 80);

        // 遍历画出敌人坦克
        for (Iterator<Tank> itr = enemies.iterator(); itr.hasNext(); ) {
            Tank tank = itr.next();
            if (!tank.isLiving()) {
                itr.remove();
            }
            tank.paint(g);
            if (RANDOM.nextInt(100) > 90) {
                Dir[] values = Dir.values();
                tank.settDir(values[RANDOM.nextInt(4)]);
                tank.setMoving(true);
                tank.move();
                tank.fire();
            }
            tank.setMoving(false);
        }

        // 主站坦克是不是还活着
        if (!tank.isLiving()) {
            System.err.println("游戏结束");
            System.exit(-1);
        }
        tank.paint(g);
    }

    Window() {
        // 从配置文件拿敌人的数量
        int initEnemies = Integer.parseInt(PropertyMgr.get("enemies"));

        enemies = new ArrayList<>();
        for (int i = 0; i < initEnemies; i++) {
            enemies.add(new Tank(200 + i * 100, 200, false, 10));
        }
        tank = new Tank(50, 50, true, 10);
        setTitle("重学Java");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(new KeyAdapter() {
            /**
             * 每次只能进一个循环，然后就break了 需要在这里记录按键的状态
             * 按键松开 需要将按键状态恢复
             * 这四个属性来记录按键的状态，坦克根据这里的状态来判断方向。
             */
            boolean dU;
            boolean dD;
            boolean dL;
            boolean dR;

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_CONTROL:
                        System.out.println("fire!");
                        tank.fire();
                        break;
                    case KeyEvent.VK_UP:
                        dU = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        dD = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        dL = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        dR = true;
                        break;
                    default:
                        break;
                }
                tank.setMoving(true);
                setMyTankDir();
            }


            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        dU = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        dD = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        dL = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        dR = false;
                        break;
                    default:
                        break;
                }
                setMyTankDir();
            }

            private void setMyTankDir() {
                if (!dR && !dL && !dU && !dD) {
                    tank.setMoving(false);
                } else {
                    tank.setMoving(true);
                    if (dD) {
                        tank.settDir(Dir.DOWN);
                        return;
                    }
                    if (dU) {
                        tank.settDir(Dir.UP);
                        return;
                    }
                    if (dL) {
                        tank.settDir(Dir.LEFT);
                        return;
                    }
                    if (dR) {
                        tank.settDir(Dir.RIGHT);
                    }
                }

            }
        });
    }

    private Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public static ArrayList<Tank> getEnemies() {
        return enemies;
    }

    public static ArrayList<Explode> getExplodes() {
        return explodes;
    }

    public static Tank getTank() {
        return tank;
    }
}
