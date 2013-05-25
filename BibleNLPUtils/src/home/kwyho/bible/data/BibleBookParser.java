package home.kwyho.bible.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BibleBookParser {
	
	private Pattern chapterIndicator = Pattern.compile("CHAPTER [1-9][0-9]|CHAPTER [0-9]");
	
	public BibleBook parseBook(String filename, String abbreviation) throws IOException {
		File inFile = new File(filename);
		FileReader fr = new FileReader(inFile);
		BufferedReader reader = new BufferedReader(fr);
	
		BibleBook book = new BibleBook(abbreviation);
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
			if (matcher.find()) {
				// System.out.println(matcher.group());
				chapterIdx = Integer.parseInt(trimmedLine.substring(8));
				BibleChapter chapter = new BibleChapter();
				chapters.add(chapter);
				verseIdx = 0;
				System.out.println("Chapter "+chapterIdx);
			} else if (trimmedLine.equals("")) {
				// System.out.println("---");
			} else {
				BibleChapter chapter = book.getChapter(chapterIdx);
				int verseNoEndPos = trimmedLine.indexOf(' ');
				int verseNo = Integer.parseInt(trimmedLine.substring(0, verseNoEndPos));
				verseIdx++;
				if (verseNo > verseIdx) {
					verseIdx = verseNo;
				}
				String passage = trimmedLine.substring(verseNoEndPos).trim();
				chapter.getVerses().add(new BibleVerse(verseIdx, passage));
				System.out.println(verseIdx+" : "+passage);
			}
		}
		
		reader.close();
		return book;
	}
	
	public static void main(String[] args) throws IOException {
		BibleBookParser parser = new BibleBookParser();
		BibleBook book = parser.parseBook("/Users/hok1/Documents/KJVBible/AV1611text/Genesis.txt", "ge");
		
		System.out.println(book.getChapter(23).getVerse(2).getPassage());
	}
	
}
