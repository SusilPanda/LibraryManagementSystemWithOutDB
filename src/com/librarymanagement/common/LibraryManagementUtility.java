package com.librarymanagement.common;

import com.librarymanagement.bean.Book;
import com.librarymanagement.bean.BookingDetails;
import com.librarymanagement.bean.Reader;

public class LibraryManagementUtility {

	public static boolean validateParameters(Object param) {

		if (param instanceof Integer) {
			return (int) param >= 0;
		} else if (param instanceof Long) {
			return (long) param >= 0L;
		} else if (param instanceof String) {
			return param != null && param != "" ? true : false;
		}
		return false;
	}

	public static Book copyBookUpdatedFields(Book source, Book target) {

		if (target.getAuthor() == null) {
			target.setAuthor(source.getAuthor());
		}
		if (target.getName() == null) {
			target.setName(source.getName());
		}
		if (target.getTitle() == null) {
			target.setTitle(source.getTitle());
		}

		if (target.getNumberOfAvailableBooks() == 0) {
			target.setNumberOfAvailableBooks(source.getNumberOfAvailableBooks());
		}

		if (target.getNumberOfBooks() == 0) {
			target.setNumberOfBooks(source.getNumberOfBooks());
		}
		if (target.getNumberOfBorrowedBooks() == 0) {
			target.setNumberOfBorrowedBooks(source.getNumberOfBorrowedBooks());
		}

		return target;
	}

	public static Reader copyReaderUpdatedFields(Reader source, Reader target) {
		
		if (target.getReaderName() == null) {
			target.setReaderName(source.getReaderName());
		}
		if (target.getUserName() == null) {
			target.setUserName(source.getUserName());
		}
		if (target.getPassword() == null) {
			target.setPassword(source.getPassword());
		}

		if (target.getEmailId() == null) {
			target.setEmailId(source.getEmailId());
		}

		if (target.getSubscriptionId() == 0) {
			target.setSubscriptionId(source.getSubscriptionId());
		}

		return target;
	}

	public static BookingDetails copyBookingUpdatedFields(BookingDetails source, BookingDetails target) {

		if (target.getBookId() == 0L) {
			target.setBookId(source.getBookId());
		}
		if (target.getReaderId() == 0) {
			target.setReaderId(source.getReaderId());
		}
		if (target.getBorrowDate() == null) {
			target.setBorrowDate(source.getBorrowDate());
		}

		if (target.getReturnDate() == null) {
			target.setReturnDate(source.getReturnDate());
		}

		return target;
	}

}
