
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
public class Background {
    
    private String bgSrc = "img\\bg.png";
    private int xBg = 0;
    
    public Background() {
        xBg = 0;
    }
    
    public void draw(Graphics g) {
        try {
            BufferedImage in = ImageIO.read(new File(bgSrc));
            BufferedImage newImage = new BufferedImage(
                    in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D g2d = newImage.createGraphics();
            g.drawImage(in, xBg, 0, null);
            g.drawImage(in, xBg + 288, 0, null);
//            g.dispose();
        } catch (IOException ex) {
            Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update() {
        xBg--;
        if (xBg <= -288) {
            xBg = 0;
        }
    }
}
