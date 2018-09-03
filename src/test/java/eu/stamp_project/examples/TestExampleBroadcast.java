package eu.stamp_project.examples;

import org.junit.*;
import static org.junit.Assert.*;


public class TestExampleBroadcast {

    @Test
    public void testBroadcast() {
        Receiver one = new Receiver();
        Receiver two = new Receiver();
        ExampleBroadcast emitter = new ExampleBroadcast(one, two);
        emitter.emit(5);
        assertEquals(one.getContent(), two.getContent());
    }
}