
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TASS
 */
public class GamePanel extends JPanel implements Runnable, MouseListener {

    Background bg = new Background();
    Base base = new Base();
    Pipe pipe = new Pipe();
    Bird bird = new Bird();
    Score score = new Score();
    Thread thread = new Thread(this);
    boolean isAlive = true;
    private Clip clip, clipBackGround;

    public GamePanel() {
        initcomponents();
    }

    public void initcomponents() {
        //
        this.addMouseListener(this);
        System.out.println(">> START GAME!");
        loopGame();

    }

    public void loopGame() {
        thread.start();
    }

    public void resetGame() {
        int choice = JOptionPane.showConfirmDialog(this, "Game Over!\nDo you want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            isAlive = true;
            bg = new Background();
            base = new Base();
            pipe = new Pipe();
            bird = new Bird();
            score = new Score();
            repaint();
        } else {
            exit(0);
        }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        bg.draw(g);
        pipe.draw(g);
        score.draw(g);
        base.draw(g);
        bird.draw(g);
        if (!isAlive) {
            try {
                playHitSound();
                BufferedImage in = ImageIO.read(new File("img\\gameover.png"));
                BufferedImage newImage = new BufferedImage(
                        in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);

                Graphics2D g2d = newImage.createGraphics();
                g.drawImage(in, 44, 100, null);
                System.out.println("DIE!!!!");
                System.out.println(">> Score: " + score.getCurrentScore());
            } catch (IOException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        g.dispose();
    }

    @Override
    public void run() {
        while (isAlive) {
            bg.update();
            pipe.update();
            score.update(pipe);
            base.update();
            bird.update();
            isAlive = bird.isAlive(pipe);
            repaint();
            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!isAlive) {
                resetGame();

            }

        }
    }

    void playHitSound() {
        try {
            String filepath = "sound\\hit.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
            // NOTICE: I am only initializing and NOT declaring (no capital Clip)
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void playJumpSound() {
        try {
            String filepath = "sound\\jump.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
            // NOTICE: I am only initializing and NOT declaring (no capital Clip)
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        playJumpSound();
        bird.setIsclicked(true);
        bird.update();
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
