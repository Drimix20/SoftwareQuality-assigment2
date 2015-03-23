package trongame;

import trongame.ournew.Player;
import trongame.ournew.MovementDirection;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import trongame.controllers.KeyboardController;

/**
 *
 * @author Drimal
 */
public class GameConsole {

    private Core gameCore;
    private boolean running;
    private ScreenManager sm;

    private List<Player> players;

    public void stop() {
        running = false;
    }

    public GameConsole() {
        players = new ArrayList<>();
        gameCore = Core.getInstance();
        this.sm = new ScreenManager();
        DisplayMode dm = sm.findFirstCompatibaleMode(gameCore.getSupportedDisplayModes());
        sm.setFullScreen(dm);
        init();
    }

    public void init() {
        //core

        Window w = sm.getFullScreenWindow();
        w.setFont(new Font("Arial", Font.PLAIN, 20));
        w.setBackground(Color.WHITE);
        w.setForeground(Color.RED);
        w.setCursor(w.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
        running = true;
        //core

        //Define players with their controllers (easy future update to mouse listener)
        KeyboardController playerOneController = new KeyboardController(MovementDirection.right, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        players.add(new Player(40, 40, Color.green, MovementDirection.right, playerOneController));
        KeyboardController playerTwoController = new KeyboardController(MovementDirection.left, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
        players.add(new Player(600, 440, Color.red, MovementDirection.left, playerTwoController));

        //register player keylistener
        for (Player player : players) {
            w.addKeyListener(player.getPlayerController());
        }
    }

    public static void main(String[] args) {
        GameConsole gameConsole = new GameConsole();
        try {
            gameConsole.gameLoop();
        } finally {
            gameConsole.restoreGameScreen();
        }
    }

    public void restoreGameScreen() {
        sm.restoreScreen();
    }

    public void draw(Graphics2D g) {
        drawBlackBackground(g);
        for (Player player : players) {
            gameCore.movePlayer(player, sm);
        }

        //TODO pul matice test
        for (int i = 0; i < players.size(); i++) {
            Player playerOne = players.get(i);
            for (int j = 0; j < players.size(); j++) {
                Player playerTwo = players.get(j);
                if (playerOne == playerTwo) {
                    continue;
                } else {
                    if (playerOne.isInCollisionWith(playerTwo)) {
                        System.exit(0);
                    }
                }

            }

        }

        //vykresleni cest hracu
        for (Player player : players) {
            player.drawPath(g);
        }
    }

    private void drawBlackBackground(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
    }

    public void gameLoop() {
        long startTime = System.currentTimeMillis();
        long cumTime = startTime;
        while (running) {
            long timePassed = System.currentTimeMillis() - cumTime;
            cumTime += timePassed;
            gameCore.update(timePassed);
            Graphics2D g = sm.getGraphics();
            draw(g);
            g.dispose();
            sm.update();
            try {
                Thread.sleep(20);
            } catch (Exception ex) {
            }
        }
    }
}