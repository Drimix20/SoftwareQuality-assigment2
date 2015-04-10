package game.core;

/**
 *
 * @author Drimal
 */
public enum MovementDirection {

    UP, RIGHT, DOWN, LEFT;

    private int val;

    public MovementDirection turnAround(MovementDirection current, MovementDirection direction) {
        if (direction.equals(LEFT)) {
            return turnLeft(current);
        } else {
            return turnRight(current);
        }
    }

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

    private MovementDirection turnRight(MovementDirection current) {
        switch (current) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
        }
        return current;
    }

    private MovementDirection turnLeft(MovementDirection current) {
        switch (current) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
        }
        return current;
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
