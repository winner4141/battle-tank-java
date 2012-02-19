/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import battletank.GameEngine.Game;
import battletank.GameEngine.Map;

/**
 *
 * @author Lijun
 */
public class Test {

    public Test() {
        
    }
    public static void main(String[] args){
        Game g= new Game();
        g.map = new Map();
        boolean[][] map = g.map.getMap();
        for (int i=0;i<map.length; i++){
            for (int j=0;j<map[0].length;j++)
                if (g.map.isFree(j,i))System.out.print("T ");
                else System.out.print("F ");
            System.out.println();
        }
    }
}
