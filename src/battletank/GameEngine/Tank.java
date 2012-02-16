/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battletank.GameEngine;

import java.util.ArrayList;
import java.awt.Point;
import static Global.Constants.*;

/**
 *
 * @author Lijun
 */
class Tank {

    /** Position on map */
    public int teamID = 0;
    /** Position on window */
    private Point absPos = new Point(0, 0);
    /** Direction */
    public int direction = NONE;
    public ArrayList<Bullet> bullets = null;

    public Tank() {
        bullets = new ArrayList<Bullet>();
    }

    public Tank(int posx, int posy) {
        bullets = new ArrayList<Bullet>();
    }

    public void Move(int ppm) {
        if (direction == NONE) {
            return;
        }
        
        if (direction == UP) {
            absPos.y = absPos.y - ppm;
        } else if (direction == DOWN) {
            absPos.y = absPos.y + ppm;
        } else if (direction == LEFT) {
            absPos.x = absPos.x - ppm;
        } else if (direction == RIGHT) {
            absPos.x = absPos.x + ppm;
        }
    }

    public void Shot() {
        throw new UnsupportedOperationException("Neimplementovany Tank.Shot");
    }
    
    /** Vrati x-ovu suradnicu mapy */
    public int getPosX(){
        throw new UnsupportedOperationException();
    }
}
