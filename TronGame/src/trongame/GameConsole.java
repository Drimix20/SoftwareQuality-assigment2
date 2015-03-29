package trongame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import trongame.controllers.KeyboardController;
import trongame.controllers.MouseController;
import trongame.ournew.IPlayer;
import trongame.ournew.MovementDirection;
import trongame.ournew.Player;
import trongame.ournew.WindowRenderer;

/**
 *
 * @author Drimal
 */
public class GameConsole {

    private GameEngine gameCore;
    private boolean running;
    private WindowRenderer windowRenderer;
    private List<IPlayer> players;

    public GameConsole() {
        players = new ArrayList<>();
        windowRenderer = new WindowRenderer();
        Window w = windowRenderer.creteGameWindow();

        this.running = true;

        //Define players with their controllers
        KeyboardController playerOneController = new KeyboardController(MovementDirection.RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        Player playerOne = new Player(new Point(40, 40), Color.green, MovementDirection.RIGHT, playerOneController);
        playerOne.setPlayerMouseController(new MouseController(MovementDirection.RIGHT, MouseEvent.BUTTON1, MouseEvent.BUTTON3));
        players.add(playerOne);
        KeyboardController playerTwoController = new KeyboardController(MovementDirection.LEFT, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
        players.add(new Player(new Point(600, 440), Color.red, MovementDirection.LEFT, playerTwoController));

        gameCore = new GameEngine(players, w);

    }

    public void runGame() {
        try {
            gameLoop();
        } finally {
            windowRenderer.restoreGameScreen();
        }
    }

    public void gameLoop() {
        while (running) {
            gameCore.movePlayers(windowRenderer.getScreenWidth(), windowRenderer.getScreenHeight());
            if (gameCore.detectCollisions()) {
                stop();
            }
            windowRenderer.draw(players);
            afterGameIteration();
        }
    }

    private void afterGameIteration() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void stop() {
        try {
            running = false;
            Thread.sleep(1500L);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
