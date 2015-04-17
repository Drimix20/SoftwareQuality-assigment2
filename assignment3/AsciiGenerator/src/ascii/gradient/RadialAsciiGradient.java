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
    public String plotGradient() {
        String output = "";
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                double dist = distanceBetweenTwoPoints(row, column, centerY, centerX);
                output += selectColorForCurrentPosition(dist);
            }
            output += "\n";
        }
        return output;
    }
}
