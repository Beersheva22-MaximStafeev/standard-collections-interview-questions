package telran.util;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class WordsImplementation implements Words {
	TreeSet<String> dictionary = new TreeSet<>(String::compareToIgnoreCase);
	
	@Override
	public boolean addWord(String word) {
		return dictionary.add(word);
	}

	@Override
	public List<String> getWordsByPrefix(String prefix) {
		Set<String> res;
		if (prefix.equals("")) {
			res = dictionary;
		} else {
			res = dictionary.subSet(prefix, nextPrefix(prefix));
		}
		return res.stream().collect(Collectors.toList());
	}

	private String nextPrefix(String prefix) {
		char[] chars = prefix.toCharArray();
		chars[prefix.length() - 1]++;
		return String.valueOf(chars);
	}

}
