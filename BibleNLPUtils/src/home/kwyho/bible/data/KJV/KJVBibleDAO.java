package home.kwyho.bible.data.KJV;

import home.kwyho.bible.data.AbbreviationHashTable;
import home.kwyho.bible.data.AbstractBibleDAO;
import home.kwyho.bible.data.BibleBook;
import home.kwyho.bible.data.BibleBookParser;

import java.io.IOException;
import java.util.Set;

public class KJVBibleDAO extends AbstractBibleDAO {

	public KJVBibleDAO() {
		super();
		translation = "King James Version";
		translationAbbreviation = "KJV";
	}


	@Override
	protected void initializeBible() {
		Set<String> abbrSet = AbbreviationHashTable.getHashTable().keySet();
		BibleBookParser parser = new BibleBookParser();
		
		for (String abbr: abbrSet) {
			String bookName = AbbreviationHashTable.retrieveBookName(abbr);
			String bookFileName = bookName.replace(" ", "")+".txt";
			
			try {
				BibleBook book = parser.parseBook(bookFileName, abbr);
				bibleBookHashTable.put(abbr, book);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	public static void main(String[] args) {
		KJVBibleDAO kjvBible = new KJVBibleDAO();
		kjvBible.getBook("ge").getChapter(1).getVerse(1);
	}
}
