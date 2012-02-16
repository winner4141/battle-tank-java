/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battletank.GUI;

import Effects.Explosion;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author Lijun
 */
public class Test extends JFrame {

    static int id = 0;
    static JFrame f = null;
    static Explosion ex = null;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (ex != null) {
            ex.paint(g);
        }
    }

    public static void main(String[] args) {

        f = new Test();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
        f.setSize(480, 480);

        f.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    id++;
                    ex = new Explosion(id);
                    ex = new Explosion(id);
                    f.getContentPane().add(ex);
                    ex.Explode(f, 100, 100);
                }
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });

    }
}
