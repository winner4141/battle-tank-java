/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battletank.GameEngine;

import java.util.ArrayList;
import java.awt.Point;
import java.util.Random;
import static Global.Constants.*;

/**
 *
 * @author Lijun
 */
public class Tank {

    /** Position on window */
    public Point absPos = null;
    /** Direction */
    public int direction = NONE;
    public ArrayList<Bullet> bullets = null;
    public int tankId = 0;
    public int xcoord = 0;
    public int ycoord = 0;

    /** Pri vytvoreni tanku musi byt niekde umiestneni, preto zakazem prazdny konstruktor */
    private Tank() {
    }

    public Tank(int x, int y, int tankID) {
        this.tankId = tankID;
        this.xcoord = x;
        this.ycoord = y;
        bullets = new ArrayList<Bullet>();
    }

    /** Prepocitat pociatocnu absolutnu poziciu na mape na zaklade suradnice */
    public void CalcAbsolutePosition() {
        int posx = ycoord * mapWidth + (mapWidth - tankWidth) / 2;
        int posy = xcoord * mapHeight + (mapHeight - tankHeight) / 2;
        absPos = new Point(posx, posy);
    }

    /** Vygenerovanie noveho tanka do volnej pozicie */
    public static Tank GenerateNewTank(int tankID) {
        boolean m[][] = game.map.getMap();
        Random rnd = new Random();
        int y = rnd.nextInt(m.length);
        int x = rnd.nextInt(m[0].length);

        while (!game.map.isFree(x, y)) {
            y = rnd.nextInt(m.length);
            x = rnd.nextInt(m[0].length);
        }

        return new Tank(y, x, tankID);
    }

    /** Pohyb o ppm pixelov */
    public void Move(int ppm) {
        if (absPos == null) {
            return;
        }

        if (direction == NONE) {
            //System.out.println("(" + absPos.x + "," + absPos.y + ") [" + game.map.getX(absPos.x) + "," + game.map.getY(absPos.y) + "]");
            return;
        }

        int nposx = absPos.x;
        int nposy = absPos.y;

        if (direction == UP) {
            nposy = absPos.y - ppm;
        } else if (direction == DOWN) {
            nposy = absPos.y + ppm;
        } else if (direction == LEFT) {
            nposx = absPos.x - ppm;
        } else if (direction == RIGHT) {
            nposx = absPos.x + ppm;
        }

        if (isFree(nposx, nposy)) {
            absPos.x = nposx;
            absPos.y = nposy;
            game.repaintTank(this);
        } else {
            //System.out.println("tank not moved!");
        }

        //System.out.println("(" + absPos.x + "," + absPos.y + ") [" + game.map.getX(absPos.x) + "," + game.map.getY(absPos.y) + "] smer" + direction);

    }
    /** Zisti, ci je volna pozicia s absolutnou suradnicou [x,y] v okne */
    private boolean isFree(int px, int py) {
        boolean b = game.map.isFreeAbs(px, py);
        b &= game.map.isFreeAbs(px, py + tankHeight);
        b &= game.map.isFreeAbs(px + tankWidth, py);
        b &= game.map.isFreeAbs(px + tankWidth, py + tankHeight);
        return b;
    }

    /** Strelba tanka, vytvori novy nabol do zoznam nabojov na zaklade otocenia tanka */
    public void Shot() {
        int x = 0;
        int y = 0;
        switch (direction) {
            case UP: {
                y = absPos.y - 5;
                x = absPos.x + (tankWidth / 2);
                break;
            }
            case DOWN: {
                y = absPos.y + tankHeight + 5;
                x = absPos.x + (tankWidth / 2);
                break;
            }
            case LEFT: {
                x = absPos.x - 5;
                y = absPos.y + (tankHeight / 2);
                break;
            }
            case RIGHT: {
                x = absPos.x + tankWidth + 5;
                y = absPos.y + (tankHeight / 2);
                break;
            }
        }
        bullets.add(new Bullet(direction, x, y));
    }
    /** Zisti ci nejaky bod s tankom koliduje */
    boolean isCollision(int x, int y) {
        return (x >= absPos.x && y >= absPos.y && (x - absPos.x) <= tankWidth && (y - absPos.y) <= tankHeight);
    }
}
