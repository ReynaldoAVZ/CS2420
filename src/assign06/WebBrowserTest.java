package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class WebBrowserTest {
    private WebBrowser WB;
    private WebBrowser WBHistory;
    private WebBrowser WBHistoryEmpty;
    private SinglyLinkedList<URL> history;
    private SinglyLinkedList<URL> history2;
    private SinglyLinkedList<URL> emptyHistory;
    private URL url1;
    private URL url2;
    private URL url3;
    private URL url4;
    private URL url5;
    @BeforeEach
    void setUp() throws MalformedURLException {
        WB = new WebBrowser();

        history = new SinglyLinkedList<>();
        url1 = new URL("https://a");
        url2 = new URL("https://b");
        url3 = new URL("https://c");
        url4 = new URL("https://d");
        url5 = new URL("https://e");

        history.insertFirst(url1);
        history.insertFirst(url2);
        history.insertFirst(url3);
        history.insertFirst(url4);

        WBHistory = new WebBrowser(history);
        history2 = new SinglyLinkedList<>();
        history2.insertFirst(url1);
        history2.insertFirst(url2);
        history2.insertFirst(url3);
        history2.insertFirst(url4);
        history2.insertFirst(url5);

        emptyHistory = new SinglyLinkedList<URL>();

        WBHistoryEmpty = new WebBrowser(emptyHistory);
    }

    @Test
    void visit() {
        WB.visit(url1);
        WB.visit(url2);
        WB.visit(url3);
        WB.visit(url4);
        SinglyLinkedList<URL> returnedHistory = WB.history();
        for (int i = 0; i < returnedHistory.size(); i++) {
            assertEquals(returnedHistory.get(i).toString(), history.get(i).toString());
        }
    }

    @Test
    void back() {
        URL real = url3;
        WB.visit(url1);
        WB.visit(url2);
        WB.visit(url3);
        WB.visit(url4);
        assertEquals(real.toString(), WB.back().toString());
    }

    @Test
    void backError() {
        assertThrows(NoSuchElementException.class, () -> {
            WB.back();
        });
    }

    @Test
    void forward() {
        URL real = url3;
        WB.visit(url1);
        WB.visit(url2);
        WB.visit(url3);
        WB.visit(url4);
        WB.back();
        WB.back();
        assertEquals(real.toString(), WB.forward().toString());
    }

    @Test
    void forwardError() {
        assertThrows(NoSuchElementException.class, () -> {
            WB.forward();
        });
    }

    @Test
    void history() {
        WB.visit(url1);
        WB.visit(url2);
        WB.visit(url3);
        WB.visit(url4);
        SinglyLinkedList<URL> returnedHistory = WB.history();
        for (int i = 0; i < returnedHistory.size(); i++) {
            assertEquals(returnedHistory.get(i).toString(), history.get(i).toString());
        }
    }

    @Test
    void historyEmpty() {
       SinglyLinkedList<URL> returnedHistory = WB.history();
       assertEquals(returnedHistory.isEmpty(), emptyHistory.isEmpty());
    }
    @Test
    void visitWBHistory() {
        WBHistory.visit(url5);
        SinglyLinkedList<URL> returnedHistory = WBHistory.history();
        for (int i = 0; i < returnedHistory.size(); i++) {
            assertEquals(returnedHistory.get(i).toString(), history2.get(i).toString());
        }
    }

    @Test
    void backWBHistory() {
        URL real = url3;
        WBHistory.visit(url1);
        WBHistory.visit(url2);
        WBHistory.visit(url3);
        WBHistory.visit(url4);
        WBHistory.visit(url5);
        WBHistory.back();
        assertEquals(real.toString(), WBHistory.back().toString());
    }

    @Test
    void backErrorWBHistory() {

        assertThrows(NoSuchElementException.class, () -> {
            WBHistoryEmpty.back();
        });
    }

    @Test
    void forwardWBHistory() {
        URL real = url3;
        WBHistory.back();
        WBHistory.back();
        assertEquals(real.toString(), WBHistory.forward().toString());
    }

    @Test
    void forwardErrorWBHistory() {
        assertThrows(NoSuchElementException.class, () -> {
            WBHistory.forward();
        });
    }

    @Test
    void historyWBHistory() {
        WBHistory.visit(url5);
        SinglyLinkedList<URL> returnedHistory = WBHistory.history();
        for (int i = 0; i < returnedHistory.size(); i++) {
            assertEquals(returnedHistory.get(i).toString(), history2.get(i).toString());
        }
    }
}