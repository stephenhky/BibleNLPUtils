package home.kwyho.bible.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BibleChapter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7115822366619473208L;
	private List<BibleVerse> verses;
	
	public BibleChapter() {
		verses = new ArrayList<BibleVerse>();
	}

	public List<BibleVerse> getVerses() {
		return verses;
	}
	
	public BibleVerse getVerse(int verseIdx) {
		return verses.get(verseIdx-1);
	}
}
