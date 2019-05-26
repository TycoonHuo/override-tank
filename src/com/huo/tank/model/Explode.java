package com.huo.tank.model;

import com.huo.tank.mgr.ResourceMgr;

import java.awt.*;

/**
 * 爆炸素材一共有9张图片 9次循环给画出来
 *
 * @author huoyun
 * @date 2019/5/26-18:23
 */
public class Explode extends BaseObject {

    private boolean living = true;
    private int step;

    public Explode(int positX, int positY) {
        this.positX = positX;
        this.positY = positY;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], positX, positY, null);
        if (step == ResourceMgr.explodes.length) {
            living = false;
        }
    }

    public boolean isLiving() {
        return living;
    }
}
