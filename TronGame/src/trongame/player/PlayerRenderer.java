package trongame.player;

import game.core.IDrawable;
import game.core.IPlayer;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Drimal
 */
public class PlayerRenderer implements IDrawable {

    @Override
    public void drawPlayer(Graphics2D g, IPlayer player) {
        int positionsSize = player.getHistoryPositions().size();
        for (int i = 0; i < positionsSize; i++) {
            g.setColor(player.getPlayerColor());
            Point point = player.getHistoryPositions().get(i);
            g.fillRect(point.x, point.y, 10, 10);
        }
    }

}
