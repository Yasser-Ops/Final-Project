package final_project;

public interface librarian_procedures {
	public void searchBookByTitle(String title);

	public void searchBookByAuthor(String author);

	public void searchBookByGenre(String genre);

	public user addUser();

	public book addBooks(book b);

}
