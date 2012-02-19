/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battletank.GameEngine;

import static Global.Constants.*;

/**
 *
 * @author Lijun
 */
public class Bullet {

    public int direction = UP;
    public int posx = 0;
    public int posy = 0;

    public Bullet(int direction, int posx, int posy) {
        this.direction = direction;
        this.posx = posx;
        this.posy = posy;
    }

    /**
     * Pohyb striel
     * @param ppm pixel per move
     * @return 
     */
    public int move(int ppm) {
        int dx = 0;
        int dy = 0;
        if (direction == UP) {
            dy = -ppm;
        } else if (direction == DOWN) {
            dy = ppm;
        } else if (direction == LEFT) {
            dx = -ppm;
        } else {
            dx = ppm;
        }

        posx += dx;
        posy += dy;
        return DetectCollision();
    }

    private int DetectCollision() {
        if (game.map.isFreeAbs(posx, posy)) {
            if (game.tank1.isCollision(posx, posy)
                    || game.tank2.isCollision(posx, posy)) {
                return TankCollision;
            } else {
                return NonCollision;
            }
        } else {
            return BarrierCollision;
        }
    }

    @Override
    public String toString() {
        return "bullet(" + this.posx + "," + this.posy + ")";
    }
}
