package home.kwyho.bible.data.KJV;

import home.kwyho.bible.data.AbbreviationHashTable;
import home.kwyho.bible.data.AbstractBibleDAO;
import home.kwyho.bible.data.BibleBook;
import home.kwyho.bible.data.BibleBookParser;

import java.io.IOException;
import java.util.Set;

public class KJVBibleDAO extends AbstractBibleDAO {

	public KJVBibleDAO(String booksFolder) {
		super("King James Version", "KJV", booksFolder);
	}
	
	public KJVBibleDAO() {
		this("");
	}

	@Override
	protected void initializeBible() {
		Set<String> abbrSet = AbbreviationHashTable.getHashTable().keySet();
		BibleBookParser parser = new BibleBookParser();
		
		for (String abbr: abbrSet) {
			String bookName = (!abbr.equals("so"))?AbbreviationHashTable.retrieveBookName(abbr):"Song of Solomon";
			String bookFileName = bookName.replace(" ", "")+".txt";
			String bookFilePath = booksFolder + bookFileName;
			//System.out.println("===="+bookName+"====");
			try {
				BibleBook book = parser.parseBook(bookFilePath, abbr);
				bibleBookHashTable.put(abbr, book);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	public static void main(String[] args) {
		String booksFolder = (args.length==1)?args[0]:"";
		KJVBibleDAO kjvBible = new KJVBibleDAO(booksFolder);
		System.out.println(kjvBible.getBook("ge").getChapter(1).getVerse(1).getPassage());
	}
}
