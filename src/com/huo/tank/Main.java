package com.huo.tank;

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
                Thread.sleep(50);
                window.repaint();
            } catch (InterruptedException e) {
                System.exit(-1);
            }
        }
    }
}
