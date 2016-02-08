package org.dd.demoapp.delegate;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Delegate {

    private String name;
    private String delegateReference;
    private String description;
    private String logoUrl;
    private String webpageUrl;

    public String getName() {
        return name;
    }

    public String getDelegateReference() {
        return delegateReference;
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

    public static Delegate newInstance(String name, String delegateReference, String description, String logoUrl, String webpageUrl) {
        Delegate delegate = new Delegate();
        delegate.name = name;
        delegate.delegateReference = delegateReference;
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
                Objects.equal(delegateReference, delegate.delegateReference) &&
                Objects.equal(description, delegate.description) &&
                Objects.equal(logoUrl, delegate.logoUrl) &&
                Objects.equal(webpageUrl, delegate.webpageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, delegateReference, description, logoUrl, webpageUrl);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("delegateReference", delegateReference)
                .add("description", description)
                .add("logoUrl", logoUrl)
                .add("webpageUrl", webpageUrl)
                .toString();
    }
}
