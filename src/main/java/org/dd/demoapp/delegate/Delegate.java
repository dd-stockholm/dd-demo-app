package org.dd.demoapp.delegate;

public class Delegate {

    private String name;
    private String description;
    private String logoUrl;
    private String webpageUrl;

    public String getName() {
        return name;
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

    public static Delegate newInstance(String name, String description, String logoUrl, String webpageUrl) {
        Delegate delegate = new Delegate();
        delegate.name = name;
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
        return com.google.common.base.Objects.equal(name, delegate.name);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("name", name)
                .toString();
    }
}
