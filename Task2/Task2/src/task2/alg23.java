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


public class alg23 {

    /**
     * Algorithm to compute the first and follow sets.
     *
     * @param  rules derivation rules of the input grammar
     * @return list of result (one for each non-terminal).
     */
    public static List<Result> computeFirstFollow(String startingSymbol, List<Rule> rules) {

        List<Result> results = new ArrayList<Result>();

        //List<ArrayList<String>> FIRST = new ArrayList<ArrayList<String>>();
        //List<Boolean> EPS = new ArrayList<Boolean>();
        HashMap<String, HashSet<String>> FIRST = new HashMap<String, HashSet<String>>();
        HashMap<String, Boolean> EPS = new HashMap<String, Boolean>();

        Set<String> terminals = new HashSet<String>();
        Set<String> nonterminals = new HashSet<String>();

        nonterminals.add(startingSymbol);
        for (Rule rule : rules) {
            nonterminals.add(rule.getLeft());
            for (String right: rule.getRight()) {
                terminals.add(right);
            }
        }

        terminals.removeAll(nonterminals);

        for (String terminal : terminals) {
            EPS.put(terminal, false);

            HashSet<String> newFirst = new HashSet<String>();
            newFirst.add(terminal);
            FIRST.put(terminal, newFirst);
        }

        for (String nonterminal : nonterminals) {
            EPS.put(nonterminal, hasEpsilonRule(nonterminal, rules));

            HashSet<String> newFirst = new HashSet<String>();
            FIRST.put(nonterminal, newFirst);
        }

        Boolean progressMade = true;

        while(progressMade == true) {
            progressMade = false;

            for (Rule rule : rules) {
                String X = rule.getLeft();
                Boolean broke = false;

                for (String right: rule.getRight()) {

                    HashSet<String> newFirst = new HashSet<String>();
                    newFirst.addAll(FIRST.get(X));
                    newFirst.addAll(FIRST.get(right));

                    if(!newFirst.equals(FIRST.get(X))) {
                        progressMade = true;
                        FIRST.put(X, newFirst);
                    }

                    if(EPS.get(right) == false) {
                        broke = true;
                        break;
                    }
                }

                if(broke) {
                    continue;
                }

                if(EPS.get(rule.getLeft()) == false) {
                    progressMade = true;
                    EPS.put(rule.getLeft(), true);
                }
            }
        }



        HashMap<String, HashSet<String>> FOLLOW = new HashMap<String, HashSet<String>>();

        Set<String> symbols = new HashSet<String>();
        symbols.addAll(terminals);
        symbols.addAll(nonterminals);

        for (String symbol : symbols) {
            HashSet<String> newFollow = new HashSet<String>();
            if(symbol.equals(startingSymbol)) {
                newFollow.add("EOF");
            }
            FOLLOW.put(symbol, newFollow);
        }

        progressMade = true;
        int p = 0;
        while(progressMade == true) {
            progressMade = false;

            for (String nonterminal : nonterminals) {

                for (Rule rule : rules) {
                    //System.out.println("IIIIIIIIIIIIIIIIIIIIIIIII " + nonterminal);
                    //System.out.println(rule.toString());
                    for (int i = 0; i < rule.getRight().size(); i++) {
                        if(rule.getRight().get(i).equals(nonterminal)) {

                            //System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
                            //System.out.println("symbol: " + symbol);
                            if(i == rule.getRight().size()-1) {
                                HashSet<String> newFollow = new HashSet<String>();

                                newFollow.addAll(FOLLOW.get(rule.getLeft()));
                                newFollow.addAll(FOLLOW.get(nonterminal));

                                if(!newFollow.equals(FOLLOW.get(nonterminal))) {
                                    //System.out.println("1");
                                    //System.out.println(newFollow);
                                    //System.out.println(FOLLOW.get(nonterminal));
                                    progressMade = true;
                                    FOLLOW.put(nonterminal, newFollow);
                                }
                                //System.out.println(FOLLOW.get(nonterminal));


                                //System.out.println("rule1: " + rule.toString());
                            } else {

                                List<String> beta =  rule.getRight().subList(i+1, rule.getRight().size());
                                boolean eps_beta = true;
                                for (String x : beta) {
                                    if(EPS.get(x) == false) {
                                        eps_beta = false;
                                        break;
                                    }
                                }

                                if(eps_beta) {
                                    HashSet<String> newFollow = new HashSet<String>();

                                    newFollow.addAll(FOLLOW.get(rule.getLeft()));
                                    newFollow.addAll(FOLLOW.get(nonterminal));

                                    if(!newFollow.equals(FOLLOW.get(nonterminal))) {
                                        //System.out.println("2");
                                        progressMade = true;
                                        FOLLOW.put(nonterminal, newFollow);
                                    }
                                } else {

                                    int k = 1;
                                    while(EPS.get(rule.getRight().get(i+k)) == true) {
                                        k++;
                                    }

									/*
									System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHH");
									System.out.println(p);
									System.out.println(nonterminal);
									System.out.println(rule.toString());
									*/

                                    HashSet<String> newFollow = new HashSet<String>();
                                    newFollow.addAll(FIRST.get(rule.getRight().get(i+k)));
                                    newFollow.addAll(FIRST.get(rule.getRight().get(i+1)));
                                    newFollow.remove("EPSILON");
                                    newFollow.addAll(FOLLOW.get(nonterminal));

                                    if(!newFollow.equals(FOLLOW.get(nonterminal))) {
                                        //System.out.println("3");
                                        //System.out.println(newFollow);
                                        //System.out.println(FOLLOW.get(nonterminal));
                                        progressMade = true;
                                        FOLLOW.put(nonterminal, newFollow);
                                    }

                                    //System.out.println("New follow for " + nonterminal + ": " + FOLLOW.get(nonterminal));

                                }
                            }

                        }
                    }
                }
            }

	        /*
	        System.out.println("FOLLOW");
	        System.out.println(FOLLOW);
	        System.out.println("STEP NUMBER " + ++p);
	        */
        }

        for (String symbol : symbols) {
            Result result = new Result(symbol, FIRST.get(symbol), FOLLOW.get(symbol));
            results.add(result);
        }



        /*
         *
        progressMade = true;

        while(progressMade == true) {
        	progressMade = false;

            for (Rule rule : rules) {
            	String X = rule.getLeft();

                for (String right: rule.getRight()) {


        		}
    		}
		}
        */


        /*
        progressMade = true;
        i++;
        if(i==10) {
        	return results;
        }
        */

    	/*
    	System.out.println("STEP " + i);
    	System.out.println("EPS" + EPS);
    	System.out.println("FIRST" + FIRST);
    	*/


        System.out.println("AAAAAAAAAAAAAAAAAAAAAA");

        //System.out.println(terminals);
        //System.out.println(nonterminals);

        System.out.println(FIRST);
        System.out.println(FOLLOW);
        System.out.println(EPS);
        System.out.println("BBBBBBBBBBBBBBBBBBBBBB");




        return results;
    }


