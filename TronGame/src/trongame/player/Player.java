package trongame.player;

import game.core.IDrawable;
import game.core.MovementDirection;
import game.core.IPlayer;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import game.core.IPlayerController;

/**
 *
 * @author Drimal
 */
public class Player implements IPlayer {

    private int MOVE_AMOUNT = 10;
    private IPlayerController playerKeyboardController;
    private IPlayerController playerMouseController;
    private IDrawable playerRenderer;
    private Color playerColor;
    private List<Point> positionHistory;

    public Player(Point startPosition, Color playerColor, MovementDirection direction,
            IPlayerController keyboardController) {

        positionHistory = new ArrayList<>();
        positionHistory.add(startPosition);
        this.playerColor = playerColor;
        playerKeyboardController = keyboardController;
    }

    public void setMoveAmount(int MOVE_AMOUNT) {
        this.MOVE_AMOUNT = MOVE_AMOUNT;
    }

    public void setPlayerRenderer(IDrawable renderer) {
        this.playerRenderer = renderer;
    }

    @Override
    public IPlayerController getPlayerMouseController() {
        return playerMouseController;
    }

    @Override
    public void setPlayerMouseController(IPlayerController playerMouseController) {
        this.playerMouseController = playerMouseController;
    }

    @Override
    public IPlayerController getPlayerKeyboardController() {
        return playerKeyboardController;
    }

    private Point getCurrentPosition() {
        return positionHistory.get(positionHistory.size() - 1);
    }

    @Override
    public int getCurrentPositionX() {
        return getCurrentPosition().x;
    }

    @Override
    public int getCurrentPositionY() {
        return getCurrentPosition().y;
    }

    @Override
    public void addCurrentPositionToHistory(Point point) {
        positionHistory.add(point);
    }

    @Override
    public Point getPositionHistory(int index) {
        return positionHistory.get(index);
    }

    @Override
    public void drawPath(Graphics2D g) {
//        for (int i = 0; i < positionHistory.size(); i++) {
//            g.setColor(playerColor);
//            Point point = positionHistory.get(i);
//            g.fillRect(point.x, point.y, 10, 10);
//        }
        playerRenderer.drawPlayer(g, this);
    }

    @Override
    public Color getPlayerColor() {
        return playerColor;
    }

    @Override
    public boolean isInCollisionWith(IPlayer player) {
        List<Point> tmp = new ArrayList<>(player.getHistoryPositions());
        int pathLength = player.getHistoryPositions().size();
        int lastPathIndex = pathLength - 1;

        if (this.equals(player)) {
            tmp.remove(lastPathIndex);
            return tmp.contains(player.getPositionHistory(lastPathIndex));
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

    @Override
    public void movePlayer(int windowWidth, int windowHeight) {
        MovementDirection playerDirection = getPlayerCurrentDirection();

        int currentPositionX = this.getCurrentPositionX();
        int currentPositionY = this.getCurrentPositionY();
        switch (playerDirection) {
            case UP:
                if (currentPositionY > 0) {
                    currentPositionY -= MOVE_AMOUNT;
                } else {
                    currentPositionY = windowHeight;
                }
                break;
            case RIGHT:
                if (currentPositionX < windowWidth) {
                    currentPositionX += MOVE_AMOUNT;
                } else {
                    currentPositionX = 0;
                }
                break;
            case DOWN:
                if (currentPositionY < windowHeight) {
                    currentPositionY += MOVE_AMOUNT;
                } else {
                    currentPositionY = 0;
                }
                break;
            case LEFT:
                if (currentPositionX > 0) {
                    currentPositionX -= MOVE_AMOUNT;
                } else {
                    currentPositionX = windowWidth;
                }
                break;
        }
        Point point = new Point(currentPositionX, currentPositionY);
        this.addCurrentPositionToHistory(point);
    }

    @Override
    public List<Point> getHistoryPositions() {
        return positionHistory;
    }
}
