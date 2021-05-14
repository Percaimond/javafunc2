package task2;

import java.awt.geom.RectangularShape;
import java.util.*;
// the imports give you some hints of what we used to implement it
// there are infinite ways to code the same algorithm, you might need these
// imports or you might do perfectly even with different ones.
// Note that you can only use the Java standard library.
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
     * @param rules derivation rules of the input grammar
     * @return list of result (one for each non-terminal).
     */
    public static List<Result> computeFirstFollow(String startingSymbol, List<Rule> rules) {

        List<Result> results = new ArrayList<Result>();//used for results
        HashMap<String, Set<String>> firstSet = new HashMap<String, Set<String>>();//used for firstset

        HashMap<String, Set<String>> followSet = new HashMap<String, Set<String>>();//used for followset
        HashMap<String, Boolean> epsilonRule = new HashMap<String, Boolean>();//used to check if right rule contains epsilon
        Set<String> symbols = new HashSet<>(); //all symbols
        Set<String> terminals = new HashSet<>();
        Set<String> nonTerminals = new HashSet<>();
        boolean isConstant = false;

        for (Rule allSymbols : rules) {//get all symbols
            symbols.add(allSymbols.getLeft());//on the left side
            symbols.addAll(allSymbols.getRight());//on the right side
        }
        symbols.remove("|");//remove |

        for (Rule allSymbols : rules) {//add all nonTerminals
            nonTerminals.add(allSymbols.getLeft());
        }
        for (String symbol : symbols) {//used to add all terminals to their respective firstset
            for (Rule rule : rules) {
                Set<String> tempresults = new HashSet<>();//used to collect all temp results
                if (!(rule.getLeft().equals(symbol))) {
                    terminals.add(symbol);
                    tempresults.add(symbol);
                    firstSet.put(symbol, tempresults);
                    epsilonRule.put(symbol, false);
                } else {
                    nonTerminals.add(symbol);
                    firstSet.put(symbol, tempresults);
                    epsilonRule.put(symbol, checkforepsilonrule(symbol, rules));
                    break;
                }
            }
        }
        terminals.removeAll(nonTerminals);

        for (String symbol : symbols) {//used to add "" to all terminal followsets
            for (Rule rule : rules) {
                Set<String> tempresults = new HashSet<>();//used to collect all temp results
                if ((rule.getLeft().equals(symbol))) {
                    if (symbol.equals(startingSymbol))
                        tempresults.add("EOF");
                    followSet.put(symbol, tempresults);
                    break;
                } else {
                    tempresults.add("");
                    followSet.put(symbol, tempresults);
                }
            }
        }

                for (String key : firstSet.keySet()) {//output all firstsets
                    System.out.println("symbol : " + key + " first : " + firstSet.get(key));
                }
                for (String key : followSet.keySet()) {//output all followsets
                    System.out.println("symbol : " + key + " follow : " + followSet.get(key));
                }
                System.out.println("all symbols: " + symbols);//output all symbols

                for (String key : terminals) {//output all terminals
                    System.out.println("terminal: " + key);
                }
                for (String key : nonTerminals) {//output all nonTerminals
                    System.out.println("nonterminal: " + key);
                }
                for (String pairs : symbols) {//adding all results
                    firstSet.get(pairs);
                    followSet.get(pairs);
                    Result intermediateResult = new Result(pairs, firstSet.get(pairs), followSet.get(pairs));
                    results.add(intermediateResult);
                }
                return results;
            }

    public static Boolean checkforepsilonrule (String nonTerminal, List < Rule > rules){
        for (Rule rule : rules) {
            if (rule.getLeft().equals(nonTerminal)) {
                for (String right : rule.getRight()) {
                    if (right.equals("EPSILON")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static Stream<Rule> ruleReader (String rawLine){
        String[] parts = rawLine.split("->");
        String head = parts[0].trim();
        String tail = parts[1].trim();

        String[] tailParts = null;
        if (tail.contains("|")) {
            tailParts = tail.split("\\|");
        } else {
            tailParts = new String[1];
            tailParts[0] = tail;
        }
        List<Rule> rules = new ArrayList<Rule>();
        for (String tp : tailParts) {
            rules.add(new Rule(head, tp));

        }
        return rules.stream();
    }

    public static void main (String[]args){
        String start = "S";
        String[] lines = {
                "S -> simple | begin Send | R",
                "R -> a S Q e | EPSILON",
                "Q -> EPSILON"
        };

        List<Rule> ruless = Arrays.asList(lines).stream()
                .flatMap((s) -> ruleReader(s))
                .collect(Collectors.toList());
        System.out.print(computeFirstFollow(start, ruless));
    }
}