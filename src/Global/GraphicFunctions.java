/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Global;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author Lijun
 */
public class GraphicFunctions {

    public static BufferedImage Rescale(BufferedImage srcImg, int destW, int destH) {
        BufferedImage dest = new BufferedImage(destW, destH, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = dest.createGraphics();
        AffineTransform at = AffineTransform.getScaleInstance((double) destW / srcImg.getWidth(), (double) destH / srcImg.getHeight());
        g.drawRenderedImage(srcImg, at);
        return dest;
    } 
}
