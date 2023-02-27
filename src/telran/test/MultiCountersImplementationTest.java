package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.*;
import telran.util.MultiCountersImplementation;

class MultiCountersImplementationTest {

	MultiCountersImplementation mc = new MultiCountersImplementation();
	
	@BeforeEach
	void setUp() throws Exception {
		String[] strings = new String[] {"aa", "bb", "cc", "aa", "bb", "zz", "xx", "gg", "aa", "cc", "zc"};
		Arrays.stream(strings).forEach(mc::addItem);
	}

	@Test
	void test() {
		assertEquals(1, mc.getValue("xx"));
		assertEquals(3, mc.getValue("aa"));
		assertNull(mc.getValue("pp"));
		assertArrayEquals(new String[] {"aa"}, mc.getMaxItems().toArray(new String[0]));
		assertEquals(2, mc.getValue("bb"));
		assertEquals(3, mc.addItem("bb"));
		assertEquals(3, mc.getValue("bb"));
		assertArrayEquals(new String[] {"aa", "bb"}, mc.getMaxItems().toArray(new String[0]));
		assertTrue(mc.remove("bb"));
		assertFalse(mc.remove("asdf"));
		assertArrayEquals(new String[] {"aa"}, mc.getMaxItems().toArray(new String[0]));
		assertTrue(mc.remove("aa"));
		assertFalse(mc.remove("aa"));
		assertArrayEquals(new String[] {"cc"}, mc.getMaxItems().toArray(new String[0]));
		assertTrue(mc.remove("cc"));
		assertFalse(mc.remove("cc"));
		assertArrayEquals(new String[] {"zz", "xx", "gg", "zc"}, mc.getMaxItems().toArray(new String[0]));
	}

	@Test
	void test2() {
		assertEquals(4, mc.addItem("aa"));
		assertEquals(3, mc.addItem("bb"));
		assertEquals(5, mc.addItem("aa"));
		assertEquals(4, mc.addItem("bb"));
		assertTrue(mc.remove("bb"));
		assertTrue(mc.remove("aa"));
		assertArrayEquals(new String[] {"cc"}, mc.getMaxItems().toArray(new String[0]));
	}
}
