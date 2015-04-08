package trongame.controllers;

import game.core.IPlayerController;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import game.core.MovementDirection;

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
        int directionIndex = directionOfMove.ordinal();
        if (e.getButton() == keyLeftMapping) {
            directionIndex++;
        } else if (e.getButton() == keyRightMapping) {
            directionIndex--;
        }

        directionOfMove = MovementDirection.values()[(directionIndex + 4) % 4];
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
