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
        try {
            width = Integer.parseInt(sizeConfiguration[0]);
            height = Integer.parseInt(sizeConfiguration[1]);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid dimension input");
        }
        colors = inputStream.nextLine().toCharArray();
        String[] gradientConfiguration = inputStream.nextLine().split("\\s+");

        AsciiGradientType gradient = AsciiGradientFactory.getGradientType(gradientConfiguration);
        gradient.setDimension(width, height);
        gradient.setColors(colors);

        return gradient;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
