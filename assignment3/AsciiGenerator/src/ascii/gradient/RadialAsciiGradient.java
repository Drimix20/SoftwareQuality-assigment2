package ascii.gradient;

/**
 *
 * @author martin
 */
public class RadialAsciiGradient extends AsciiGradientType {

    public RadialAsciiGradient(int centerX, int centerY, int radius) {
        super.centerX = centerX;
        super.centerY = centerY;
        super.radius = radius;
    }

    @Override
    public String computeGradient(int row, int column) {
        double dist = distanceBetweenTwoPoints(row, column, centerY, centerX);
        return selectColorForCurrentPosition(dist) + "";
    }
}
