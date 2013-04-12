package home.kwyho.bible.data;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractBibleDAO {
	private String translation;
	private String translationAbbreviation;
	
	protected ArrayList<BibleBook> books;
	private HashMap<String, Integer> bookToIdx;

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public String getTranslationAbbreviation() {
		return translationAbbreviation;
	}

	public void setTranslationAbbreviation(String translationAbbreviation) {
		this.translationAbbreviation = translationAbbreviation;
	}
	
	protected void initializeBible() {
		books = new ArrayList<BibleBook>();
		bookToIdx = new HashMap<String, Integer>();
		
	}
}
