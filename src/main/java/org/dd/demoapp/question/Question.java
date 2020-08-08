package org.dd.demoapp.question;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.time.Instant;

public class Question {

    private String id;
    private String riksdagsId;
    private String question;
    private Instant closeTime;

    public String getId() {
        return id;
    }

    public String getRiksdagsId() {
        return riksdagsId;
    }

    public String getQuestion() {
        return question;
    }

    public Instant getCloseTime() {
        return closeTime;
    }

    public static Question newInstance(String id, String riksdagsId, String question, Instant closeTime) {
        Question q = new Question();
        q.id = id;
        q.riksdagsId = riksdagsId;
        q.question = question;
        q.closeTime = closeTime;
        return q;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, riksdagsId, question, closeTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Question other = (Question) obj;
        return Objects.equal(this.id, other.id)
            && Objects.equal(this.riksdagsId, other.riksdagsId)
            && Objects.equal(this.question, other.question)
            && Objects.equal(this.closeTime, other.closeTime);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .add("riksdagsId", riksdagsId)
            .add("question", question)
            .add("closeTime", closeTime)
            .toString();
    }
}
