package eu.stamp_project.examples;

public class ExamplePartially {

    private int value;

    public ExamplePartially(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ExamplePartially && ((ExamplePartially)other).value == value;
    }
}