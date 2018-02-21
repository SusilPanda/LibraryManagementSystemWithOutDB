package com.librarymanagement.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.librarymanagement.bean.Book;
import com.librarymanagement.common.LibraryManagementException;
import com.librarymanagement.common.LibraryManagementUtility;

public class BookDaoImpl implements BookDao {
	
	//private static Connection dbConnection;
	private final static Logger LOGGER = Logger.getLogger(BookDaoImpl.class.getName());
	private static Map<Long, Book> bookMap =  new HashMap<>();
	
	@Override
	public Book get(long bookId) throws LibraryManagementException {
		Book book = null;
		
		try {
			book = bookMap.get(bookId);
				
		} catch (Exception e) {
			LOGGER.severe("Exception in get book : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}
		return book;
	}

	@Override
	public List<Book> getAll() {
		return bookMap.values().stream().collect(Collectors.toList());
	}

	@Override
	public void create(Book book) throws LibraryManagementException {
		
		 try {
			 bookMap.put(book.getBookId(), book);			 
		} catch (Exception e) {
			LOGGER.severe("Exception in create book : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}		
	}

	@Override
	public String update(Book book) throws LibraryManagementException {
		
		 try {
			 Book oldBook = bookMap.get(book.getBookId());
			 book = LibraryManagementUtility.copyBookUpdatedFields(oldBook, book);
			 
			 bookMap.replace(book.getBookId(), oldBook, book);
		} catch (Exception e) {
			LOGGER.severe("Exception in update book : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}	
		 return "success";
	}

	@Override
	public void delete(long bookId) throws LibraryManagementException {
		
		try {
			bookMap.remove(bookId);
		} catch (Exception e) {
			LOGGER.severe("Exception in delete book : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}
	}
}
