package ascii.gradient;

import java.util.Scanner;

/**
 *
 * @author martin
 */
public class ParamsParser {

    private int width;
    private int height;
    private char[] colors;

    public AsciiGradientType parseParams(Scanner inputStream) {
        String[] sizeConfiguration = inputStream.nextLine().split("\\s+");
        width = Integer.parseInt(sizeConfiguration[0]);
        height = Integer.parseInt(sizeConfiguration[1]);
        colors = inputStream.nextLine().toCharArray();
        String[] gradientType = inputStream.nextLine().split("\\s+");

        AsciiGradientType gradient = AsciiGradientFactory.getGradientType(gradientType);
        gradient.setDimension(width, height);
        gradient.setColors(colors);

        return gradient;
    }
}
