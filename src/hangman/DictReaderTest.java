package hangman;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DictReaderTest {
	DictReader dr;
	String filePosition = "short_list.txt";
	
	@Before
	public void setUp() throws Exception {
		dr = new DictReader();
	}

	@Test
	public void testDictReader() {
		DictReader theReader = new DictReader();
		assertEquals(0, theReader.getDictLength());
	}
	
	@Test
	public void testReadInFile() {
		assertEquals(0, dr.getDictLength());
		dr.readInFile(filePosition);
		assertNotEquals(0, dr.getDictLength());
	}

	@Test
	public void testGetDictLength() {
		assertEquals(0, dr.getDictLength());
		dr.passThroughFilter("one");
		assertEquals(1, dr.getDictLength());
		dr.passThroughFilter("two");
		assertEquals(2, dr.getDictLength());

	}

	@Test
	public void testPassThroughFilter1() {
		assertEquals(0, dr.getDictLength());
		dr.passThroughFilter("");
		assertEquals(0, dr.getDictLength());
	}
	
	@Test
	public void testPassThroughFilter2() {
		assertEquals(0, dr.getDictLength());
		dr.passThroughFilter("Amy");
		assertEquals(0, dr.getDictLength());
	}

	@Test
	public void testPassThroughFilter3() {
		assertEquals(0, dr.getDictLength());
		dr.passThroughFilter("also-run");
		assertEquals(0, dr.getDictLength());
	}

	@Test
	public void testPassThroughFilter4() {
		assertEquals(0, dr.getDictLength());
		dr.passThroughFilter("post office");
		assertEquals(0, dr.getDictLength());
	}

	@Test
	public void testPassThroughFilter5() {
		assertEquals(0, dr.getDictLength());
		dr.passThroughFilter("Levi's");
		assertEquals(0, dr.getDictLength());
	}

	@Test
	public void testPassThroughFilter6() {
		assertEquals(0, dr.getDictLength());
		dr.passThroughFilter("Mr.");
		assertEquals(0, dr.getDictLength());
	}

	@Test
	public void testPassThroughFilter7() {
		assertEquals(0, dr.getDictLength());
		dr.passThroughFilter("99");
		assertEquals(0, dr.getDictLength());
	}

	@Test
	public void testPassThroughFilter8() {
		assertEquals(0, dr.getDictLength());
		dr.passThroughFilter("word");
		assertEquals(1, dr.getDictLength());
	}
	
	
	@Test
	public void testGetRandomFamily1() {
		boolean exceptionOccured = false;
		try {
			dr.getRandomFamily();
		}catch (RuntimeException e) {
			exceptionOccured = true;
		}
		assertTrue(exceptionOccured);
	}
	
	@Test
	public void testGetRandomFamily2() {
		dr.readInFile(filePosition);
		assertEquals(new ArrayList<String>().getClass(),
				dr.getRandomFamily().getClass());
	}
	
	@Test
	public void testGetRandomWord1() {
		boolean exceptionOccured = false;
		try {
			dr.getRandomWord();
		}catch (RuntimeException e) {
			exceptionOccured = true;
		}
		assertTrue(exceptionOccured);
	}
	
	@Test
	public void testGetRandomWord2() {
		dr.readInFile(filePosition);
		assertEquals(new String().getClass(),
				dr.getRandomWord().getClass());
	}
	
	@Test
	public void testIsAllowed1() {
		assertFalse(dr.isAllowed(' '));
	}
	
	@Test
	public void testIsAllowed2() {
		assertFalse(dr.isAllowed('-'));
	}
	
	@Test
	public void testIsAllowed3() {
		assertFalse(dr.isAllowed('.'));
	}
	
	@Test
	public void testIsAllowed4() {
		assertFalse(dr.isAllowed('\''));
	}
	
	@Test
	public void testIsAllowed5() {
		assertFalse(dr.isAllowed('A'));
	}
	
	@Test
	public void testIsAllowed6() {
		assertFalse(dr.isAllowed('1'));
	}
	
	@Test
	public void testIsAllowed7() {
		assertTrue(dr.isAllowed('w'));
	}
	
	@Test
	public void testIsAllowed8() {
		assertTrue(dr.isAllowed('#'));
	}

}
