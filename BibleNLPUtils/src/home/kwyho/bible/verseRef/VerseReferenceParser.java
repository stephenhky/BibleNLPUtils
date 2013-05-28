package home.kwyho.bible.verseRef;

import java.io.FileNotFoundException;
import java.io.IOException;

import home.kwyho.bible.data.AbbreviationHashTable;
import home.kwyho.bible.data.AbstractBibleDAO;
import home.kwyho.bible.data.KJV.KJVBibleDAO;


public class VerseReferenceParser {

	public static VerseReference parseSingleVerseReference(String verseRef) {
		String[] tokens = verseRef.split(" ");
		
		String bookAbbr = tokens[0];
		try {
			Integer chapter = Integer.parseInt(tokens[1]);
			Integer verse = Integer.parseInt(tokens[2]);
			
			if (AbbreviationHashTable.retrieveBookName(bookAbbr) != null) {
				return new VerseReference(bookAbbr, chapter, verse);
			}
		} catch (NumberFormatException e) {
			return new VerseReference("jn", 0, 0);
		}
		
		return new VerseReference("jn", 0, 0);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		AbstractBibleDAO kjv = new KJVBibleDAO();
		kjv.loadSerializedBible();
		VerseReference ref = VerseReferenceParser.parseSingleVerseReference("jn 3 16");
		System.out.println(ref.getText(kjv));
	}
}
