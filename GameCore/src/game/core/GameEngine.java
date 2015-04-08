package game.core;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class GameEngine {

    protected WindowRenderer windowRenderer;
    protected boolean running;
    protected List<IPlayer> players;
    private int mapWidth;
    private int mapHeight;

    public GameEngine(List<IPlayer> players) {
        windowRenderer = new WindowRenderer();
        this.players = players;

        mapWidth = windowRenderer.getScreenWidth();
        mapHeight = windowRenderer.getScreenHeight();
        registerListeners();
    }

    public void setMapDimension(int width, int height) {
        this.mapWidth = width;
        this.mapHeight = height;
    }

    public void movePlayers() {
        for (IPlayer player : players) {
            player.movePlayer(mapWidth, mapHeight);
        }
    }

    public abstract void registerListeners();

    public abstract void afterMoveOperation();

    /**
     * Metoda po jedne iteraci hry
     */
    public void afterGameIteration() {
    }

    /**
     * Metoda pro vykonani cinnosti po dokonceni hry, napriklad ulozeni, zobrazeni statistiky hry atd.
     */
    public void afterGameLoopOperation() {
    }

    public void gameLoop() {
        while (running) {
            movePlayers();
            afterMoveOperation();
            windowRenderer.draw(players);
            afterGameIteration();
            gameTiming();
        }
        afterGameLoopOperation();
    }

    private void gameTiming() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
