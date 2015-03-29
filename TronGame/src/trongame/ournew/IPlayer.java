package trongame.ournew;

import java.awt.Graphics2D;
import java.awt.Point;
import trongame.controllers.PlayerController;

/**
 *
 * @author Drimal
 */
public interface IPlayer {

    void addCurrentPositionToHistory(Point point);

    void drawPath(Graphics2D g);

    int getCurrentPositionX();

    int getCurrentPositionY();

    PlayerController getPlayerKeyboardController();

    PlayerController getPlayerMouseController();

    Point getPositionHistory(int index);

    boolean isInCollisionWith(Player player);

    void movePlayer(int windowWidth, int windowHeight);

    void setPlayerMouseController(PlayerController playerMouseController);

}
