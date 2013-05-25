package home.kwyho.bible.data;

public class BibleVerse {
	private int verseIdx;
	private String passage;
	
	public BibleVerse(int verseIdx, String passage) {
		super();
		this.verseIdx = verseIdx;
		this.passage = passage;
	}

	public int getVerseIdx() {
		return verseIdx;
	}

	public void setVerseIdx(int verseIdx) {
		this.verseIdx = verseIdx;
	}

	public String getPassage() {
		return passage;
	}

	public void setPassage(String passage) {
		this.passage = passage;
	}
}
