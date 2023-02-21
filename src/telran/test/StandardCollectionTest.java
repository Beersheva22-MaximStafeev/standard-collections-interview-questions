package telran.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
class StandardCollectionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Disabled
	void test() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
//		list.add(5); // throw UnsupportedOperatorException because it is not real list.
		List<Integer> list__1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 70, -20));
		list__1.add(5); // good operation
		// как удалить повторяющиеся элементы из аррэйЛиста с сохранением порядка элементов? 
		// сделать из него линкедХашСет и выгрузить его снова в аррэйЛист.
		List<Integer> list__2 = new ArrayList<>(new LinkedHashSet<>(list__1));
		System.out.println(list__1);
		System.out.println(list__2);
		List<Integer> listSub = list__1.subList(6, 9);
		System.out.println(listSub);
		listSub.add(1, -2);
		System.out.println(list__1);
		listSub.sort(Integer::compare);
		System.out.println(list__1);
	}

	@Test
	@Disabled
	void test1() {
		String[] ar = new String[] {"1", "lmn", "1", "2", "3", "lmn", "0", "vv", "vv", "lmn"};
		Map<String, List<String>> map = Arrays.stream(ar).collect(Collectors.groupingBy(s -> s));
		System.out.println(map);
		Map<String, Long> map1 = Arrays.stream(ar)
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		System.out.println(map1);
		Arrays.stream(ar)
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()))
				.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e-> System.out.printf("%s: %d%n", e.getKey(), e.getValue()));
	}
	
	@Test
	void displayDigitStatistics() {
		// генерируем 1 млн значений интеджеров от [1 до макс-1], надо определить частоту встречаемости цифр
		// и распечатать частоту встречаемости этих цифр
		new Random().ints(1_000_000, 1, Integer.MAX_VALUE)
			.mapToObj(Integer::toString)
			.flatMapToInt(el -> el.chars())
			.map(Character::getNumericValue)
			.boxed()
			.collect(Collectors.groupingBy(el -> el, Collectors.counting()))
			.entrySet().stream()
			.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
			.forEach(e-> System.out.printf("%d: %d%n", e.getKey(), e.getValue()));
	}

}
