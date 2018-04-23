package hangman;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WordTest {
	Word testWord;
	Word anotherTestWord;

	@Before
	public void setUp() throws Exception {
		testWord = new Word("testword");
		anotherTestWord = new Word(6);
	}

	@Test
	public void testWordString1() {
		boolean notAllowed = false;
		try {
			new Word("");
		}catch (RuntimeException e){
			notAllowed = true;
		}
		assertTrue(notAllowed);
	}

	@Test
	public void testWordString2() {
		String str = "word";
		Word w = new Word(str);
		char[] wordChar = str.toCharArray();
		for(int i = 0; i < wordChar.length; i++) {
			assertEquals(wordChar[i], w.theWord[i]);
		}
		assertEquals(str.length(), w.getLength());
		for (boolean mask : w.getMask()) {
			assertTrue(mask);
		}
		assertFalse(w.isFinished());
	}


	@Test
	public void testWordInt1() {
		boolean notAllowed = false;
		try {
			new Word(0);
		}catch (RuntimeException e){
			notAllowed = true;
		}
		assertTrue(notAllowed);
	}

	@Test
	public void testWordInt2() {
		Word w = new Word(3);
		assertEquals(null, w.theWord);
		assertEquals(3, w.getLength());
		for (boolean mask : w.getMask()) {
			assertTrue(mask);
		}
		assertFalse(w.isFinished());
	}

	@Test
	public void testGetLength1() {
		assertEquals(8, testWord.getLength());
	}

	@Test
	public void testGetLength2() {
		assertEquals(6, anotherTestWord.getLength());
	}

	@Test
	public void testGetLength3() {
		assertEquals(7, new Word(7).getLength());
	}

	@Test
	public void testGetMask1() {
		assertEquals(8, testWord.getMask().length);
		for (boolean mask : testWord.getMask()) {
			assertEquals(true, mask);
		}
	}

	@Test
	public void testGetMask2() {
		assertEquals(6, anotherTestWord.getMask().length);
		for (boolean mask : anotherTestWord.getMask()) {
			assertEquals(true, mask);
		}
	}

	@Test
	public void testGetMask3() {
		Word newWord = new Word(1);
		assertEquals(1, newWord.getMask().length);
	}

	@Test
	public void testUnmaskSlot1() {
		testWord.unmaskSlot(0);
		assertEquals(false, testWord.getMask()[0]);
		for (int i = 1; i < testWord.getLength(); i++) {
			assertEquals(true, testWord.getMask()[i]);
		}
	}

	@Test
	public void testUnmaskSlot2() {
		testWord.unmaskSlot(testWord.getLength() - 1);
		for (int i = 0; i < testWord.getLength()-1; i++) {
			assertEquals(true, testWord.getMask()[i]);
		}
		assertEquals(false, testWord.getMask()[testWord.getLength() - 1]);

	}

	@Test
	public void testUnmaskSlot3() {
		// not changing the status, invalid request
		testWord.unmaskSlot(testWord.getLength());
		for (int i = 0; i < testWord.getLength(); i++) {
			assertEquals(true, testWord.getMask()[i]);
		}

	}

	@Test
	public void testIsFinished1() {
		assertFalse(testWord.isFinished());
	}

	public void testIsFinished2() {
		testWord.unmaskSlot(0);
		assertFalse(testWord.isFinished());
	}

	public void testIsFinished3() {
		for (int i = 0; i < testWord.getLength(); i++) {
			testWord.unmaskSlot(i);
			assertFalse(testWord.isFinished());
		}
		assertTrue(testWord.isFinished());
	}

	@Test
	public void testTakeGuessOneSlot() {
		testWord.takeGuess('e');
		assertFalse(testWord.getMask()[1]);
	}

	public void testTakeGuessTwoSlot() {
		testWord.takeGuess('t');
		assertFalse(testWord.getMask()[0]);
		assertFalse(testWord.getMask()[3]);
	}

	public void testTakeGuessNoSlot() {
		testWord.takeGuess('0');
		for (boolean mask : testWord.getMask()) {
			assertTrue(mask);

		}
	}

	@Test
	public void testToString1() {
		assertEquals(" _ _ _ _ _ _ _ _", 
				testWord.toString());
	}

	@Test
	public void testToString2() {
		testWord.takeGuess('t');
		assertEquals(" t _ _ t _ _ _ _", 
				testWord.toString());
	}

	@Test
	public void testToString3() {
		for (char ch : "testword".toCharArray()) {
			testWord.takeGuess(ch);
		}
		assertEquals(" t e s t w o r d", 
				testWord.toString());
	}

	@Test
	public void testToString4() {
		assertEquals(" _ _ _ _ _ _", 
				anotherTestWord.toString());
	}
}
