package trongame.ournew;

/**
 *
 * @author Drimal
 */
public enum MovementDirection {

    UP(0), RIGHT(1), DOWN(2), LEFT(3);

    private int val;

    private MovementDirection(int val) {
        this.val = val;
    }
}
