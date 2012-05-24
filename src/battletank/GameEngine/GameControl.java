/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battletank.GameEngine;

import Effects.SoundEffect;
import static Global.Constants.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

/**
 *
 * @author Lijun
 */
public class GameControl implements KeyListener {

    /** Cas medzi jednotlivymi strelami */
    private static final int freezeTime = 500;
    /** Cas medzi jednotlivymi krokmi tankov */
    private static final int tankMoveTime = 20;
    /** Cas medzi jednotlivymi krokmi striel */
    private static final int bulletMoveTime = 10;
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
    /* Tank1 dopredu */
    private final int t1Up = KeyEvent.VK_W;
    /* Tank1 dozau */
    private final int t1Down = KeyEvent.VK_S;
    /* Tank1 dolava */
    private final int t1Left = KeyEvent.VK_A;
    /* Tank1 doprava */
    private final int t1Right = KeyEvent.VK_D;
    /* Tank1 strela */
    private final int t1Shoot = KeyEvent.VK_SPACE;
    /* Tank2 dopredu */
    private final int t2Up = KeyEvent.VK_UP;
    /* Tank2 dozadu */
    private final int t2Down = KeyEvent.VK_DOWN;
    /* Tank2 dolava */
    private final int t2Left = KeyEvent.VK_LEFT;
    /* Tank2 doprava */
    private final int t2Right = KeyEvent.VK_RIGHT;
    /* Tank2 strela */
    private final int t2Shoot = KeyEvent.VK_ENTER;
    /** Prerusena hra */
    public boolean paused = false;

    public GameControl() {
        InitTimers();
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
                if (game == null) {
                    return;
                }
                // parametre su kvoli strelbe tankov, ked stoji na mieste, vtedy smer nie je zaznamenany v tankoch
                // aby to nekomplikovalo timer na pohyb tankov.
                game.tanksMove(tank1direction, tank2direction);
            }
        });

        bulletMoveTimer = new Timer(bulletMoveTime, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (game == null) {
                    return;
                }
                game.BulletsMove();
            }
        });
    }

    /** Startovanie hry */
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
        int keycode = e.getKeyCode();

        if (keycode == KeyEvent.VK_ESCAPE) {
            if (!paused) {
                StopAllTimers();
                paused = !paused;
                game.PauseGame();
            }
            return;
        }
        switch (keycode) {
            // Strelba tank 1 po stlaceni medzernik
            case t1Shoot: {
                if (!freeze1) {
                    game.tank1.Shot();
                    SoundEffect.PlayShotSound();
                    freeze1 = true;
                    freezeTimer1.start();
                }
                break;
            }

            // Strelba tank 1 po stlaceni Enter
            case t2Shoot: {
                if (!freeze2) {
                    game.tank2.Shot();
                    SoundEffect.PlayShotSound();
                    freeze2 = true;
                    freezeTimer2.start();
                }
                break;
            }

            case t1Up: {
                tank1direction = UP;
                break;
            }
            case t1Down: {
                tank1direction = DOWN;
                break;
            }
            case t1Left: {
                tank1direction = LEFT;
                break;
            }
            case t1Right: {
                tank1direction = RIGHT;
                break;
            }
            case t2Up: {
                tank2direction = UP;
                break;
            }
            case t2Down: {
                tank2direction = DOWN;
                break;
            }
            case t2Left: {
                tank2direction = LEFT;
                break;
            }
            case t2Right: {
                tank2direction = RIGHT;
                break;
            }

            default:
                break;
        }

        if (tank1direction != NONE) {
            game.tank1.direction = tank1direction;
        }
        if (tank2direction != NONE) {
            game.tank2.direction = tank2direction;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case t2Up: {
                if (tank2direction == UP) {
                    tank2direction = NONE;
                }
                break;
            }
            case t2Down: {
                if (tank2direction == DOWN) {
                    tank2direction = NONE;
                }
                break;
            }
            case t2Left: {
                if (tank2direction == LEFT) {
                    tank2direction = NONE;
                }
                break;
            }
            case t2Right: {
                if (tank2direction == RIGHT) {
                    tank2direction = NONE;
                }
                break;
            }

            case t1Up: {
                if (tank1direction == UP) {
                    tank1direction = NONE;
                }
                break;
            }
            case t1Down: {
                if (tank1direction == DOWN) {
                    tank1direction = NONE;
                }
                break;
            }
            case t1Left: {
                if (tank1direction == LEFT) {
                    tank1direction = NONE;
                }
                break;
            }
            case t1Right: {
                if (tank1direction == RIGHT) {
                    tank1direction = NONE;
                }
                break;
            }
            default:
                break;
        }

        //game.tank1.direction = tank1direction;
        //game.tank2.direction = tank2direction;
    }

    public void Start() {
        StartAllTimers();
    }
}
