package org.dd.demoapp.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class ImportConfig {

    @NotNull
    private String dokumentlistaUrl;

    @JsonProperty
    public String getDokumentlistaUrl() {
        return dokumentlistaUrl;
    }

    @JsonProperty
    public void setDokumentlistaUrl(String dokumentlistaUrl) {
        this.dokumentlistaUrl = dokumentlistaUrl;
    }
}
