package trongame;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import trongame.ournew.IPlayer;
import java.util.List;
import trongame.ournew.Player;

public class GameEngine {

    private List<IPlayer> players;

    public GameEngine(List<IPlayer> players, Window w) {
        this.players = players;

        for (IPlayer player : players) {
            w.addKeyListener((KeyListener) player.getPlayerKeyboardController());
            w.addMouseListener((MouseListener) player.getPlayerMouseController());
        }
    }

    public void movePlayers(int windowWidth, int windowHeight) {
        for (IPlayer player : players) {
            player.movePlayer(windowWidth, windowHeight);
        }
    }

    public boolean detectCollisions() {
        for (int i = 0; i < players.size(); i++) {
            IPlayer playerOne = players.get(i);
            for (int j = 0; j < players.size(); j++) {
                IPlayer playerTwo = players.get(j);
                if (playerOne.isInCollisionWith((Player) playerTwo)) {
                    return true;
                }
            }
        }
        return false;
    }
}
