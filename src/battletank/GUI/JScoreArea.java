/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battletank.GUI;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import static Global.Constants.*;

/**
 *
 * @author Lijun
 */
public class JScoreArea extends JPanel {

    public String player1 = "Player 1";
    public String player2 = "Player 2";
    Font f = new Font("Times New Roman", Font.BOLD, 20);
    public JScoreArea() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(f);
        g.drawString(player1, mapWidth * game.map.getMap()[0].length + 20, 50);
        g.drawString(String.valueOf(game.player1lives), mapWidth * game.map.getMap()[0].length + 20, 100);
        g.drawString(player2, mapWidth * game.map.getMap()[0].length + 20, 150);
        g.drawString(String.valueOf(game.player2lives), mapWidth * game.map.getMap()[0].length + 20, 200);
    }
}
