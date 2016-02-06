package org.dd.demoapp.question;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.time.Instant;

public class Question {

    private String id;
    private String question;
    private Instant closeTime;

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Instant getCloseTime() {
        return closeTime;
    }

    public static Question newInstance(String id, String question, Instant closeTime) {
        Question q = new Question();
        q.id = id;
        q.question = question;
        q.closeTime = closeTime;
        return q;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("question", question)
                .add("closeTime", closeTime)
                .toString();
    }
}
