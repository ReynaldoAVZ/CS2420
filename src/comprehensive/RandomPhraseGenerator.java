package comprehensive;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class RandomPhraseGenerator {
    private static HashMap<String, ArrayList<String>> hashMap;
    public static void main(String fileName, int numberOfPhrases) throws FileNotFoundException {
        // read the file and create our hash map that contains all the key, value pairs
        // key: non-terminal object (things inside <>)
        // value: terminal object

        // read the file and add it all to a hash map in a list of keys with their associated
        hashMap = ReadFile.Read(fileName);
    }
}
