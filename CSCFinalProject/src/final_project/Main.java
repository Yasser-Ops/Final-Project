package final_project;

public class main {
	public static void main(String[] args) {
		book.getBooks().add(new book(1, "a", "a", "a", true, false));
		book.getBooks().add(new book(2, "b", "b", "b", true, false));
		book b1 = new book(3, "c", "c", "c", true, false);
		book.getBooks().add(b1);
		book.getBooks().add(new book(4, "d", "d", "a", false, false));
		librarian l1 = new librarian("ali", 20, "male", 2222);
		librarian.getLibrarians().add(l1);
		librarian.getLibrarians().add(new librarian("zoro", 25, "female", 3333));
		l1.addLibrarian();
		l1.addUser();
		user.getUsers().add(new user("hussein", 21, "male", 2320074));
		user u1 = new user("mahmoud", 18, "female", 2410000);
		user.getUsers().add(u1);

		System.out.println("books:");
		for (book b : book.getBooks()) {
			System.out.println(b.toString());
		}

		System.out.println("librarians:");
		for (librarian l : librarian.getLibrarians()) {
			System.out.println(l.toString());
		}

		System.out.println("users:");
		for (user u : user.getUsers()) {
			System.out.println(u.toString());
		}

		u1.searchBookByAuthor("a");
		u1.searchBookByGenre("b");
		u1.searchBookByTitle("d");
		u1.searchBookByAuthor("a");
		u1.borrowBook("a");
		u1.returnBook("a");
		u1.returnBook("c");
		u1.reserveBook("d");
		u1.borrowBook("d");

		for (book i : book.getBooks()) {
			System.out.println(i.toString());
		}

		u1.checkInformation();
		l1.checkInformation();
		l1.searchBookByAuthor("f");
		l1.searchBookByAuthor("a");
		l1.searchBookByGenre("m");
		l1.searchBookByGenre("a");
		l1.searchBookByTitle("c");
		l1.searchBookByTitle("g");

	}

}
