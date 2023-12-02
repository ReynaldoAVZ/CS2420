package comprehensive;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {

    @Test
    void read() throws FileNotFoundException {
        HashMap<String, ArrayList<String>> hashMap = ReadFile.Read("src/comprehensive/assignment_extension_request.g");
        hashMap.size();
    }
}