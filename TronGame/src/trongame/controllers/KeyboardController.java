package trongame.controllers;

import java.awt.event.KeyEvent;
import trongame.ournew.MovementDirection;

/**
 *
 * @author Drimal
 */
public class KeyboardController implements PlayerController {

    private MovementDirection directionOfMove;
    private int keyUpMapping;
    private int keyDownMapping;
    private int keyRightMapping;
    private int keyLeftMapping;

    public KeyboardController(MovementDirection initialDirectionOfMove, int keyUpMapping, int keyDownMapping, int keyRightMapping, int keyLeftMapping) {
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
        if (directionOfMove != MovementDirection.left) {
            directionOfMove = MovementDirection.right;
        }
    }

    private void moveLeft() {
        if (directionOfMove != MovementDirection.right) {
            directionOfMove = MovementDirection.left;
        }
    }

    private void moveDown() {
        if (directionOfMove != MovementDirection.up) {
            directionOfMove = MovementDirection.down;
        }
    }

    private void moveUp() {
        if (directionOfMove != MovementDirection.down) {
            directionOfMove = MovementDirection.up;
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
