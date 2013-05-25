package home.kwyho.bible.data;

import java.util.HashMap;

public abstract class AbstractBibleDAO {
	protected String translation;
	protected String translationAbbreviation;
	
	protected HashMap<String, BibleBook> bibleBookHashTable;
	
	public AbstractBibleDAO() {
		bibleBookHashTable = new HashMap<String, BibleBook>();
		initializeBible();
	}

	public String getTranslation() {
		return translation;
	}

	public String getTranslationAbbreviation() {
		return translationAbbreviation;
	}
	
	public BibleBook getBook(String abbr) {
		return bibleBookHashTable.get(abbr);
	}
	
	public HashMap<String, BibleBook> getBibleBookHashTable() {
		return bibleBookHashTable;
	}

	abstract protected void initializeBible();
}
