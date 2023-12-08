package comprehensive;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {

    @Test
    void readPoeticSentence() throws FileNotFoundException {
        HashMap<String, ArrayList<String>> hashMap = ReadFile.Read("src/comprehensive/poetic_sentence.g");
        hashMap.size();
    }
    @Test
    void readAssignmentExtension() throws FileNotFoundException {
        HashMap<String, ArrayList<String>> hashMap = ReadFile.Read("src/comprehensive/assignment_extension_request.g");
        hashMap.size();
    }
    @Test
    void readSuperSimple() throws FileNotFoundException {
        HashMap<String, ArrayList<String>> hashMap = ReadFile.Read("src/comprehensive/super_simple.g");
        hashMap.size();
    }

    @Test
    void readMathematicalExpression() throws FileNotFoundException {
        HashMap<String, ArrayList<String>> hashMap = ReadFile.Read("src/comprehensive/mathematical_expression.g");
        hashMap.size();
    }
}