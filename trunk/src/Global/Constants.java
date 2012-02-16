package Global;

import battletank.GameEngine.Game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Lijun
 */
public abstract class Constants {
    public static String MapImage0 = "media/images/map_road.jpg";
    public static String MapImage1 = "media/images/map_box.jpg";
    public static String MapFile = "map/map.dat";
    public static String explosionFile = "media/images/explosion.png";
    public static String explosionSound = "media/sounds/explosion.wav";
    public static String bgSound = "media/sounds/bgsound.mid";
    public static String tankImg = "media/images/tank.gif";
    
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int NONE = 4;
    
    public static final int BezZasahu = 100;
    public static final int ZasahPrekazka = 101;
    public static final int ZasahTank = 102;
    
    Game game = null;
}
