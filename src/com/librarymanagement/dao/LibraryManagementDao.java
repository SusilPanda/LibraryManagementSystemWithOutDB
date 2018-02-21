package com.librarymanagement.dao;

import java.util.List;

import com.librarymanagement.bean.BookingDetails;
import com.librarymanagement.common.LibraryManagementException;

public interface LibraryManagementDao {

	public List<BookingDetails> getAll() throws LibraryManagementException;
	public BookingDetails get(long bookingId) throws LibraryManagementException;
	public void create(int readerId, long bookId) throws LibraryManagementException;
	public void update(BookingDetails booking) throws LibraryManagementException;
	public void delete(long bookingId) throws LibraryManagementException;
	public int getCount(int readerId) throws LibraryManagementException;
	public boolean verifyBooking(int readerId, long bookId) throws LibraryManagementException;
	
}
