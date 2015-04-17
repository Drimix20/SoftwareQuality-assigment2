package ascii.gradient;

/**
 *
 * @author martin
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
        radius = distanceBetweenTwoPoints2(startPointX, startPointY, endPointX, endPointY);
    }

    @Override
    String plotGradient() {
        String output = "";
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                double distance = pointDistanceFromLine(column, row);
                char str1 = selectColorForCurrentPosition(distance);
                output += str1;
            }
            output += "\n";
        }
        return output;
    }

    private double pointDistanceFromLine(int x, int y) {
        return commonLineEquation(x, y) / distanceBetweenTwoPoints2(endPointX, endPointY, startPointX, startPointY);
    }

    private double computeThirdElementOfLineEquation(int x, int y) {
        return ((startPointX - endPointX) * x + (startPointY - endPointY) * y);
    }

    private double commonLineEquation(int x, int y) {
        return ((endPointX - startPointX) * x + (endPointY - startPointY) * y + computeThirdElementOfLineEquation(x, y));
    }

    private double distanceBetweenTwoPoints2(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    protected char selectColorForCurrentPosition(double distance) {
        int stretchIndex = (int) ((distance / radius) * colors.length);
        return colors[Math.max(0, Math.min(stretchIndex, colors.length - 1))];
    }
}
