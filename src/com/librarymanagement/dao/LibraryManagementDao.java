package com.librarymanagement.dao;

import java.util.List;

import com.librarymanagement.bean.BookingDetails;
import com.librarymanagement.common.LibraryManagementException;

/** Interface which contains all the major business methods related to library
 *  management.
 * @author susilpanda
 *
 */
public interface LibraryManagementDao {

	/** Method to get booking details from DB by bookingId
	 * @param bookingId
	 * @return one booking
	 * @throws LibraryManagementException
	 */
	public BookingDetails get(long bookingId) throws LibraryManagementException;
	
	/** Method to get all booking details from DB.
	 * @return list of bookings
	 * @throws LibraryManagementException
	 */
	public List<BookingDetails> getAll() throws LibraryManagementException;
	
	/** Method to create booking in DB.
	 * @param readerId
	 * @param bookId
	 * @throws LibraryManagementException
	 */
	public void create(int readerId, long bookId) throws LibraryManagementException;
	
	/** Method to update a booking in DB.
	 * @param booking
	 * @throws LibraryManagementException
	 */
	public void update(BookingDetails booking) throws LibraryManagementException;
	
	/** Method to delete a booking in DB.
	 * @param bookingId
	 * @throws LibraryManagementException
	 */
	public void delete(long bookingId) throws LibraryManagementException;
	
	/** Method to get total count of books issued to a reader.
	 * @param readerId
	 * @return int : count value
	 * @throws LibraryManagementException
	 */
	public int getCount(int readerId) throws LibraryManagementException;
	
	/** Method to verify lending of a new book, If the same book is already borrowed by the reader. 
	 * @param readerId
	 * @param bookId
	 * @return boolean : true or false
	 * @throws LibraryManagementException
	 */
	public boolean verifyBooking(int readerId, long bookId) throws LibraryManagementException;
	
}
