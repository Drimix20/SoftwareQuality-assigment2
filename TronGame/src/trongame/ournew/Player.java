package trongame.ournew;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import trongame.Core;
import trongame.ScreenManager;
import trongame.controllers.PlayerController;

/**
 *
 * @author Drimal
 */
public class Player {

    PlayerController playerKeyboardController;
    PlayerController playerMouseController;

    private Color playerColor;

    private List<Point> positionHistory;

    public Player(int centrex1, int centrey1, Color playerColor, MovementDirection direction,
            PlayerController keyboardController) {

        positionHistory = new ArrayList<>();
        positionHistory.add(new Point(centrex1, centrey1));
        this.playerColor = playerColor;
        playerKeyboardController = keyboardController;
    }

    public PlayerController getPlayerMouseController() {
        return playerMouseController;
    }

    public void setPlayerMouseController(PlayerController playerMouseController) {
        this.playerMouseController = playerMouseController;
    }

    public PlayerController getPlayerKeyboardController() {
        return playerKeyboardController;
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
        int pathLength = player.positionHistory.size();
        int lastPathIndex = pathLength - 1;

        //kolize sam se sebou
        if (this.equals(player)) {
            tmp.remove(lastPathIndex);
            return tmp.contains(player.positionHistory.get(lastPathIndex));
        }

        return tmp.removeAll(this.positionHistory);
    }

    private MovementDirection getPlayerCurrentDirection() {

        if (this.playerMouseController != null) {
            return this.getPlayerMouseController().getPlayerDirection();
        } else {
            return this.getPlayerKeyboardController().getPlayerDirection();
        }
    }

    public void movePlayer(ScreenManager screenManager) {
        MovementDirection playerDirection = getPlayerCurrentDirection();

        int currentPositionX = this.getCurrentPositionX();
        int currentPositionY = this.getCurrentPositionY();
        int windowWidth = screenManager.getWidth();
        int windowHeight = screenManager.getHeight();
        switch (playerDirection) {
            case UP:
                if (currentPositionY > 0) {
                    currentPositionY -= Core.MOVE_AMOUNT;
                } else {
                    currentPositionY = windowHeight;
                }
                break;
            case RIGHT:
                if (currentPositionX < windowWidth) {
                    currentPositionX += Core.MOVE_AMOUNT;
                } else {
                    currentPositionX = 0;
                }
                break;
            case DOWN:
                if (currentPositionY < windowHeight) {
                    currentPositionY += Core.MOVE_AMOUNT;
                } else {
                    currentPositionY = 0;
                }
                break;
            case LEFT:
                if (currentPositionX > 0) {
                    currentPositionX -= Core.MOVE_AMOUNT;
                } else {
                    currentPositionX = windowWidth;
                }
                break;
        }
        Point point = new Point(currentPositionX, currentPositionY);
        this.addCurrentPositionToHistory(point);
    }
}
