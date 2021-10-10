package business;

import java.util.List;
import java.util.Map;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public void addMember(Map<String, String> memberMap);
	public List<String[]> checkout(String memberId, String isbn) throws LibrarySystemException;
}
