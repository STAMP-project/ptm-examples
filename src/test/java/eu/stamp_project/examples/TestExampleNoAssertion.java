package eu.stamp_project.examples;

import org.junit.*;
import static org.junit.Assert.*;


public class TestExampleNoAssertion {
    @Test
    public void testComparison() {

        String[][] input = {
            {"1", "12"},
            {"12", "123"},
            {"23", "3"}
        };

        for(int pos=0; pos<input.length; pos++) {
            new ExampleNoAssertion().isShorter(input[pos][0], input[pos][1]);
        }

    }
}