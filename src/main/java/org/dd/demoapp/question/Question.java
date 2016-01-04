package org.dd.demoapp.question;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

public class Question {

    private final String id;
    private final String question;
    private final String closeTime;

    @JsonCreator
    public Question(@JsonProperty("id") String id, @JsonProperty("question") String question, @JsonProperty("closeTime") String closeTime) {
        this.id = id;
        this.question = question;
        this.closeTime = closeTime;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getCloseTime() {
        return closeTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question that = (Question) o;

        return Objects.equal(this.id, that.id) &&
                Objects.equal(this.question, that.question) &&
                Objects.equal(this.closeTime, that.closeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, question, closeTime);
    }
}
