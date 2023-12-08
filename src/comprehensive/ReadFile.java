package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The class ReadFile is in charge of reading a .g file that is used to build a hash map that represents our file and
 * grammar structure.
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 12-00-23
 */
public class ReadFile {

    public static HashMap<String, ArrayList<String>> Read(String fileName) throws FileNotFoundException {
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        Scanner grammarFile = new Scanner(new File(fileName));
        String currentToken;
        String nonTerminal;
        ArrayList<String> currentList;
        while (grammarFile.hasNext()) {
            currentToken = grammarFile.next();
            if (currentToken.equals("{")) { // we've reached a point in the file that we actually want to traverse
                while (!currentToken.equals("}") && grammarFile.hasNext()) {// keep going until we've reached the end of the block
                    currentToken = grammarFile.next();
                    if (currentToken.contains("<")) { // we've reached a non-terminal
                        nonTerminal = currentToken;
                        hashMap.put(nonTerminal, new ArrayList<String>());
                        currentToken = grammarFile.nextLine();
                        while (!currentToken.contains("}")) { // add all the values into the corresponding array list
                            currentList = hashMap.get(nonTerminal);
                            if (!currentToken.equals("")) {
                                currentList.add(currentToken);
                            }
                            currentToken = grammarFile.nextLine();
                        }
                        if (grammarFile.hasNext()) {
                            currentToken = grammarFile.nextLine();
                        }
                    }
                }
            }
        }
        return hashMap;
    }
}
