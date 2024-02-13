package main;

public class Admin {

	private Connect connect;
	
	public Admin(Connect connect) {
		this.connect = connect;
	}
	
	public Object[][] getPermsTableData(){
		
		Object[][] data = {{false, "leserej", true, true, true, true}};
		
		// TODO: implement sql calls
		
		return data;
	}
	
	public void updatePermsTable(String username, String newPerms) {
		System.out.println("update user: " + username + " newperms: " + newPerms);
		
		// TODO: implement sql calls
	}
	
	public void removeUser(String username) {
		System.out.println("remove user: " + username);
		
		// TODO: implement sql calls
	}
	
}
