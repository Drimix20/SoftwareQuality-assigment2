package trongame.controllers;

import game.core.IPlayerController;
import game.core.MovementDirection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Drimal
 */
public class KeyboardController implements IPlayerController, KeyListener {

    private MovementDirection directionOfMove;
    private int keyUpMapping;
    private int keyDownMapping;
    private int keyRightMapping;
    private int keyLeftMapping;

    public KeyboardController(MovementDirection initialDirectionOfMove, int keyUpMapping, int keyDownMapping, int keyLeftMapping, int keyRightMapping) {
        this.directionOfMove = initialDirectionOfMove;
        this.keyUpMapping = keyUpMapping;
        this.keyDownMapping = keyDownMapping;
        this.keyRightMapping = keyRightMapping;
        this.keyLeftMapping = keyLeftMapping;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == keyUpMapping) {
            directionOfMove = directionOfMove.tryChangeTo(MovementDirection.UP);
        } else if (e.getKeyCode() == keyDownMapping) {
            directionOfMove = directionOfMove.tryChangeTo(MovementDirection.DOWN);
        } else if (e.getKeyCode() == keyRightMapping) {
            directionOfMove = directionOfMove.tryChangeTo(MovementDirection.RIGHT);
        } else if (e.getKeyCode() == keyLeftMapping) {
            directionOfMove = directionOfMove.tryChangeTo(MovementDirection.LEFT);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public MovementDirection getPlayerDirection() {
        return directionOfMove;
    }
}
