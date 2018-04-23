package hangman;
import java.util.*;

/**
 * the controller of the game
 */
public class HangmanController {

	// variables

	// save the status of the game
	// the ArrayList contains the letters already guessed
	private ArrayList<Character> guessedLetters;
	// the arrayList contains the letters are guessed and wrong
	private ArrayList<Character> wrongLetters;
	// the number of wrong guesses
	private int numOfWrongGuess;
	// the word to guess
	protected Word word;
	// maximum allowed wrong guess
	private int allowance = 10;
	//position of the word file
	private String filePosition = "words.txt";
	// the DictReader
	private DictReader dr;
	// methods

	/**
	 * The constructor
	 * initialize the instance variables except word
	 */
	public HangmanController() {
		this.guessedLetters = new ArrayList<Character>();
		this.wrongLetters = new ArrayList<Character>();
		this.numOfWrongGuess = 0;
		this.dr = new DictReader();
	}

	/**
	 * get a random word to this.word from the location inputed
	 */
	protected void getRndWord(String fileName) {
		// create the dict in the DictReader
		dr.readInFile(fileName);
		// get a random word and save it to this.word from the DictReader
		this.word = new Word(dr.getRandomWord());
	}

	/**
	 * as the user to guess and get the users input
	 * repeat the request if the input is not satisfying
	 */
	private char getGuessLetter(Scanner sc) {

		// the array format of the input
		char[] ch;
		while (true) {
			// ask for the input
			System.out.println("Please enter one letter to guess:");
			System.out.print(">>>");
			// reading the input
			String input = sc.nextLine();
			ch = input.toCharArray();
			// if the input is a allowed letter
			if(ch.length == 1
				&&(dr.isAllowed(ch[0]) || Character.isUpperCase(ch[0]))) {
				// break the loop
				break;
			}else {
				System.out.println("Please enter an allowed letter.");
			}
		}
		//return the lower-cased version of the letter
		return Character.toLowerCase(ch[0]);
	}

	/**
	 * execute the guess
	 * change the status of the controller, this.word,
	 * and the result of the guess
	 */
	private void tryGuess(char letter) {
		System.out.println("=============================");
		// if the same guess has been done before, print corresponding information
		if (this.guessedLetters.contains(letter)){
			System.out.println("Letter already guessed, try another one.");
		}else {
			// else, update the guessedLetters
			this.guessedLetters.add(letter);
			// if the guess is correct
			if(this.word.takeGuess(letter)) {
				// print corresponding information
				System.out.println("Correct letter!");
			}else {
				// else, add the letter to the wrongLetters list
				this.wrongLetters.add(letter);
				// increase the count of wrong guesses
				this.numOfWrongGuess ++;
				// print out the corresponding information
				System.out.println("Wrong letter, better luck next turn.");
			}
		}
	}

	/**
	 * print out the information during the game
	 */
	private void showInfo() {
		System.out.println("=============================");
		System.out.println("Guess a letter:\n" + this.word);
		System.out.println("You have made " 
				+ this.numOfWrongGuess + " wrong guesses.");
		System.out.println("You will lose the game if you make more than "
				+ this.allowance + " wrong guesses.");
		System.out.println("The already guessed wrong letters are:");
		System.out.println(this.wrongLetters);
		System.out.println("-----------------------------");
	}

	/**
	 *  print out the statistics after winning the game
	 */
	private void showStat() {
		System.out.println("=============================");
		System.out.println("Your won! Congrates!");
		System.out.println("The word is:");
		System.out.println("->\t" + this.word.toString().replaceAll(" ", ""));
		System.out.println("Your found the word with " 
				+ this.numOfWrongGuess + " wrong guesses.");
	}

	/**
	 *  print out the statistics after losing the game
	 */
	private void showFacts() {
		System.out.println("=============================");
		System.out.println("Your lose! Try harder and better luck next time.");
	}


	/**
	 *  run the game
	 *  Not tested since it simply calls other methods
	 *  and contains a lot of input output functions
	 */
	protected void runGame() {
		Scanner sc = new Scanner(System.in);
		this.getRndWord(filePosition);
		while((! this.word.isFinished()) && (this.numOfWrongGuess <= this.allowance)) {
			this.showInfo();
			this.tryGuess(this.getGuessLetter(sc));
		}
		if (this.word.isFinished()) {
			this.showStat();
		}else {
			this.showFacts();
		}
		sc.close();


	}

//	/**
//	 * Main Method
//	 * create a new controller, execute its runGame method
//	 */
//	public static void main(String[] arg) {
//		HangmanController controller = new HangmanController();
//		controller.runGame();
//	}

}
