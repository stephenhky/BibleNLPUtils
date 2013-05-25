package home.kwyho.bible.data;

import java.util.ArrayList;
import java.util.List;

public class BibleChapter {
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
