package hangman;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class EvilWordTest {
	EvilWord ew;
	ArrayList<String> mockFamily;
	
	@Before
	public void setUp() throws Exception {
		mockFamily = new ArrayList<String>();
		mockFamily.add("one");
		mockFamily.add("two");
		mockFamily.add("foe");
		ew = new EvilWord(mockFamily);
	}


	@Test
	public void testEvilWord1() {
		this.mockFamily.add("three");
		boolean exceptionOccured = false;
		try {
			this.ew = new EvilWord(this.mockFamily);
		}catch (RuntimeException e){
			exceptionOccured = true;
		}
		
		assertTrue(exceptionOccured);
	}
	
	@Test
	public void testEvilWord2() {
		ew = new EvilWord(mockFamily);
		assertEquals(3, ew.getFamilySize());
		assertEquals(3 ,ew.getLength());
		for (boolean mask : ew.getMask()) {
			assertTrue(mask);
		}
		assertEquals(" _ _ _", ew.toString());
	}
	
	@Test
	public void testGetFamilySize() {
		assertEquals(mockFamily.size(), ew.getFamilySize());
	}
	
	@Test
	public void testTakeGuess1() {
		assertTrue(ew.takeGuess('e'));
		assertFalse(ew.getMask()[2]);
		assertEquals(2, ew.getFamilySize());
		assertEquals(" _ _ e", ew.toString());
	}
	
	@Test
	public void testTakeGuess2() {
		assertFalse(ew.takeGuess('x'));
		for (boolean mask : ew.getMask()) {
			assertTrue(mask);
		}
		assertEquals(3, ew.getFamilySize());
		assertEquals(" _ _ _", ew.toString());
	}
	
	@Test
	public void testTakeGuess3() {
		assertTrue(ew.takeGuess('o'));
		assertFalse(ew.getMask()[0]);
		assertEquals(1, ew.getFamilySize());
		assertEquals(" o _ _", ew.toString());
	}

	@Test
	public void testGetKey1() {
		assertEquals("a__", ew.getKey("abc", 'a'));
	}
	
	@Test
	public void testGetKey2() {
		assertEquals("___", ew.getKey("abc", 'd'));
	}
	
	@Test
	public void testGetKey3() {
		assertEquals("a_a", ew.getKey("aba", 'a'));
	}

	@Test
	public void testUpdateWordFamily1() {
		ew.updateWordFamily('e');
		assertEquals(2, ew.getFamilySize());
	}
	
	@Test
	public void testUpdateWordFamily2() {
		ew.updateWordFamily('x');
		assertEquals(3, ew.getFamilySize());
	}
	
	@Test
	public void testUpdateWordFamily3() {
		ew.updateWordFamily('o');
		assertEquals(1, ew.getFamilySize());
	}
}
