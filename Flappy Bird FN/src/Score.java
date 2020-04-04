
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TASS
 */
public class Score {

    private int scoreFrame = 0;
    private int currentScore = 0;
    private String[] scoreSrcs = new String[10];
    private int tensDigit = 0;
    private int unitsDigit = 0;

    private Clip clip, clipBackGround;
    
    public Score() {
        scoreFrame = 0;
        currentScore = 0;
        tensDigit = 0;
        unitsDigit = 0;
        for (int i = 0; i < 10; i++) {
            scoreSrcs[i] = "img\\" + i + ".png";
        }
    }

    public void draw(Graphics g) {
        try {
            int oldScore = currentScore;
            currentScore = scoreFrame / 26;
            if(currentScore > oldScore){
                //playsound
                playGetPointSound();
            }
            tensDigit = (int) Math.floor(currentScore / 10);
            unitsDigit = currentScore % 10;
            //
            BufferedImage t = ImageIO.read(new File(scoreSrcs[tensDigit]));
            BufferedImage newImageT = new BufferedImage(
                    t.getWidth(), t.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2dt = newImageT.createGraphics();

            BufferedImage u = ImageIO.read(new File(scoreSrcs[unitsDigit]));
            BufferedImage newImageU = new BufferedImage(
                    u.getWidth(), u.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2du = newImageU.createGraphics();

            g.drawImage(t, 108, 50, null);
            g.drawImage(u, 135, 50, null);
        } catch (IOException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Pipe pipe) {
        if (pipe.getxPipe() <= 0) {
            scoreFrame++;
        }
    }

    public int getCurrentScore() {
        return currentScore;
    }
    
    void playGetPointSound() {
        try {
            String filepath = "sound\\point.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
            // NOTICE: I am only initializing and NOT declaring (no capital Clip)
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
