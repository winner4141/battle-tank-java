/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battletank.GameEngine;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import static Global.Constants.*;

/**
 *
 * @author Lijun
 */
public class GameControl implements KeyListener {

    /** Cas medzi jednotlivymi strelami */
    private static final int freezeTime = 500;
    /** Cas medzi jednotlivymi krokmi tankov */
    private static final int tankMoveTime = 50;
    /** Cas medzi jednotlivymi krokmi striel */
    private static final int bulletMoveTime = 30;
    /** Rychlost pohybu tankov po mape. */
    private static final int pixelPerMove = 5;
    /** Timeout pre strelu z tank 1 */
    boolean freeze1 = false;
    /** Timeout pre strelu z tank 2 */
    boolean freeze2 = false;
    /** Timer na riadenie casu striel pre tank 1 */
    Timer freezeTimer1 = null;
    /** Timer na riadenie casu striel pre tank 2 */
    Timer freezeTimer2 = null;
    /** Timer na riadenie pohybu tankov */
    Timer tankMoveTimer = null;
    /** Timer na riadenie pohybu striel */
    Timer bulletMoveTimer = null;
    /** Smer tank 1 */
    private int tank1direction = NONE;
    /** Smer tank 2 */
    private int tank2direction = NONE;
    /** Herna struktura */
    Game game = null;
    /** Prerusena hra */
    private boolean paused = false;

    public GameControl(Game game) {
        this.game = game;
        InitTimers();
        StartAllTimers();
    }

    private void InitTimers() {
        freezeTimer1 = new Timer(freezeTime, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                freeze1 = false;
                freezeTimer1.stop();
            }
        });

        freezeTimer2 = new Timer(freezeTime, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                freeze2 = false;
                freezeTimer2.stop();
            }
        });

        tankMoveTimer = new Timer(tankMoveTime, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                game.tank1.Move(pixelPerMove);
                game.tank2.Move(pixelPerMove);
            }
        });

        bulletMoveTimer = new Timer(bulletMoveTime, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Bullet> bullets = game.tank1.bullets;
                for (Bullet b : bullets) {
                    b.move(pixelPerMove);
                }

                bullets = game.tank1.bullets;
                for (Bullet b : bullets) {
                    b.move(pixelPerMove);
                }
            }
        });
    }

    private void StartAllTimers() {
        freezeTimer1.start();
        freezeTimer2.start();
        tankMoveTimer.start();
        bulletMoveTimer.start();
    }

    private void StopAllTimers() {
        freezeTimer1.stop();
        freezeTimer2.stop();
        tankMoveTimer.stop();
        bulletMoveTimer.stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (paused) {
                StartAllTimers();
            } else {
                StopAllTimers();
            }
            paused = !paused;
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!freeze1) {
                game.tank1.Shot();
                freeze1 = true;
                freezeTimer1.start();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!freeze2) {
                freeze2 = true;
                game.tank2.Shot();
                freezeTimer2.start();
            }
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: {
                tank2direction = UP;
                break;
            }
            case KeyEvent.VK_DOWN: {
                tank2direction = DOWN;
                break;
            }
            case KeyEvent.VK_LEFT: {
                tank2direction = LEFT;
                break;
            }
            case KeyEvent.VK_RIGHT: {
                tank2direction = RIGHT;
                break;
            }

            case KeyEvent.VK_W: {
                tank1direction = UP;
                break;
            }
            case KeyEvent.VK_S: {
                tank1direction = DOWN;
                break;
            }
            case KeyEvent.VK_A: {
                tank1direction = LEFT;
                break;
            }
            case KeyEvent.VK_D: {
                tank1direction = RIGHT;
                break;
            }
            default:
                break;
        }

        game.tank1.direction = tank1direction;
        game.tank2.direction = tank2direction;

        System.out.print(tank1direction);
        System.out.println(tank2direction);


    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: {
                if (tank1direction == UP) {
                    tank1direction = NONE;
                }
                break;
            }
            case KeyEvent.VK_DOWN: {
                if (tank1direction == DOWN) {
                    tank1direction = NONE;
                }
                break;
            }
            case KeyEvent.VK_LEFT: {
                if (tank1direction == LEFT) {
                    tank1direction = NONE;
                }
                break;
            }
            case KeyEvent.VK_RIGHT: {
                if (tank1direction == RIGHT) {
                    tank1direction = NONE;
                }
                break;
            }

            case KeyEvent.VK_W: {
                if (tank2direction == UP) {
                    tank2direction = NONE;
                }
                break;
            }
            case KeyEvent.VK_S: {
                if (tank2direction == DOWN) {
                    tank2direction = NONE;
                }
                break;
            }
            case KeyEvent.VK_A: {
                if (tank2direction == LEFT) {
                    tank2direction = NONE;
                }
                break;
            }
            case KeyEvent.VK_D: {
                if (tank2direction == RIGHT) {
                    tank2direction = NONE;
                }
                break;
            }
            default:
                break;
        }

        game.tank1.direction = tank1direction;
        game.tank2.direction = tank2direction;

        System.out.print(tank1direction);
        System.out.println(tank2direction);
    }
}
