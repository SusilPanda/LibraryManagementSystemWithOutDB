package com.librarymanagement.dao;

import java.util.List;

import com.librarymanagement.bean.Reader;
import com.librarymanagement.common.LibraryManagementException;

/** Interface to do CRUD operation on Reader Object.
 * @author susilpanda
 *
 */
public interface ReaderDao {
	
	/** Method to get the reader details from DB by readerId.
	 * @param readerId
	 * @return
	 * @throws LibraryManagementException
	 */
	public Reader get(int readerId) throws LibraryManagementException;
	
	/** Method to get all readers from DB.
	 * @return
	 * @throws LibraryManagementException
	 */
	public List<Reader> get() throws LibraryManagementException;
	
	/** Method to create reader in DB.
	 * @param reader
	 * @throws LibraryManagementException
	 */
	public void create(Reader reader) throws LibraryManagementException;
	
	/** Method to update a reader in DB.
	 * @param reader
	 * @return
	 * @throws LibraryManagementException
	 */
	public String update(Reader reader) throws LibraryManagementException;
	
	/** Method to delete a reader in DB.
	 * @param readerId
	 * @throws LibraryManagementException
	 */
	public void delete(int readerId) throws LibraryManagementException;

}