    // DECLARE HELPER STATIC FUNCTIONs HERE (IN CASE YOU NEED THEM)

    public static String helperFunction(String whatever) {
        return "WHAT YOU NEED";
    }

    public static Boolean hasEpsilonRule(String nonterminal, List<Rule> rules) {
        for (Rule rule : rules) {
            if(rule.getLeft().equals(nonterminal)) {
                for (String right: rule.getRight()) {
                    if(right.equals("EPSILON")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static Boolean mapEquals(HashMap<String, HashSet<String>> a, HashMap<String, HashSet<String>> b) {
        for (String key : a.keySet() ) {
            if(!b.get(key).equals(a.get(key))) {
                return false;
            }
        }
        if(!a.keySet().equals(b.keySet())) {
            return false;
        }
        return true;
    }
    public static Stream<Rule> ruleReader(String rawLine) {
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

    public static void main(String[] args){
        String start = "S";
       /* String[] lines = {
                "start -> menu",
                "menu -> pizza dollar price | pizza dollar price ; menu",
                "pizza -> margherita | diavola | napoletana",
                "price -> 1 | 2 | 3 | 4 "
        };*/
       /* String[] lines = {
                "S -> A B C",
                "A -> r A r | EPSILON",
                "B -> A A A | EPSILON",
                "C -> B"
        };*/
        String[] lines = {
                "S -> simple | begin Send | R",
                "R -> a S Q e | EPSILON",
                "Q -> EPSILON"
        };

        List<Rule> ruless = Arrays.asList(lines).stream()
                .flatMap((s) -> ruleReader(s))
                .collect(Collectors.toList());
        System.out.print(computeFirstFollow(start,ruless));
    }

}