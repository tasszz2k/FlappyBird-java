
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
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
public class Bird {

    private String bird1Src = "img\\bird_normal.png";
    private String bird2Src = "img\\bird_up.png";
    private String bird3Src = "img\\bird_down.png";
    private String[] birds = {bird1Src, bird2Src, bird3Src};
    private int birdFrame = 0;
    private int speed = 0;
    private int xBird = 20;
    private int yBird = 200;
    private boolean alive;
    private float accelerationDown = 0;
    Boolean isclicked = false;

    public Bird() {
        birdFrame = 0;
        speed = 0;
        xBird = 20;
        yBird = 200;
        accelerationDown = 0;
        isclicked = false;
        alive = true;
    }

    public void draw(Graphics g) {
        try {
            BufferedImage in = ImageIO.read(new File(birds[birdFrame]));
            BufferedImage newImage = new BufferedImage(
                    in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = newImage.createGraphics();
            g.drawImage(in, xBird, yBird, null);
//            g.dispose();

        } catch (IOException ex) {
            Logger.getLogger(Bird.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update() {
        updateFrameOfBird();
        fall();
        fly();
    }

    public void fall() {
        accelerationDown += 0.3;
        yBird += accelerationDown;
    }

    public void updateFrameOfBird() {
        if (speed >= 30) {
            speed = 0;
        }
        if (speed % 10 == 0) {
            birdFrame++;
        }
        if (birdFrame >= 3) {
            birdFrame = 0;
        }
        speed += 2;
    }

    public void fly() {
        if (isclicked) {
            isclicked = false;
            if (yBird <= 22) {
                accelerationDown = 0;
                return;
            }
            accelerationDown = -4;

        }
    }

    public boolean isAlive(Pipe pipe) {
        if (yBird >= 395) {
            alive = false;
        } else if ((xBird + 32 >= pipe.getxPipe())
                && (xBird + 32 <= (pipe.getxPipe() + 80))
                && ((yBird + 22 >= pipe.getyPipe())
                || (yBird + 22 <= pipe.getyPipe() - pipe.getSlit() + 20))) {
            alive = false;
        } else {
            alive = true;
        }
        return alive;
    }

    public void setIsclicked(Boolean isclicked) {
        this.isclicked = isclicked;
    }

    public int getxBird() {
        return xBird;
    }

    public int getyBird() {
        return yBird;
    }

}
