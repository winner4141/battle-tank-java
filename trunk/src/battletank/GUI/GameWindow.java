/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battletank.GUI;

import battletank.GameEngine.Game;
import battletank.GameEngine.GameControl;
import Effects.Explosion;
import Interfaces.ICallBack;
import battletank.GameEngine.Map;
import battletank.GameEngine.Tank;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static Global.Constants.*;

/**
 *
 * @author Lijun
 */
public class GameWindow extends JFrame {

    /** Cast GUI, kde je nakreslena mapa */
    private JMapArea mapArea = null;
    /** Cast GUI, kde su informacie, napr. skore. */
    private JScoreArea scoreArea = null;
    /** Vybuch na mape */
    private Explosion expl = null;
    private int ExplID = 0;
    private int width = 800;
    private int height = 600;
    /** Ovladac hry, su v nom definicie klavesnice a celkove ovladanie hry */
    private GameControl gameControl = null;
    private boolean explTank1 = false;
    private boolean explTank2 = false;
    private int dir1 = NONE;
    private int dir2 = NONE;

    /** CallBack funkcia po vybuchu */
    private ICallBack callback() {
        return new ICallBack() {

            @Override
            public void postExec() {
                if (ExplID == expl.id) {
                    remove(expl);
                    expl = null;
                }
                if (explTank1) {
                    // Ak bol vybuchnuty 1. tank, tak vygenurujem novy.
                    game.tank1 = Tank.GenerateNewTank(tank1ID);
                    game.tank1.CalcAbsolutePosition();
                    mapArea.drawT1 = true;
                    game.tank1.direction = dir1;
                    explTank1 = false;
                }
                if (explTank2) {
                    // Ak bol vybuchnuty 2. tank, tak vygenurujem novy.
                    game.tank2 = Tank.GenerateNewTank(tank2ID);
                    game.tank2.CalcAbsolutePosition();
                    mapArea.drawT2 = true;
                    game.tank2.direction = dir2;
                    explTank2 = false;
                }
            }
        };
    }

    public GameWindow(int width, int height) {
        this.height = height;
        this.width = width;
        InitWindow();
    }

    public GameWindow() {
        InitWindow();
    }

    public GameWindow(String[] args) {
        HandleArgs(args);
        InitWindow();
    }
/** Inicializacia hracieho okna a herneho jadra */
    private void InitWindow() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pixelPerMove = this.height / 300;
        game = new Game(this);
        game.map = new Map();
        game.GenerateTanks();

        gameControl = new GameControl();
        int mapsize = Math.min(height, (int) (0.8 * width));
        mapArea = new JMapArea(null, true, new Dimension(mapsize, mapsize));
        addKeyListener(gameControl);

        if (scoreArea == null) {
            scoreArea = new JScoreArea();
        }
        
        this.pack();
        this.setVisible(true);
        this.setSize(width, height);
        
        // Ostartovanie hry
        gameControl.Start();
    }

    @Override
    public void paint(Graphics g) {
        scoreArea.paint(g);
        mapArea.paint(g);
        if (expl != null) {
            expl.paint(g);
        }
    }
    /** Vyvolane pri stlaceni ESC, po stlaceni OK pokracovanie v hre */
    public void Pause() {
        JOptionPane.showMessageDialog(this.getContentPane(), "Game paused !");
        Continue();
        //dialogbox.setVisible(true);
    }
    /** Pokracovanie v hre */
    public void Continue() {
        gameControl.paused = false;
        gameControl.Start();
    }

    /** Vymazat tank po zasiahnuti */
    public void DeleteTank(Tank tank) {
        ExplID++;
        if (tank.tankId == tank1ID) {
            dir1 = tank.direction;
            int x = game.tank1.absPos.x;
            int y = game.tank1.absPos.y;
            mapArea.drawT1 = false;
            explTank1 = true;
            expl = new Explosion(ExplID);
            //cp.add(expl);
            expl.setCallBack(callback());
            expl.Explode(this, x, y);
        } else {
            dir2 = tank.direction;
            int x = game.tank2.absPos.x;
            int y = game.tank2.absPos.y;
            mapArea.drawT2 = false;
            explTank2 = true;
            expl = new Explosion(ExplID);
            //cp.add(expl);
            expl.setCallBack(callback());
            expl.Explode(this, x, y);
        }
    }

    /** Spracovanie argumentov */
    private void HandleArgs(String[] args) {
        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i].compareTo("-res") == 0) {
                    i++;
                    String[] res = args[i].split("\\*");
                    int w = Integer.valueOf(res[0]).intValue();
                    int h = Integer.valueOf(res[1]).intValue();
                    this.width = w;
                    this.height = h;
                } else if (args[i].compareTo("-players") == 0) {
                    scoreArea = new JScoreArea();
                    scoreArea.player1 = args[++i];
                    scoreArea.player2 = args[++i];
                }
            }
        } catch (Exception ex) {
        }
    }
}
