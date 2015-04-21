package ascii.gradient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Drimal
 */
public class ParamsParserTest {

    private static InputStream stream;
    private static Scanner scanner;

    @Test
    public void testRadialParseParams() throws UnsupportedEncodingException {
        stream = new ByteArrayInputStream("40 30\n .,:;xX&@\nradial 20 15 20\n".getBytes("UTF-8"));
        scanner = new Scanner(stream);

        ParamsParser instance = new ParamsParser();
        AsciiGradientType result = instance.parseParams(scanner);;
        assertTrue(result instanceof RadialAsciiGradient);
    }

    @Test
    public void testParseLinearParams() throws UnsupportedEncodingException {
        stream = new ByteArrayInputStream("60 30\n '\"^+$\nlinear 30 30 0 0".getBytes("UTF-8"));
        scanner = new Scanner(stream);

        ParamsParser instance = new ParamsParser();
        AsciiGradientType result = instance.parseParams(scanner);;
        assertTrue(result instanceof LinearAsciiGradient);
    }
}
