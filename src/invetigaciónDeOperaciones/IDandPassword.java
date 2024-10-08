package invetigaciónDeOperaciones;

import java.util.HashMap;

public class IDandPassword {

	private HashMap<String,String> users = new HashMap<String,String> ();
	 
	public IDandPassword(){
		users.put("Anto7", "Pizza");
		users.put("", "");
		users.put("1", "1");
	}
	
	private HashMap getLoginInfo() {
		
		return users;
	}
	
	public HashMap getInfoMethod() {
		
		return getLoginInfo();
	}
	
}
