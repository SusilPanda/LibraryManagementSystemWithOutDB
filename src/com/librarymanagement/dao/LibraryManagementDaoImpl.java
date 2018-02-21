package com.librarymanagement.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.librarymanagement.bean.BookingDetails;
import com.librarymanagement.common.LibraryManagementException;
import com.librarymanagement.common.LibraryManagementUtility;

public class LibraryManagementDaoImpl implements LibraryManagementDao {
	
	private static final Logger LOGGER = Logger.getLogger(LibraryManagementDaoImpl.class.getName());
	private static long bookingCounter = 0L;
	private static Map<Long, BookingDetails> bookingMap = new HashMap<>();
	
	public LibraryManagementDaoImpl() {
	}
	
	@Override
	public void create(int readerId, long bookId) throws LibraryManagementException {

		 try {
			java.util.Date date = Calendar.getInstance().getTime();
			Date currentDate = new Date(date.getTime());
			BookingDetails booking = new BookingDetails(bookingCounter++, currentDate, null, bookId, readerId);
			
			bookingMap.put(booking.getBookingId(), booking);
			
		} catch (Exception e) {
			LOGGER.severe("Exception in create booking : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}		
	}

	@Override
	public BookingDetails get(long bookingId) throws LibraryManagementException {
		BookingDetails bookingDetails = null;
		
		try {
			bookingDetails = bookingMap.get(bookingId);
		} catch (Exception e) {
			LOGGER.severe("Exception in get booking details : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}
		
		return bookingDetails;
	}

	@Override
	public void update(BookingDetails booking) throws LibraryManagementException {
		
		 try {
			 BookingDetails oldBooking = bookingMap.get(booking.getBookingId());
			 booking = LibraryManagementUtility.copyBookingUpdatedFields(oldBooking, booking);
			 
			 bookingMap.replace(booking.getBookingId(), oldBooking, booking);
		} catch (Exception e) {
			LOGGER.severe("Exception in update booking : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}	
	}

	@Override
	public void delete(long bookingId) throws LibraryManagementException {
		 try {
			 bookingMap.remove(bookingId);
		} catch (Exception e) {
			LOGGER.severe("Exception in delete booking : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}	
	}

	@Override
	public List<BookingDetails> getAll() throws LibraryManagementException {
		List<BookingDetails> bookingList = null;
		
		try {
			bookingList = bookingMap.values().stream().collect(Collectors.toList());
		} catch (Exception e) {
			LOGGER.severe("Exception in getAll booking details : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}
		return bookingList;
	}

	@Override
	public int getCount(int readerId) throws LibraryManagementException {
		int numberOfBooksBorrowed = 0;
		
		try {
			numberOfBooksBorrowed = (int) bookingMap.values().stream().filter(param -> param.getReaderId() == readerId).count();
		} catch (Exception e) {
			LOGGER.severe("Exception in get count of booking : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}
		
		return numberOfBooksBorrowed;
	}

	@Override
	public boolean verifyBooking(int readerId, long bookId) throws LibraryManagementException {
		
        boolean isPresent = false;
		
		try {
			isPresent = bookingMap.values().stream().anyMatch(param -> param.getReaderId() == readerId && param.getBookId() == bookId);
		} catch (Exception e) {
			LOGGER.severe("Exception in get booking details : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}
		
		return isPresent;
	}
}
