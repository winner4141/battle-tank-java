/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battletank.GUI;

import battletank.GameEngine.Game;
import battletank.GameEngine.GameControl;
import Effects.Explosion;
import Interfaces.ICallBack;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Lijun
 */
public class GameWindow extends JFrame {

    // Herne jadro
    Game game = new Game();
    // Cast GUI, kde je nakreslena mapa
    private JMapArea mapArea = null;
    // Cast GUI, kde su informacie, napr. skore.
    private JPanel scoreArea = null;
    private Container cp = null;
    private Explosion expl = null;
    private int ExplID = 0;
    
private GameControl gameControl = null;

    private ICallBack callback() {
        return new ICallBack() {

            @Override
            public void postExec() {
                if (ExplID == expl.id) {
                    remove(expl);
                    expl = null;
                }
            }
        };
    }

    public GameWindow(int width, int height) {
    }

    public GameWindow() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout gl = new GridLayout(10,10);
        
        mapArea = new JMapArea(gl, true);

        gameControl = new GameControl(game);
        addKeyListener(gameControl);

        scoreArea = new JPanel();

        this.pack();
        this.setVisible(true);
        mapArea.setSize(480, 480);
        game.map = mapArea.map;
        this.setSize(800, 600);

        cp = getContentPane();

        cp.add(mapArea, BorderLayout.WEST);
        cp.add(scoreArea, BorderLayout.EAST);
    }

    @Override
    public void paint(Graphics g) {
        
        mapArea.paint(g);
        scoreArea.paint(g);
        if (expl != null) {
            expl.paint(g);
        }
    }
}
