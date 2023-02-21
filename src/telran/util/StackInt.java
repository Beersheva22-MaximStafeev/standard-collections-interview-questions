package telran.util;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Stack;

public class StackInt {
	// all the methods should be with complexity O[1] 
	private Stack<Node> stack = new Stack<>();
	private static class Node {
		int value;
		int max;
		public Node(int value, int max) {
			super();
			this.value = value;
			this.max = max;
		}
	}
	
	/**
	 * adds element into top of stack
	 * @param element
	 */
	public void push(int element) {
		stack.push(new Node(element, getMax(element)));
	}
	
	private int getMax(int element) {
		return stack.isEmpty() ? element : Math.max(getMax(), element);
	}

	/**
	 * @return number from top of stack or throws NoSuchElementException if stack is empty
	 */
	public int pop() {
		try {
			return stack.pop().value;
		} catch (EmptyStackException e) {
			throw new NoSuchElementException();
		}
	}
	
	/** 
	 * @return true if stack is empty otherwise false
	 */
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	/**
	 * @return maximal value of the stack or throws NoSuchElementException if stack is empty
	 */
	public int getMax() {
		try {
			return stack.peek().max;
		} catch (EmptyStackException e) {
			throw new NoSuchElementException();
		}
	}
}
