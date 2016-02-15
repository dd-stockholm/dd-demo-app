package org.dd.demoapp.common;

import java.time.Instant;

public class DateTimeService {

    public Instant now() {
        return Instant.now();
    }

    public Instant epoch() {
        return Instant.EPOCH;
    }

}
