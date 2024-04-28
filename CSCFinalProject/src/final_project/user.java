package final_project;

import java.util.*;
import static final_project.book.books;

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

	// checkInformation() method override
	@Override
	public String checkInformation() {
		return "name: " + getName() + "\nage: " + getAge() + "\ngender: " + getGender() + "\nlibrary card number: "
				+ libraryCardNum;
	}

	// interface methods implementation
	@Override
	public boolean searchBookByTitle(String title) {
		for (book book : books) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				System.out.println("book with title: " + title + " is found");
				return true;
			}
		}
		System.out.println("book with title: " + title + " not found");
		return false;
	}

	@Override
	public boolean searchBookByAuthor(String author) {
		for (book book : books) {
			if (book.getAuthor().equalsIgnoreCase(author)) {
				System.out.println("book from author: " + author + ", by the title: " + book.getTitle() + " is found");
				return true;
			}
		}
		System.out.println("book from author: " + author + " is not found");
		return false;
	}

	@Override
	public boolean searchBookByGenre(String genre) {
		for (book book : books) {
			if (book.getGenre().equalsIgnoreCase(genre)) {
				System.out.println("book from genre: " + genre + ", by the title: " + book.getTitle() + " is found");
				return true;
			}
		}
		System.out.println("book from genre: " + genre + " is not found");
		return false;
	}

	@Override
	public void borrowBook(String title) {
		for (book book : books) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				if (book.isAvailability() && !book.isReserved()) {
					// book is available and not reserved, so borrow it
					book.setAvailability(false);
					System.out.println("book by the title: " + title + ", is borrowed successfully");
					return;
				} else {
					System.out.println("book is not available or reserved");
					return;
				}
			}
		}
		System.out.println("book does not exist");
	}

	@Override
	public void returnBook(String title) {
		for (book book : books) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				if (!book.isAvailability() && !book.isReserved()) {
					// book is borrowed, so return it
					book.setAvailability(true);
					System.out.println("book by the title: " + title + ", is returned successfully");
					return;
				} else {
					System.out.println("book is not borrowed");
					return;
				}
			}
		}
		System.out.println("book does not exist");
	}

	@Override
	public void reserveBook(String title) {
		for (book book : books) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				if (book.isAvailability() && !book.isReserved()) {
					// book is available and not reserved, so reserve it
					book.setReserved(true);
					System.out.println("book by the title: " + title + ", is reserved");
					return;
				} else {
					System.out.println("book is not available or already reserved");
					return;
				}
			}
		}
		System.out.println("book does not exist");
	}

}
