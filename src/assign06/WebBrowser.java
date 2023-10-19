package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

/**
 * This Java class represents a Web Browser that is meant to help simulate the behavior of a real web browser.
 * This class has built in methods and is backed by 2 instances of a stack, one to help simulate the behavior of
 * a browsers back button and the other to help simulate the behavior of a browsers forward button.
 * <p></p>
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-10-00
 */
public class WebBrowser {
    private LinkedListStack<URL> forward;
    private LinkedListStack<URL> backward;
    private URL current;

    /**
     * This constructor creates a new web browser with no previously-visited webpages and no webpages to visit next.
     */
    public WebBrowser() {
        this.forward = new LinkedListStack<>();
        this.backward = new LinkedListStack<>();
        this.current = null;
    }

    /**
     * This constructor creates a new web browser with a preloaded history of visited webpages, given as a list
     * of URL objects. The first webpage in the list is the "current" webpage visited, and the remaining webpages
     * are ordered from most recently visited to least recently visited.
     * @param history - SinglyLinkedList<URL> that contains websites visited. The first object is the current website.
     */
    public WebBrowser(SinglyLinkedList<URL> history) {
        this.forward = new LinkedListStack<>();
        this.backward = new LinkedListStack<>();
        for(int i = history.size() - 1; i > 0; i--) {
            this.backward.push(history.get(i));
        }
        if (!history.isEmpty()) {
            this.current = history.get(0);
        }

    }

    /**
     * This method simulates visiting a webpage, given as a URL.  Note that calling this method should clear the
     * forward button stack, since there is no URL to visit next.
     * @param webpage - URL webpage that is currently being visited.
     */
    public void visit(URL webpage) {
        this.forward.clear();
        if (current != null) {
            this.backward.push(current);
        }
        this.current = webpage;
    }

    /**
     * This method simulates using the back button, returning the URL visited.  NoSuchElementException is thrown if
     * there is no previously-visited URL.
     * @return URL page - previously-visited URL.
     * @throws NoSuchElementException if there is no previously-visited URL
     */
    public URL back() throws NoSuchElementException {
        URL page = this.backward.pop();
        this.forward.push(current);
        current = page;
        return current;
    }

    /**
     * This method simulates using the forward button, returning the URL visited.  NoSuchElementException is thrown if
     * there is no URL to visit next.
     * @return URL page -
     * @throws NoSuchElementException if there are no elements to be popped from the forward LinkedListStack<URL>
     */
    public URL forward() throws NoSuchElementException {
        URL page = this.forward.pop();
        this.backward.push(current);
        current = page;
        return current;
    }

    /**
     * This method generates a history of URLs visited, as a list of URL objects ordered from most recently visited to
     * least recently visited (including the "current" webpage visited), without altering subsequent behavior of this
     * web browser.  "Forward" URLs are not included.  The behavior of the method must be O(N), where N is the number
     * of URLs.
     *
     * @return a SinglyLinkedList<URL> that represents the entire history of the backward LinkedListStack<URL> and the current
     * website being visited.
     */
    public SinglyLinkedList<URL> history() {
        LinkedListStack<URL> backwardTemp1 = new LinkedListStack<>();
        LinkedListStack<URL> backwardTemp2 = new LinkedListStack<>();
        SinglyLinkedList<URL> history = new SinglyLinkedList<>();
        URL val;
        int size = backward.size();
        for(int i = 0; i < size; i++) {
            val = this.backward.pop();
            backwardTemp1.push(val);
        }
        int temp_size = backwardTemp1.size();
        for(int i = 0; i < temp_size; i++) {
            val = backwardTemp1.pop();
            history.insertFirst(val);
            backwardTemp2.push(val);
        }
        this.backward = backwardTemp2;
        if (current != null) {
            history.insertFirst(current);
        }
        return history;
    }
}
