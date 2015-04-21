package ascii.gradient;

import java.util.Scanner;

/**
 *
 * @author martin
 */
public class AsciiGradient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ParamsParser parser = new ParamsParser();
        AsciiGradientType gradient = parser.parseParams(new Scanner(System.in));
        SystemOutWriter writer = new SystemOutWriter(gradient);
        writer.writeToSystemOutput(parser.getWidth(), parser.getHeight());
    }
}
