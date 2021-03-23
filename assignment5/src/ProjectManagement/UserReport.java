package ProjectManagement;

public class UserReport implements Comparable<UserReport>, UserReport_ {

	String user;
	int consumed;
	UserReport(String user){
		this.user=user;
		consumed=0;
	}
	public String user()    { return this.user; }
	public int consumed() { return consumed; }
	@Override
	public int compareTo(UserReport u) {
		// TODO Auto-generated method stub
		return consumed-u.consumed;
	}
}
