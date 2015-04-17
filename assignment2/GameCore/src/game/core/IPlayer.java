package game.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author Drimal
 */
public interface IPlayer {

    void addCurrentPositionToHistory(Point point);

    void drawPath(Graphics2D g);

    int getCurrentPositionX();

    int getCurrentPositionY();

    List<Point> getHistoryPositions();

    Color getPlayerColor();

    IPlayerController getPlayerKeyboardController();

    IPlayerController getPlayerMouseController();

    Point getPositionHistory(int index);

    boolean isInCollisionWith(IPlayer player);

    void movePlayer(int windowWidth, int windowHeight);

    void setPlayerMouseController(IPlayerController playerMouseController);

}
