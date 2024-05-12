package final_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.event.ActionEvent;

public class gui extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton librarianButton;
	JButton userButton;
	JLabel background;
	JLabel welcomeLabel;

	public gui() {

		setTitle("Welcome");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		welcomeLabel = new JLabel(
				"<html><div style='text-align: center; width: 600px;'>library system<br><br></div></html>");
		welcomeLabel.setFont(new Font("New Times Roman", Font.BOLD, 14));
		welcomeLabel.setForeground(Color.BLACK);
		welcomeLabel.setBounds(10, 10, 780, 150);
		welcomeLabel.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel librarianInstruction = new JLabel(
				"<html>If you are a librarian, click the Librarian button. &#8595;</html>");
		librarianInstruction.setBounds(100, 250 - 40, 200, 40);
		JLabel userInstruction = new JLabel("<html>If you are a user, click the User button. &#8595;</html>");
		userInstruction.setBounds(500, 250 - 40, 200, 40);
		librarianButton = new JButton("Librarian");
		librarianButton.setBounds(100, 250, 200, 80);
		librarianButton.setFont(new Font("New Times Roman", Font.BOLD, 24));
		librarianButton.setForeground(Color.WHITE);
		librarianButton.setBackground(new Color(66, 134, 244));
		librarianButton.addActionListener(this);
		userButton = new JButton("User");
		userButton.setBounds(500, 250, 200, 80);
		userButton.setFont(new Font("New Times Roman", Font.BOLD, 24));
		userButton.setForeground(Color.WHITE);
		userButton.setBackground(new Color(66, 134, 244));
		userButton.addActionListener(this);
		add(welcomeLabel);
		add(librarianButton);
		add(userButton);
		add(librarianInstruction);
		add(userInstruction);
		setLocationRelativeTo(null);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				file.writeUsersToFile(user.getUsers(), "C:/Users/user/Desktop/users.txt");
				file.writeLibrariansToFile(librarian.getLibrarians(), "C:/Users/user/Desktop/librarians.txt");
				file.writeBooksToFile(book.getBooks(), "C:/Users/user/Desktop/books.txt");
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == librarianButton) {
			dispose();
			new LoginUI("Librarian");
		} else if (e.getSource() == userButton) {
			dispose();
			new LoginUI("User");
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			file.readUsersFromFile(user.getUsers(), "C:/Users/user/Desktop/users.txt");
			file.readLibrariansFromFile(librarian.getLibrarians(), "C:/Users/user/Desktop/librarians.txt");
			file.readBooksFromFile(book.getBooks(), "C:/Users/user/Desktop/books.txt");
		});
	}

	class LoginUI extends JFrame implements ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JTextField idField;
		JTextField libraryCardField;
		JButton loginButton;
		JButton registerButton;
		String userType;

		public LoginUI(String userType) {
			this.userType = userType;
			setTitle("Login (" + userType + ")");
			setSize(800, 600);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(null);
			JLabel libraryCardLabel = null;
			JLabel idLabel = null;
			if (userType.equals("User")) {
				libraryCardLabel = new JLabel("Library Card Number:");
				libraryCardLabel.setFont(new Font("New Times Roman", Font.PLAIN, 16));
				libraryCardLabel.setBounds(300 - 150, 150, 200, 30);
				libraryCardField = new JTextField();
				libraryCardField.setBounds(300 + 5, 150, 250, 30);
			} else if (userType.equals("Librarian")) {
				idLabel = new JLabel("Employee ID:");
				idLabel.setFont(new Font("New Times Roman", Font.PLAIN, 16));
				idLabel.setBounds(300 - 150, 200, 200, 30);
				idField = new JTextField();
				idField.setBounds(300 + 5, 200, 250, 30);
			}
			loginButton = new JButton("Login");
			loginButton.setBounds(100, 250, 200, 80);
			loginButton.setFont(new Font("New Times Roman", Font.BOLD, 24));
			loginButton.setBackground(new Color(66, 134, 244));
			loginButton.setForeground(Color.WHITE);
			loginButton.addActionListener(this);
			registerButton = new JButton("Register");
			registerButton.setFont(new Font("New Times Roman", Font.BOLD, 24));
			registerButton.setBounds(500, 250, 200, 80);
			registerButton.setBackground(new Color(66, 134, 244));
			registerButton.setForeground(Color.WHITE);
			registerButton.addActionListener(this);
			JButton backButton = new JButton("Back");
			backButton.setBounds(10, 10, 80, 30);
			backButton.addActionListener(e -> {
				dispose();
				new gui();
			});
			if (userType.equals("User")) {
				add(libraryCardLabel);
				add(libraryCardField);
			} else if (userType.equals("Librarian")) {
				add(idLabel);
				add(idField);
			}
			add(backButton);
			add(loginButton);
			add(registerButton);
			setLocationRelativeTo(null);
			setVisible(true);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					file.writeUsersToFile(user.getUsers(), "C:/Users/user/Desktop/users.txt");
					file.writeLibrariansToFile(librarian.getLibrarians(), "C:/Users/user/Desktop/librarians.txt");
					file.writeBooksToFile(book.getBooks(), "C:/Users/user/Desktop/books.txt");
				}
			});
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == loginButton) {
				if (userType.equals("Librarian")) {
					String empId = idField.getText();
					try {
						int employeeid = Integer.parseInt(empId);
						boolean librarianfound = false;
						for (librarian librarian : librarian.getLibrarians()) {
							if (librarian.getEmployeeID() == employeeid) {
								librarianfound = true;
								JOptionPane.showMessageDialog(this, "Welcome Librarian!");
								displayLibrarianOptions(librarian);
								break;
							}
						}
						if (!librarianfound) {
							Object[] options = { "Retry", "Go to Menu", "Exit" };
							int option = JOptionPane.showOptionDialog(this,
									"Invalid Employee ID. What do you want to do?", "Invalid Library Card Number",
									JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, options,
									options[0]);
							if (option == JOptionPane.YES_OPTION) {
								idField.setText("");
							} else if (option == JOptionPane.NO_OPTION) {
								dispose();
								new gui();
							} else {
								System.exit(0);
							}
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(this, "Employee ID must be an integer");
					}
				} else {
					String libraryCardNumber = libraryCardField.getText();
					try {
						int cardNumber = Integer.parseInt(libraryCardNumber);
						boolean userfound = false;
						for (user user : user.getUsers()) {
							if (user.getLibraryCardNum() == cardNumber) {
								userfound = true;
								JOptionPane.showMessageDialog(this, "Welcome User!");
								displayUserOptions(user);
								break;
							}
						}
						if (!userfound) {
							Object[] options = { "Retry", "Go to Menu", "Exit" };
							int option = JOptionPane.showOptionDialog(this,
									"Invalid library card number. What do you want to do?",
									"Invalid Library Card Number", JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.ERROR_MESSAGE, null, options, options[0]);
							if (option == JOptionPane.YES_OPTION) {
								libraryCardField.setText("");
							} else if (option == JOptionPane.NO_OPTION) {
								dispose();
								new gui();
							} else {
								System.exit(0);
							}
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(this, "Library card number must be an integer");
					}
				}
			} else if (e.getSource() == registerButton) {
				dispose();
				new RegistrationForm(userType);
			}
		}

		@SuppressWarnings("null")
		public void displayLibrarianOptions(librarian librarian) {
			setTitle("Librarian Options");
			getContentPane().removeAll();
			JButton addLibrarianButton = new JButton("Add Librarian");
			JButton addUserButton = new JButton("Add User");
			JButton checkInfoButton = new JButton("Check Information");
			JButton searchByTitleButton = new JButton("Search Book By Title");
			JButton searchByAuthorButton = new JButton("Search Book By Author");
			JButton searchByGenreButton = new JButton("Search Book By Genre");
			JButton borrowBookButton = new JButton("Borrow Book");
			JButton returnBookButton = new JButton("Return Book");
			JButton reserveBookButton = new JButton("Reserve Book");
			JButton addBookButton = new JButton("Add Book");
			JButton backButton = new JButton("Back");
			backButton.setBounds(10, 10, 80, 30);
			int buttonWidth = 200;
			int buttonHeight = 30;
			int startX = (getWidth() - buttonWidth) / 2;
			int startY = (getHeight() - (10 * buttonHeight + 9 * 10)) / 2;
			addLibrarianButton.setBounds(startX, startY, buttonWidth, buttonHeight);
			addUserButton.setBounds(startX, startY + (buttonHeight + 10), buttonWidth, buttonHeight);
			addBookButton.setBounds(startX, startY + 2 * (buttonHeight + 10), buttonWidth, buttonHeight);
			checkInfoButton.setBounds(startX, startY + 3 * (buttonHeight + 10), buttonWidth, buttonHeight);
			searchByTitleButton.setBounds(startX, startY + 4 * (buttonHeight + 10), buttonWidth, buttonHeight);
			searchByAuthorButton.setBounds(startX, startY + 5 * (buttonHeight + 10), buttonWidth, buttonHeight);
			searchByGenreButton.setBounds(startX, startY + 6 * (buttonHeight + 10), buttonWidth, buttonHeight);
			borrowBookButton.setBounds(startX, startY + 7 * (buttonHeight + 10), buttonWidth, buttonHeight);
			returnBookButton.setBounds(startX, startY + 8 * (buttonHeight + 10), buttonWidth, buttonHeight);
			reserveBookButton.setBounds(startX, startY + 9 * (buttonHeight + 10), buttonWidth, buttonHeight);
			backButton.addActionListener(e -> {
				dispose();
				new LoginUI("Librarian");
			});
			addLibrarianButton.addActionListener(e -> {
				dispose();
				RegistrationForm registrationForm = new RegistrationForm("Librarian", this);
				registrationForm.setVisible(true);
			});
			addUserButton.addActionListener(e -> {
				dispose();
				RegistrationForm registrationForm = new RegistrationForm("User", this);
				registrationForm.setVisible(true);
			});
			checkInfoButton.addActionListener(e -> {
				System.out.println("Check Information button clicked");
				librarian.checkInformation();
			});
			searchByTitleButton.addActionListener(e -> {
				String title = JOptionPane.showInputDialog(null, "Enter book title:");
				if (title != null && !title.isEmpty()) {
					librarian.searchBookByTitle(title);
				} else if (title == null && title.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a valid book title.");
				}
			});
			addBookButton.addActionListener(e -> {
				new AddBookWindow();
			});
			searchByAuthorButton.addActionListener(e -> {
				String author = JOptionPane.showInputDialog(null, "Enter book author:");
				if (author != null && !author.isEmpty()) {
					librarian.searchBookByAuthor(author);
				} else if (author == null && author.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a valid book title.");
				}
			});
			searchByGenreButton.addActionListener(e -> {
				String genre = JOptionPane.showInputDialog(null, "Enter book genre:");
				if (genre != null && !genre.isEmpty()) {
					librarian.searchBookByGenre(genre);
				} else if (genre == null && genre.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a valid book title.");
				}
			});
			borrowBookButton.addActionListener(e -> {
				String title = JOptionPane.showInputDialog(null, "Enter book title to borrow:");
				if (title != null && !title.isEmpty()) {
					librarian.BorrowBook(title);
				} else {
					JOptionPane.showMessageDialog(null, "Please enter a valid book title to borrow.");
				}
			});
			returnBookButton.addActionListener(e -> {
				String title = JOptionPane.showInputDialog(null, "Enter book title to return:");
				if (title != null && !title.isEmpty()) {
					librarian.returnBook(title);
				} else {
					JOptionPane.showMessageDialog(null, "Please enter a valid book title to return.");
				}
			});
			reserveBookButton.addActionListener(e -> {
				String title = JOptionPane.showInputDialog(null, "Enter book title to reserve:");
				if (title != null && !title.isEmpty()) {
					librarian.reserveBook(title);
				} else {
					JOptionPane.showMessageDialog(null, "Please enter a valid book title to reserve.");
				}
			});
			add(addLibrarianButton);
			add(addUserButton);
			add(checkInfoButton);
			add(searchByTitleButton);
			add(searchByAuthorButton);
			add(searchByGenreButton);
			add(borrowBookButton);
			add(returnBookButton);
			add(reserveBookButton);
			add(addBookButton);
			add(backButton);
			revalidate();
			repaint();
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					file.writeBooksToFile(book.getBooks(), "books.txt");
				}
			});
		}

		@SuppressWarnings("null")
		public void displayUserOptions(user user) {
			setTitle("User Options");
			getContentPane().removeAll();
			JButton searchByTitleButton = new JButton("Search Book By Title");
			JButton searchByAuthorButton = new JButton("Search Book By Author");
			JButton searchByGenreButton = new JButton("Search Book By Genre");
			JButton borrowBookButton = new JButton("Borrow Book");
			JButton returnBookButton = new JButton("Return Book");
			JButton reserveBookButton = new JButton("Reserve Book");
			JButton checkInfoButton = new JButton("Check Information");
			JButton backButton = new JButton("Back");
			backButton.setBounds(10, 10, 80, 30);
			int buttonWidth = 200;
			int buttonHeight = 30;
			int startX = (getWidth() - buttonWidth) / 2;
			int startY = (getHeight() - (7 * buttonHeight + 6 * 10)) / 2;
			searchByTitleButton.setBounds(startX, startY, buttonWidth, buttonHeight);
			searchByAuthorButton.setBounds(startX, startY + (buttonHeight + 10), buttonWidth, buttonHeight);
			searchByGenreButton.setBounds(startX, startY + 2 * (buttonHeight + 10), buttonWidth, buttonHeight);
			borrowBookButton.setBounds(startX, startY + 3 * (buttonHeight + 10), buttonWidth, buttonHeight);
			returnBookButton.setBounds(startX, startY + 4 * (buttonHeight + 10), buttonWidth, buttonHeight);
			reserveBookButton.setBounds(startX, startY + 5 * (buttonHeight + 10), buttonWidth, buttonHeight);
			checkInfoButton.setBounds(startX, startY + 6 * (buttonHeight + 10), buttonWidth, buttonHeight);
			backButton.addActionListener(e -> {
				dispose();
				new LoginUI("User");
			});
			checkInfoButton.addActionListener(e -> {
				System.out.println("Check Information button clicked");
				user.checkInformation();
			});
			searchByTitleButton.addActionListener(e -> {
				String title = JOptionPane.showInputDialog(null, "Enter book title:");
				if (title != null && !title.isEmpty()) {
					user.searchBookByTitle(title);
				} else if (title == null && title.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a valid book title.");
				}
			});
			searchByAuthorButton.addActionListener(e -> {
				String author = JOptionPane.showInputDialog(null, "Enter book author:");
				if (author != null && !author.isEmpty()) {
					user.searchBookByAuthor(author);
				} else if (author == null && author.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a valid book title.");
				}
			});
			searchByGenreButton.addActionListener(e -> {
				String genre = JOptionPane.showInputDialog(null, "Enter book genre:");
				if (genre != null && !genre.isEmpty()) {
					user.searchBookByGenre(genre);
				} else if (genre == null && genre.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a valid book title.");
				}
			});
			borrowBookButton.addActionListener(e -> {
				String title = JOptionPane.showInputDialog(null, "Enter book title to borrow:");
				if (title != null && !title.isEmpty()) {
					user.borrowBook(title);
				} else {
					JOptionPane.showMessageDialog(null, "Please enter a valid book title to borrow.");
				}
			});
			returnBookButton.addActionListener(e -> {
				String title = JOptionPane.showInputDialog(null, "Enter book title to return:");
				if (title != null && !title.isEmpty()) {
					user.returnBook(title);
				} else {
					JOptionPane.showMessageDialog(null, "Please enter a valid book title to return.");
				}
			});
			reserveBookButton.addActionListener(e -> {
				String title = JOptionPane.showInputDialog(null, "Enter book title to reserve:");
				if (title != null && !title.isEmpty()) {
					user.reserveBook(title);
				} else {
					JOptionPane.showMessageDialog(null, "Please enter a valid book title to reserve.");
				}
			});
			add(checkInfoButton);
			add(searchByTitleButton);
			add(searchByAuthorButton);
			add(searchByGenreButton);
			add(borrowBookButton);
			add(returnBookButton);
			add(reserveBookButton);
			add(backButton);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					file.writeBooksToFile(book.getBooks(), "C:/Users/user/Desktop/books.txt");
				}
			});
			revalidate();
			repaint();
		}
	}

	class AddBookWindow extends JFrame implements ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JTextField titleField, authorField, genreField, isbnField;

		public AddBookWindow() {
			setTitle("Add Book");
			setSize(800, 600);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLayout(new GridLayout(5, 2, 10, 10));
			JLabel titleLabel = new JLabel("Title:");
			titleField = new JTextField();
			JLabel authorLabel = new JLabel("Author:");
			authorField = new JTextField();
			JLabel genreLabel = new JLabel("Genre:");
			genreField = new JTextField();
			JLabel isbnLabel = new JLabel("ISBN:");
			isbnField = new JTextField();
			add(titleLabel);
			add(titleField);
			add(authorLabel);
			add(authorField);
			add(genreLabel);
			add(genreField);
			add(isbnLabel);
			add(isbnField);
			JButton submitButton = new JButton("Submit");
			submitButton.addActionListener(this);
			add(submitButton);
			setLocationRelativeTo(null);
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton sourceButton = (JButton) e.getSource();
				if (sourceButton.getText().equals("Submit")) {
					String title = titleField.getText();
					String author = authorField.getText();
					String genre = genreField.getText();
					int isbn = Integer.parseInt(isbnField.getText());
					book b11 = new book(isbn, title, author, genre, true, false);
					librarian L = new librarian("", 0, "", 0);
					L.addBooks(b11);
					dispose();
				}
			}
		}
	}

	class RegistrationForm extends JFrame implements ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JTextField nameField;
		JTextField ageField;
		JComboBox<String> genderComboBox;
		JButton submitButton;
		String userType;
		JLabel errorMessage;
		JFrame previousWindow;
		boolean v;

		public RegistrationForm(String userType) {
			v = true;
			this.userType = userType;
			setTitle("Registration (" + userType + ")");
			setSize(800, 600);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLayout(null);
			JLabel nameLabel = new JLabel("Name:");
			nameLabel.setBounds(200, 150, 80, 20);
			nameField = new JTextField();
			nameField.setBounds(290, 150, 250, 30);
			JLabel ageLabel = new JLabel("Age:");
			ageLabel.setBounds(200, 200, 80, 20);
			ageField = new JTextField();
			ageField.setBounds(290, 200, 250, 30);
			JLabel genderLabel = new JLabel("Gender:");
			genderLabel.setBounds(200, 250, 80, 20);
			String[] genders = { "Male", "Female", "nerd" };
			genderComboBox = new JComboBox<>(genders);
			genderComboBox.setBounds(290, 250, 250, 30);
			submitButton = new JButton("Submit");
			submitButton.setBounds(300, 320, 150, 40);
			submitButton.setBackground(new Color(66, 134, 244));
			submitButton.setForeground(Color.WHITE);
			submitButton.setFont(new Font("New Times Roman", Font.BOLD, 16));
			submitButton.addActionListener(this);
			add(nameLabel);
			add(nameField);
			add(ageLabel);
			add(ageField);
			add(genderLabel);
			add(genderComboBox);
			add(submitButton);
			setLocationRelativeTo(null);
			setVisible(true);
		}

		public RegistrationForm(String userType, JFrame previousWindow) {
			v = false;
			this.userType = userType;
			this.previousWindow = previousWindow;
			setTitle("Registration (" + userType + ")");
			setSize(800, 600);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLayout(null);
			JLabel nameLabel = new JLabel("Name:");
			nameLabel.setBounds(200, 150, 80, 20);
			nameField = new JTextField();
			nameField.setBounds(290, 150, 250, 30);
			JLabel ageLabel = new JLabel("Age:");
			ageLabel.setBounds(200, 200, 80, 20);
			ageField = new JTextField();
			ageField.setBounds(290, 200, 250, 30);
			JLabel genderLabel = new JLabel("Gender:");
			genderLabel.setBounds(200, 250, 80, 20);
			String[] genders = { "Male", "Female", "nerd" };
			genderComboBox = new JComboBox<>(genders);
			genderComboBox.setBounds(290, 250, 250, 30);
			submitButton = new JButton("Submit");
			submitButton.setBounds(300, 320, 150, 40);
			submitButton.setBackground(new Color(66, 134, 244));
			submitButton.setForeground(Color.WHITE);
			submitButton.setFont(new Font("New Times Roman", Font.BOLD, 16));
			submitButton.addActionListener(this);
			add(nameLabel);
			add(nameField);
			add(ageLabel);
			add(ageField);
			add(genderLabel);
			add(genderComboBox);
			add(submitButton);
			setLocationRelativeTo(null);
			setVisible(true);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submitButton) {
				if (userType.equals("User")) {
					if (v) {
						String name = nameField.getText();
						String ageText = ageField.getText();
						if (name.isEmpty() || ageText.isEmpty()) {
							JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						boolean userExists = checkIfUserExists(name);
						if (userExists) {
							JOptionPane.showMessageDialog(this, "User with the same name already exists!", "Error",
									JOptionPane.ERROR_MESSAGE);
							nameField.setText("");
							return;
						}
						int age = Integer.parseInt(ageField.getText());
						try {
							age = Integer.parseInt(ageText);
							if (age < 18) {
								throw new IllegalArgumentException("Age must be 18 or above.");
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(this, "Please enter a valid age!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						} catch (IllegalArgumentException ex) {
							JOptionPane.showMessageDialog(this, "The age must be 18 or above!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						String gender = (String) genderComboBox.getSelectedItem();
						int libraryCardNumber = generateNumber();
						user newUser = new user(name, age, gender, libraryCardNumber);
						user.getUsers().add(newUser);
						JOptionPane.showMessageDialog(this, "Your library card number is: " + libraryCardNumber,
								"Library Card Number", JOptionPane.INFORMATION_MESSAGE);
						JOptionPane.showMessageDialog(this, "User registered successfully!");
						dispose();
					} else {
						String name = nameField.getText();
						String ageText = ageField.getText();
						if (name.isEmpty() || ageText.isEmpty()) {
							JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						boolean userExists = checkIfUserExists(name);
						if (userExists) {
							JOptionPane.showMessageDialog(this, "User with the same name already exists!", "Error",
									JOptionPane.ERROR_MESSAGE);
							nameField.setText("");
							return;
						}
						int age = Integer.parseInt(ageField.getText());
						try {
							age = Integer.parseInt(ageText);
							if (age < 18) {
								throw new IllegalArgumentException("Age must be 18 or above.");
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(this, "Please enter a valid age!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						} catch (IllegalArgumentException ex) {
							JOptionPane.showMessageDialog(this, "The age must be 18 or above!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						String gender = (String) genderComboBox.getSelectedItem();
						librarian l1 = new librarian(name, age, gender, 0);
						l1.addUser();
						dispose();
					}
					if (v) {
						new gui();
					} else {
						previousWindow.setVisible(true);
					}
				} else {
					if (!v) {
						String name = nameField.getText();
						String ageText = ageField.getText();
						if (name.isEmpty() || ageText.isEmpty()) {
							JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						boolean librarianExists = checkIfLibrarianExists(name);
						if (librarianExists) {
							JOptionPane.showMessageDialog(this, "Librarian with the same name already exists!", "Error",
									JOptionPane.ERROR_MESSAGE);
							nameField.setText("");
							return;
						}
						int age = Integer.parseInt(ageField.getText());
						try {
							age = Integer.parseInt(ageText);
							if (age < 18) {
								throw new IllegalArgumentException("Age must be 18 or above.");
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(this, "Please enter a valid age!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						} catch (IllegalArgumentException ex) {
							JOptionPane.showMessageDialog(this, "The age must be 18 or above!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						String gender = (String) genderComboBox.getSelectedItem();
						librarian newLibrarian = new librarian(name, age, gender, 0);
						newLibrarian.addLibrarian();
						dispose();
					} else {
						String name = nameField.getText();
						String ageText = ageField.getText();
						if (name.isEmpty() || ageText.isEmpty()) {
							JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						boolean librarianExists = checkIfLibrarianExists(name);
						if (librarianExists) {
							JOptionPane.showMessageDialog(this, "Librarian with the same name already exists!", "Error",
									JOptionPane.ERROR_MESSAGE);
							nameField.setText("");
							return;
						}
						int age = Integer.parseInt(ageField.getText());
						try {
							age = Integer.parseInt(ageText);
							if (age < 18) {
								throw new IllegalArgumentException("Age must be 18 or above.");
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(this, "Please enter a valid age!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						} catch (IllegalArgumentException ex) {
							JOptionPane.showMessageDialog(this, "The age must be 18 or above!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						String gender = (String) genderComboBox.getSelectedItem();
						int employeeID = generateNumber();
						librarian newLibrarian = new librarian(name, age, gender, employeeID);
						librarian.getLibrarians().add(newLibrarian);
						JOptionPane.showMessageDialog(this, "Your Employee ID is: " + employeeID, "Employee ID",
								JOptionPane.INFORMATION_MESSAGE);
						JOptionPane.showMessageDialog(this, "Librarian registered successfully!");
						dispose();
					}
					if (v) {
						new gui();
					} else {
						previousWindow.setVisible(true);
					}
				}
			}
		}

		private int generateNumber() {
			Random random = new Random();
			return random.nextInt(9000) + 1000;
		}

		private boolean checkIfUserExists(String name) {
			for (user user : user.getUsers()) {
				if (user.getName().equalsIgnoreCase(name)) {
					return true;
				}
			}
			return false;
		}

		private boolean checkIfLibrarianExists(String name) {
			for (librarian librarian : librarian.getLibrarians()) {
				if (librarian.getName().equalsIgnoreCase(name)) {
					return true;
				}
			}
			return false;
		}
	}
}
