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
        Delegate delegate1 = new DelegateBuilder().name("MyCoolDelegate").build();
        List<Delegate> delegates = Collections.singletonList(delegate1);

        when(dao.getAll()).thenReturn(delegates);

        List<Delegate> response = resources.client().target("/delegate/all").request().get(new GenericType<List<Delegate>>() {});

        assertEquals(delegates, response);
    }

    private class DelegateBuilder {

        private String name;

        public Delegate build() {
            return Delegate.newInstance(name);
        }

        public DelegateBuilder name(String name) {
            this.name = name;
            return this;
        }
    }
}