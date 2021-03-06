package home.kwyho.bible.verseRef;

import home.kwyho.bible.data.AbbreviationHashTable;
import home.kwyho.bible.data.AbstractBibleDAO;

public class VerseReference {
	String bibleBookAbbr;
	Integer chapter;
	Integer verse;
	
	public VerseReference(String bibleBookAbbr, Integer chapter, Integer verse) {
		this.bibleBookAbbr = bibleBookAbbr;
		this.chapter = chapter;
		this.verse = verse;
	}

	public String getBibleBookAbbr() {
		return bibleBookAbbr;
	}

	public void setBibleBookAbbr(String bibleBookAbbr) {
		this.bibleBookAbbr = bibleBookAbbr;
	}

	public Integer getChapter() {
		return chapter;
	}

	public void setChapter(Integer chapter) {
		this.chapter = chapter;
	}

	public Integer getVerse() {
		return verse;
	}

	public void setVerse(Integer verse) {
		this.verse = verse;
	}
	
	public String getText(AbstractBibleDAO bibleDAO) {
		String scripture = "";
		try {
			scripture = bibleDAO.getBook(bibleBookAbbr).getChapter(chapter).getVerse(verse).getPassage();
		} catch (IndexOutOfBoundsException e) {
			scripture = "--";
		}
		return scripture;
	}
	
	public String getTextWithRef(AbstractBibleDAO bibleDAO) {
		String displayTxt = AbbreviationHashTable.retrieveBookName(bibleBookAbbr)+" "+chapter+":"+verse+"  ";
		displayTxt += getText(bibleDAO);
		return displayTxt;
	}
}
