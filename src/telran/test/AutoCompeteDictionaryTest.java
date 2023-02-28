package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Collectors;

import telran.util.AutoCompeteDictionary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutoCompeteDictionaryTest {
	String[] dict = new String[] {"one", "two", "three", "four", "five", "six", "seven", "once", "onto"};
	AutoCompeteDictionary dictionary;

	@BeforeEach
	void setUp() throws Exception {
		dictionary = new AutoCompeteDictionary();
		for (String word: dict) {
			dictionary.addWord(word);
		}
	}

	@Test
	void test() {
		runTest("one");
		runTest("");
		runTest("o");
		runTest("qq");
		runTest("ops");
		runTest("tratata");
		runTest("e");
		for (String word: dict) {
			for (int i = 0; i < word.length(); i++) {
				runTest(word.substring(0, i));
				runTest("!" + word.substring(0, i));
				runTest(word.substring(0, i) + "!");
				for (int j = 0; j <= i; j++) {
					runTest(word.substring(0, j) + "!" + word.substring(j, i));
				}
			}
		}
	}
	
	void runTest(String prefix) {
		assertIterableEquals(
				Arrays.stream(dict).filter(el -> el.startsWith(prefix)).collect(Collectors.toSet()),				
				dictionary.autoCompete(prefix));
	}

}
