package trongame.controllers;

import game.core.PlayerController;
import game.core.MovementDirection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Drimal
 */
public class KeyboardController implements PlayerController, KeyListener {

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
            moveUp();
        } else if (e.getKeyCode() == keyDownMapping) {
            moveDown();
        } else if (e.getKeyCode() == keyRightMapping) {
            moveRight();
        } else if (e.getKeyCode() == keyLeftMapping) {
            moveLeft();
        }
    }

    private void moveRight() {
        if (directionOfMove != MovementDirection.LEFT) {
            directionOfMove = MovementDirection.RIGHT;
        }
    }

    private void moveLeft() {
        if (directionOfMove != MovementDirection.RIGHT) {
            directionOfMove = MovementDirection.LEFT;
        }
    }

    private void moveDown() {
        if (directionOfMove != MovementDirection.UP) {
            directionOfMove = MovementDirection.DOWN;
        }
    }

    private void moveUp() {
        if (directionOfMove != MovementDirection.DOWN) {
            directionOfMove = MovementDirection.UP;
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

    @Override
    public void setPlayerDirection(MovementDirection direction) {
        this.directionOfMove = direction;
    }

}
