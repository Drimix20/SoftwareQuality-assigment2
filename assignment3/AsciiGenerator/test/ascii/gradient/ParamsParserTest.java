package ascii.gradient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Drimal
 */
public class ParamsParserTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    private static InputStream stream;
    private static Scanner scanner;

    @Test
    public void testRadialParseParams() throws UnsupportedEncodingException {
        expected = ExpectedException.none();

        stream = new ByteArrayInputStream("40 30\n .,:;xX&@\nradial 20 15 20\n".getBytes("UTF-8"));
        scanner = new Scanner(stream);

        ParamsParser instance = new ParamsParser();
        AsciiGradientType result = instance.parseParams(scanner);
        assertTrue(result instanceof RadialAsciiGradient);
        assertEquals(40, instance.getWidth());
        assertEquals(30, instance.getHeight());
    }

    @Test
    public void testRadialParseBadParams() throws UnsupportedEncodingException {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Invalid gradient type");

        stream = new ByteArrayInputStream("40 30\n .,:;xX&@\nradal 20 15 20\n".getBytes("UTF-8"));
        scanner = new Scanner(stream);

        ParamsParser instance = new ParamsParser();
        AsciiGradientType result = instance.parseParams(scanner);
    }

    @Test
    public void testRadialParseBadDimension() throws UnsupportedEncodingException {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Invalid dimension input");

        stream = new ByteArrayInputStream("a 30\n .,:;xX&@\nradial 20 15 20\n".getBytes("UTF-8"));
        scanner = new Scanner(stream);

        ParamsParser instance = new ParamsParser();
        AsciiGradientType result = instance.parseParams(scanner);
    }

    @Test
    public void testRadialParseBadConfiguration() throws UnsupportedEncodingException {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Invalid gradient configuration");

        stream = new ByteArrayInputStream("40 30\n .,:;xX&@\nradial 20 20\n".getBytes("UTF-8"));
        scanner = new Scanner(stream);

        ParamsParser instance = new ParamsParser();
        AsciiGradientType result = instance.parseParams(scanner);
    }

    @Test
    public void testParseLinearParams() throws UnsupportedEncodingException {
        expected = ExpectedException.none();

        stream = new ByteArrayInputStream("60 30\n '\"^+$\nlinear 30 30 0 0".getBytes("UTF-8"));
        scanner = new Scanner(stream);

        ParamsParser instance = new ParamsParser();
        AsciiGradientType result = instance.parseParams(scanner);
        assertTrue(result instanceof LinearAsciiGradient);
        assertEquals(60, instance.getWidth());
        assertEquals(30, instance.getHeight());
    }

    @Test
    public void testParseLinearBadParams() throws UnsupportedEncodingException {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Invalid gradient type");

        stream = new ByteArrayInputStream("60 30\n '\"^+$\nnear 30 30 0 0".getBytes("UTF-8"));
        scanner = new Scanner(stream);

        ParamsParser instance = new ParamsParser();
        AsciiGradientType result = instance.parseParams(scanner);
    }

    @Test
    public void testParseLinearBadDimension() throws UnsupportedEncodingException {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Invalid dimension input");

        stream = new ByteArrayInputStream("60 1'4\n '\"^+$\nlinear 30 30 0 0".getBytes("UTF-8"));
        scanner = new Scanner(stream);

        ParamsParser instance = new ParamsParser();
        AsciiGradientType result = instance.parseParams(scanner);
    }

    @Test
    public void testParseLinearBadConfiguration() throws UnsupportedEncodingException {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Invalid gradient configuration");

        stream = new ByteArrayInputStream("60 30\n '\"^+$\nlinear 30 0 0".getBytes("UTF-8"));
        scanner = new Scanner(stream);

        ParamsParser instance = new ParamsParser();
        AsciiGradientType result = instance.parseParams(scanner);
    }
}
