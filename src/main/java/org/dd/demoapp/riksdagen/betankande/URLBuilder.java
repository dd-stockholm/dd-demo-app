package org.dd.demoapp.riksdagen.betankande;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

/*
* Example: http://data.riksdagen.se/dokumentlista/?doktyp=bet&from=2016-02-01&tom=2016-02-31&sort=datum&sortorder=desc&utformat=json&a=s#soktraff
*/
public class URLBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(URLBuilder.class);
    private final UriBuilder internal;
    private final Period period;

    public URLBuilder(String basePath, Period period) {
        this.period = period;
        internal = UriBuilder.fromPath(basePath)
            .queryParam("doktyp", "bet")
            .queryParam("from", "{from}")
            .queryParam("tom", "{to}")
            .queryParam("sort", "datum")
            .queryParam("sortorder", "desc")
            .queryParam("utformat", "json");
    }

    public Optional<URL> asUrl(LocalDate to) {
        LocalDate from = to.minus(period);
        try {
            return Optional.of(internal.build(from, to).toURL());
        } catch (MalformedURLException e) {
            LOGGER.error("Failed to build URL!", e);
            return Optional.empty();
        }
    }
}
