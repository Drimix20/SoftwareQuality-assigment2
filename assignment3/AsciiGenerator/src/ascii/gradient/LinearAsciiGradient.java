package ascii.gradient;

/**
 *
 * @author Drimal
 * @author Novak
 */
public class LinearAsciiGradient extends AsciiGradientType {

    private int startPointX;
    private int startPointY;
    private int endPointX;
    private int endPointY;

    public LinearAsciiGradient(int startPointX, int startPointY, int endPointX, int endPointY) {
        this.startPointX = startPointX;
        this.startPointY = startPointY;
        this.endPointX = endPointX;
        this.endPointY = endPointY;
        radius = distanceBetweenTwoPoints(startPointX, startPointY, endPointX, endPointY);
    }

    @Override
    public String computeGradient(int row, int column) {
        double distance = pointDistanceFromLine(column, row);
        return selectColorForCurrentPosition(distance) + "";
    }

    private double pointDistanceFromLine(int x, int y) {
        return (commonLineEquation(x, y)) / distanceBetweenTwoPoints(endPointX, endPointY, startPointX, startPointY);
    }

    private double computeThirdElementOfLineEquation(int x, int y) {
        return ((startPointX - endPointX) * x + (startPointY - endPointY) * y);
    }

    private double commonLineEquation(int x, int y) {
        return ((endPointX - startPointX) * x + (endPointY - startPointY) * y + computeThirdElementOfLineEquation(startPointX, startPointY));
    }

    @Override
    protected char selectColorForCurrentPosition(double distance) {
        int stretchIndex = (int) ((distance / radius) * colors.length);
        return colors[Math.max(0, Math.min(stretchIndex, colors.length - 1))];
    }
}
