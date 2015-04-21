package ascii.gradient;

/**
 *
 * @author Drimal
 */
public class SystemOutWriter {

    private AsciiGradientType gradientType;

    public SystemOutWriter(AsciiGradientType gradientType) {
        this.gradientType = gradientType;
    }

    public void writeToSystemOutput(int width, int height) {
        String output = "";
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                output += gradientType.computeGradient(row, column);
            }
            output += "\n";
        }
        System.out.println(output);
    }
}
