package business;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
	public void addMember(LibraryMember member) {
		System.out.println(member);
		DataAccess dataAccess = new DataAccessFacade();
		dataAccess.saveNewMember(member);
	}

	@Override
	public List<CheckoutRecordEntry> checkout(String memberId, String isbn) throws LibrarySystemException {
		DataAccess dataAccess = new DataAccessFacade();
		LibraryMember member = dataAccess.readMemberMap().get(memberId);
		Book book = dataAccess.readBooksMap().get(isbn);
		if(member == null) throw new LibrarySystemException("Member not found");
		if(book == null) throw new LibrarySystemException("Book not found");
		if(!book.isAvailable()) throw new LibrarySystemException("Book copy not available");
		BookCopy bookCopy = book.getNextAvailableCopy();
		LocalDate today = LocalDate.now();

		CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry(today, today.plusDays(book.getMaxCheckoutLength()), bookCopy);
		bookCopy.changeAvailability();
		CheckoutRecord checkoutRecord = member.getCheckoutRecord();
		List<CheckoutRecordEntry> checkoutRecordEntries = checkoutRecord.getCheckoutRecordEntries();
		checkoutRecordEntries.add(checkoutRecordEntry);
		member.setCheckoutRecord(checkoutRecord);
		dataAccess.saveNewMember(member);
		return checkoutRecordEntries;
	}
}
