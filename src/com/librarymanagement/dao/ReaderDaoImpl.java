package com.librarymanagement.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.librarymanagement.bean.Reader;
import com.librarymanagement.common.LibraryManagementException;
import com.librarymanagement.common.LibraryManagementUtility;

public class ReaderDaoImpl implements ReaderDao {
	
	private static final Logger LOGGER = Logger.getLogger(ReaderDaoImpl.class.getName());
	private static Map<Integer, Reader> readerMap = new HashMap<>();
	
	@Override
	public Reader get(int readerId) throws LibraryManagementException {
		
		Reader reader = null;
		
		try {
			reader = readerMap.get(readerId);
		} catch (Exception e) {
			LOGGER.severe("Exception in get reader : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}
		return reader;
	}

	@Override
	public List<Reader> get() throws LibraryManagementException {
		
		List<Reader> readerList = null;
		
		try {
			readerList = readerMap.values().stream().collect(Collectors.toList());
		} catch (Exception e) {
			LOGGER.severe("Exception in getAll reader : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}
		return readerList;
	}

	@Override
	public void create(Reader reader) throws LibraryManagementException {
		
		 try {
			 readerMap.put(reader.getReaderId(), reader);
		} catch (Exception e) {
			LOGGER.severe("Exception in create reader : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}		
	}

	@Override
	public String update(Reader reader) throws LibraryManagementException {
		
		 try {
			 Reader oldReader = readerMap.get(reader.getReaderId());
			 reader = LibraryManagementUtility.copyReaderUpdatedFields(oldReader, reader);
			 
			 readerMap.replace(reader.getReaderId(), oldReader, reader);
		} catch (Exception e) {
			LOGGER.severe("Exception in update reader : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}
		 return "success";
	}

	@Override
	public void delete(int readerId) throws LibraryManagementException {
		
		try {
			readerMap.remove(readerId);
		} catch (Exception e) {
			LOGGER.severe("Exception in delete reader : " + e.getMessage());
			throw new LibraryManagementException(e.getMessage());
		}
	}
}
