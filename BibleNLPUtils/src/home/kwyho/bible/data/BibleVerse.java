package home.kwyho.bible.data;

import java.io.Serializable;

public class BibleVerse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 627832917716483467L;
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
