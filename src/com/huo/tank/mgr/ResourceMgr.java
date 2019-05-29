package com.huo.tank.mgr;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author huoyun
 * @date 2019/5/26-15:26
 */
public class ResourceMgr {
    public static BufferedImage goodTank1L;
    public static BufferedImage goodTank2L;
    public static BufferedImage goodTank1U;
    public static BufferedImage goodTank2U;
    public static BufferedImage goodTank1R;
    public static BufferedImage goodTank2R;
    public static BufferedImage goodTank1D;
    public static BufferedImage goodTank2D;
    public static BufferedImage badTank1L;
    public static BufferedImage badTank2L;
    public static BufferedImage badTank1U;
    public static BufferedImage badTank2U;
    public static BufferedImage badTank1R;
    public static BufferedImage badTank2R;
    public static BufferedImage badTank1D;
    public static BufferedImage badTank2D;

    /**
     * 爆炸的9张图片
     */
    public static BufferedImage[] explodes = new BufferedImage[9];

    static {
        goodTank1L = loadImg("img/坦克/我方坦克/1Player/1/m1-1-1.png");
        goodTank2L = loadImg("img/坦克/我方坦克/1Player/1/m1-1-2.png");
        goodTank1U = loadImg("img/坦克/我方坦克/1Player/1/m1-2-1.png");
        goodTank2U = loadImg("img/坦克/我方坦克/1Player/1/m1-2-2.png");
        goodTank1R = loadImg("img/坦克/我方坦克/1Player/1/m1-3-1.png");
        goodTank2R = loadImg("img/坦克/我方坦克/1Player/1/m1-3-2.png");
        goodTank1D = loadImg("img/坦克/我方坦克/1Player/1/m1-4-1.png");
        goodTank2D = loadImg("img/坦克/我方坦克/1Player/1/m1-4-2.png");

        badTank1L = loadImg("img/坦克/敌方坦克/普通坦克/1/1-1-1.png");
        badTank2L = loadImg("img/坦克/敌方坦克/普通坦克/1/1-1-2.png");
        badTank1U = loadImg("img/坦克/敌方坦克/普通坦克/1/1-2-1.png");
        badTank2U = loadImg("img/坦克/敌方坦克/普通坦克/1/1-2-2.png");
        badTank1R = loadImg("img/坦克/敌方坦克/普通坦克/1/1-3-1.png");
        badTank2R = loadImg("img/坦克/敌方坦克/普通坦克/1/1-3-2.png");
        badTank1D = loadImg("img/坦克/敌方坦克/普通坦克/1/1-4-1.png");
        badTank2D = loadImg("img/坦克/敌方坦克/普通坦克/1/1-4-2.png");

        for (int i = 0; i < explodes.length; i++) {
            explodes[i] = loadImg("img/待使用素材/动画截图/爆炸/坦克爆炸/敌方坦克/11/Image 5" + (i + 1) + ".png");
        }
    }

    private ResourceMgr() {
    }

    private static BufferedImage loadImg(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
