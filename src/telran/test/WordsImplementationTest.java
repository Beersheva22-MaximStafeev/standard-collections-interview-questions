package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Collectors;

import telran.util.WordsImplementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WordsImplementationTest {
	String[] dict = new String[] {"one", "two", "three", "Four", "fiVe", "six", "seven", "Once", "onto"};
	WordsImplementation dictionary;

	@BeforeEach
	void setUp() throws Exception {
		dictionary = new WordsImplementation();
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
				Arrays.stream(dict).filter(el -> el.toLowerCase().startsWith(prefix.toLowerCase())).sorted(String::compareToIgnoreCase).collect(Collectors.toList()),				
				dictionary.getWordsByPrefix(prefix));
	}

}
