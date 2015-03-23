package trongame.ournew;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import trongame.controllers.PlayerController;

/**
 *
 * @author Drimal
 */
public class Player {

    PlayerController playerController;

    private Color playerColor;

    private List<Point> positionHistory;

    public Player(int centrex1, int centrey1, Color playerColor, MovementDirection direction,
            PlayerController keyboardController) {

        positionHistory = new ArrayList<>();
        positionHistory.add(new Point(centrex1, centrey1));
        this.playerColor = playerColor;
        playerController = keyboardController;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    private Point getCurrentPosition() {
        return positionHistory.get(positionHistory.size() - 1);
    }

    public int getCurrentPositionX() {
        return getCurrentPosition().x;
    }

    public int getCurrentPositionY() {
        return getCurrentPosition().y;
    }

    public void addCurrentPositionToHistory(Point point) {
        positionHistory.add(point);
    }

    public Point getPositionHistory(int index) {
        return positionHistory.get(index);
    }

    public void drawPath(Graphics2D g) {
        for (int i = 0; i < positionHistory.size(); i++) {
            g.setColor(playerColor);
            Point point = positionHistory.get(i);
            g.fillRect(point.x, point.y, 10, 10);
        }
    }

    public boolean isInCollisionWith(Player player) {
        List<Point> tmp = new ArrayList<>(player.positionHistory);
        return tmp.removeAll(this.positionHistory);
    }
}
