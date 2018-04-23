package hangman;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class EvilHangmanControllerTest {
	
	EvilHangmanController controller;
	String filePosition = "short_list.txt";

	@Before
	public void setUp() throws Exception {
		controller = new EvilHangmanController();
		
	}
	
	@Test
	public void testGetRndWord1() {
		ArrayList<String> mockFamily = new ArrayList<String>();
		mockFamily.add("one");
		mockFamily.add("two");
		mockFamily.add("dog");

		controller.getRndWord(filePosition);
		assertEquals(new EvilWord(mockFamily).getClass(),
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
