package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

/**
 * This Java class represents a Web Browser that is meant to help simulate the behavior of a real web browser.
 * This class has built in methods and is backed by 2 instances of a LinkedListStack, one to help simulate the behavior of
 * a browsers back button and the other to help simulate the behavior of a browsers forward button. Also has a single URL
 * object that represents the current website that a user would be on.
 * <p></p>
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-10-19
 */
public class WebBrowser {
    // instantiate instance variables in WebBrowser
    private LinkedListStack<URL> forward;
    private LinkedListStack<URL> backward;
    private URL current;

    /**
     * This constructor creates a new web browser with no previously-visited webpages and no webpages to visit next.
     */
    public WebBrowser() {
        // Creating forward and backwards stacks for WebBrowser objects
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
        // Iterating to add all the contents into backward stack except the first element
        for(int i = history.size() - 1; i > 0; i--) {
            this.backward.push(history.get(i));
        }
        // check and make sure that the history SLL is not empty such that the first element can be indexed out
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
        // when we visit a new URL, the forward LLS must be cleared
        this.forward.clear();
        // Adding current webpage into backward stack if the current page is not null
        if (current != null) {
            this.backward.push(current);
        }
        // update our current website to be the new website just visited
        this.current = webpage;
    }

    /**
     * This method simulates using the back button, returning the URL visited.  NoSuchElementException is thrown if
     * there is no previously-visited URL.
     * @return URL page - previously-visited URL.
     * @throws NoSuchElementException if there is no previously-visited URL
     */
    public URL back() throws NoSuchElementException {
        // get the head of the backward LLS and pop it out (or assign the error if no more URLs are inside the LLS)
        URL page = this.backward.pop();
        // add the current website that the user was on into the forward LLS
        this.forward.push(current);
        // assign our current website that user is on to be the value that was popped from the backward LLS
        current = page;
        // return the page the user is now on
        return current;
    }

    /**
     * This method simulates using the forward button, returning the URL visited.  NoSuchElementException is thrown if
     * there is no URL to visit next.
     * @return URL page -
     * @throws NoSuchElementException if there are no elements to be popped from the forward LinkedListStack<URL>
     */
    public URL forward() throws NoSuchElementException {
        // get the head of the forward LLS and pop it out (or assign the error if no more URLs are inside the LLS)
        URL page = this.forward.pop();
        // add the current website that the user was on into the backward LLS
        this.backward.push(current);
        // assign our current website that user is on to be the value that was popped from the forward LLS
        current = page;
        // return the page the user is now on
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
        // initializing temporary LLS in order to assign correct history and maintain original LLS for backward stack
        LinkedListStack<URL> backwardTemp1 = new LinkedListStack<>();
        LinkedListStack<URL> backwardTemp2 = new LinkedListStack<>();
        SinglyLinkedList<URL> history = new SinglyLinkedList<>();
        URL val;
        // declaring a size variable that will not change, due to the fact that the size of backward LLS changes through the loop
        int size = backward.size();
        // iterate through the backward LLS and pop all values
        for(int i = 0; i < size; i++) {
            val = this.backward.pop();
            backwardTemp1.push(val);
        }
        // tracking backwardTemp1 size which is varying in the loop
        int temp_size = backwardTemp1.size();
        // iterate through the temporary LLS in order to build up original backward LLS and history that will be returned
        // in correct order
        for(int i = 0; i < temp_size; i++) {
            val = backwardTemp1.pop();
            history.insertFirst(val);
            backwardTemp2.push(val);
        }
        // assign the backward LLS to now point to backwardTemp2
        this.backward = backwardTemp2;
        // make sure that the current value is not null before adding it to the front of the history SLL
        if (current != null) {
            history.insertFirst(current);
        }
        // return the SLL that now contains the history of URLs visited in correct order
        return history;
    }
}
