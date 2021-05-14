package task2;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Unit test for First and Follow algorithm
 */
public class Ex2Task2Test
{

    private final int MAX_EXECUTION_TIME = 1 * 000; // in milliseconds


    @Test(timeout = MAX_EXECUTION_TIME)
    public void testStandardExample() {
        String[] lines = {
                "S -> a S R e | EPSILON",
                "R -> r Q r | EPSILON",
                "Q -> EPSILON"
        };

        // expected result
        List<Result> expectedResults = new ArrayList<Result>();
        expectedResults.addAll(
                Arrays.asList(
                        new Result("a", "a", ""),
                        new Result("e", "e", ""),
                        new Result("EPSILON", "EPSILON", ""),
                        new Result("r", "r", ""),
                        new Result("S", "a EPSILON", "r e EOF"),
                        new Result("R", "r EPSILON", "e"),
                        new Result("Q", "EPSILON", "r")
                )
        );

        // Parse the rules
        List<Rule> rules = Arrays.asList(lines).stream()
                .flatMap((s) -> ruleReader(s))
                .collect(Collectors.toList());
        // Print the rules
        rules.stream().forEachOrdered(System.out::println);

        // Run your Algorithm
        List<Result> userResults = Algorithm.computeFirstFollow("S", rules);

        performChecksOn(
                userResults, expectedResults);

    }


    @Test(timeout = MAX_EXECUTION_TIME)
    public void testPizzaExample() {
        String[] lines = {
                "start -> menu",
                "menu -> pizza dollar price | pizza dollar price ; menu",
                "pizza -> margherita | diavola | napoletana",
                "price -> 1 | 2 | 3 | 4 "
        };

        // expected result
        List<Result> expectedResults = new ArrayList<Result>();
        expectedResults.addAll(
                Arrays.asList(
                        new Result("dollar", "dollar", ""),
                        new Result(";", ";", ""),
                        new Result("1", "1", ""),
                        new Result("2", "2", ""),
                        new Result("3", "3", ""),
                        new Result("4", "4", ""),
                        new Result("margherita", "margherita", ""),
                        new Result("diavola", "diavola", ""),
                        new Result("napoletana", "napoletana", ""),
                        new Result("pizza", "margherita diavola napoletana", "dollar"),
                        new Result("price", "1 2 3 4", "; EOF"),
                        new Result("menu", "margherita diavola napoletana", "EOF"),
                        new Result("start", "margherita diavola napoletana", "EOF")
                )
        );

        // Parse the rules
        List<Rule> rules = Arrays.asList(lines).stream()
                .flatMap((s) -> ruleReader(s))
                .collect(Collectors.toList());
        // Print the rules
        rules.stream().forEachOrdered(System.out::println);

        // Run your Algorithm
        List<Result> userResults = Algorithm.computeFirstFollow("start", rules);

        // Verify your result matches the expected result
        performChecksOn(
                userResults, expectedResults);

    }

    /**
     * Convert one line of grammar into the corresponding rules
     *
     *
     * @param  rawLine One line of the input file
     * @return Stream of Rule objects
     */
    public static Stream<Rule> ruleReader(String rawLine) {
        String[] parts = rawLine.split("->");
        String head = parts[0].trim();
        String tail = parts[1].trim();

        String[] tailParts = null;
        if (tail.indexOf("|") != -1) {
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

    /**
     * Verify that the two result sets are equivalent in the First sets.
     *
     *
     * @param  userResults submission form the user
     * @param  expectedResults test defined ground truth
     * @return the number of correct sets provided as a percentage (float)
     */
    public static float performChecksOnFirstSet(
            List<Result> userResults,
            List<Result> expectedResults ) {

        int identicalResults = 0;
        for (Result expR : expectedResults) {
            for (Result userR : userResults) {
                if (expR.getSymbolName().equals(userR.getSymbolName())) {
                    if (expR.equalsFirstSet(userR)) {
                        identicalResults++;
                    } else {
                        System.out.println("Error on First Set:");
                        System.out.println("User:");
                        System.out.println(userR);
                        System.out.println("Expected:");
                        System.out.println(expR);
                    }
                    break;
                }
            }
        }

        return identicalResults / expectedResults.size();
    }


    /**
     * Verify that result sets are equivalent for both First and Follow sets.
     *
     *
     * @param  userResults submission form the user
     * @param  expectedResults test defined ground truth
     * @return whether the two sets are equivalent (true) or not (false)
     */
    public static void performChecksOn(
            List<Result> userResults,
            List<Result> expectedResults ) {



        float percentage_correct_firstsets = 0;

        percentage_correct_firstsets =
                performChecksOnFirstSet(
                        userResults, expectedResults);

        float percentage_correct_followsets = 0;

        percentage_correct_followsets =
                performChecksOnFollowSet(
                        userResults, expectedResults);

        if (userResults.size() != expectedResults.size()) {
            System.out.println("The number of first/follow sets provided doesn't match the number of sybol in the grammar:");
            System.out.println("Expected:");
            System.out.println(expectedResults);
            System.out.println("User Sets:");
            System.out.println(userResults);
        }

        assertTrue(userResults.size() == expectedResults.size());

        assertTrue ((
                percentage_correct_firstsets == 1 &&
                        percentage_correct_followsets == 1)
        );

    }

    /**
     * Verify that the two result sets are equivalent in the Follow sets.
     *
     *
     * @param  userResults submission form the user
     * @param  expectedResults test defined ground truth
     * @return the number of correct sets provided as a percentage (float)
     */
    public static float performChecksOnFollowSet(
            List<Result> userResults,
            List<Result> expectedResults ) {

        int identicalResults = 0;
        for (Result expR : expectedResults) {
            for (Result userR : userResults) {
                if (expR.getSymbolName().equals(userR.getSymbolName())) {
                    if (expR.equalsFollowSet(userR)) {
                        identicalResults++;
                    } else {
                        System.out.println("Error on Follow Set:");
                        System.out.println("User:");
                        System.out.println(userR);
                        System.out.println("Expected:");
                        System.out.println(expR);
                    }
                    break;
                }
            }
        }

        return identicalResults / expectedResults.size();
    }

}

