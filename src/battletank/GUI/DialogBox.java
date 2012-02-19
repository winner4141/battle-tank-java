/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battletank.GUI;

import java.awt.Container;
import java.awt.Graphics;

/**
 *
 * @author Lijun
 */
public class DialogBox extends javax.swing.JDialog {
    
    @Override
    public final Graphics getGraphics() {
        return super.getGraphics();
    }

    public DialogBox(String text) {
        Graphics g = getGraphics();
        g.drawString(text, 0,0);
        repaint();
    }
}
