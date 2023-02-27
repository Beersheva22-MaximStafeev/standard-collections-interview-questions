package telran.util;

import java.util.HashMap;
import java.util.Map;

public class MyArrayInt {
	private int size;
	private int defaultValue;
	Map<Integer, Integer> map = new HashMap<>();
	
	public MyArrayInt(int size, int value) {
		this.size = size;
		this.defaultValue = value;
		
	}

	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public void set(int index, int value) {
		checkIndex(index);
		map.put(index, value);
	}
	
	public int get(int index) {
		checkIndex(index);
		return map.getOrDefault(index, defaultValue);
//		Integer res;
//		res = map.get(index);
//		return res == null ? defaultValue : res;
	}
	
	public void setAll(int value) {
		map = new HashMap<>();
		defaultValue = value;
	}
}
