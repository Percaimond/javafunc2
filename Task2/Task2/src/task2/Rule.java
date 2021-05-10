package task2;

import java.util.Arrays;
import java.util.List;

// DO NOT MODIFY THIS CLASS
// YOUR PROGRAM WILL BE TESTED WITH THIS VERSION OF THE CLASS

public class Rule {

    private final String left;
    private final List<String> right;

    public Rule(String left, String right) {
        this.left = left.trim();
        this.right = Arrays.asList(right.trim().split(" "));
    }

    public String getLeft() {
        return this.left;
    }

    public List<String> getRight() {
        return this.right;
    }

    public String toString() {
        return this.left + " -> " + this.right;
    }

}
