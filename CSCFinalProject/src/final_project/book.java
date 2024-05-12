package final_project;

import java.util.LinkedList;

public class book {
	private int ISBN;
	private String title;
	private String author;
	protected String genre;
	private boolean availability;
	private boolean reserved;
	static LinkedList<book> books = new LinkedList<>();

	// default constructor
	public book() {
		this.title = "unknown";
		this.author = "unknown";
		this.genre = "unknown";
		this.availability = true;
		this.reserved = false;
	}

	// book constructor
	public book(int ISBN, String title, String author, String genre, boolean availability, boolean reserved) {
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.availability = availability;
		this.reserved = reserved;
	}

	// copy constructor
	public book(book b) {
		this.ISBN = b.ISBN;
		this.title = b.title;
		this.author = b.author;
		this.genre = b.genre;
		this.availability = b.availability;
		this.reserved = b.reserved;
	}

	// getters and setters
	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public static LinkedList<book> getBooks() {
		return books;
	}

	public static void setBooks(LinkedList<book> books) {
		book.books = books;
	}

	// toString() method
	@Override
	public String toString() {
		return "book [title=" + title + ", author=" + author + ", ISBN=" + ISBN + ", availability=" + availability + "]";
	}

}
