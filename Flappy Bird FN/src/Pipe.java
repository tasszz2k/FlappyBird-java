
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
public class Pipe {

    private String pipeSrc = "img\\pipe140.png";
    private int xPipe;//x: [-270 -> -80] |--> [60->-250]
    private int yPipe;
    private final int MIN_Y = -270;
    private final int MAX_Y = -80;
    private final int slit = 135;

    public Pipe() {
        xPipe = 290;
        yPipe = (int) Math.floor(Math.random() * (MAX_Y - MIN_Y) + 1) + MIN_Y;
    }

    public void draw(Graphics g) {
        try {
            BufferedImage in = ImageIO.read(new File(pipeSrc));
            BufferedImage newImage = new BufferedImage(
                    in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = newImage.createGraphics();
            g.drawImage(in, xPipe, yPipe, null);
//            g.dispose();
        } catch (IOException ex) {
            Logger.getLogger(Pipe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update() {
        if (xPipe <= -52) {
            xPipe = 290;
            yPipe = (int) Math.floor(Math.random() * (320 - 100) - 320);
        }
        xPipe -= 2;
    }

    public int getxPipe() {
        return xPipe;
    }

    public int getyPipe() {
        return yPipe + 330 + slit;
    }

    public int getSlit() {
        return slit;
    }

}
