package game.core;

/**
 *
 * @author Drimal
 */
public enum MovementDirection {

    UP, RIGHT, DOWN, LEFT;

    private int val;

    public MovementDirection tryChangeTo(MovementDirection newDirection) {
        switch (newDirection) {
            case UP:
                return moveUp(this);
            case DOWN:
                return moveDown(this);
            case LEFT:
                return moveLeft(this);
            case RIGHT:
                return moveRight(this);
        }
        return this;
    }

    private MovementDirection moveRight(MovementDirection directionOfMove) {
        if (directionOfMove != MovementDirection.LEFT) {
            return MovementDirection.RIGHT;
        }
        return directionOfMove;
    }

    private MovementDirection moveLeft(MovementDirection directionOfMove) {
        if (directionOfMove != MovementDirection.RIGHT) {
            return MovementDirection.LEFT;
        }
        return directionOfMove;
    }

    private MovementDirection moveDown(MovementDirection directionOfMove) {
        if (directionOfMove != MovementDirection.UP) {
            return MovementDirection.DOWN;
        }
        return directionOfMove;
    }

    private MovementDirection moveUp(MovementDirection directionOfMove) {
        if (directionOfMove != MovementDirection.DOWN) {
            return MovementDirection.UP;
        }
        return directionOfMove;
    }
}
