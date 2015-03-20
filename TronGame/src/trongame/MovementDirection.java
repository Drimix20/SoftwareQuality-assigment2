package trongame;

/**
 *
 * @author Drimal
 */
public enum MovementDirection {

    up(0), right(1), down(2), left(3);

    private int val;

    private MovementDirection(int val) {
        this.val = val;
    }
}
