package org.dd.demoapp.delegate;

public class Delegate {

    private String name;

    public String getName() {
        return name;
    }

    public static Delegate newInstance(String name) {
        Delegate delegate = new Delegate();
        delegate.name = name;
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
