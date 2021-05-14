package task2;

import java.util.ArrayList;
import java.util.List;
// the imports give you some hints of what we used to implement it
// there are infinite ways to code the same algorithm, you might need these
// imports or you might do perfectly even with different ones.
// Note that you can only use the Java standard library.
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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

        HashMap<String, Set<String>> first = new HashMap<String, Set<String>>();
        HashMap<String, Boolean> eps = new HashMap<String, Boolean>();

        Set<String> left_side  = new HashSet<String>();
        Set<String> right_side = new HashSet<String>();

        for (Rule rule : rules) {
            left_side.add(rule.getLeft());
            right_side.addAll(rule.getRight());
            if(rule.getRight().contains("EPSILON")) {
                eps.put(rule.getLeft(), true);
            } else {
                if(eps.get(rule.getLeft()) == null) {
                    eps.put(rule.getLeft(), false);
                }
            }
        }

        left_side.add(startingSymbol);

        Set<String> non_terminals  = new HashSet<String>();
        non_terminals.addAll(left_side);
        Set<String> terminals  = new HashSet<String>();
        terminals.addAll(right_side);
        terminals.removeAll(non_terminals);

        for (String terminal : terminals) {
            eps.put(terminal, false);

            Set<String> set = new HashSet<>(Arrays.asList(terminal));
            first.put(terminal, set);
        }

        for (String non_terminal : non_terminals) {
            Set<String> set = Collections.emptySet();
            first.put(non_terminal, set);
        }

        int upper_limit = (terminals.size() + non_terminals.size()) * 3;

        for (int i = 0; i < upper_limit; i++) {
            outer:
            for (Rule rule : rules) {
                for (int j = 0; j < rule.getRight().size(); j++) {
                    String left_symbol = rule.getLeft();
                    String right_symbol = rule.getRight().get(j);

                    Set<String> set = new HashSet<String>();
                    set.addAll(first.get(left_symbol));
                    set.addAll(first.get(right_symbol));
                    first.put(left_symbol, set);

                    if(eps.get(right_symbol) == false) {
                        continue outer;
                    }
                }
                eps.put(rule.getLeft(), true);
            }
        }






        HashMap<String, Set<String>> follow = new HashMap<String, Set<String>>();

        Set<String> all_symbols = new HashSet<String>();
        all_symbols.addAll(left_side);
        all_symbols.addAll(right_side);

        for (String symbol : all_symbols) {
            Set<String> set = Collections.emptySet();
            if(symbol.equals(startingSymbol)) {
                set = new HashSet<>(Arrays.asList("EOF"));
            }
            follow.put(symbol, set);
        }


        for (int o = 0; o < upper_limit; o++) {
            for (String non_terminal : non_terminals) {
                for (Rule rule : rules) {

                    for (int i = 0; i < rule.getRight().size(); i++) {
                        if(rule.getRight().get(i).equals(non_terminal)) {
                            try {

                                boolean crash = false;

                                int k2 = 1;

                                try {
                                    while(eps.get(rule.getRight().get(i+k2))) {
                                        k2++;
                                    }
                                } catch (Exception e) {
                                    crash = true;
                                }

                                Set<String> set = new HashSet<String>();

                                for (int v = 0; v < rule.getRight().size() - i - 1; v++) {
                                    set.addAll(first.get(rule.getRight().get(i+v+1)));
                                    if(eps.get(rule.getRight().get(i+v+1)) != true) {
                                        break;
                                    }
                                }

                                set.remove("EPSILON");
                                set.addAll(follow.get(non_terminal));


                                follow.put(non_terminal, set);

                                if(crash) {
                                    new Long("");
                                }


                            } catch (Exception e) {
                                Set<String> set = new HashSet<String>();

                                set.addAll(follow.get(rule.getLeft()));
                                set.addAll(follow.get(non_terminal));

                                follow.put(non_terminal, set);
                            }
                        }
                    }
                }
            }

        }

        for (String symbol : all_symbols) {
            Result result = new Result(symbol, first.get(symbol), follow.get(symbol));
            results.add(result);
        }


        return results;
    }




    // DECLARE HELPER STATIC FUNCTIONs HERE (IN CASE YOU NEED THEM)

    public static String helperFunction(String whatever) {
        return "WHAT YOU NEED";
    }


}


