package eu.stamp_project.examples;


public class ExampleBroadcast {

    private Receiver one, two;

    public ExampleBroadcast(Receiver one, Receiver two) {
        this.one = one;
        this.two = two;
    }

    public void emit(int value) {
        one.receive(value);
        two.receive(value);
    }

}