
package hangman;

import java.util.*;

/**
 * the evil edition of the Word
 * hide the "evil" fact by overriding the methods
 * use a different constructor, however
 */
public class EvilWord extends Word{
	// the variable to save the family of the words
	private ArrayList<String> wordFamily;

	/**
	 * The constructor
	 * take in the length of the word and the word family as an ArrayList
	 */
	public EvilWord(ArrayList<String> family) {
		// call the constructor in the Word class
		super(family.get(0).length());
		

		int length = family.get(0).length();
		for (String word : family) {
			if (word.length() != length) {
				throw new RuntimeException("All the words must equal in length.");
			}
		}
		// save the word family
		this.wordFamily = family;
		// set this.theWord whose size is length, and the each element as '_'
		this.theWord = new char[length];
		for (int i = 0; i< length; i++) {
			this.theWord[i] = '_';
		}
	}

	/**
	 * return the key of the word
	 * based on the letter character
	 */
	protected String getKey(String word, char letter) {
		// the key to return
		String key = new String();
		// for each character in the word
		char[] wordLetters = word.toCharArray();
		for (int i = 0; i < this.getLength(); i++) {
			// if the character is revealed already
			if (this.theWord[i] != '_') {
				// remain the character unchanged
				key += this.theWord[i];
				// if not
			}else {
				// if the character is same to the letter
				if (wordLetters[i] == letter) {
					// show the letter
					key += letter;
				}else
					// else show "_"
					key += "_";
			}
		}
		return key;
	}
	
	/**
	 * update the wordFamily to be the largest sub-family separated by the letter input
	 * and update theWord accordingly
	 */
	protected void updateWordFamily(char letter) {
		// the key of each family
		String key;
		// create a HashMap to save each sub-family
		HashMap<String, ArrayList<String>> sortedFamily 
						= new HashMap<String, ArrayList<String>>();
		for (String word : this.wordFamily) {
			// for each word in the original wordFamily,
			// get the new key 
			key = this.getKey(word, letter);
			// and assign it to the corresponding ArrayList
			if(sortedFamily.get(key) == null) {
				sortedFamily.put(key, new ArrayList<String>());
			}
			sortedFamily.get(key).add(word);
		}
		
		// get the first largest sub-family and its key
		int maxLength = 0;
		// the key of the new family
		String newKey = new String();
		//for each key in the HashMap
		for (String keyName : sortedFamily.keySet()) {
			// update if the corresponding ArrayList is larger, update the key
			if (maxLength < sortedFamily.get(keyName).size()) {
				maxLength = sortedFamily.get(keyName).size();
				newKey = keyName;
			}
		}
		
		// update this.theWord as the new key
		this.theWord = newKey.toCharArray();
		// update the selected sub-family to be the new this.wordFamily
		this.wordFamily = sortedFamily.get(newKey);
	}

	/**
	 * (seemingly) identical behavior to the Word.takeGuess
	 * but update the wordFamily and theWord under the surface
	 * @see hangman.Word#takeGuess(char)
	 */
	@Override
	public boolean takeGuess(char letter) {
		// update the this.wordFamily and this.word by calling a method
		this.updateWordFamily(letter);
		
		//run the takeGuess() method in the Word class
		return super.takeGuess(letter);
	}

	/**
	 * get the size of the family
	 * this method is for unit test
	 */
	protected int getFamilySize() {
		return this.wordFamily.size();
	}

}
