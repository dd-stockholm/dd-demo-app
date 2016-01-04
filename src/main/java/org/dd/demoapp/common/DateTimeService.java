package org.dd.demoapp.common;

import org.jvnet.hk2.annotations.Service;

import javax.inject.Singleton;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@Singleton
public class DateTimeService {

    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    public LocalDateTime epoch() {
        return LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.of("UTC+01:00"));
    }

}
