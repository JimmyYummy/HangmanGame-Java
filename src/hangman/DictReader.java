package hangman;

import java.io.*;
import java.util.*;

/**
 * This class has the following functionalities:
 * 1. read in the word file, and save the word in right format in an ArrayList
 * 2. provide a random word
 * 3. provide the full ArrayList that contains all the words
 */
public class DictReader {
	//variables

	// the "dictionary" that saves all the "good" words
	private ArrayList<String> dict;

	//methods

	/**
	 * The constructor
	 * simply create an empty ArrayList<String> for the dict variable
	 */
	public DictReader() {
		this.dict = new ArrayList<String>();
	}

	/**
	 * read the word in the words.txt line by line
	 * the location of the txt file is specified by the string input
	 * the read words are send to the passThroughFilter method for further inspection
	 */
	public void readInFile(String fileName) {
		// point to the file
		File dictFile = new File(fileName);
		try {
			// read the words file
			Scanner sc = new Scanner(dictFile);
			// take a word at a time and send it to the passThoughFilter method
			while(sc.hasNextLine()) {
				this.passThroughFilter(sc.nextLine());
			}
			// close the scanner
			sc.close();
		}catch (FileNotFoundException e) {
			//exception tracking
			e.printStackTrace();
		}
	}

	/**
	 * take a String format word
	 * save the word to this.dict if it adheres to the formatting rule
	 * else discard the word
	 */
	protected void passThroughFilter(String word) {
		//change the word to a char array
		char[] chList = word.toCharArray();
		//assuming the word is in the right format
		boolean pass = true;

		//set the pass to false if the length of the word is 0
		if (chList.length == 0) {
			pass = false;
		}else {
			// if any of the character in the word is not a lower cased letter
			// set the pass to false
			for (char ch : chList) {
				if (!this.isAllowed(ch)) {
					pass = false;
				}
			}
		}
		// add the word to the dict if it pass the test
		if (pass) {
			this.dict.add(word);
		}
	}

	
	/**
	 * take a char as input,
	 * return whether it is a allowed letter in the word 
	 */
	public boolean isAllowed(char ch) {
		// return false if the character is under the 6 un-allowed condition
		if(Character.isUpperCase(ch)
				|| Character.isDigit(ch)
				|| ch == ' '
				|| ch == '.'
				|| ch == '\''
				|| ch == '-') {
			return false;
		}
		return true;
	}
	
	/**
	 * return random word family (whose members are same in length)
	 */
	public ArrayList<String> getRandomFamily(){
		// throw an exception when the dict is empty
		if (this.dict.size() == 0) {
			throw new RuntimeException("Please load the legal words first");
		}

		// create a HashMap to save word families
		int key;
		HashMap<Integer, ArrayList<String>> families 
		= new HashMap<Integer, ArrayList<String>>(); 
		// assign the words by their length to the families
		for (String word : this.dict) {
			key = word.length();
			if(families.get(key) == null) {
				families.put(key, new ArrayList<String>());
			}
			families.get(key).add(word);
		}

		//pick a family randomly
		Integer[] keys = families.keySet().toArray(new Integer[families.keySet().size()]);
		Random rnd = new Random();
		int length = keys[rnd.nextInt(keys.length)];
		return families.get(length);
	}

	/**
	 * return a random word in the dict in String format
	 */
	public String getRandomWord() {
		// throw an exception when the dict is empty
		if (this.dict.size() == 0) {
			throw new RuntimeException("Please load the legal words first");
		}

		Random rnd = new Random();
		// get a random location in the dict and return the word in the location
		return dict.get(rnd.nextInt(dict.size()));
	}

	/**
	 * return the length of this.dict
	 * this method is for unit test only
	 */
	protected int getDictLength(){
		return this.dict.size();
	}
}
