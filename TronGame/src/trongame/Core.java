package trongame;

import java.awt.*;
import trongame.ournew.MovementDirection;
import trongame.ournew.Player;

public class Core {

    private static Core gameCore = new Core();

    private Core() {
    }

    public static Core getInstance() {
        return gameCore;
    }

    private static final int MOVE_AMOUNT = 10;
    private final DisplayMode[] supportedDisplayModes
            = {
                //new DisplayMode(1920,1080,32,0),
                new DisplayMode(1680, 1050, 32, 0),
                //new DisplayMode(1280,1024,32,0),
                new DisplayMode(800, 600, 32, 0),
                new DisplayMode(800, 600, 24, 0),
                new DisplayMode(800, 600, 16, 0),
                new DisplayMode(640, 480, 32, 0),
                new DisplayMode(640, 480, 24, 0),
                new DisplayMode(640, 480, 16, 0),};

    public DisplayMode[] getSupportedDisplayModes() {
        return supportedDisplayModes;
    }

    //??
    public void update(long timePassed) {
    }

    public void movePlayer(Player player, ScreenManager screenManager) {
        MovementDirection playerDirection = player.getPlayerController().getPlayerDirection();
        int currentPositionX = player.getCurrentPositionX();
        int currentPositionY = player.getCurrentPositionY();
        int windowWidth = screenManager.getWidth();
        int windowHeight = screenManager.getHeight();
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

        Point point = new Point(currentPositionX, currentPositionY);
        player.addCurrentPositionToHistory(point);
    }
}
