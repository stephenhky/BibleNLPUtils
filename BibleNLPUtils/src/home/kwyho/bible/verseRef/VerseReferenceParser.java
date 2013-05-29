package home.kwyho.bible.verseRef;

import home.kwyho.bible.data.AbbreviationHashTable;
import home.kwyho.bible.data.AbstractBibleDAO;
import home.kwyho.bible.data.BibleBook;
import home.kwyho.bible.data.BibleChapter;
import home.kwyho.bible.data.KJV.KJVBibleDAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VerseReferenceParser {
	
	private static Pattern[] patterns = {Pattern.compile("[a-z]{2,3} \\d{1,3}:\\d{1,3}-\\d{1,3}:\\d{1,3}"),
		Pattern.compile("[a-z]{2,3} \\d{1,3}:\\d{1,3}-\\d{1,3}"),
		Pattern.compile("[a-z]{2,3} \\d{1,3}:\\d{1,3}"),
		Pattern.compile("[a-z]{2,3} \\d{1,3}-\\d{1,3}"),
		Pattern.compile("[a-z]{2,3} \\d{1,3} \\d{1,3}"),
		Pattern.compile("[a-z]{2,3} \\d{1,3}")};

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
	
	public static List<VerseReference> parseVersesReference(String versesRef, AbstractBibleDAO bibleDAO) {
		List<VerseReference> verses = new ArrayList<VerseReference>();

		for (int i=0; i<patterns.length; i++) {
			Matcher matcher = patterns[i].matcher(versesRef);
			if (matcher.find()) {
				String matchedRef = matcher.group();

				if (i==4) {
					verses.add(parseSingleVerseReference(matchedRef));
					break;
				} else if (i==2) {
					verses.add(parseSingleVerseReference(matchedRef.replace(":", " ")));
					break;
				} else if (i==1) {
					String[] tokens0 = matchedRef.split(" ");
					String abbr = tokens0[0];
					String[] tokens1 = tokens0[1].split(":");
					Integer chapterIdx = Integer.parseInt(tokens1[0]);
					String[] tokens2 = tokens1[1].split("-");
					Integer startVerseIdx = Integer.parseInt(tokens2[0]);
					Integer endVerseIdx = Integer.parseInt(tokens2[1]);
					for (int verseIdx=startVerseIdx; verseIdx<=endVerseIdx; verseIdx++) {
						verses.add(new VerseReference(abbr, chapterIdx, verseIdx));
					}
					break;
				} else if (i==3) {
					String[] tokens0 = matchedRef.split(" ");
					String abbr = tokens0[0];
					String[] tokens1 = tokens0[1].split("-");
					Integer startChapterIdx = Integer.parseInt(tokens1[0]);
					Integer endChapterIdx = Integer.parseInt(tokens1[1]);
					
					BibleBook book = bibleDAO.getBook(abbr);
					for (int chapterIdx=startChapterIdx; chapterIdx<=endChapterIdx; chapterIdx++) {
						BibleChapter chapter = book.getChapter(chapterIdx);
						for (int verseIdx=0; verseIdx<chapter.getVerses().size(); verseIdx++) {
							verses.add(new VerseReference(abbr, chapterIdx, verseIdx+1));
						}
					}
					
					break;
				} else if (i==0) {
					String[] tokens0 = matchedRef.split(" ");
					String abbr = tokens0[0];
					String[] tokens1 = tokens0[1].split("-");
					String[] tokens2a = tokens1[0].split(":");
					String[] tokens2b = tokens1[1].split(":");
					Integer startChapterIdx = Integer.parseInt(tokens2a[0]);
					Integer startVerseIdx = Integer.parseInt(tokens2a[1]);
					Integer endChapterIdx = Integer.parseInt(tokens2b[0]);
					Integer endVerseIdx = Integer.parseInt(tokens2b[1]);
					
					BibleBook book = bibleDAO.getBook(abbr);
					for (int chapterIdx=startChapterIdx; chapterIdx<=endChapterIdx; chapterIdx++) {
						BibleChapter chapter = book.getChapter(chapterIdx);
						for (int verseIdx=(chapterIdx==startChapterIdx)?startVerseIdx:1; verseIdx<=((chapterIdx==endChapterIdx)?endVerseIdx:chapter.getVerses().size()); verseIdx++) {
							verses.add(new VerseReference(abbr, chapterIdx, verseIdx));
						}
					}
					break;
				} else if (i==5) {
					String[] tokens0 = matchedRef.split(" ");
					String abbr = tokens0[0];
					Integer chapterIdx = Integer.parseInt(tokens0[1]);
					
					BibleBook book = bibleDAO.getBook(abbr);
					BibleChapter chapter = book.getChapter(chapterIdx);
					for (int verseIdx=0; verseIdx<chapter.getVerses().size(); verseIdx++) {
						verses.add(new VerseReference(abbr, chapterIdx, verseIdx+1));
					}
					
					break;
				}
			}
		}
		
		return verses;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		AbstractBibleDAO kjv = new KJVBibleDAO();
		kjv.loadSerializedBible();
		VerseReference ref = VerseReferenceParser.parseSingleVerseReference("jn 3 16");
		System.out.println(ref.getText(kjv));
		List<VerseReference> refs = VerseReferenceParser.parseVersesReference("ps 1:1-5:1", kjv);
		for (VerseReference verse: refs) {
			System.out.println(verse.getTextWithRef(kjv));
		}
	}
}
