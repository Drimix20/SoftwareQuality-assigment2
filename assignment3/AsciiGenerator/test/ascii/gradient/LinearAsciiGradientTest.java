package ascii.gradient;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Drimal
 */
public class LinearAsciiGradientTest {

    private AsciiGradientType gradient;

    @Test
    public void testComputeGradient() {
        gradient = new LinearAsciiGradient(30, 30, 0, 0);
        gradient.setColors(new char[]{' ', '\'', '|', '+', '$'});
        String result = gradient.computeGradient(0, 15);

        assertEquals("+", result);
    }

    @Test
    public void testColorSelection() {
        gradient = new LinearAsciiGradient(30, 30, 0, 0);
        gradient.setColors(new char[]{' ', '\'', '|', '+', '$'});
        char result = gradient.selectColorForCurrentPosition(9);

        assertEquals('\'', result);
    }

}
