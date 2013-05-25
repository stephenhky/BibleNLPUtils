package home.kwyho.bible.data;

import java.util.HashMap;

public abstract class AbstractBibleDAO {
	protected String translation;
	protected String translationAbbreviation;
	protected String booksFolder;
	
	public AbstractBibleDAO(String translation, String translationAbbreviation,
			String booksFolder) {
		this.translation = translation;
		this.translationAbbreviation = translationAbbreviation;
		this.booksFolder = booksFolder;
		bibleBookHashTable = new HashMap<String, BibleBook>();
		initializeBible();
	}

	protected HashMap<String, BibleBook> bibleBookHashTable;

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
