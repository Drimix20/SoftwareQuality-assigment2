package productfilter;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Drimal
 */
public class AtLeastNOfFilterTest {

    private Filter[] filters;
    private AtLeastNOfFilter instance;

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsIllegalArgumentException() {

        filters = new Filter[]{new TestFilter()};
        instance = new AtLeastNOfFilter(0, filters);
    }

    @Test(expected = FilterNeverSucceeds.class)
    public void testConstructorThrowsFilterNeverSucceeds() {

        filters = new Filter[]{mock(Filter.class)};
        instance = new AtLeastNOfFilter(3, filters);
    }

    @Test
    public void testFilterPassWhenAllFiltersPass() {

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

    private class TestFilter implements Filter<Object> {

        @Override
        public boolean passes(Object item) {
            return true;
        }

    }
}
