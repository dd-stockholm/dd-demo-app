package org.dd.demoapp.riksdagen;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class QuestionImportItem {

    private final String riksdagsId;
    private final String title;
    private final boolean decided;
    private Optional<String> description;
    private Optional<Instant> closeTime;

    private QuestionImportItem(String riksdagsId, String title, boolean decided, Optional<String> description, Optional<Instant> closeTime) {
        this.riksdagsId = riksdagsId;
        this.title = title;
        this.decided = decided;
        this.description = description;
        this.closeTime = closeTime;
    }

    public String getRiksdagsId() {
        return riksdagsId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDecided() {
        return decided;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public Optional<Instant> getCloseTime() {
        return closeTime;
    }

    public void setDescription(@NotNull String description) {
        this.description = Optional.of(description);
    }

    public void setCloseTime(@NotNull Instant closeTime) {
        this.closeTime = Optional.of(closeTime);
    }

    @JsonCreator
    public static QuestionImportItem newFromImportData(
        @JsonProperty("id") String id,
        @JsonProperty("titel") String titel,
        @JsonProperty("beslutad") String beslutad,
        @JsonProperty("notis") Optional<String> notis,
        @JsonProperty("beslutsdag") Optional<String> beslutsdag) {

        Optional<Instant> closeTime = beslutsdag.map(d -> LocalDate.parse(d, DateTimeFormatter.ISO_DATE).atStartOfDay().toInstant(ZoneOffset.UTC));
        return new QuestionImportItem(id, titel, beslutad.equals("1"), notis, closeTime);
    }
}
