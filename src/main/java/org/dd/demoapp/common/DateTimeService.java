package org.dd.demoapp.common;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeService {

    public Instant now() {
        return Instant.now();
    }

    public Instant epoch() {
        return Instant.EPOCH;
    }

    public LocalDate today() {
        return LocalDateTime.ofInstant(now(), ZoneId.of("UTC")).toLocalDate();
    }
}
