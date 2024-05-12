package final_project;

import java.io.*;
import java.util.LinkedList;

public class file {
	public static void writeUsersToFile(LinkedList<user> userList, String filename) {
		try (PrintWriter writer = new PrintWriter(filename)) {
			for (user user : userList) {
				writer.println(
						user.getName() + "," + user.getAge() + "," + user.getGender() + "," + user.getLibraryCardNum());
			}
		} catch (IOException e) {
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}

	public static void readUsersFromFile(LinkedList<user> userList, String filename) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				String name = parts[0];
				int age = Integer.parseInt(parts[1]);
				String gender = parts[2];
				int libraryCardNum = Integer.parseInt(parts[3]);
				userList.add(new user(name, age, gender, libraryCardNum));
			}
		} catch (IOException | NumberFormatException e) {
			System.err.println("Error reading from file: " + e.getMessage());
		}
	}

	public static void writeLibrariansToFile(LinkedList<librarian> librarianList, String filename) {
		try (PrintWriter writer = new PrintWriter(filename)) {
			for (librarian librarian : librarianList) {
				writer.println(librarian.getName() + "," + librarian.getAge() + "," + librarian.getGender() + ","
						+ librarian.getEmployeeID());
			}
		} catch (IOException e) {
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}

	public static void readLibrariansFromFile(LinkedList<librarian> librarianList, String filename) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				String name = parts[0];
				int age = Integer.parseInt(parts[1]);
				String gender = parts[2];
				int employeeID = Integer.parseInt(parts[3]);
				librarianList.add(new librarian(name, age, gender, employeeID));
			}
		} catch (IOException | NumberFormatException e) {
			System.err.println("Error reading from file: " + e.getMessage());
		}
	}

	public static void writeBooksToFile(LinkedList<book> bookList, String filename) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
			for (book book : bookList) {
				writer.println(book.getISBN() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getGenre()
						+ "," + book.isAvailability() + "," + book.isReserved());
			}
		} catch (IOException e) {
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}

	public static void readBooksFromFile(LinkedList<book> bookList, String filename) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				int ISBN = Integer.parseInt(parts[0]);
				String title = parts[1];
				String author = parts[2];
				String genre = parts[3];
				boolean available = Boolean.parseBoolean(parts[4]);
				boolean reserved = Boolean.parseBoolean(parts[5]);
				bookList.add(new book(ISBN, title, author, genre, available, reserved));
			}
		} catch (IOException | NumberFormatException e) {
			System.err.println("Error reading from file: " + e.getMessage());
		}
	}

}
