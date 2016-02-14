package org.dd.demoapp.riksdagen;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class QuestionImportItem {

    private final String riksdagsId;
    private final String title;
    private final boolean decided;
    private final Optional<Instant> closeTime;
    private final Optional<String> documentUrl;
    private Optional<String> description;

    private QuestionImportItem(String riksdagsId, String title, boolean decided, Optional<Instant> closeTime,
                               Optional<String> documentUrl, Optional<String> description) {

        this.riksdagsId = riksdagsId;
        this.title = title;
        this.decided = decided;
        this.closeTime = closeTime;
        this.documentUrl = documentUrl;
        this.description = description;
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

    public Optional<Instant> getCloseTime() {
        return closeTime;
    }

    public Optional<String> getDocumentUrl() {
        return documentUrl;
    }

    public Optional<String> getDescription() {
        return description;
    }

    void setDescription(@NotNull String description) {
        this.description = Optional.of(description);
    }


    @JsonCreator
    public static QuestionImportItem newFromImportData(
        @JsonProperty("id") String id,
        @JsonProperty("titel") String titel,
        @JsonProperty("beslutad") String beslutad,
        @JsonProperty("beslutsdag") Optional<String> beslutsdag,
        @JsonProperty("notis") Optional<String> notis,
        @JsonProperty("filbilaga") Optional<JsonNode> filBilaga) {

        Optional<Instant> closeTime = beslutsdag.map(QuestionImportItem::parseInstant).map(QuestionImportItem::plusOneDay);
        Optional<String> filBilagaUrl = filBilaga.map(n -> n.get("fil").get("url").textValue());

        return new QuestionImportItem(id, titel, beslutad.equals("1"), closeTime, filBilagaUrl, notis);
    }

    private static Instant parseInstant(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE).atStartOfDay().toInstant(ZoneOffset.UTC);
    }

    private static Instant plusOneDay(Instant instant) {
        return instant.plus(Period.ofDays(1));
    }

}
