package home.kwyho.bible.data;

public class BibleBook {
	private String abbreviatedName;

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
