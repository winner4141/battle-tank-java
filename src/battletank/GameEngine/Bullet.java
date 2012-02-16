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
    
    public Bullet(int direction, int pos, int posy) {
        this.direction = direction;
    }
    
    /**
     * Pohyb striel
     * @param ppm pixel per move
     * @return 
     */
    public int move(int ppm){
        return BezZasahu;
    }
}
