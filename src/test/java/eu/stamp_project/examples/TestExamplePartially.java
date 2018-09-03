package eu.stamp_project.examples;


import org.junit.*;
import static org.junit.Assert.*;

public class TestExamplePartially {

    @Test
    public void testEquals() {
        ExamplePartially ep1 = new ExamplePartially(3);
        ExamplePartially ep2 = new ExamplePartially(3);
        ExamplePartially ep3 = new ExamplePartially(4);

        assertTrue(ep1.equals(ep2));
        assertFalse(ep1 == ep3);
    }
}