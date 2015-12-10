package tvattstuga;


import java.util.*;

public class Tvattstuga implements TvattstugaInterface {
	
	
    private int[][] schedule = new int[30][14];;
    private int today;
    public ArrayList<User> userList = new ArrayList<User>();
    
  

    public Tvattstuga() {

    }
    

    public synchronized Boolean BookTime(int time, int day,  String name, String pass) {
    	int ID = ValidateUser(name, pass);
    	if (ID != 0 && VacantTime(time, day)){
    	
    		this.schedule[(this.today+day) % 30][time] = ID;
    		return true;
    	}
    	return false;
    }
    public synchronized int[][] GetSchedule(){
    	return this.schedule;
    }
    
    public synchronized Boolean RemoveBooking(int time, int day, String name, String pass){
    	if(ValidateUser(name, pass) != 0 && ValidateUser(name, pass) == this.schedule[(this.today + day) % 30][time]){
    		this.schedule[(this.today + day) % 30][time] = 0;
    		return true;
    	}
    	return false;
    }
    
    public Boolean CheckIn(String name, String pass){
    	
    	return false;
    }
    
  public Boolean AddUser(String name, String pass){
	  userList.add(new User(name, pass));
	  return true;
  }
  
  public int ValidateUser(String name, String pass){
	  User user = GetUser(name);
	  if(user != null && user.ValidatePass(pass)) return user.GetID();
	  return 0;
  }
  
  public User GetUser(String name){
		for(int i = 0; i < userList.size(); i++){
			if(userList.get(i).GetName().equals(name)) return userList.get(i);
		}
		return null;
	}
  
  public User GetUserNr(int i){
	  return userList.get(i);
  }
  
  public Boolean VacantTime(int time, int day){
	  if(this.schedule[(this.today + day) % 30][time] == 0) return true;
	  return false;
  }
  
  private void NewDay(){
	  this.schedule[today] = new int[14];
	  this.today = (this.today+1) %30;
	 
  }

  public int GetSize(){
	  return userList.size();
  }


    public String sayHello() {
        return "Hello, world!";
    }
}