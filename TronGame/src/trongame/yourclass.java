package trongame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import trongame.controllers.KeyboardController;

/**
 *
 * @author Drimal
 */
//TODO trida mela v hlavice implementaci MouseListener a MouseMotionListener (nevyuzite)
public class yourclass extends Core {

    Player playerOne;
    Player playerTwo;

    public void init() {
        super.init();
        Window w = sm.getFullScreenWindow();

        //Define players with their controllers (easy future update to mouse listener)
        KeyboardController playerOneController = new KeyboardController(MovementDirection.right, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        playerOne = new Player(40, 40, Color.green, MovementDirection.right, playerOneController, sm.getWidth(), sm.getHeight());
        KeyboardController playerTwoController = new KeyboardController(MovementDirection.left, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
        playerTwo = new Player(600, 440, Color.red, MovementDirection.left, playerTwoController, sm.getWidth(), sm.getHeight());

        //register player keylistener
        w.addKeyListener(playerOne.getKeyboardListener());
        w.addKeyListener(playerTwo.getKeyboardListener());

        //nevyuzite, zadne mouseListenery nebyly implementovani
        //w.addMouseListener(this);
        //w.addMouseMotionListener(this);
    }

    public static void main(String[] args) {
        new yourclass().run();
    }

    public void draw(Graphics2D g) {
        drawBlackBackground(g);
        playerOne.move();
        playerTwo.move();

        //definovano kvuli kolizi 2 hracu
        List<Integer> pathx1 = playerOne.getPathx1();
        List<Integer> pathy1 = playerOne.getPathy1();
        List<Integer> pathx2 = playerTwo.getPathx1();
        List<Integer> pathy2 = playerTwo.getPathy1();

        //Otazka jakym zpusobem resit kolize hracu, predstava bude, ze budu mit list hracu
        /*
         for(Player player : allPlayers){
         if(doslo ke kolizi s nejakym hracem?){
         System.exit(0);
         }
         }
         */
        for (int i = 0; i < pathx1.size(); i++) {
            if (((playerOne.getCurrentPositionX() == pathx1.get(i)) && (playerOne.getCurrentPositionY() == pathy1.get(i))) || ((playerTwo.getCurrentPositionX() == pathx2.get(i)) && (playerTwo.getCurrentPositionY() == pathy2.get(i))) || ((playerOne.getCurrentPositionX() == pathx2.get(i)) && (playerOne.getCurrentPositionY() == pathy2.get(i))) || ((playerTwo.getCurrentPositionX() == pathx1.get(i)) && (playerTwo.getCurrentPositionY() == pathy1.get(i)))) {
                System.exit(0);
            }
        }
        playerOne.addCurrentPositionToPathx1();
        playerOne.addCurrentPositionToPathy1();
        playerTwo.addCurrentPositionToPathx1();
        playerTwo.addCurrentPositionToPathy1();

        //vykresleni cest hracu
        playerOne.drawPath(g);
        playerTwo.drawPath(g);
    }

    private void drawBlackBackground(Graphics2D g) {
        //draw backgro
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }
}
