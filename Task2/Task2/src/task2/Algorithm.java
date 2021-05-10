package task2;

import java.util.ArrayList;
import java.util.List;
// the imports give you some hints of what we used to implement it
// there are infinite ways to code the same algorithm, you might need these 
// imports or you might do perfectly even with different ones.
// Note that you can only use the Java standard library.
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// WRITE YOUR IMPLEMENTATION IN THE computeFirstFollow METHOD
// YOU CAN CREATE ADDITIONAL HELPER FUNCTIONs, BUT KEEP THEM AS
// STATIC METHODS OF THIS CLASS.
// THIS CLASS WILL BE THE ONLY ONE COPIED AND TESTED IN OUR TESTING
// ENVIRONMENT


public class Algorithm {

    /**
     * Algorithm to compute the first and follow sets.
     *
     * @param  rules derivation rules of the input grammar
     * @return list of result (one for each non-terminal).
     */
    public static List<Result> computeFirstFollow(String startingSymbol, List<Rule> rules) {

        List<Result> results = new ArrayList<Result>();
        Result result = new Result("S","simple begin","d");

        for(Rule c : rules ){
            c.getRight();
        }

        return results;
    }

    
    // DECLARE HELPER STATIC FUNCTIONs HERE (IN CASE YOU NEED THEM)

    public static String helperFunction(String whatever) {
        return "WHAT YOU NEED";
    }
    public static void main(String[] args){
        String start = "S";
        List<Rule> rules = new ArrayList<Rule>();
        Rule rule1 = new Rule("S","simple | begin S end");
        /*
        output should be
        new Result("S", "simple, begin", "end, EOF"),
        new Result("simple", "simple", ""),
        new Result("begin", "begin", ""),
        new Result("end", "end", ""),
         */
        rules.add(rule1);
    }

}


