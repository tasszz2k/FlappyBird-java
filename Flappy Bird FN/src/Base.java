
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TASS
 */
public class Base {

    private String baseSrc = "img\\base.png";
    private int xBase = 0;

    public Base() {
        xBase = 0;
    }

    public void draw(Graphics g) {
        try {
            BufferedImage in = ImageIO.read(new File(baseSrc));
            BufferedImage newImage = new BufferedImage(
                    in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = newImage.createGraphics();
            g.drawImage(in, xBase, 420, null);
            g.drawImage(in, xBase + 336, 420, null);
//            g.dispose();

        } catch (IOException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update() {
        xBase -= 2;
        if (xBase <= -336) {
            xBase = 0;
        }
    }
}
