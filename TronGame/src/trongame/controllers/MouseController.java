package trongame.controllers;

import game.core.IPlayerController;
import game.core.MovementDirection;
import static game.core.MovementDirection.LEFT;
import static game.core.MovementDirection.RIGHT;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Drimal
 */
public class MouseController implements IPlayerController, MouseListener {

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
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == keyLeftMapping) {
            directionOfMove = directionOfMove.turnAround(directionOfMove, LEFT);
        } else if (e.getButton() == keyRightMapping) {
            directionOfMove = directionOfMove.turnAround(directionOfMove, RIGHT);
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
