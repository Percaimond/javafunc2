package task2;

import java.util.*;

// the imports give you some hints of what we used to implement it
// there are infinite ways to code the same algorithm, you might need these
// imports or you might do perfectly even with different ones.
// Note that you can only use the Java standard library.

// WRITE YOUR IMPLEMENTATION IN THE computeFirstFollow METHOD
// YOU CAN CREATE ADDITIONAL HELPER FUNCTIONs, BUT KEEP THEM AS
// STATIC METHODS OF THIS CLASS.
// THIS CLASS WILL BE THE ONLY ONE COPIED AND TESTED IN OUR TESTING
// ENVIRONMENT

public class alg23 {

    /**
     * Algorithm to compute the first and follow sets.
     *
     * @param rules derivation rules of the input grammar
     * @return list of result (one for each non-terminal).
     */
    public static List<Result> computeFirstFollow(String startingSymbol, List<Rule> rules) {
        HashMap<String, Result> first = new HashMap<>();
        Set<String> nonTerminals = new HashSet<>();
        Set<String> terminals = new HashSet<>();

        HashMap<String, Boolean> eps = new HashMap<>();

        // IMPLEMENT YOUR ALGORITHM HERE
        computeSetNonTerminals(rules, nonTerminals, first);
        computeSetTerminals(rules, terminals, nonTerminals, first);
        computeEPSTerminals(terminals, eps);
        computeEPSNonTerminals(rules, eps);

        outerInner(rules, eps, first, terminals);
        computeFOLLLOW(rules, first, nonTerminals, eps, startingSymbol);

        return new ArrayList<>(first.values());
    }
    // Compute all Terminals and add all FIRST(terminal, terminal,"") in results

    // Berechnet Menge der NICHT-TERMINALE ohne Duplikate
    public static void computeSetNonTerminals(List<Rule> rules, Set<String> nonTerminals,
                                              HashMap<String, Result> first) {

        for (Rule r : rules) {
            nonTerminals.add(r.getLeft());
            computeFirstNonTerminals(r.getLeft(), first);
        }

    }

    // Berechnet Menge der TERMINALE ohne Duplikate //Hier
    public static void computeSetTerminals(List<Rule> rules, Set<String> terminals, Set<String> nonterminals,
                                           HashMap<String, Result> first) {

        for (Rule r : rules) {

            boolean check = true;

            for (int h = 0; h < r.getRight().size(); h++) {

                for (String nonTerminal : nonterminals) {

                    if (r.getRight().get(h).equals(nonTerminal)) {
                        check = false;

                        break;
                    }
                }

                if (check) {

                    terminals.add(r.getRight().get(h));
                    computeFirstTerminals(r.getRight().get(h), first);

                }
                check = true;

            }
        }

    }

    // Hier
    public static void computeEPSTerminals(Set<String> terminals, HashMap<String, Boolean> eps) {

        for (String t : terminals) {
            eps.put(t, false);
        }

    }

    // Hier
    public static void computeEPSNonTerminals(List<Rule> rules, HashMap<String, Boolean> eps) {
        for (Rule r : rules) {
            eps.put(r.getLeft(), false);
        }

        for (Rule r : rules) {
            if (r.getRight().get(0).equals("EPSILON")) {
                eps.put(r.getLeft(), true);
            }
        }

    }

    public static void computeFirstTerminals(String terminal, HashMap<String, Result> first) {
        if (!first.containsKey(terminal)) {

            first.put(terminal, new Result(terminal, terminal, ""));
        }

    }

    public static void computeFirstNonTerminals(String nonTerminal,
                                                HashMap<String, Result> first) {
        if (!first.containsKey(nonTerminal)) {

            first.put(nonTerminal, new Result(nonTerminal, "", ""));
        }

    }

    public static void outerInner(List<Rule> rules, HashMap<String, Boolean> eps,
                                  HashMap<String, Result> first, Set<String> terminals) {

        do {

            for (Rule rule : rules) {

                boolean check = true;

                for (int i = 0; i < rule.getRight().size(); i++) {
                    if (check) {
                        checkrightrule(rules, first, terminals, rule, i);

                        if (!checkEPS(rule.getRight().get(i), eps) /* || i == rule.getRight().size() - 1 */ ) {
                            check = false;
                        }
                    }

                }
                eps.put(rule.getLeft(), true);

            }

        } while (!first.equals(first));

    }

