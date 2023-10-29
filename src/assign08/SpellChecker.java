package assign08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a "dictionary" of strings using a binary search tree and offers
 * methods for spell-checking documents.
 *
 * @author Aaron Wood and Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-10-26
 */
public class SpellChecker {

	private BinarySearchTree<String> dictionary;

	/**
	 * Default constructor--creates empty dictionary.
	 */
	public SpellChecker() {
		this.dictionary = new BinarySearchTree<String>();
	}

	/**
	 * Creates dictionary from a list of words.
	 * 
	 * @param words - the List of Strings used to build the dictionary
	 */
	public SpellChecker(List<String> words) {
		this();
		buildDictionary(words);
	}

	/**
	 * Creates dictionary from a file.
	 * 
	 * @param dictionaryFile - the File that contains Strings used to build the
	 *                        dictionary
	 */
	public SpellChecker(File dictionaryFile) {
		this();
		buildDictionary(readFromFile(dictionaryFile));
	}

	/**
	 * Add a word to the dictionary.
	 * 
	 * @param word - the String to be added to the dictionary
	 */
	public void addToDictionary(String word) {
		this.dictionary.add(word.toLowerCase());
	}

	/**
	 * Remove a word from the dictionary.
	 * 
	 * @param word - the String to be removed from the dictionary
	 */
	public void removeFromDictionary(String word) {
		this.dictionary.remove(word.toLowerCase());
	}

	/**
	 * Spell-checks a document against the dictionary.
	 * 
	 * @param documentFile - the File that contains Strings to be looked up in the
	 *                      dictionary
	 * @return a List of misspelled words
	 */
	public List<String> spellCheck(File documentFile) {
		// create an ArrayList that will contain the incorrect words
		List<String> incorrectWords = new ArrayList<String>();
		// read a file and place words into a List
		List<String> wordsToCheck = readFromFile(documentFile);
		// iterate through every String in the wordsToCheck list
		for (String currString : wordsToCheck) {
			// check if the current string is in the dictionary (using .contains) and if it does not, it means the word is mispelled
			boolean result = this.dictionary.contains(currString.toLowerCase()); // TREE -> tree, Tree -> tree, TrEe -> tree
			// if the result is true (which means the String was not in the dictionary)
			if (result != true) {
				// place the String in our incorrectWords list that we will return
				incorrectWords.add(currString.toLowerCase());
			}
		}
		// return the list of incorrect words
		return incorrectWords;
	}

	/**
	 * Fills in the dictionary with the input list of words.
	 * 
	 * @param words - the List of Strings to be added to the dictionary
	 */
	private void buildDictionary(List<String> words) {
		for (String currString : words) {
			this.dictionary.add(currString.toLowerCase());
		}
	}

	/**
	 * Returns a list of the words contained in the specified file. (Note that
	 * symbols, digits, and spaces are ignored; and all words are converted
	 * to lower case.)
	 * 
	 * @param file - the File to be read
	 * @return a List of the Strings in the input file
	 */
	private List<String> readFromFile(File file) {
		ArrayList<String> words = new ArrayList<String>();

		try {
			/*
			 * Java's Scanner class is a simple lexer for Strings and primitive types (see
			 * the Java API, if you are unfamiliar).
			 */
			Scanner fileInput = new Scanner(file);

			/*
			 * The scanner can be directed how to delimit (or divide) the input. By default,
			 * it uses whitespace as the delimiter. The following statement specifies
			 * anything other than alphabetic characters as a delimiter (so that punctuation
			 * and such will be ignored). The string argument is a regular expression that
			 * specifies "anything but an alphabetic character". You need not understand any
			 * of this for the assignment.
			 */
			fileInput.useDelimiter("\\s*[^a-zA-Z]\\s*");

			while (fileInput.hasNext()) {
				String s = fileInput.next();
				if (!s.isEmpty())
					words.add(s.toLowerCase());
			}
			
			fileInput.close();

		} 
		catch(FileNotFoundException e) {
			System.err.println("File " + file + " cannot be found.");
		}

		return words;
	}
}
