package iTBook;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Server implements Tvattstuga {
	
    private int[][] schedule = new int[30][14];;
    private int today;
    private ArrayList<Users> userList;
    
  

    public Server() {

    }

    public String sayHello() {
        return "Hello, world!";
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
	  userList.add(new Users(name, pass));
	  return true;
  }
  
  private int ValidateUser(String name, String pass){
	  Users user = GetUser(name);
	  if(user != null && user.ValidatePass(pass)) return user.GetID();
	  return 0;
  }
  
  private Users GetUser(String name){
		for(int i = 0; i < userList.size(); i++){
			if(userList.get(i).GetName() == name) return userList.get(i);
		}
		return null;
	}
  
  private Boolean VacantTime(int time, int day){
	  if(this.schedule[(this.today + day) % 30][time] != 0) return true;
	  return false;
  }
  
  private void NewDay(){
	  this.schedule[today] = new int[14];
	  this.today = (this.today+1) %30;
	 
  }

    public static void main(String args[]) {

        try {
            Server obj = new Server();
            Tvattstuga stub = (Tvattstuga) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Tvattstuga", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}