package org.dd.demoapp.hello;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.dd.demoapp.TestDefaults;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class HelloResourceTest implements TestDefaults {

    @Rule
    public ResourceTestRule resources = resourceBuilder().addResource(new HelloResource()).build();

    @Test
    public void testHello_withName() throws Exception {
        String name = "MrTester";
        String response = resources.client().target("/hello").queryParam("name", name).request().get(String.class);
        assertEquals(String.format("Hello %s!", name), response);
    }

    @Test
    public void testHello_withoutName() throws Exception {
        String response = resources.client().target("/hello").request().get(String.class);
        assertEquals("Hello Incognito!", response);
    }
}