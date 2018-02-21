package com.librarymanagement.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import com.librarymanagement.bean.Book;
import com.librarymanagement.bean.BookingDetails;
import com.librarymanagement.bean.Reader;
import com.librarymanagement.common.LibraryManagementException;
import com.librarymanagement.common.LibraryManagementUtility;
import com.librarymanagement.dao.BookDao;
import com.librarymanagement.dao.BookDaoImpl;
import com.librarymanagement.dao.LibraryManagementDao;
import com.librarymanagement.dao.LibraryManagementDaoImpl;
import com.librarymanagement.dao.ReaderDao;
import com.librarymanagement.dao.ReaderDaoImpl;

public class LibraryManager {
	private static final Logger LOGGER = Logger.getLogger(LibraryManager.class.getName());

	/**
	 * @return
	 */
	public List<Book> getAllAvailableBooks() {

		return null;
	}

	/**
	 * Method to get all borrowed books.
	 * @return
	 * @throws LibraryManagementException
	 */
	public List<BookingDetails> getAllBorrowedBooks() throws LibraryManagementException {
		LibraryManagementDao bookingDao = new LibraryManagementDaoImpl();
		List<BookingDetails> bookingList = bookingDao.getAll();

		return bookingList;
	}

	/**
	 * Method to borrow a book.
	 * @param readerId
	 * @param bookId
	 * @throws LibraryManagementException
	 */
	public void borrowABook(int readerId, long bookId) throws LibraryManagementException {
		// validate the request
		if (!LibraryManagementUtility.validateParameters(readerId)
				&& !LibraryManagementUtility.validateParameters(bookId)) {
			LOGGER.severe("invalid parameters in the request : ");
			throw new LibraryManagementException(
					"Invalid Parameters received, Library Management doesn't support these parameters");
		}
		
		// check subscription for user
		ReaderDao readerDao = new ReaderDaoImpl();
		Reader reader = readerDao.get(readerId);
		
		int numberOfBooksBorrowed = getNumberOfBooksBorrowed(readerId);
		LOGGER.info("number of books issued for user : " + reader.getReaderName() + " : " + numberOfBooksBorrowed);
		
		// sub - 1, monthly subscription
		if(reader.getSubscriptionId() == 1 && numberOfBooksBorrowed >= 5) {
			LOGGER.warning("number of books issued limit reached for user : " + reader.getReaderName());
			throw new LibraryManagementException("Reader has borrowed the maximum number of books.");
		// sub - 2 , annual subscription
		} else if(reader.getSubscriptionId() == 2 && numberOfBooksBorrowed >= 10) {
			LOGGER.warning("number of books issued limit reached for user : " + reader.getReaderName());
			throw new LibraryManagementException("Reader has borrowed the maximum number of books.");
		}
		
		//check if the book is already borrowed by the user
		if(getAlreadyBorrowed(readerId, bookId)) {
			LOGGER.warning("requesting book is already issued to user : " + reader.getReaderName());
			throw new LibraryManagementException("Reader has already borrowed the book.");
		}
		// insert in the bookingDetails table
		LibraryManagementDao bookingDao = new LibraryManagementDaoImpl();
		bookingDao.create(readerId, bookId);

		BookDao bookDao = new BookDaoImpl();
		// get book from db
		Book book = bookDao.get(bookId);

		if (null != book) {
			// update in the book table
			book.setNumberOfAvailableBooks(book.getNumberOfAvailableBooks() - 1);
			book.setNumberOfBorrowedBooks(book.getNumberOfBorrowedBooks() + 1);
			bookDao.update(book);
		} else {
			LOGGER.warning("requesting book is not available in db");
			throw new LibraryManagementException("Lending of a book failed, due to book is not present in DB");
		}
	}

	private boolean getAlreadyBorrowed(int readerId, long bookId) throws LibraryManagementException {
		LibraryManagementDao bookingDao = new LibraryManagementDaoImpl();
		return  bookingDao.verifyBooking(readerId, bookId);
	}

	/**
	 * Method to get number of books borrowed by a user as per the subscription..
	 * @param readerId
	 * @return
	 * @throws LibraryManagementException
	 */
	private int getNumberOfBooksBorrowed(int readerId) throws LibraryManagementException {
		LibraryManagementDao bookingDao = new LibraryManagementDaoImpl();
		int numberOfBooksBorrowed = bookingDao.getCount(readerId);
		return numberOfBooksBorrowed;
	}

	/** Method to return a book with booking id.
	 * @param bookingId
	 * @throws LibraryManagementException
	 */
	public void returnABookToLibrary(long bookingId) throws LibraryManagementException {

		// validate the request
		if (!LibraryManagementUtility.validateParameters(bookingId)) {
			LOGGER.severe("invalid parameters in the request : ");
			throw new LibraryManagementException(
					"Invalid Parameter received, Library Management doesn't support this parameter");
		}

		//check if the book is already returned
		
		// update return date in the bookingDetails table
		LibraryManagementDao bookingDao = new LibraryManagementDaoImpl();
		Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

		BookingDetails bookingDetails = bookingDao.get(bookingId);
		bookingDetails.setReturnDate(currentDate);

		bookingDao.update(bookingDetails);

		BookDao bookDao = new BookDaoImpl();
		// get book from db
		Book book = bookDao.get(bookingDetails.getBookId());

		if (null != book) {
			// update in the book table
			book.setNumberOfAvailableBooks(book.getNumberOfAvailableBooks() + 1);
			book.setNumberOfBorrowedBooks(book.getNumberOfBorrowedBooks() - 1);
			bookDao.update(book);
		} else {
			LOGGER.warning("returning book is not available in db");
			throw new LibraryManagementException("Return of a book failed, due to book is not present in DB");
		}

	}
	
	/**
	 * Get booking details for a booking.
	 * @param bookingId
	 * @return
	 * @throws LibraryManagementException
	 */
	public BookingDetails getBookingDetails(long bookingId) throws LibraryManagementException {
		LibraryManagementDao bookingDao = new LibraryManagementDaoImpl();
		BookingDetails booking = bookingDao.get(bookingId);
		return booking;
	}

	/**
	 * @param bookingId
	 * @throws LibraryManagementException
	 */
	public void removeBooking(long bookingId) throws LibraryManagementException {
		LibraryManagementDao bookingDao = new LibraryManagementDaoImpl();
		bookingDao.delete(bookingId);
	}
	
	public void reserveABook() {

	}

	public Book searchABook() {
		return new Book();
	}
}
