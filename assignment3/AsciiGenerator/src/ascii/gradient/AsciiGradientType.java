package ascii.gradient;

/**
 *
 * @author martin
 */
public abstract class AsciiGradientType {

    protected int width;
    protected int height;
    protected char[] colors;

    protected int centerX;
    protected int centerY;
    protected double radius;

    public void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setColors(char[] colors) {
        this.colors = colors;
    }

    abstract String computeGradient(int row, int column);

    protected char selectColorForCurrentPosition(double distance) {
        int stretchIndex = (int) ((distance / radius) * colors.length);
        return colors[Math.min(stretchIndex, colors.length - 1)];
    }

    protected double distanceBetweenTwoPoints(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
