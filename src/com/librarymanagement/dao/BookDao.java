package com.librarymanagement.dao;

import java.util.List;

import com.librarymanagement.bean.Book;
import com.librarymanagement.common.LibraryManagementException;

/** Interface for book CRUD operation.
 * @author susilapnda
 *
 */
public interface BookDao {
	
	/** Method to get book details from DB by bookId
	 * @param bookId
	 * @return
	 * @throws LibraryManagementException
	 */
	public Book get(long bookId) throws LibraryManagementException;
	
	/** Method to get all books from DB.
	 * @return
	 * @throws LibraryManagementException
	 */
	public List<Book> getAll() throws LibraryManagementException;
	
	/** Method to create book in DB.
	 * @param book
	 * @throws LibraryManagementException
	 */
	public void create(Book book) throws LibraryManagementException;
	
	/** Method to update book details in DB.
	 * @param book
	 * @return
	 * @throws LibraryManagementException
	 */
	public String update(Book book) throws LibraryManagementException;
	
	/** Method to delete a book from DB.
	 * @param bookId
	 * @throws LibraryManagementException
	 */
	public void delete(long bookId) throws LibraryManagementException;

}
