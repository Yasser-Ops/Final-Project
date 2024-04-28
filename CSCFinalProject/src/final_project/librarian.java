package final_project;

import java.util.*;
import static final_project.book.books;
import static final_project.user.users;

public class librarian extends person implements librarian_procedures {
	private int employeeID;
	static LinkedList<librarian> librarians = new LinkedList<>();

	// constructor
	public librarian(String name, int age, String gender, int employeeID) {
		super(name, age, gender);
		this.employeeID = employeeID;
	}

	// copy constructor
	public librarian(librarian l) {
		super(l.getName(), l.getAge(), l.getGender());
		this.employeeID = l.employeeID;
	}

	// getters and setters
	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	// addLibrarian() method
	public void addLibrarian() {
		Scanner sc = new Scanner(System.in);

		System.out.println("enter name:");
		String name = sc.nextLine();

		System.out.println("enter age:");
		int age = sc.nextInt();

		if (age < 18 || age > 64) {
			throw new IllegalArgumentException("age must be between 18 and 64");
		}

		System.out.println("enter gender:");
		String gender = sc.next();

		// random employee ID
		int employeeID;
		int ub = 9999, lb = 1000;
		do {
			employeeID = (int) (Math.random() * 100) % ((ub - lb) + 1) + lb;
		} while (isEmployeeIDExists(employeeID));

		librarian lib = new librarian(name, age, gender, employeeID);

		librarians.add(lib);

		System.out.println("librarian added successfully");
	}

	// method to check if employeeID already exists
	private boolean isEmployeeIDExists(int employeeID) {
		for (librarian librarian : librarians) {
			if (librarian.getEmployeeID() == employeeID) {
				return true;
			}
		}
		return false;
	}

	// checkInformation() method override
	@Override
	public String checkInformation() {
		return "name: " + getName() + "\nage: " + getAge() + "\ngender: " + getGender() + "\nemployee ID: "
				+ employeeID;
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
	public void addUser() {
		Scanner sc = new Scanner(System.in);

		System.out.println("enter user name: ");
		String name = sc.nextLine();

		System.out.println("enter user age: ");
		int age = sc.nextInt();

		if (age < 18 || age > 100) {
			throw new IllegalArgumentException("age must be between 18 and 100");
		}

		System.out.println("enter user gender: ");
		String gender = sc.next();

		// random library card
		int libraryCard;
		int ub = 9999, lb = 1000;
		do {
			libraryCard = (int) (Math.random() * 100) % ((ub - lb) + 1) + lb;
		} while (isLibraryCardExists(libraryCard));

		user user = new user(name, age, gender, libraryCard);

		users.add(user);

		System.out.println("user added successfully");
	}

	// method to check if library card already exists
	private boolean isLibraryCardExists(int libraryCard) {
		for (user user : users) {
			if (user.getLibraryCardNum() == libraryCard) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void addBooks() {
		Scanner sc = new Scanner(System.in);

		System.out.println("enter the ISBN:");
		int ISBN = sc.nextInt();

		System.out.println("enter book title:");
		String title = sc.nextLine();

		System.out.print("enter book author:");
		String author = sc.nextLine();

		System.out.print("enter book genre:");
		String genre = sc.nextLine();

		System.out.println("is it available? (0 for no, 1 for yes)");
		int x = sc.nextInt();
		boolean b = true;
		if (x == 0) {
			b = false;
		} else if (x == 1) {
			b = true;
		}

		System.out.println("is it reserved? (0 for no, 1 for yes)");
		int y = sc.nextInt();
		boolean t = true;
		if (y == 0) {
			t = false;
		} else if (y == 1) {
			t = true;
		}

		book book = new book(ISBN, title, author, genre, b, t);
		books.add(book);
		System.out.println("book added successfully");
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

}
