package eu.stamp_project.examples;


public class ExampleNoAssertion {
    public int compare(String str, String other) {
        return  str.length() - other.length();
    }

    public boolean isShorter(String str, String other) {
       return compare(str, other) < 0;
    }
}