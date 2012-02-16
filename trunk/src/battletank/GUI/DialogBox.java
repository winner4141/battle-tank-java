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
    Container c = new Container();
    @Override
    public Graphics getGraphics() {
        return c.getGraphics();
    }

    public DialogBox() {
        Graphics g = getGraphics();
    }
}
