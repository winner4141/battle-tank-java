/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battletank.GUI;

import Global.GraphicFunctions;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    private int height = 480;
    private int width = 480;
    private int mapHeight = 96;
    private int mapWidth = 96;

    public JMapArea() {
    }

    JMapArea(GridLayout gridLayout, boolean doubleBuffered) {
        super(gridLayout, doubleBuffered);
        try {
            this.map = JMapArea.ReadMap();
        } catch (Exception ex) {
        }
    }

    public static boolean[][] ReadMap() throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader(new File(MapFile)));
        String s = reader.readLine();
        String[] ss = s.split(" ");
        if (ss.length != 2) {
            throw new Exception("Invalid map file");
        }
        int x = Integer.valueOf(ss[0]).intValue();
        int y = Integer.valueOf(ss[1]).intValue();
        boolean[][] m = new boolean[x][y];
        int c = -1;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                c = reader.read();
                if (c == '0') {
                    m[i][j] = false;
                } else if (c == '1') {
                    m[i][j] = true;
                } else {
                    throw new Exception("Invalid map file");
                }
            }
            reader.readLine();
        }
        return m;
    }

    @Override
    public void paint(Graphics g) {
        if (map == null) {
            return;
        }
        BufferedImage mapImg0 = null;
        BufferedImage mapImg1 = null;

        try {
            mapImg0 = ImageIO.read(new File(MapImage0));
            mapImg1 = ImageIO.read(new File(MapImage1));
        } catch (IOException ex) {
            return;
        }
        mapImg0 = GraphicFunctions.Rescale(mapImg0, mapWidth, mapHeight);
        mapImg1 = GraphicFunctions.Rescale(mapImg1, mapWidth, mapHeight);

        int h = getHeight();
        int w = getWidth();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j]) {
                    g.drawImage(mapImg1, j * 100, i * 100, null);
                } else {
                    g.drawImage(mapImg0, j * 100, i * 100, null);
                }
            }
        }
    }
}
