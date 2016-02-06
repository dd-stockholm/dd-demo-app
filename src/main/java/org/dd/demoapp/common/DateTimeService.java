package org.dd.demoapp.common;

import org.jvnet.hk2.annotations.Service;

import javax.inject.Singleton;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@Singleton
public class DateTimeService {

    public Instant now() {
        return Instant.now();
    }

    public Instant epoch() {
        return Instant.EPOCH;
    }

}
