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
		hashTable.put("ru", "Ruth");
		hashTable.put("1sa", "1 Samuel");
		hashTable.put("2sa", "2 Samuel");
		hashTable.put("1ki", "1 Kings");
		hashTable.put("2ki", "2 Kings");
		hashTable.put("1ch", "1 Chronicles");
		hashTable.put("2ch", "2 Chronicles");
		hashTable.put("ezr", "Ezrah");
		hashTable.put("ne", "Nehemiah");
		hashTable.put("est", "Esther");
		hashTable.put("job", "Job");
		hashTable.put("ps", "Psalm");
		hashTable.put("pr", "Proverbs");
		hashTable.put("ec", "Ecclesiastes");
		hashTable.put("so", "Song of Songs");
		hashTable.put("is", "Isaiah");
		hashTable.put("je", "Jeremiah");
		hashTable.put("la", "Lamentations");
		hashTable.put("eze", "Ezekiel");
		hashTable.put("da", "Daniel");
		hashTable.put("ho", "Hosea");
		hashTable.put("joe", "Joel");
		hashTable.put("am", "Amos");
		hashTable.put("ob", "Obadiah");
		hashTable.put("jon", "Jonah");
		hashTable.put("mic", "Micah");
		hashTable.put("na", "Nahum");
		hashTable.put("hab", "Habakkuk");
		hashTable.put("zep", "Zephaniah");
		hashTable.put("hag", "Haggai");
		hashTable.put("zec", "Zechariah");
		hashTable.put("mal", "Malachi");
		
		hashTable.put("mt", "Matthew");
		hashTable.put("mk", "Mark");
		hashTable.put("lk", "Luke");
		hashTable.put("jn", "John");
		hashTable.put("ac", "Acts");
		hashTable.put("ro", "Romans");
		hashTable.put("1co", "1 Corinthians");
		hashTable.put("2co", "2 Corinthians");
		hashTable.put("ga", "Galatians");
		hashTable.put("eph", "Ephesians");
		hashTable.put("php", "Philippians");
		hashTable.put("col", "Colossians");
		hashTable.put("1th", "1 Thessalonians");
		hashTable.put("2th", "2 Thessalonians");
		hashTable.put("1ti", "1 Timothy");
		hashTable.put("2ti", "2 Timothy");
		hashTable.put("ti", "Titus");
		hashTable.put("phm", "Philemon");
		hashTable.put("heb", "Hebrews");
		hashTable.put("jam", "James");
		hashTable.put("1pe", "1 Peter");
		hashTable.put("2pe", "2 Peter");
		hashTable.put("1jn", "1 John");
		hashTable.put("2jn", "2 John");
		hashTable.put("3jn", "3 John");
		hashTable.put("jud", "Jude");
		hashTable.put("rev", "Revelation");
	}
	
	public static String retrieveBookName(String abbreviation) {
		String bookName = "";
		if (hashTable.containsKey(abbreviation)) {
			bookName = hashTable.get(abbreviation);
		}
		return bookName;
	}
	
}
