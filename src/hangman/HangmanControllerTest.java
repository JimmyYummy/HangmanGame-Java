package hangman;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HangmanControllerTest {
	
	HangmanController controller;
	String filePosition = "short_list.txt";

	@Before
	public void setUp() throws Exception {
		controller = new HangmanController();
	}

	@Test
	public void testHangmanController() {
		HangmanController ehc = new HangmanController();
		assertEquals(null, ehc.word);	
	}

	@Test
	public void testGetRndWord1() {
		controller.getRndWord(filePosition);
		assertEquals(new Word(1).getClass(),
				controller.word.getClass());
	}
	
	@Test
	public void testGetRndWord2() {
		boolean exceptionOccured = false;
		try {
			controller.getRndWord("invalid pointer");
		}catch (RuntimeException e) {
			exceptionOccured = true;
			System.out.println("Not an error, pass the test.");
		}
		assertTrue(exceptionOccured);
	}

}
