
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TASS
 */
public class FlappyBird extends JFrame {

    GamePanel game;

    public FlappyBird() {
        game = new GamePanel();
        add(game);
        ImageIcon icon = new ImageIcon("img\\bird_normal.png");
        this.setIconImage(icon.getImage());
        this.setSize(288, 512);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);  // *** this will center this app ***
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Flappy Bird");

    }

    public static void main(String[] args) {
        new FlappyBird();
    }
}
