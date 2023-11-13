package assign08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SpellCheckerTest {
    private SpellChecker SC;
    private SpellChecker SCEmpty;
    @BeforeEach
    void setUp() {
        SC = new SpellChecker(new File("src/assign08/dictionary.txt"));
        SCEmpty = new SpellChecker();
    }

    @Test
    void addToDictionaryTrue() {
        SC.addToDictionary("bst");
        SC.addToDictionary("Cs");
        List<String> incorrectWords = SC.spellCheck(new File("src/assign08/good_luck.txt"));
        assertTrue(incorrectWords.isEmpty());
    }

    @Test
    void removeFromDictionaryTrue() {
        SC.removeFromDictionary("world");
        List<String> incorrectWords = SC.spellCheck(new File("src/assign08/hello_world.txt"));
        assertTrue(incorrectWords.size() == 1);
        assertTrue(incorrectWords.contains("world"));
    }

    @Test
    void removeFromDictionaryFalse() {
        SC.removeFromDictionary("rey");
        List<String> incorrectWords = SC.spellCheck(new File("src/assign08/hello_world.txt"));
        assertTrue(incorrectWords.isEmpty());
    }

    @Test
    void spellCheck() {
    }
}