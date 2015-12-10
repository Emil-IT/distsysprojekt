package tvattstuga;
import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		if(this.password.equals(pass)) return true;
		return false;
	}
	
	public int GetID(){
		return this.ID;
	}

}
