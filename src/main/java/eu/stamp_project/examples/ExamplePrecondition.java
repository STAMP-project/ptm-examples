package eu.stamp_project.examples;

public class ExamplePrecondition {

    public void check(double value) throws IllegalArgumentException {
        if(value < 0) {
            throw new IllegalArgumentException();
        }
    }

    public double doOperate(double operand) throws IllegalArgumentException {
        check(operand);
        return Math.sqrt(2*operand);
    }
    
}