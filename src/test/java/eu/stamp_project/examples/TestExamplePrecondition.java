package eu.stamp_project.examples;

import org.junit.*;
import static org.junit.Assert.*;


public class TestExamplePrecondition {

    @Test
    public void testOperation() {
        ExamplePrecondition op = new ExamplePrecondition();
        assertEquals(op.doOperate(2.0), 2.0, 0.001);
    }
}
