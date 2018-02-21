package com.librarymanagement.bean;

import java.util.Date;

/** Booking Details pojo class contains the booking details.
 * @author esusich
 *
 */
public class BookingDetails {

	private long bookingId;
	private int readerId;
	private long bookId;
	private Date borrowDate;
	private Date returnDate;
	
	public BookingDetails() {
	}
	
	/**
	 * @param bookingId
	 * @param borrowDate
	 * @param returnDate
	 * @param bookId
	 * @param readerId
	 */
	public BookingDetails(long bookingId, Date borrowDate, Date returnDate, long bookId, int readerId) {
		this.bookingId = bookingId;
		this.bookId = bookId;
		this.readerId = readerId;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
	}
	
	/**
	 * @return the userId
	 */
	public long getBookingId() {
		return bookingId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}
	
	/**
	 * @return the userId
	 */
	public int getReaderId() {
		return readerId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setReaderId(int userId) {
		this.readerId = userId;
	}
	/**
	 * @return the bookId
	 */
	public long getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the borrowDate
	 */
	public Date getBorrowDate() {
		return borrowDate;
	}
	/**
	 * @param borrowDate the borrowDate to set
	 */
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	/**
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}
	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", readerId=" + readerId + ", bookId=" + bookId
				+ ", borrowDate=" + borrowDate + ", returnDate=" + returnDate + "]";
	}
	
	
}
