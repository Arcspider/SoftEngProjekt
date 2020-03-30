package dtu.library.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents the business logic associated with the library application.
 * @author Hubert
 *
 */
public class LibraryApp {

	private boolean loggedIn = false;
	private List<Book> books = new ArrayList<>();

	/**
	 * @return true if the administrator is logged in, false otherwise.
	 */
	public boolean adminLoggedIn() {
		return loggedIn;
	}

	/**
	 * Logs in the administrator provided that the correct password is supplied.
	 * @param password
	 * @return true if the administrator could be logged in, false otherwise.
	 */
	public boolean adminLogin(String password) {
		loggedIn = password.equals("adminadmin");
		return loggedIn;
	}

	/**
	 * Adds a book to the library, so that it can be searched and borrowed.
	 * Only the administrator can do this. Thus, the administrator has to be logged in.
	 * @param book, the book to be added
	 * @throws OperationNotAllowedException if the administrator is not logged in
	 */
	public void addBook(Book book) throws OperationNotAllowedException {
		if (!adminLoggedIn()) {
			throw new OperationNotAllowedException("Administrator login required");
		}
		books.add(book);
	}

	/**
	 * @return the list of books currently known by the library.
	 */
	public List<Book> getBooks() {
		return books;
	}

	/**
	 * Search for a set of books by text. The text can be any substring of 
	 * either the title, the author, and the signature.
	 * @param searchText
	 * @return a list of books containing the searchText in either title, author, and signature
	 */
	public List<Book> search(String searchText) {
//		/* Simple version */
//		List<Book> found = new ArrayList<>();
//		for (Book book : books) {
//			if (book.match(searchText)) {
//				found.add(book);
//			}
//		}
//		return found;
		/* Using Java 8 streams to search */
		return books.stream().filter(b -> b.match(searchText)).collect(Collectors.toList());
	}

	/**
	 * Logs out the administrator.
	 */
	public void adminLogout() {
		loggedIn = false;
	}
	
	public boolean containsBookWithSinature(String signature) {
		return books.stream().anyMatch(m -> m.getSignature().contentEquals(signature));
	}

}
