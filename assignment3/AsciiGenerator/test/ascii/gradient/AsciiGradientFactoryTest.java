package ascii.gradient;

import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Drimal
 */
public class AsciiGradientFactoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetRadialGradient() {
        String[] params = new String[]{"radial", "20", "15", "20"};
        AsciiGradientType result = AsciiGradientFactory.getGradientType(params);
        assertTrue(result instanceof RadialAsciiGradient);
    }

    @Test
    public void testBadRadialGradient() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid gradient type");

        String[] params = new String[]{"rad", "20", "15", "20"};
        AsciiGradientFactory.getGradientType(params);
    }

    @Test
    public void testBadRadialGradientConfiguration() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid gradient configuration");

        String[] params = new String[]{"radial", "ab", "15", "20"};
        AsciiGradientFactory.getGradientType(params);
    }

    @Test
    public void testGetLinearGradient() {
        String[] params = new String[]{"linear", "30", "30", "0", "0"};
        AsciiGradientType result = AsciiGradientFactory.getGradientType(params);
        assertTrue(result instanceof LinearAsciiGradient);
    }

    @Test
    public void testBadLinearGradient() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid gradient type");

        String[] params = new String[]{"ear", "30", "30", "0", "0"};
        AsciiGradientFactory.getGradientType(params);
    }

    @Test
    public void testBadLinearGradientConfiguration() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid gradient configuration");

        String[] params = new String[]{"linear", "30", "ab", "0", "0"};
        AsciiGradientFactory.getGradientType(params);
    }
}
