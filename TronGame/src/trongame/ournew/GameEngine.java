package trongame.ournew;

/**
 *
 * @author Drimal
 */
public interface GameEngine {

    void start();

    void stop();

    boolean movePlayer(Player player, MovementDirection direction);

    void draw();
}
