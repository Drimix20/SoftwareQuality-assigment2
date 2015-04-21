package ascii.gradient;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Drimal
 */
public class RadialAsciiGradientTest {

    private AsciiGradientType gradient;

    @Test
    public void testComputeRadialGradient() {
        gradient = new RadialAsciiGradient(20, 15, 20);
        gradient.setColors(new char[]{' ', '.', ',', ':', ';', 'x', 'X', '&', '@'});
        String result = gradient.computeGradient(0, 15);
        assertEquals("&", result);
    }

    @Test
    public void testColorSelection() {
        gradient = new RadialAsciiGradient(20, 15, 20);
        gradient.setColors(new char[]{' ', '.', ',', ':', ';', 'x', 'X', '&', '@'});
        char result = gradient.selectColorForCurrentPosition(10);
        assertEquals(';', result);
    }
}
