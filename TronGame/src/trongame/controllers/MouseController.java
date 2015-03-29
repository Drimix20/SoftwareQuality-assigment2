package trongame.controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import trongame.ournew.MovementDirection;
import static trongame.ournew.MovementDirection.*;

/**
 *
 * @author Drimal
 */
public class MouseController implements PlayerController, MouseListener {

    private MovementDirection directionOfMove;
    private int keyLeftMapping;
    private int keyRightMapping;

    public MouseController(MovementDirection initialDirectionOfMove, int keyLeftMapping, int keyRightRight) {
        this.directionOfMove = initialDirectionOfMove;
        this.keyLeftMapping = keyLeftMapping;
        this.keyRightMapping = keyRightRight;
    }

    @Override
    public MovementDirection getPlayerDirection() {
        return directionOfMove;
    }

    @Override
    public void setPlayerDirection(MovementDirection direction) {
        this.directionOfMove = direction;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == keyLeftMapping) {
            if (directionOfMove == UP) {
                directionOfMove = LEFT;
            } else if (directionOfMove == LEFT) {
                directionOfMove = DOWN;
            } else if (directionOfMove == DOWN) {
                directionOfMove = RIGHT;
            } else if (directionOfMove == RIGHT) {
                directionOfMove = UP;
            }
        } else if (e.getButton() == keyLeftMapping) {
            if (directionOfMove == UP) {
                directionOfMove = RIGHT;
            } else if (directionOfMove == RIGHT) {
                directionOfMove = DOWN;
            } else if (directionOfMove == DOWN) {
                directionOfMove = LEFT;
            } else if (directionOfMove == LEFT) {
                directionOfMove = UP;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
