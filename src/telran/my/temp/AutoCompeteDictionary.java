package telran.my.temp;

import java.util.*;
import java.util.Map.Entry;

public class AutoCompeteDictionary {
	
	Dictionary words = new Dictionary();
	
	private static class Dictionary {
		HashMap<Character, Dictionary> dict = new HashMap<>();

		void addWord(String word) {
			if (word.length() >= 1) {
				dict.computeIfAbsent(word.charAt(0), k -> new Dictionary()).addWord(word.substring(1));
			}
		}

		public HashSet<String> getWords(String prefix) {
			// FIX ME
			HashSet<String> res = new HashSet<>();
			if (prefix.length() > 0) {
				Dictionary endings = dict.get(prefix.charAt(0));
				if (endings != null) {
					for (String s: endings.getWords(prefix.substring(1))) {
						res.add(prefix.substring(0, 1).concat(s));
					}
				}
			} else {
				for (Entry<Character, Dictionary> entry: dict.entrySet()) {
					if (entry.getValue().dict.size() > 0) {
						for (String s: entry.getValue().getWords("")) {
							res.add(entry.getKey().toString().concat(s));
						}
					} else {
						res.add(entry.getKey().toString());
					}
				}
				if (dict.size() == 0) {
					res.add("");
				}
			}
			return res;
		}
		
	}

	public void addWord(String word) {
		words.addWord(word);
	}
	
	public Set<String> autoCompete(String prefix) {
		return words.getWords(prefix);
	}
}
