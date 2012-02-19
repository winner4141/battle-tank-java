/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battletank.GUI;

import battletank.GameEngine.Bullet;
import Global.GraphicFunctions;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import static Global.Constants.*;

/**
 *
 * @author Lijun
 */
public class JMapArea extends JPanel {

    boolean[][] map = null;
    int tnk1lastdir = UP;
    int tnk2lastdir = UP;
    BufferedImage bul = null;
    BufferedImage mapImg0 = null;
    BufferedImage mapImg1 = null;
    BufferedImage[] tank1 = new BufferedImage[4];
    BufferedImage[] tank2 = new BufferedImage[4];
    boolean drawT1 = false;
    boolean drawT2 = false;
    
    public JMapArea() {
    }

    JMapArea(GridLayout gridLayout, boolean doubleBuffered, Dimension size) {
        super(gridLayout, doubleBuffered);
        this.setDoubleBuffered(true);
        this.setSize(size);
        try {
            this.map = game.map.getMap();
            mapHeight = this.getHeight() / map.length;
            mapWidth = this.getWidth() / map[0].length;
            tankWidth = (int) (0.8 * mapWidth);
            tankHeight = (int) (0.8 * mapHeight);
            this.setLayout(new GridLayout(map[0].length, map.length));
            game.tank1.CalcAbsolutePosition();
            game.tank2.CalcAbsolutePosition();
            drawT1 = true;
            drawT2 = true;
            bul = ImageIO.read(new File(bulletImg));
            mapImg0 = ImageIO.read(new File(MapImage0));
            mapImg1 = ImageIO.read(new File(MapImage1));
            mapImg0 = GraphicFunctions.Rescale(mapImg0, mapWidth, mapHeight);
            mapImg1 = GraphicFunctions.Rescale(mapImg1, mapWidth, mapHeight);
            
            BufferedImage im = ImageIO.read(new File(tankImg));
            ToImageArray(im,tank1, 0);
            ToImageArray(im,tank2, 1);
        } catch (Exception ex) {
        }
    }

    @Override
    public void paint(Graphics g) {
        if (map == null) {
            return;
        }

        int h = getHeight();
        int w = getWidth();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j]) {
                    g.drawImage(mapImg1, j * mapWidth, i * mapHeight, null);
                } else {
                    g.drawImage(mapImg0, j * mapWidth, i * mapHeight, null);
                }
            }
        }

        if (drawT1) {
            if (game.tank1.direction != NONE) {
                tnk1lastdir = game.tank1.direction;
            }
            g.drawImage(tank1[tnk1lastdir], game.tank1.absPos.x, game.tank1.absPos.y, null);

        }
        if (drawT2) {
            if (game.tank2.direction != NONE) {
                tnk2lastdir = game.tank2.direction;
            }
            g.drawImage(tank2[tnk2lastdir], game.tank2.absPos.x, game.tank2.absPos.y, null);
        }
        DrawBullets(g);
    }

    private void DrawBullets(Graphics g) {
        for (Bullet b : game.tank1.bullets) {
            g.drawImage(bul, b.posx, b.posy, null);
        }
        for (Bullet b : game.tank2.bullets) {
            g.drawImage(bul, b.posx, b.posy, null);
        }
    }
    
    private void ToImageArray(BufferedImage im, BufferedImage[] tank, int x) {
        for (int i = 0; i < 4; i++) {
            BufferedImage _img = im.getSubimage(i * im.getWidth() / 4, x * im.getHeight() / 2, im.getWidth() / 4, im.getHeight() / 2);
            tank[i] = GraphicFunctions.Rescale(_img, tankWidth, tankHeight);
        }
    }
}
