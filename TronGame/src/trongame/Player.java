package trongame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import trongame.controllers.PlayerController;

/**
 *
 * @author Drimal
 */
public class Player {

    private static final int MOVE_AMOUNT = 10;
    PlayerController playerController;

    private int currentPositionX;
    private int currentPositionY;
    private Color playerColor;
    private int windowWidth;
    private int windowHeight;

    List<Integer> pathx1 = new ArrayList();
    List<Integer> pathy1 = new ArrayList();

    public Player(int centrex1, int centrey1, Color playerColor, MovementDirection direction, PlayerController keyboardController, int windowWidth,
            int windowHeight) {
        this.currentPositionX = centrex1;
        this.currentPositionY = centrey1;
        this.playerColor = playerColor;
        playerController = keyboardController;
        //TODO is not interactive, when windows size is changed via game this variables will be same (because of constructor)
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public KeyListener getKeyboardListener() {
        return playerController;
    }

    public int getCurrentPositionX() {
        return currentPositionX;
    }

    public int getCurrentPositionY() {
        return currentPositionY;
    }

    public List<Integer> getPathx1() {
        return pathx1;
    }

    public void addCurrentPositionToPathx1() {
        pathx1.add(currentPositionX);
    }

    public void addCurrentPositionToPathy1() {
        pathy1.add(currentPositionY);
    }

    public List<Integer> getPathy1() {
        return pathy1;
    }

    public void drawPath(Graphics2D g) {
        for (int i = 0; i < pathx1.size(); i++) {
            g.setColor(playerColor);
            g.fillRect(pathx1.get(i), pathy1.get(i), 10, 10);
        }
    }

    public void move() {
        MovementDirection playerDirection = playerController.getPlayerDirection();
        switch (playerDirection) {
            case up:
                if (currentPositionY > 0) {
                    currentPositionY -= MOVE_AMOUNT;
                } else {
                    currentPositionY = windowHeight;
                }
                break;
            case right:
                if (currentPositionX < windowWidth) {
                    currentPositionX += MOVE_AMOUNT;
                } else {
                    currentPositionX = 0;
                }
                break;
            case down:
                if (currentPositionY < windowHeight) {
                    currentPositionY += MOVE_AMOUNT;
                } else {
                    currentPositionY = 0;
                }
                break;
            case left:
                if (currentPositionX > 0) {
                    currentPositionX -= MOVE_AMOUNT;
                } else {
                    currentPositionX = windowWidth;
                }
                break;
        }
    }

}
