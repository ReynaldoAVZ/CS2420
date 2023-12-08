package comprehensive;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class RandomPhraseGenerator {
    public static void main(String[] args) throws FileNotFoundException {
        // read the file and create our hash map that contains all the key, value pairs
        // key: non-terminal object (things inside <>)
        // value: terminal object

        // read the file and add it all to a hash map in a list of keys with their associated
        HashMap<String, ArrayList<String>> hashMap = ReadFile.Read(args[0]);
        for (int i = 0; i < Integer.parseInt(args[1]); i++) { // iterate for however many random phrases the user wants
            System.out.println(RandomPhrase.Generate(hashMap));
        }
    }
}
