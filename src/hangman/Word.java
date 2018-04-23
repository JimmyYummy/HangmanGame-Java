package hangman;

/**
 * This class saves the chosen word in a char array when constructed
 * and has the following functionalities
 * take guess, and change the status of the Word based on the guess
 * present the Word in a string format based on the revealed positions (Word's status)
 * provide a method showing whether the guessing procedure is finished
 */
public class Word {
	//variables

	// the char array saves the word
	protected char[] theWord;
	// showing the whether the corresponding position is masked (true) or revealed (false)
	private boolean[] mask;
	// the length of the word
	private int length;
	// number of the not revealed slots
	private int unrevealedSlots;

	// Methods

	/*
	 * The constructor
	 * take a specific String formated word as input
	 */
	public Word (String word) {
		// Call another constructor
		this(word.length());
		// save the input word in this.theWord
		this.theWord = word.toCharArray();
	}

	/**
	 * The constructor
	 * a more general-cased constructor which allows not initializing this.theWord
	 */
	protected Word(int length) {
		// check if the length of is reasonable
		if(length > 0) {
			// initial the instance variables
			this.length = length;
			this.unrevealedSlots = this.length;
			this.mask = new boolean[this.length];
			//each slots in the mask should be true (masked) by default
			for (int i = 0; i < this.length; i++) {
				this.mask[i] = true;
			}
		}else {
			// throw a exception if the length is not allowed
			throw new RuntimeException("Non-positive value is unacceptable.");
		}
	}

	/**
	 * return the length of the word
	 */
	protected int getLength() {
		return this.length;
	}

	/**
	 * return the current mask of the word
	 */
	protected boolean[] getMask() {
		return this.mask;
	}

	/**
	 * un-mask the corresponding position of the word
	 * decrement this.unrevealedSlots by 1
	 */
	protected void unmaskSlot(int slot) {
		if (slot >= 0 && slot < this.length) {
			this.mask[slot] = false;
			this.unrevealedSlots--;
		}
	}

	/**
	 * return if the word is fully guessed out
	 */
	public boolean isFinished() {
		// the word is guessed out only when the unrevealedSlots is 0
		if (this.unrevealedSlots == 0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * take the letter to proceed the guess on the word
	 * return true if the guess is correct
	 * else return false
	 */
	public boolean takeGuess(char letter) {
		// reset default result as false
		boolean result = false;
		// iterate through the characters in the word
		for (int i = 0; i < this.getLength(); i++) {
			// if the guess is correct
			if (this.theWord[i] == letter) {
				// unmask the slot, set the result as true
				this.unmaskSlot(i);
				result = true;
			}
		}
		//return the result
		return result;
	}

	/**
	 * return the String format presentation of the Word
	 * Separated by space, each position will be shown
	 * as the original letter if unmasked
	 * or be shown as "_" if masked
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// create the String to return
		String str = new String();
		// get the mask
		boolean[] mask = this.getMask();
		// the new char of the string
		char oneChar;
		// for each character in the word
		for (int i = 0; i < this.getLength(); i++) {
			// if it is masked, set the new char as "_"
			if (mask[i] == true) {
				oneChar = '_';
				// if it is unmasked, set the new char as the true character
			}else {
				oneChar = this.theWord[i];
			}
			// add the new char to the string
			str += " " + oneChar;
		}
		// return the string
		return str;
	}


}
