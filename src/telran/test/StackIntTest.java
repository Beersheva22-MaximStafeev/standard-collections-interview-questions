package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.StackInt;

class StackIntTest {
	StackInt stack;
	Integer[] ar = {1, 5, 2, 6, 1, 4, 8, 7, 7};

	@BeforeEach
	void setUp() throws Exception {
		stack = new StackInt();
		Arrays.stream(ar).forEach(stack::push);
	}

	@Test
	void pushTest() {
		stack.push(10);
		assertEquals(10, stack.pop());
		assertEquals(7, stack.pop());
		stack.push(10);
		assertEquals(10, stack.pop());
	}

	@Test
	void getMaxTest() {
		assertEquals(8, stack.getMax());
		assertEquals(7, stack.pop());
		assertEquals(8, stack.getMax());
		assertEquals(7, stack.pop());
		assertEquals(8, stack.getMax());
		assertEquals(8, stack.pop());
		assertEquals(6, stack.getMax());
		assertEquals(4, stack.pop());
		assertEquals(6, stack.getMax());
		assertEquals(1, stack.pop());
		assertEquals(6, stack.getMax());
		assertEquals(6, stack.pop());
		assertEquals(5, stack.getMax());
		assertEquals(2, stack.pop());
		assertEquals(5, stack.getMax());
		assertEquals(5, stack.pop());
		assertEquals(1, stack.getMax());
		assertEquals(1, stack.pop());
		assertThrowsExactly(NoSuchElementException.class, () -> stack.getMax());
	}
	
	@Test
	void popTest() {
		for (int i = ar.length - 1; i >= 0; i--) {
			assertEquals(ar[i], stack.pop());
		}
		assertThrowsExactly(NoSuchElementException.class, () -> stack.pop());
	}
	
	@Test
	void isEmptyTest() {
		for (int i = 0; i < ar.length; i++) {
			assertFalse(stack.isEmpty());
			assertEquals(ar[ar.length - 1 - i], stack.pop());
		}
		assertTrue(stack.isEmpty());
		stack.push(1);
		assertFalse(stack.isEmpty());
	}
}
