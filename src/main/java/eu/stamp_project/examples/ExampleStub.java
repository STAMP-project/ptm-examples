package eu.stamp_project.examples;

import java.util.stream.IntStream;

public class ExampleStub {

    private int count;

    public ExampleStub(int count) {
        this.count = Math.max(0, count);
    }

    public void prepare(Target target) {
        target.setValues(IntStream.range(0,count).toArray());
    }

}