    private static void checkrightrule(List<Rule> rules, HashMap<String, Result> first, Set<String> terminals, Rule rule, int i) {
        if (!terminals.contains(rule.getRight().get(i))) {

            computeRecursivTerminalFirst(rule.getRight().get(i), rules, terminals, first
            );
        }
        setcontainsrightrule(first, rule, i);
    }

    private static void setcontainsrightrule(HashMap<String, Result> first, Rule rule, int i) {
        Set<String> firstx = first.get(rule.getLeft()).getFirstSet();

        Set<String> firsty = first.get(rule.getRight().get(i)).getFirstSet();

        firstx.addAll(firsty);

        first.put(rule.getLeft(),
                new Result(rule.getLeft(), firstx, first.get(rule.getLeft()).getFollowSet()));
    }

    public static void computeRecursivTerminalFirst(String itIsTerminal, List<Rule> rules, Set<String> terminals,
                                                    HashMap<String, Result> first) {
        int i = 0;

        for (Rule rule : rules) {

            if (rule.getLeft().equals(itIsTerminal)) {

                checkrightrule(rules, first, terminals, rule, i);

            }

        }

    }

    public static boolean checkEPS(String epsKEY, HashMap<String, Boolean> eps) {
        return eps.get(epsKEY);
    }

    public static void computeFOLLLOW(List<Rule> rules, HashMap<String, Result> first, Set<String> nonTerminals,
                                      HashMap<String, Boolean> eps, String startingSymbol) {

        first.get(startingSymbol).getFollowSet().add("EOF");

        do {
            for (Rule r : rules) {

                for (int i = r.getRight().size(); i >= 2; i--) {

                    if (nonTerminals.contains(r.getRight().get(i - 2))) {
                        Set<String> followB = first.get(r.getRight().get(i - 2)).getFollowSet();

                        if (checkEPS(r.getRight().get(i - 1), eps)) {
                            Set<String> firstBeta2 = first.get(r.getRight().get(i)).getFirstSet();
                            followB.addAll(firstBeta2);
                        }

                        Set<String> firstBeta = first.get(r.getRight().get(i - 1)).getFirstSet();
                        followB.addAll(firstBeta);

                        followB.removeIf(s -> s.equals("EPSILON"));
                        first.put(r.getRight().get(i - 2), new Result(r.getRight().get(i - 2),
                                first.get(r.getRight().get(i - 2)).getFirstSet(), followB));
                    }

                }

            }

            for (Rule r : rules) {

                for (int i = r.getRight().size(); i >= 1; i--) {

                    if (nonTerminals.contains(r.getRight().get(i - 1)) && checkUpEPS(i, r, eps)) {

                        Set<String> followA = first.get(r.getLeft()).getFollowSet();
                        Set<String> followB2 = first.get(r.getRight().get(i - 1)).getFollowSet();

                        followB2.addAll(followA);
                        followB2.removeIf(s -> s.equals("EPSILON"));
                        first.put(r.getRight().get(i - 1), new Result(r.getRight().get(i - 1),
                                first.get(r.getRight().get(i - 1)).getFirstSet(), followB2));

                    }

                }
            }
        } while (!first.equals(first));

    }

    public static boolean checkUpEPS(int i, Rule r, HashMap<String, Boolean> eps) {

        for (int j = i; j < r.getRight().size(); j++) {

            if (!checkEPS(r.getRight().get(j), eps)) {

                return false;

            }

        }

        return true;

    }
    public static void main(String[] args) {
        String start = "S";
        List<Rule> rules = new ArrayList<>();

        Rule rule1 = new Rule("S", "simple | begin Send | R");
        Rule rule2 = new Rule("R", "a S Q e | EPSILON");
        Rule rule3 = new Rule("Q", "EPSILON");
        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);

        System.out.print(computeFirstFollow(start, rules));
    }
}