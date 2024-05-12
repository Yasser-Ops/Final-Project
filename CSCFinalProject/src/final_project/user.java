package final_project;

import java.util.*;
import javax.swing.JOptionPane;

public class user extends person implements user_procedures {
	private int libraryCardNum;
	static LinkedList<user> users = new LinkedList<>();

	// constructor
	public user(String name, int age, String gender, int libraryCardNum) {
		super(name, age, gender);
		this.libraryCardNum = libraryCardNum;
	}

	// copy constructor
	public user(user u) {
		super(u.getName(), u.getAge(), u.getGender());
		this.libraryCardNum = u.libraryCardNum;
	}

	// getters and setters
	public int getLibraryCardNum() {
		return libraryCardNum;
	}

	public void setLibraryCardNum(int libraryCardNum) {
		this.libraryCardNum = libraryCardNum;
	}

	public static LinkedList<user> getUsers() {
		return users;
	}

	public static void setUsers(LinkedList<user> users) {
		user.users = users;
	}

	// checkInformation() method override
	@Override
	public String checkInformation() {
		JOptionPane.showMessageDialog(null, super.toString() + "\ncard number:" + this.libraryCardNum);
		return "";
	}

	// interface methods implementation
	@Override
	public void searchBookByTitle(String title) {
		StringBuilder result = new StringBuilder();
		boolean f = false;
		for (book b : book.getBooks()) {
			if (b.getTitle().equals(title)) {
				f = true;
				result.append(b.toString()).append("\n");
				break;
			}
		}
		if (f) {
			JOptionPane.showMessageDialog(null, "Search Results for Title '" + title + "':\n" + result.toString());
		} else {
			JOptionPane.showMessageDialog(null, "no books found with the title '" + title + "'");
		}
	}

	@Override
	public void searchBookByAuthor(String author) {
		StringBuilder result = new StringBuilder();
		Boolean f = false;
		for (book b : book.getBooks()) {
			if (b.getAuthor().equals(author)) {
				f = true;
				result.append(b.getTitle()).append("\n");
			}
		}
		if (f) {
			JOptionPane.showMessageDialog(null, "books by '" + author + "':\n" + result.toString());
		} else {
			JOptionPane.showMessageDialog(null, "no books found by '" + author + "'");
		}

	}

	@Override
	public void searchBookByGenre(String genre) {
		StringBuilder result = new StringBuilder();
		boolean f = false;
		for (book b : book.getBooks()) {
			if (b.getGenre().equals(genre)) {
				f = true;
				result.append(b.getTitle()).append("\n");
			}
		}
		if (f) {
			JOptionPane.showMessageDialog(null, "Books in '" + genre + "':\n" + result.toString());
		} else {
			JOptionPane.showMessageDialog(null, "no books found in '" + genre + "'");
		}

	}

	@Override
	public void borrowBook(String title) {
		for (book b : book.getBooks()) {
			if (b.getTitle().equals(title)) {
				if (b.isAvailability() == true) {
					if (b.isReserved() == false) {
						b.setAvailability(false);
						JOptionPane.showMessageDialog(null, "the book '" + title + "' has been borrowed");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "the book '" + title + "' cannot be borrowed");
						return;
					}
				}

			}
		}
		JOptionPane.showMessageDialog(null, "the book '" + title + "' does not exist");
	}

	@Override
	public book returnBook(String title) {
		for (book b : book.getBooks()) {
			if (b.getTitle().equals(title)) {
				if (b.isAvailability() == false) {
					if (b.isReserved() == false) {
						return b;
					} else {
						JOptionPane.showMessageDialog(null, "the book '" + title + "' cannot be returned");
						return null;
					}
				}
			}
		}
		JOptionPane.showMessageDialog(null, "the book '" + title + "' does not exist");
		return null;
	}

	@Override
	public void reserveBook(String title) {
		for (book b : book.getBooks()) {
			if (b.getTitle().equals(title)) {
				if (b.isAvailability() == true) {
					if (b.isReserved() == false) {
						b.setReserved(true);
						JOptionPane.showMessageDialog(null, "the book '" + title + "' has been reserved");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "the book '" + title + "' cannot be reserved");
						return;
					}
				}

			}
		}
		JOptionPane.showMessageDialog(null, "the book '" + title + "' does not exist");
	}

}
