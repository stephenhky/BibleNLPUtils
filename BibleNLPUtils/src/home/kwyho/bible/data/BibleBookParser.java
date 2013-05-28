package home.kwyho.bible.data;

import java.io.IOException;


public interface BibleBookParser {
	public BibleBook parseBook(String filename, String abbr) throws IOException;
}
