package final_project;

import java.util.Scanner;

public class Main {

	public static void printMenu() {
		System.out.println("Choose an action :");
		System.out.println("1- Add a librarian");
		System.out.println("2- Add a user");
		System.out.println("3- Add a book");
		System.out.println("4- Search for a book by Author");
		System.out.println("5- Search for a book by Title");
		System.out.println("6- Search for a book by Genre");
		System.out.println("7- Borrow a book");
		System.out.println("8- Return a book");
		System.out.println("9- Reserve a book");
		System.out.println("10- Exit");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		librarian librarian = new librarian("default", 21, "Male", 1);
		user user = new user("default", 21, "Male", 1);
		book book = new book();
		boolean running = true;
		
		while(running) {
			printMenu();
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				librarian.addLibrarian();
				break;
			case 2:
				user.addUser();
				break;
			case 3:
				librarian.addBooks();
				break;
			case 4:
				System.out.println("Enter author:");
				String author = sc.next();
				librarian.searchBookByAuthor(author);
				break;
			case 5:
				System.out.println("Enter title:");
				String title = sc.next();
				librarian.searchBookByAuthor(title);
				break;
			case 6:
				System.out.println("Enter genre:");
				String genre = sc.next();
				librarian.searchBookByGenre(genre);
				break;
			case 7:
				System.out.println("Enter title");
				String title1 = sc.next();
				user.borrowBook(title1);
				break;
			case 8:
				System.out.println("Enter title");
				String title2 = sc.next();
				user.returnBook(title2);
				break;
			case 9: 
				System.out.println("Enter title");
				String title3 = sc.next();
				user.reserveBook(title3);
				break;
			case 10:
				running = false;
				break;
			}
		}
	}

}
