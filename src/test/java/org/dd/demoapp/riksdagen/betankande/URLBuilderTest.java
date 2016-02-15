package org.dd.demoapp.riksdagen.betankande;

import org.dd.demoapp.TestDefaults;
import org.junit.Test;

import java.net.URL;
import java.time.*;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class URLBuilderTest implements TestDefaults {

    @Test
    public void testAsUrl() throws Exception {

        LocalDate currentTime = LocalDate.of(2016, 2, 12);
        Optional<URL> urlOpt = new URLBuilder("http://data.riksdagen.se/dokumentlista/", Period.ofMonths(1)).asUrl(currentTime);

        String expected = "http://data.riksdagen.se/dokumentlista/?doktyp=bet&from=2016-01-12&tom=2016-02-12&sort=datum&sortorder=desc&utformat=json";

        assertThat(urlOpt, new OptionalIsPresentMatcher<>());
        assertThat(urlOpt.get().toString(), equalTo(expected));

    }
}