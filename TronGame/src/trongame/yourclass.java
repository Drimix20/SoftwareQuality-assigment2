package trongame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Ahoj, mrkni se na to. Nejdrive jsem trochu upravil tridy yourClass. A pak zacal tvorit tridu Player. No narazil jsem na neocekavane chovani, kdyz
 * jsem nahrazoval path[x|y][1|2] normalne listem Point. Hra padala. Nevim, jakym zpusobem vyresit ten prunik cest jednotlivych hracu. Idea je takova,
 * ze budu mit zadefinovany list hracu a po kazdeho se vola jeho nadefinovane ovladani, jeho metoda vykresleni, pohybu atd... Celkove je to jakesi moc
 * provazane. Zkus se na to zitra podivat. Ja se k netu dostanu az nekdy kolem 6.
 *
 * @author Drimal
 */
public class yourclass extends Core implements KeyListener, MouseListener,
        MouseMotionListener {

    int centrex2 = 600;
    int centrey2 = 440;
    Player playerOne = new Player(40, 40, Color.green, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
    Player playerTwho = new Player(600, 440, Color.red, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
    PlayerDirection playerOneDirection = PlayerDirection.right;
    PlayerDirection playerTwoDirection = PlayerDirection.left;
    int moveAmount = 5;

    List<Integer> pathx2 = new ArrayList();
    List<Integer> pathy2 = new ArrayList();

    public void init() {
        super.init();

        Window w = sm.getFullScreenWindow();
        //pridani uzivatele1 pro ovladani
        w.addKeyListener(playerOne);
        //ovladani uzivatele2 je v metode KeyPressed v teto tride, implementace z KeyListener.
        //Kdyz jsem dal KeyListener do okna w, tak jsem 2 nastavenimi ovladal vsehny hrace, coz je spatne.
        //Jednim listenerem chci ovladat prave toho hrace.
        w.addKeyListener(this);
        w.addMouseListener(this);
        w.addMouseMotionListener(this);
    }

    public static void main(String[] args) {
        new yourclass().run();
    }

    public void draw(Graphics2D g) {
        playerOne.move();
        switch (playerTwoDirection) {
            case up:
                if (centrey2 > 0) {
                    centrey2 -= moveAmount;
                } else {
                    centrey2 = sm.getHeight();
                }
                break;
            case right:
                if (centrex2 < sm.getWidth()) {
                    centrex2 += moveAmount;
                } else {
                    centrex2 = 0;
                }
                break;
            case down:
                if (centrey2 < sm.getHeight()) {
                    centrey2 += moveAmount;
                } else {
                    centrey2 = 0;
                }
                break;
            case left:
                if (centrex2 > 0) {
                    centrex2 -= moveAmount;
                } else {
                    centrex2 = sm.getWidth();
                }
                break;
        }
        List<Integer> pathx1 = playerOne.getPathx1();
        List<Integer> pathy1 = playerOne.getPathy1();

        //Otazka jakym zpusobem to resit pro tento pripad:
        /*
         for(Player player : allPlayers){
         if(doslo ke kolizi s nejakym hracem?){
         System.exit(0);
         }
         }
         */
        for (int i = 0; i < pathx1.size(); i++) {
            if (((playerOne.getCurrentPositionX() == pathx1.get(i)) && (playerOne.getCurrentPositionY() == pathy1.get(i))) || ((centrex2 == pathx2.get(i)) && (centrey2 == pathy2.get(i))) || ((playerOne.getCurrentPositionX() == pathx2.get(i)) && (playerOne.getCurrentPositionY() == pathy2.get(i))) || ((centrex2 == pathx1.get(i)) && (centrey2 == pathy1.get(i)))) {
                System.exit(0);
            }
        }
        playerOne.addCurrentPositionToPathx1();
        playerOne.addCurrentPositionToPathy1();
        pathx2.add(centrex2);
        pathy2.add(centrey2);

        //nastaveni pozadi
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, sm.getWidth(), sm.getHeight());

        playerOne.draw(g);
        for (int i = 0; i < pathx1.size(); i++) {
            g.setColor(Color.red);
            g.fillRect(pathx2.get(i), pathy2.get(i), 10, 10);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (playerTwoDirection != PlayerDirection.down) {
                playerTwoDirection = PlayerDirection.up;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            if (playerTwoDirection != PlayerDirection.up) {
                playerTwoDirection = PlayerDirection.down;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            if (playerTwoDirection != PlayerDirection.left) {
                playerTwoDirection = PlayerDirection.right;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            if (playerTwoDirection != PlayerDirection.right) {
                playerTwoDirection = PlayerDirection.left;
            }
        }
    }

    private void moveRight() {
        if (playerOneDirection != PlayerDirection.left) {
            playerOneDirection = PlayerDirection.right;
        }
    }

    private void moveLeft() {
        if (playerOneDirection != PlayerDirection.right) {
            playerOneDirection = PlayerDirection.left;
        }
    }

    private void moveDown() {
        if (playerOneDirection != PlayerDirection.up) {
            playerOneDirection = PlayerDirection.down;
        }
    }

    private void moveUp() {
        if (playerOneDirection != PlayerDirection.down) {
            playerOneDirection = PlayerDirection.up;
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent arg0) {

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

    public static enum PlayerDirection {

        up(0), right(1), down(2), left(3);

        private int val;

        private PlayerDirection(int val) {
            this.val = val;
        }
    }

    public class Player implements KeyListener {

        private int currentPositionX;
        private int currentPositionY;
        private Color playerColor;
        private int up;
        private int down;
        private int right;
        private int left;

        List<Integer> pathx1 = new ArrayList();
        List<Integer> pathy1 = new ArrayList();

        public Player(int centrex1, int centrey1, Color playerColor, int up, int down, int left, int right) {
            this.currentPositionX = centrex1;
            this.currentPositionY = centrey1;
            this.playerColor = playerColor;
            this.up = up;
            this.left = left;
            this.down = down;
            this.right = right;
        }

        public int getCurrentPositionX() {
            return currentPositionX;
        }

        public int getCurrentPositionY() {
            return currentPositionY;
        }

        public List<Integer> getPathx1() {
            return pathx1;
        }

        public void addCurrentPositionToPathx1() {
            pathx1.add(currentPositionX);
        }

        public void addCurrentPositionToPathy1() {
            pathy1.add(currentPositionY);
        }

        public List<Integer> getPathy1() {
            return pathy1;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == up) {
                moveUp();
            } else if (e.getKeyCode() == down) {
                moveDown();
            } else if (e.getKeyCode() == right) {
                moveRight();
            } else if (e.getKeyCode() == left) {
                moveLeft();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        public void draw(Graphics2D g) {
            for (int i = 0; i < pathx1.size(); i++) {
                g.setColor(playerColor);
                g.fillRect(pathx1.get(i), pathy1.get(i), 10, 10);
            }
        }

        public void move() {
            switch (playerOneDirection) {
                case up:
                    if (currentPositionY > 0) {
                        currentPositionY -= moveAmount;
                    } else {
                        currentPositionY = sm.getHeight();
                    }
                    break;
                case right:
                    if (currentPositionX < sm.getWidth()) {
                        currentPositionX += moveAmount;
                    } else {
                        currentPositionX = 0;
                    }
                    break;
                case down:
                    if (currentPositionY < sm.getHeight()) {
                        currentPositionY += moveAmount;
                    } else {
                        currentPositionY = 0;
                    }
                    break;
                case left:
                    if (currentPositionX > 0) {
                        currentPositionX -= moveAmount;
                    } else {
                        currentPositionX = sm.getWidth();
                    }
                    break;
            }
        }

    }
}
