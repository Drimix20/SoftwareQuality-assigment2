package ascii.gradient;

/**
 *
 * @author Drimal
 * @author Novak
 */
public class AsciiGradientFactory {

    public static AsciiGradientType getGradientType(String[] params) {
        AsciiGradientType gradient = null;
        String gradientType = params[0];
        try {
            switch (gradientType) {
                case "radial":
                    gradient = new RadialAsciiGradient(
                            parseInteger(params[1]),
                            parseInteger(params[2]),
                            parseInteger(params[3])
                    );
                    break;
                case "linear":
                    gradient = new LinearAsciiGradient(
                            parseInteger(params[1]),
                            parseInteger(params[2]),
                            parseInteger(params[3]),
                            parseInteger(params[4])
                    );
                    break;
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid gradient configuration");
        }

        if (gradient == null) {
            throw new IllegalArgumentException("Invalid gradient type");
        }

        return gradient;
    }

    private static int parseInteger(String number) {
        return Integer.parseInt(number);
    }
}
