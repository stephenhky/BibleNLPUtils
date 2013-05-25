package home.kwyho.bible.data;

import java.util.ArrayList;
import java.util.List;

public class BibleBook {
	private String abbreviatedName;
	private List<BibleChapter> chapters;

	public List<BibleChapter> getChapters() {
		return chapters;
	}
	
	public BibleChapter getChapter(int chapterIdx) {
		return chapters.get(chapterIdx-1);
	}

	public BibleBook(String abbreviatedName) {
		this.abbreviatedName = abbreviatedName;
		chapters = new ArrayList<BibleChapter>();
	}

	public String getAbbreviatedName() {
		return abbreviatedName;
	}

	public void setAbbreviatedName(String abbreviatedName) {
		this.abbreviatedName = abbreviatedName;
	}
	
	public String getBookName() {
		return AbbreviationHashTable.retrieveBookName(abbreviatedName);
	}
}
