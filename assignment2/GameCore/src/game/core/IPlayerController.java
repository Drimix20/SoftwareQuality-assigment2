package game.core;

import java.util.EventListener;

/**
 *
 * @author Drimal
 */
public interface IPlayerController extends EventListener {

    /**
     * Return enum represents current player direction of movement
     *
     * @return
     */
    public MovementDirection getPlayerDirection();
}
