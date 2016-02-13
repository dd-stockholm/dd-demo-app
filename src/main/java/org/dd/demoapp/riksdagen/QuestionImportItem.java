package org.dd.demoapp.riksdagen;

import java.time.Instant;

public class QuestionImportItem {

    private final String title;
    private final String description;
    private final Instant closeTime;

    public QuestionImportItem(String title, String description, Instant closeTime) {
        this.title = title;
        this.description = description;
        this.closeTime = closeTime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Instant getCloseTime() {
        return closeTime;
    }
}
