package tvattstuga;

public class User {

	private String name;
	private String password;
	private int ID;
	static int numberOfUsers = 1;
	
	public User(String name, String pass){
		this.name = name;
		this.password = pass;
		//numberOfUsers = numberOfUsers+1;
		this.ID = numberOfUsers++;
	}
	
	public String GetName(){
		return this.name;
	}
	
	public Boolean ValidatePass(String pass){
		if(this.password == pass) return true;
		return false;
	}
	
	public int GetID(){
		return this.ID;
	}

}
