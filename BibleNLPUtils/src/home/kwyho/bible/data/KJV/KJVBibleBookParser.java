package home.kwyho.bible.data.KJV;

import home.kwyho.bible.data.BibleBook;
import home.kwyho.bible.data.BibleBookParser;
import home.kwyho.bible.data.BibleChapter;
import home.kwyho.bible.data.BibleVerse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KJVBibleBookParser implements BibleBookParser {
	
	private Pattern chapterIndicator = Pattern.compile("CHAPTER [1-9][0-9]|CHAPTER [0-9]|PSALM [1-9][1-9][0-9]|PSALM [1-9][0-9]|PSALM [0-9]");
	
	public BibleBook parseBook(String filename, String abbr) throws IOException {
		File inFile = new File(filename);
		FileReader fr = new FileReader(inFile);
		BufferedReader reader = new BufferedReader(fr);
	
		BibleBook book = new BibleBook(abbr);
		List<BibleChapter> chapters = book.getChapters();
		
		// Skip the first two lines
		reader.readLine();
		reader.readLine();
		
		String line = "";
		Integer chapterIdx = 0;
		Integer verseIdx = 0;
		while ((line = reader.readLine()) != null) {
			String trimmedLine = line.trim();
			Matcher matcher = chapterIndicator.matcher(trimmedLine);
			int firstSpacePos = trimmedLine.indexOf(' ');  // first space
			int verseNo = 0;
			try {
				verseNo = Integer.parseInt(trimmedLine.substring(0, firstSpacePos));
			} catch (NumberFormatException e) {
				verseNo = 0;
			} catch (StringIndexOutOfBoundsException e1) {
				verseNo = 0;
			}
			if (matcher.find()) {
				chapterIdx = Integer.parseInt(trimmedLine.substring(firstSpacePos+1));
				BibleChapter chapter = new BibleChapter();
				chapters.add(chapter);
				verseIdx = 0;
			} else if (trimmedLine.equals("")) {
				// System.out.println("---");
			} else if (verseNo > 0) {
				BibleChapter chapter = book.getChapter(chapterIdx);
				verseIdx++;
				if (verseNo > verseIdx) {
					verseIdx = verseNo;
				}
				String passage = trimmedLine.substring(firstSpacePos).replaceAll("[^a-zA-Z1-9\\s\\p{Punct}]", "").trim();
				chapter.getVerses().add(new BibleVerse(verseIdx, passage));
				//System.out.println(abbr+" "+chapterIdx+" : "+verseIdx+" : "+passage);
			}
		}
		
		reader.close();
		return book;
	}
}
