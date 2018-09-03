package eu.stamp_project.examples;

import org.junit.*;
import static org.junit.Assert.*;


public class TestExampleUnsupported {

    @Test
    public void testUnsupported() {
        try {
            new ExampleUnsupported().unsuported();
        }
        catch(UnsupportedOperationException exc) {}
    }

}