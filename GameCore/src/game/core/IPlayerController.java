package game.core;

import java.util.EventListener;
import game.core.MovementDirection;

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

    /**
     * Change current player direction of movement
     */
    public void setPlayerDirection(MovementDirection direction);
}
