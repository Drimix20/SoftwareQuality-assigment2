package trongame;

import game.core.GameEngine;
import java.util.List;
import game.core.IPlayer;
import java.awt.Window;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Drimal
 */
public class TronGame extends GameEngine {

    public TronGame(List<IPlayer> players) {
        super(players);
    }

    public void runGame() {
        running = true;
        gameLoop();
        windowRenderer.restoreGameScreen();
    }

    @Override
    public void afterMoveOperation() {
        collisionDetection();
    }

    private void collisionDetection() {
        for (int i = 0; i < players.size(); i++) {
            IPlayer playerOne = players.get(i);
            for (int j = 0; j < players.size(); j++) {
                IPlayer playerTwo = players.get(j);
                if (playerOne.isInCollisionWith((IPlayer) playerTwo)) {
                    running = false;
                }
            }
        }
    }

    @Override
    public void registerListeners() {
        Window w = windowRenderer.creteGameWindow();
        for (IPlayer player : players) {
            w.addKeyListener((KeyListener) player.getPlayerKeyboardController());
            w.addMouseListener((MouseListener) player.getPlayerMouseController());
        }
    }

    @Override
    public void afterGameLoopOperation() {
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
