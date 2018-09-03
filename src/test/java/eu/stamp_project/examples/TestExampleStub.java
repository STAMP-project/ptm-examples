package eu.stamp_project.examples;

import org.junit.*;
import static org.junit.Assert.*;


public class TestExampleStub {

    @Test
    public void testPrepare() {

        ExampleStub manager = new ExampleStub(5);
        manager.prepare(new Target() {
            public void setValues(int[] values) {
                assertArrayEquals(values, new int[]{0,1,2,3,4});
            }
        });

    }

}