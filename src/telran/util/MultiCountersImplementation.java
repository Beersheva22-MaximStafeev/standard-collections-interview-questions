package telran.util;

import java.util.*;
import java.util.Map.Entry;

public class MultiCountersImplementation implements MultiCounters {

	 HashMap<Object, Integer> hash = new HashMap<>();
	 TreeMap<Integer, HashSet<Object>> counter = new TreeMap<>();
	 
	// return How many times the item has been added, if item is the new one then 1 will be returned
	@Override
	public Integer addItem(Object item) { //O[LogN]
		Integer res = hash.get(item); //O[1]
		if (res != null) {
			removeItem(item, res); //O[LogN]
			res++;
		} else {
			res = 1;
		}
		hash.put(item, res); //O[1]
		
		HashSet<Object> set = counter.get(res); //O[LogN]
		if (set != null) {
			set.add(item); //O[1]
		} else {
			set = new HashSet<>();
			set.add(item); //O[1]
			counter.put(res, set); //O[LogN]
		}
		return res;
	}

	// return how many times a given item has been added, if no one then null will be returned
	@Override
	public Integer getValue(Object item) { //O[1]
		return hash.get(item);
	}

	// @return true if item has been removed otherwise false (if a given item doesn't exists)
	@Override
	public boolean remove(Object item) { //O[LOgN]
		boolean res = false;
		Integer value = hash.remove(item); //O[1]
		if (value != null) {
			res = true;
			removeItem(item, value); //O[LogN]
		}
		return res;
	}

	private void removeItem(Object item, Integer value) {
		counter.get(value).remove(item); //O[LogN]*O[1]
		if (counter.get(value).size() == 0) { //O[LogN]
			counter.remove(value); //O[LogN]
		}
	}

	// @return set of items with maximal counters
	@Override
	public Set<Object> getMaxItems() { //O[LogN]
		Entry<Integer, HashSet<Object>> res = counter.lastEntry();
		return res == null ? new HashSet<>() : res.getValue();
	}
//	
//	public void trace() {
//		System.out.println("In set counters:");
//		counter.forEach(el -> System.out.println(el.obj + ": " + el.counter));
//		System.out.println("In map:");
//		hash.forEach((k, v) -> System.out.println("Key:" + k + ", v: " + v.counter));
//	}
//
}
