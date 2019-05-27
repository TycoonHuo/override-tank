package test;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 测试加载图片
 * @author huoyun
 * @date 2019/5/26-15:22
 */
public class ImageTest {
    @Test
    void test() throws IOException {
//        BufferedImage read = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("img/坦克/我方坦克/1Player/1/m1-1-1.png"));
//        BufferedImage read1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("img/坦克/敌方坦克/1Player/1/m1-1-1.png"));
        BufferedImage read = ImageIO.read(new File("img/坦克/我方坦克/1Player/1/m1-1-1.png"));
        System.out.println(read);
    }
}
