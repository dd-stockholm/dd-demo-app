package org.dd.demoapp.delegate;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.dd.demoapp.TestDefaults;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.GenericType;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class DelegateResourceTest implements TestDefaults {

    private DelegateDAO dao = mock(DelegateDAO.class);
    private DelegateResource resource = new DelegateResource(dao);

    @Rule
    public ResourceTestRule resources = resourceBuilder().addResource(resource).build();

    @Test
    public void testGetDelegates() throws Exception {
        Delegate delegate1 = new DelegateBuilder()
                .name("MyCoolDelegate")
                .description("my cool description")
                .logoUrl("http://testurl.test")
                .webpageUrl("http://testurl.test")
                .build();

        List<Delegate> delegates = Collections.singletonList(delegate1);

        when(dao.getAll()).thenReturn(delegates);

        List<Delegate> response = resources.client().target("/delegate").request().get(new GenericType<List<Delegate>>() {});

        assertEquals(delegates, response);
    }

    @Test
    public void testGetDelegate() throws Exception {
        Delegate delegate = new DelegateBuilder()
                .name("MyCoolDelegate")
                .delegateReference("MyCoolDelegate")
                .description("my cool description")
                .logoUrl("http://testurl.test")
                .webpageUrl("http://testurl.test")
                .build();

        String id = "coolId";

        when(dao.get(id)).thenReturn(delegate);

        Delegate response = resources.client().target("/delegate/" + id).request().get(Delegate.class);

        assertEquals(delegate, response);
    }

    private class DelegateBuilder {

        private Delegate inner = Delegate.newInstance("", "", "", "", "");

        public DelegateBuilder name(String name) {
            inner = Delegate.newInstance(name, inner.getDelegateReference(), inner.getDescription(), inner.getLogoUrl(), inner.getWebpageUrl());
            return this;
        }

        public DelegateBuilder description(String description) {
            inner = Delegate.newInstance(inner.getName(), inner.getDelegateReference(), description, inner.getLogoUrl(), inner.getWebpageUrl());
            return this;
        }

        public DelegateBuilder logoUrl(String logoUrl) {
            inner = Delegate.newInstance(inner.getName(), inner.getDelegateReference(), inner.getDescription(), logoUrl, inner.getWebpageUrl());
            return this;
        }

        public DelegateBuilder webpageUrl(String webpageUrl) {
            inner = Delegate.newInstance(inner.getName(), inner.getDelegateReference(), inner.getDescription(), inner.getLogoUrl(), webpageUrl);
            return this;
        }

        public DelegateBuilder delegateReference(String delegateReference) {
            inner = Delegate.newInstance(inner.getName(), delegateReference, inner.getDescription(), inner.getLogoUrl(), inner.getWebpageUrl());
            return this;
        }

        public Delegate build() {
            return Delegate.newInstance(inner.getName(), inner.getDelegateReference(), inner.getDescription(), inner.getLogoUrl(), inner.getWebpageUrl());
        }
    }
}