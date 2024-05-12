package final_project;

import java.util.*;

import javax.swing.JOptionPane;

public class librarian extends person implements librarian_procedures {
	private int employeeID;
	static LinkedList<librarian> librarians = new LinkedList<>();

	public librarian(String Name, int Age, String gender, int employeeID) {
		super(Name, Age, gender);
		this.employeeID = employeeID;

	}

	// Copy constructor
	public librarian(librarian l) {
		super(l.getName(), l.getAge(), l.getGender());
		this.employeeID = l.employeeID;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public static LinkedList<librarian> getLibrarians() {
		return librarians;
	}

	public static void setLibrarians(LinkedList<librarian> librarians) {
		librarian.librarians = librarians;
	}

	public void addLibrarian() {

		Random random = new Random();
		int generatedID = random.nextInt(9000) + 1000;
		int finalGeneratedID = generatedID;
		generatedID = librarians.stream().anyMatch(lib -> lib.getEmployeeID() == finalGeneratedID)
				? random.nextInt(9000) + 1000
				: generatedID;
		librarian newLibrarian = new librarian(this.getName(), this.getAge(), this.getGender(), generatedID);
		librarians.add(newLibrarian);
		JOptionPane.showMessageDialog(null, "Employee ID is: " + generatedID, "employee ID",
				JOptionPane.INFORMATION_MESSAGE);

		JOptionPane.showMessageDialog(null, "registered successfully");
	}

	@Override
	public String checkInformation() {
		JOptionPane.showMessageDialog(null, super.toString() + "\nEmployee ID:" + this.getEmployeeID());
		return "";
	}

	@Override
	public void searchBookByTitle(String Title) {
		StringBuilder result = new StringBuilder();
		boolean i = false;
		for (book j : book.getBooks()) {
			if (j.getTitle().equals(Title)) {
				i = true;
				result.append(j.toString()).append("\n");
				break;
			}
		}
		if (i) {
			JOptionPane.showMessageDialog(null, "search results for '" + Title + "':\n" + result.toString());
		} else {
			JOptionPane.showMessageDialog(null, "no books found by thr title: '" + Title + "'");
		}
	}

	@Override
	public void searchBookByAuthor(String Name) {
		StringBuilder result = new StringBuilder();
		Boolean k = false;
		for (book w : book.getBooks()) {
			if (w.getAuthor().equals(Name)) {
				k = true;
				result.append(w.getTitle()).append("\n");
			}
		}
		if (k) {
			JOptionPane.showMessageDialog(null, "books by author '" + Name + "':\n" + result.toString());
		} else {
			JOptionPane.showMessageDialog(null, "no books found by '" + Name + "'");
		}
	}

	@Override
	public void searchBookByGenre(String Genre) {
		StringBuilder result = new StringBuilder();
		boolean b = false;
		for (book c : book.getBooks()) {
			if (c.getGenre().equals(Genre)) {
				b = true;
				result.append(c.getTitle()).append("\n");
			}
		}
		if (b) {
			JOptionPane.showMessageDialog(null, "books in genre '" + Genre + "':\n" + result.toString());
		} else {
			JOptionPane.showMessageDialog(null, "no books found in '" + Genre + "'");
		}
	}

	@Override
	public user addUser() {
		Random random = new Random();
		int generatedLibraryCard = random.nextInt(9000) + 1000;
		user newUser = new user(this.getName(), this.getAge(), this.getGender(), generatedLibraryCard);
		user.getUsers().add(newUser);
		JOptionPane.showMessageDialog(null, "library card number: " + generatedLibraryCard, "library card number",
				JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "registered successfully");
		return newUser;
	}

	@Override
	public book addBooks(book b) {
		book newBook = new book(b.getISBN(), b.getTitle(), b.getAuthor(), b.getGenre(), b.isAvailability(),
				b.isReserved());
		book.getBooks().add(newBook);
		return newBook;
	}

	public void BorrowBook(String Title) {
		for (book x : book.getBooks()) {
			if (x.getTitle().equals(Title)) {
				if (x.isAvailability() == true) {
					if (x.isReserved() == false) {
						x.setAvailability(false);
						JOptionPane.showMessageDialog(null, Title + " has been borrowed");
						return;
					} else {
						JOptionPane.showMessageDialog(null, Title + " cannot be borrowed");
						return;
					}
				}
			}
		}
		JOptionPane.showMessageDialog(null, Title + " does not exist");
	}

	public book returnBook(String Title) {
		for (book h : book.getBooks()) {
			if (h.getTitle().equals(Title)) {
				if (h.isAvailability() == false) {
					if (h.isReserved() == false) {
						return h;
					} else {
						JOptionPane.showMessageDialog(null, Title + " cannot be returned");
						return null;
					}
				}
			}
		}
		JOptionPane.showMessageDialog(null, Title + " does not exist");
		return null;
	}

	public void reserveBook(String Title) {
		for (book d : book.getBooks()) {
			if (d.getTitle().equals(Title)) {
				if (d.isAvailability() == true) {
					if (d.isReserved() == false) {
						d.setReserved(true);
						JOptionPane.showMessageDialog(null, Title + " has been reserved");
						return;
					} else {
						JOptionPane.showMessageDialog(null, Title + " cannot be reserved");
						return;
					}
				}
			}
		}
		JOptionPane.showMessageDialog(null, Title + " does not exist");
	}

}