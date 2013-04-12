package home.kwyho.bible.data;

import java.util.HashMap;
import java.util.Map;

public class AbbreviationHashTable {
	private static Map<String, String> hashTable;
	
	static {
		hashTable = new HashMap<String, String>();
		hashTable.put("ge", "Genesis");
		hashTable.put("ex", "Exodus");
		hashTable.put("le", "Leviticus");
		hashTable.put("nu", "Numbers");
		hashTable.put("de", "Deuteronomy");
		hashTable.put("jos", "Joshua");
		hashTable.put("jdg", "Judges");
	}
	
	public static String retrieveBookName(String abbreviation) {
		String bookName = "";
		if (hashTable.containsKey(abbreviation)) {
			bookName = hashTable.get(abbreviation);
		}
		return bookName;
	}
	
}
