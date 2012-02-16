/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battletank.GameEngine;

import Effects.SoundEffect;

/**
 *
 * @author Lijun
 */


public class Game {
    private final int UP = 0;
    private final int DOWN = 1;
    private final int LEFT = 2;
    private final int RIGHT = 3;
    
    public boolean[][] map = {{false, true},{true, false}};
    
    public Tank tank1 = new Tank();
    public Tank tank2 = new Tank();
    
    private boolean t1Move = false;
    private int t1Direction = UP;
    
    private boolean t2Move = false;
    private int t2Direction = UP;
    
    public Game(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                SoundEffect.PlayBackgroundSound();
            }
        }).start();
    }
}
