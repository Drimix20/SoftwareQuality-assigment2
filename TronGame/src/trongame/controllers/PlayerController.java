package trongame.controllers;

import java.util.EventListener;
import trongame.ournew.MovementDirection;

/**
 *
 * @author Drimal
 */
public interface PlayerController extends EventListener {

    /**
     * Return enum represents current player direction of movement
     *
     * @return
     */
    public MovementDirection getPlayerDirection();

    /**
     * Change current player direction of movement
     */
    public void setPlayerDirection(MovementDirection direction);
}
