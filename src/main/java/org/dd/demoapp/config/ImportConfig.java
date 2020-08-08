package org.dd.demoapp.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.Period;

public class ImportConfig {

    @NotNull
    private String dokumentlistaUrl;

    @NotNull
    private Period period;

    @JsonProperty
    public String getDokumentlistaUrl() {
        return dokumentlistaUrl;
    }

    @JsonProperty
    public void setDokumentlistaUrl(String dokumentlistaUrl) {
        this.dokumentlistaUrl = dokumentlistaUrl;
    }

    @JsonProperty
    public Period getPeriod() {
        return period;
    }

    @JsonProperty
    public void setPeriod(Period period) {
        this.period = period;
    }
}
