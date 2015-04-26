package productfilter;

import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.Mockito.*;

/**
 *
 * @author Drimal
 */
public class AtLeastNOfFilterTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();
    private Filter[] filters;
    private AtLeastNOfFilter instance;

    @Test
    public void testConstructorThrowsIllegalArgumentException() {
        expected.expect(IllegalArgumentException.class);

        filters = new Filter[]{mock(Filter.class)};
        instance = new AtLeastNOfFilter(0, filters);
    }

    @Test
    public void testConstructorThrowsFilterNeverSucceeds() {
        expected.expect(FilterNeverSucceeds.class);

        filters = new Filter[]{mock(Filter.class)};
        instance = new AtLeastNOfFilter(3, filters);
    }

    @Test
    public void testFilterPassWhenAllFiltersPass() {
        expected = ExpectedException.none();

        filters = new Filter[2];
        filters[0] = mock(Filter.class);
        filters[1] = mock(Filter.class);
        when(filters[0].passes(any())).thenReturn(true);
        when(filters[1].passes(any())).thenReturn(true);

        instance = new AtLeastNOfFilter(2, filters);
        boolean passes = instance.passes(any());
        assertEquals("Filter should passes", true, passes);
    }

    @Test
    public void testFilterPassWhenMoreFiltersPass() {
        expected = ExpectedException.none();

        filters = new Filter[3];
        filters[0] = mock(Filter.class);
        filters[1] = mock(Filter.class);
        filters[2] = mock(Filter.class);
        when(filters[0].passes(any())).thenReturn(true);
        when(filters[1].passes(any())).thenReturn(true);
        when(filters[2].passes(any())).thenReturn(true);

        instance = new AtLeastNOfFilter(2, filters);
        boolean passes = instance.passes(any());
        assertEquals("Filter should passes", true, passes);
    }

    @Test
    public void testFilterFailWhenLessThanNFiltersPass() {
        expected = ExpectedException.none();

        filters = new Filter[3];
        filters[0] = mock(Filter.class);
        filters[1] = mock(Filter.class);
        filters[2] = mock(Filter.class);
        when(filters[0].passes(any())).thenReturn(true);
        when(filters[1].passes(any())).thenReturn(true);
        when(filters[2].passes(any())).thenReturn(false);

        instance = new AtLeastNOfFilter(3, filters);
        boolean passes = instance.passes(any());
        assertEquals("Filter should passes", false, passes);
    }
}
