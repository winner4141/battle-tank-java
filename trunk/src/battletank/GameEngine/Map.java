/*
 * Trieda Map v sebe nesie informacie o mape a poskytuje zakladne funkcie
 * na prepocet medzi suradnicami a poziciami na mape.
 */
package battletank.GameEngine;

import static Global.Constants.*;
import Interfaces.IMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Lijun
 */
public class Map implements IMap {

     public boolean[][] map = null;

     public Map() {
          try {
               ReadMap(MapFile);
          } catch (Exception ex) {
          }
     }

     private Map(String inputfile) {
          try {
               ReadMap(inputfile);
          } catch (Exception ex) {
          }
     }

     /**
      * Nacitanie mapy zo suboru "map/map.dat"
      */
     
     @Override
     public void ReadMap(String filename) throws Exception {
          BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
          String s = reader.readLine();
          String[] ss = s.split(" ");
          if (ss.length != 2) {
               throw new Exception("Invalid map file");
          }
          int x = Integer.valueOf(ss[0]).intValue();
          int y = Integer.valueOf(ss[1]).intValue();

          if (x == 0 || y == 0) {
               throw new Exception("Invalid map structure");
          }

          map = new boolean[x][y];

          int c = -1;
          for (int i = 0; i < x; i++) {
               for (int j = 0; j < y; j++) {
                    c = reader.read();
                    if (c == '0') {
                         map[i][j] = false;
                    } else if (c == '1') {
                         map[i][j] = true;
                    } else {
                         throw new Exception("Invalid map file");
                    }
               }
               reader.readLine();
          }
     }

     public Map(boolean[][] map) {
          this.map = map;
     }

     public int getX(int absX) {
          return (absX + mapWidth - 1) / mapWidth - 1;
     }

     public int getY(int absY) {
          return (absY + mapHeight - 1) / mapHeight - 1;
     }

     /**
      * Zisti, ci je volne policko na suradnici (x,y)
      */
     public boolean isFreeAbs(int absPosX, int absPosY) {
          if (map == null) {
               return false;
          }

          int x = getX(absPosX);
          int y = getY(absPosY);

          return isFree(x, y);
     }

     /**
      * Zisti, ci je volne policko na pozici [x,y]
      */
     public boolean isFree(int x, int y) {
          if (map == null) {
               return false;
          }

          if (y < map.length && x < map[0].length && x >= 0 && y >= 0) {
               return !map[y][x];
          }

          return false;
     }

     public boolean[][] getMap() {
          return map;
     }
}
