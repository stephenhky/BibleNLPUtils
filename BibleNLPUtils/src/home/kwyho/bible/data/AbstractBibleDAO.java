package home.kwyho.bible.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public abstract class AbstractBibleDAO {
	protected String translation;
	protected String translationAbbreviation;
	protected String booksFolder;
	protected HashMap<String, BibleBook> bibleBookHashTable;
	
	public AbstractBibleDAO(String translation, String translationAbbreviation,
			String booksFolder) {
		this.translation = translation;
		this.translationAbbreviation = translationAbbreviation;
		this.booksFolder = booksFolder;
		bibleBookHashTable = new HashMap<String, BibleBook>();
	}

	public String getTranslation() {
		return translation;
	}

	public String getTranslationAbbreviation() {
		return translationAbbreviation;
	}
	
	public BibleBook getBook(String abbr) {
		return bibleBookHashTable.get(abbr);
	}
	
	public HashMap<String, BibleBook> getBibleBookHashTable() {
		return bibleBookHashTable;
	}

	abstract public void parseBible();
	
	public void serializeBible() throws IOException, IOException {
		String filename = translationAbbreviation+"Bible.zip";
		File outFile = new File(filename);
		ObjectOutputStream oos = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(outFile)));
		oos.writeObject(bibleBookHashTable);
		oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public void loadSerializedBible() throws FileNotFoundException, IOException, ClassNotFoundException {
		String filename = translationAbbreviation+"Bible.zip";
		File inFile = new File(filename);
		ObjectInputStream ois = new ObjectInputStream(new GZIPInputStream(new FileInputStream(inFile)));
		//ObjectInputStream ois = new ObjectInputStream(new GZIPInputStream(AbstractBibleDAO.class.getClassLoader().getResourceAsStream(filename)));
		bibleBookHashTable = (HashMap<String, BibleBook>) ois.readObject();
		ois.close();
	}
}
