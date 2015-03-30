package trongame;

import game.core.WindowRenderer;
import game.core.GameEngine;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import game.core.IPlayer;

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
    }

    public void registerPlayers(List<IPlayer> players) {
        Window w = windowRenderer.creteGameWindow();
        this.players = players;
        gameCore = new GameEngine(players, w);
    }

    public void runGame() {
        this.running = true;
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
