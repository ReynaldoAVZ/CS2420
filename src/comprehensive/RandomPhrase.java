package comprehensive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * The RandomPhrase class is a class that handles all the randomness required for the program.
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 12-00-23
 */
public class RandomPhrase {
    private static Random rng;

    public static String Generate(HashMap<String, ArrayList<String>> hashMap) {
        // instantiate a Random Object
        rng = new Random();
        // get sentence structure in order to fulfill the phrase requirement
        ArrayList<String> startList = hashMap.get("<start>");
        String structure;
        if (startList.size() < 2) { // there's only a single grammar structure
            structure = startList.get(0);
        }
        else { // there is more than one sentence structure
            structure = startList.get(rng.nextInt(0, startList.size()));
        }
        StringBuilder stringBuilder = new StringBuilder();
        generatePhrase(structure, hashMap, stringBuilder);
        return stringBuilder.toString().trim();
    }

    /**
     * The generatePhrase method is a private recursive helper method that takes care of creating the string with the
     * required terminals.
     *
     * @param structure
     * @param hashMap
     * @param stringBuilder
     */
    private static void generatePhrase(String structure, HashMap<String, ArrayList<String>> hashMap, StringBuilder stringBuilder) {
        char c;
        StringBuilder nonTerminal;
        ArrayList<String> productions;
        for (int i = 0; i < structure.length(); i++) { // iterate over our string
            c = structure.charAt(i);
            if (c == '<') { // start of a non-terminal
                // create a string that contains the non-terminal string object
                nonTerminal = new StringBuilder();
                nonTerminal.append(c);
                while (c != '>' && i < structure.length() - 1) { // add the entire non-terminal
                    c = structure.charAt(++i);
                    nonTerminal.append(c);
                }
                productions = hashMap.get(nonTerminal.toString()); // use string to get array
                if (productions != null && !productions.isEmpty()) { // make sure a list is returned and its not empty
                    generatePhrase(productions.get(rng.nextInt(productions.size())), hashMap, stringBuilder);
                }
            }
            else { // the character is a non-terminal character
                stringBuilder.append(c);
            }
        }
    }
}
