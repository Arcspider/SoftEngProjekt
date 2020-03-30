package dtu.library.app;

/**
 * This class represents a book with title, author, and signature, where signature
 * is a unique key used by the librarian to identify the book. Very often it is 
 * composed of the first letters of the authors plus the year the book was published.
 * @author Hubert
 *
 */
public class Book {
	private String title;
	private String author;
	private String signature;

	public Book(String title, String author, String signature) {
		this.title = title;
		this.author = author;
		this.signature = signature;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getSignature() {
		return signature;
	}

	/**
	 * 
	 * @param searchText
	 * @return returns true if the searchText appears as substring in either title, author, or signature field.
	 */
	public boolean match(String searchText) {
		return signature.contains(searchText) || title.contains(searchText) || author.contains(searchText);
	}

}
