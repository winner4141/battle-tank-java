/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Effects;

import Interfaces.ICallBack;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import static Global.Constants.*;

/**
 *
 * @author Lijun
 */
public class Explosion extends JPanel {
/** Jednotlive framy na animaciu vybuchu */
    Image[] imgs = new Image[25];
    private int index = 0;
    private Timer t = null;
    /** Callback funkcia po ukonceni animacie */
    private ICallBack callback = null;
    private int imgSize = 64;
    private int posx = 0;
    private int posy = 0;
    private JFrame parent = null;
    public int id = 0;
    
    public Explosion(int id) {
        this.id = id;
        Init();
    }

    public void postExec(){
        if (this.callback == null) {
           return;
        }
        this.callback.postExec();
    }

    public void setCallBack(ICallBack callBack) {
        this.callback = callBack;
    }

    private void Expl() {
        t = new Timer(50, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index == 24) {
                    t.stop();
                    postExec();
                } else {
                    index++;
                }
                parent.repaint();
            }
        });
        t.start();
    }
    
    public void Explode(final JFrame window, int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
        this.parent = window;
        
        Explode();
    }

    public void Explode() {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                Expl();
            }
        });
        th.start();
        SoundEffect.PlayExplosion();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imgs[index], posx, posy, imgs[index].getWidth(this), imgs[index].getHeight(this), this);
    }

    private void Init() {
        File imgfile = new File(explosionFile);
        index=0;
        BufferedImage im = null;

        try {
            im = ImageIO.read(imgfile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int index_ = 5 * i + j;
                imgs[index_] = im.getSubimage(j * 64, i * 64, 64, 64);
            }
        }
    }
}
