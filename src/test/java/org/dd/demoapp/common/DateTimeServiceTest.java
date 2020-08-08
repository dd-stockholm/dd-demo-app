package org.dd.demoapp.common;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DateTimeServiceTest {

    @Test
    public void testNow() throws Exception {
        long now = System.currentTimeMillis();
        assertThat(new DateTimeService().now().toEpochMilli(), allOf(lessThan(now + 1000L), greaterThan(now - 1000L)));
    }

    @Test
    public void testEpoch() throws Exception {
        assertThat(new DateTimeService().epoch().toEpochMilli(), equalTo(0L));
    }

    @Test
    public void testToday() throws Exception {
        new DateTimeService().today();
    }
}