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
    /** Cesta k obrazku volneho policka */
    public static String MapImage0 = "media/images/map_road.jpg";
    /** Cesta k obrazku prekazkoveho policka */
    public static String MapImage1 = "media/images/map_box.jpg";
    /** Subor s mapou */
    public static String MapFile = "map/map.dat";
    /** Cesta k obrazoku s keyframami fazy vybuchu */
    public static String explosionFile = "media/images/explosion.png";
    /** Cesta k zvuku vybuchu */
    public static String explosionSound = "media/sounds/explosion.wav";
    /** Cesta k zvuku na pozadi */
    public static String bgSound = "media/sounds/bgsound.mid";
    /** Cesta k obrazku tankov */
    public static String tankImg = "media/images/tank.png";
    /** Cesta k obrazku naboja */
    public static String bulletImg = "media/images/bullet.png";
    /** Cesta k zvuku striel */
    public static String shotSound = "media/sounds/shot.wav";
    
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int NONE = 4;
    
    //Sluzi ako navratova hodnota pre detekciu kolizie nabojov
    /** Bez kolizie */
    public static final int NonCollision = 100;
    /** Kolizie s prekazkou */
    public static final int BarrierCollision = 101;
    /** Kolizie s tankom protihraca */
    public static final int TankCollision = 102;
    
    public static Game game = null;
    
    //Sirka a vyska policok mapy.
    public static int mapHeight = 96;
    public static int mapWidth = 96;
    
    //Sirka a vyska tankov
    public static int tankWidth = (int) (0.8 * 96);
    public static int tankHeight = (int) (0.8 * 96);
    
    // Identifikatory tankov
    public static final int tank1ID = 100000;
    public static final int tank2ID = 100001;
    
    // Pocet pixel v jednom kroku, ktore moze tank alebo naboj spravit
    public static int pixelPerMove = 2;
}