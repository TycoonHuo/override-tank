package com.huo.tank;

import com.huo.tank.mgr.ResourceMgr;

/**
 * 程序启动入口
 *
 * @author huo
 */
public class Main {
    public static void main(String[] args) {
        Window window = new Window();
        while (true) {
            try {
                Thread.sleep(25);
                window.repaint();
            } catch (InterruptedException e) {
                System.exit(-1);
            }
        }
    }
}
