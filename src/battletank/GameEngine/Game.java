/*
 * Herne jadro: u sebe ma informacie o tank 1, tank 2 a mapu.
 * Sluzi zaroven ako spojka medzi strukturou hry a GUI.
 */
package battletank.GameEngine;

import Effects.SoundEffect;
import battletank.GUI.GameWindow;
import static Global.Constants.*;

/**
 *
 * @author Lijun
 */
public class Game {
    
    public Map map = null;
    public Tank tank1 = null;
    public Tank tank2 = null;
    
    private GameWindow gamewindow = null;
    
    public int player1lives = 50;
    public int player2lives = 50;

    public Game() {
        SoundEffect.PlayBackgroundSound();
    }

    public Game(GameWindow window) {
        gamewindow = window;
        SoundEffect.PlayBackgroundSound();
    }

    public void PauseGame() {
        gamewindow.Pause();
    }

    public void GenerateTanks() {
        tank1 = Tank.GenerateNewTank(tank1ID);
        tank2 = Tank.GenerateNewTank(tank2ID);
    }

    void RepaintBullets() {
    }

    void BulletsMove() {

        if (tank1 != null) {
            for (int i = 0; i < tank1.bullets.size(); i++) {
                Bullet b = tank1.bullets.get(i);
                int bx = b.posx;
                int by = b.posy;
                int res = b.move(pixelPerMove);
                if (res != NonCollision) {
                    if (res == TankCollision) {
                        DeleteTank(tank2);
                        player2lives--;
                        gamewindow.update(gamewindow.getGraphics());
                    }
                    tank1.bullets.remove(b);
                    gamewindow.repaint(bx, by, 5, 5);
                } else {
                    gamewindow.repaint(b.posx - pixelPerMove, b.posy - pixelPerMove, 5 + 2 * pixelPerMove, 5 + 2 * pixelPerMove);
                }
            }
        }
        if (tank2 != null) {

            for (int i = 0; i < tank2.bullets.size(); i++) {
                Bullet b = tank2.bullets.get(i);
                int bx = b.posx;
                int by = b.posy;
                int res = b.move(pixelPerMove);
                if (res != NonCollision) {
                    if (res == TankCollision) {
                        DeleteTank(tank1);
                        player1lives--;
                        gamewindow.repaint();
                    }
                    tank2.bullets.remove(b);
                    gamewindow.repaint(bx, by, 5, 5);
                } else {
                    gamewindow.repaint(b.posx - pixelPerMove, b.posy - pixelPerMove, 5 + 2 * pixelPerMove, 5 + 2 * pixelPerMove);
                }
            }
        }
    }

    void tanksMove(int dir1, int dir2) {

        if (tank1 != null && dir1 != NONE) {
            //System.out.print("Tank 1: ");
            tank1.Move(pixelPerMove);
        } else {
            //System.out.println("Tank 1: Not move! dir:" + tank1.direction);
        }
        if (tank2 != null && dir2 != NONE) {
            //System.out.print("Tank 2: ");
            tank2.Move(pixelPerMove);
        } else {
            //System.out.println("Tank 2: Not move! dir" + tank2.direction);
        }
    }

    private void DeleteTank(Tank tank) {
        gamewindow.DeleteTank(tank);
        gamewindow.repaint(gamewindow.getHeight(), 0, gamewindow.getWidth() - gamewindow.getHeight(), gamewindow.getHeight());
    }

    void repaintTank(Tank tnk) {
        gamewindow.repaint(tnk.absPos.x - pixelPerMove, tnk.absPos.y - pixelPerMove, tankWidth + 2 * pixelPerMove, tankHeight + 2 * pixelPerMove);
    }

    void ContinueGame() {
        gamewindow.Continue();
    }
}
