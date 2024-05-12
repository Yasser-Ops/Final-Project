package final_project;

public interface user_procedures {
	public void searchBookByTitle(String title);

	public void searchBookByAuthor(String author);

	public void searchBookByGenre(String genre);

	public void borrowBook(String title);

	public book returnBook(String title);

	public void reserveBook(String title);

}
