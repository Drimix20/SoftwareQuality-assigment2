package ascii.gradient;

import org.junit.Test;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Drimal
 */
public class SystemOutWriterTest {

    private static AsciiGradientType gradient;

    @Test
    public void testComputationIteration() {
        gradient = mock(RadialAsciiGradient.class);
        when(gradient.computeGradient(anyInt(), anyInt())).thenReturn("");

        SystemOutWriter instance = new SystemOutWriter(gradient);
        instance.writeToSystemOutput(2, 2);

        verify(gradient, times(4)).computeGradient(anyInt(), anyInt());
    }

    @Test
    public void testOutput() {
        gradient = mock(RadialAsciiGradient.class);
        when(gradient.computeGradient(0, 0)).thenReturn("?");
        when(gradient.computeGradient(0, 1)).thenReturn(".");
        when(gradient.computeGradient(1, 0)).thenReturn(" ");
        when(gradient.computeGradient(1, 1)).thenReturn("-");

        SystemOutWriter instance = new SystemOutWriter(gradient);
        instance.writeToSystemOutput(2, 2);

        verify(gradient, times(1)).computeGradient(0, 0);
        verify(gradient, times(1)).computeGradient(0, 1);
        verify(gradient, times(1)).computeGradient(1, 0);
        verify(gradient, times(1)).computeGradient(1, 1);
    }

}
