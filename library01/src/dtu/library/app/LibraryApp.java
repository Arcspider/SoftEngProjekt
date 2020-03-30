package dtu.library.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryApp {

	public boolean loggedIn = false;
	public ArrayList<Book> library = new ArrayList<>();
	
	public void addBook(Book book) throws OperationNotAllowedException {
		if(loggedIn) {
			library.add(book);
		} else {
			throw new OperationNotAllowedException("Administrator login required");
		}
	}

	public boolean containsBookWithSinature(String signature) {
		return true;
	}

	public boolean adminLoggedIn() {
		// TODO Auto-generated method stub
		return loggedIn;
	}

	public boolean adminLogin(String password) {
		// TODO Auto-generated method stub
		if(password.equals("adminadmin") && (!loggedIn)) {
			loggedIn = true;
			return loggedIn;
		}
		loggedIn = false;
		return loggedIn;
	}

	public void adminLogout() {
		// TODO Auto-generated method stub
		if(loggedIn) {
			loggedIn = false;
		}
		
	}

	public List<Book> search(String searchText) {
		// TODO Auto-generated method stub
		return library.stream().filter(book -> book.getSignature().contains(searchText) || book.getAuthor().contains(searchText) || book.getTitle().contains(searchText)).collect(Collectors.toList());
	}

}
