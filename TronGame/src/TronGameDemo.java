
import game.core.IDrawable;
import game.core.IPlayer;
import game.core.MovementDirection;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import trongame.GameConsole;
import trongame.controllers.KeyboardController;
import trongame.player.Player;
import trongame.player.PlayerRenderer;

/**
 *
 * @author Drimal
 */
public class TronGameDemo {

    public static void main(String[] args) {
        List<IPlayer> players = new ArrayList<>();

        IDrawable playerRenderer = new PlayerRenderer();
        //Define players with their controllers
        KeyboardController playerOneController = new KeyboardController(MovementDirection.RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        Player playerOne = new Player(new Point(40, 40), Color.green, MovementDirection.RIGHT, playerOneController);
        //playerOne.setPlayerMouseController(new MouseController(MovementDirection.RIGHT, MouseEvent.BUTTON1, MouseEvent.BUTTON3));
        playerOne.setPlayerRenderer(playerRenderer);
        players.add(playerOne);
        KeyboardController playerTwoController = new KeyboardController(MovementDirection.LEFT, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
        Player playerTwo = new Player(new Point(600, 440), Color.red, MovementDirection.LEFT, playerTwoController);
        playerTwo.setPlayerRenderer(playerRenderer);
        players.add(playerTwo);

        GameConsole gameConsole = new GameConsole();
        gameConsole.registerPlayers(players);
        gameConsole.runGame();
    }
}
