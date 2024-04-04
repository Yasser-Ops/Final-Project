package final_project;

public interface user_procedures {
	public void SearchBookByTitle(String Title);

	public void SearchBookByAuthor(String Name);

	public void SearchBookByGenre(String Genre);

	public void BorrowBook(String Title);

	public void ReturnBook(String Title);

	public void ReserveBook(String Title);

}
