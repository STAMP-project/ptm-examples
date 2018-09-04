package eu.stamp_project.examples;

import org.junit.*;
import static org.junit.Assert.*;

public class TestPartially {

    @Test
    public void testEquals() {
        ExamplePartially a = new ExamplePartially(3);
        ExamplePartially b = new ExamplePartially(3);
        ExamplePartially c = new ExamplePartially(4);
        assertTrue(a.equals(b));
        assertFalse(a == c);

    }

}