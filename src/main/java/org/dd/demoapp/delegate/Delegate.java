package org.dd.demoapp.delegate;

import com.google.common.base.Objects;

public class Delegate {

    private String name;
    private String partyId;
    private String description;
    private String logoUrl;
    private String webpageUrl;

    public String getName() {
        return name;
    }

    public String getPartyId() {
        return partyId;
    }

    public String getDescription() {
        return description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getWebpageUrl() {
        return webpageUrl;
    }

    public static Delegate newInstance(String name, String partyId, String description, String logoUrl, String webpageUrl) {
        Delegate delegate = new Delegate();
        delegate.name = name;
        delegate.partyId = partyId;
        delegate.description = description;
        delegate.logoUrl = logoUrl;
        delegate.webpageUrl = webpageUrl;
        return delegate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delegate delegate = (Delegate) o;
        return Objects.equal(name, delegate.name) &&
                Objects.equal(partyId, delegate.partyId) &&
                Objects.equal(description, delegate.description) &&
                Objects.equal(logoUrl, delegate.logoUrl) &&
                Objects.equal(webpageUrl, delegate.webpageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, partyId, description, logoUrl, webpageUrl);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("partyId", partyId)
                .add("description", description)
                .add("logoUrl", logoUrl)
                .add("webpageUrl", webpageUrl)
                .toString();
    }
}
