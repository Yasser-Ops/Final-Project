package final_project;

public interface user_procedures {
	public boolean searchBookByTitle(String Title);

	public boolean searchBookByAuthor(String Name);

	public boolean searchBookByGenre(String Genre);

	public void borrowBook(String Title);

	public void returnBook(String Title);

	public void reserveBook(String Title);

}
