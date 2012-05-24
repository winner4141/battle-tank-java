/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author Lijun
 */
public interface IMap {

     void ReadMap(String filename) throws Exception;

     int getX(int absX);

     boolean isFreeAbs(int absPosX, int absPosY);

     boolean isFree(int x, int y);

     boolean[][] getMap();
}
