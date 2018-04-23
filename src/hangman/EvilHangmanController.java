package hangman;

import java.util.*;
import java.util.Scanner;

/**
 * the controller of the Evil edition
 * extends the original controller, only override the rndWord method
 * in order to set the Word as an EvilWord
 */
public class EvilHangmanController extends HangmanController {
	
	/**
	 * override the original method
	 * assign this.word as an EvilWord,
	 * and put a random word family (whose members are same in length) in it
	 * @see hangman.HangmanController#getRndWord(java.lang.String)
	 */
	@Override
	protected void getRndWord(String fileName) {
		// get a DictReader
		DictReader dr = new DictReader();
		// read, filter, and save the words in the file
		dr.readInFile(fileName);
		// get the random word family
		ArrayList<String> wordFamily = dr.getRandomFamily();
		// construct the Evil word, and assign it to this.word
		this.word = new EvilWord(wordFamily);
	}
	
	
	public static void main(String[] arg) {
		//mode selection
		Scanner sc = new Scanner(System.in);
		boolean isEvil;
		while (true) {
			System.out.println("Please select mode: n for normal, e for evil");
			String input = sc.nextLine().toLowerCase();
			if (input.equals("n")){
				isEvil = false;
				break;
			}else if (input.equals("e")) {
				isEvil = true;
				break;
			}
		}
		sc.close();
		
		if (isEvil) {
			EvilHangmanController controller = new EvilHangmanController();
			controller.runGame();
		}else {
			HangmanController controller = new HangmanController();
			controller.runGame();
		}
	}
}
