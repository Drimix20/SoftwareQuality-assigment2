package trongame.ournew;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.util.List;
import trongame.ScreenManager;

/**
 *
 * @author Drimal
 */
public class WindowRenderer {

    private ScreenManager sm;

    public WindowRenderer() {
        sm = new ScreenManager();
        DisplayMode dm = sm.findFirstCompatibaleMode();
        sm.setFullScreen(dm);
    }

    public void draw(List<IPlayer> players) {
        Graphics2D g = sm.getGraphics();
        drawBlackBackground(g);

        for (IPlayer player : players) {
            player.drawPath(g);
        }
        g.dispose();
        sm.update();
    }

    private void drawBlackBackground(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
    }

    public void restoreGameScreen() {
        sm.restoreScreen();
    }

    public int getScreenWidth() {
        return sm.getWidth();
    }

    public int getScreenHeight() {
        return sm.getHeight();
    }

    public Window creteGameWindow() {
        Window w = sm.getFullScreenWindow();
        w.setFont(new Font("Arial", Font.PLAIN, 20));
        w.setBackground(Color.WHITE);
        w.setForeground(Color.RED);
        w.setCursor(w.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));

        return w;
    }

}
