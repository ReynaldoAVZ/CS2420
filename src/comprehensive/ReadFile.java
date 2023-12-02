package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReadFile {
    public static HashMap<String, ArrayList<String>> Read(String fileName) throws FileNotFoundException {
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();

        Scanner grammarFile = new Scanner(new File(fileName));
        // grammarFile.useDelimiter(" ");

        while (grammarFile.hasNext()) {
            String currentToken = grammarFile.next();
            if (currentToken.equals("{")) { // we've reached a point in the file that we actually want to traverse
                while (!currentToken.equals("}")) {// keep going until we've reached the end of the block
                    currentToken = grammarFile.next();
                    if (currentToken.contains("<")) { // we've reached a non-terminal
                        String nonTerminal = currentToken;
                        while (!currentToken.contains("}")) { // add all the values into the corresponding array list
                            currentToken = grammarFile.nextLine();
                            hashMap.get(nonTerminal).add(currentToken);
                        }
                        currentToken = grammarFile.next();
                    }
                }
            }
        }
        return hashMap;
    }
}
