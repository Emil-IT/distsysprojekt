package tvattstuga;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Tvattstuga implements TvattstugaInterface {
	
	
    private int[][] schedule = new int[30][14];
    private int today;
    public ArrayList<User> userList = new ArrayList<User>();
    private ArrayList<String> confirmList = new ArrayList<String>();
    
  

    public Tvattstuga() {
    	try {
    		File f = new File("users.txt");
			Scanner inFile = new Scanner(f);
			String token;
			while (inFile.hasNext()) {
			      // find next line
				token = inFile.nextLine();				
				
		      	Scanner line = new Scanner(token);
		      	line.useDelimiter(",");
		      	while(line.hasNext()){
			      	String uName = line.next();
					String uPass = line.next();
					int uID = line.nextInt();
					this.userList.add(new User(uName, uPass, uID));
		      	}
		      	line.close();
		      	
		    }
		    inFile.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	try {
    		File f = new File("schedule.txt");
			Scanner inFile = new Scanner(f);
			String token;
			int d = 0;
			int t = 0;
			
			while (inFile.hasNext()) {
			      // find next line
				token = inFile.nextLine();
				if(!token.equals("")){
			      	Scanner line = new Scanner(token);
			      	line.useDelimiter(",");
			      	while(line.hasNext()){
		      			int id = line.nextInt();
		      			this.schedule[d][t] = id;
		      			t++;
			      	}
			      	t = 0;
			      	line.close();
					d++;
				}
				
		    }
		    
			inFile.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
    	Timer timer = new Timer();
    	TimerTask newDay = new TimerTask(){
    		@Override
    		public void run(){
    			NewDay();
    		}
    	};
    	timer.schedule(newDay, 1000*60*60*24, 1000*60*60*24);
    	
    }
    

    public synchronized Boolean BookTime(int time, int day,  String name, String pass) {
    	int ID = ValidateUser(name, pass);
    	if (ID != 0 && VacantTime(time, day)){
    	
    		this.schedule[(this.today+day) % 30][time] = ID;
    		
    		
    		

    		Timer bookTimer = new Timer();
        	TimerTask confirmed = new TimerTask(){
        		@Override
        		public void run(){
        			if(confirmList.contains(name)){
        				confirmList.remove(name);
        			}else{
        				RemoveBooking(time, day, name, pass);
        			}
        		}
        	};
        	bookTimer.schedule(confirmed, 1000*15);
        	try {
				PrintWriter out = new PrintWriter("schedule.txt");
				for(int d = 0; d < 30; d++){
					String str = "";
					
					for (int t = 0; t < 14; t++){
					  str += schedule[d][t] + (t==13 ? "\n" : ",");
					}
					
	    			out.println(str);
	    			
	    		}
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return true;
    	}
    	return false;
    }
    
    public Boolean ConfirmBookTime(String name, String pass){
    	if(ValidateUser(name, pass) != 0){
    		confirmList.add(name);
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
    		try {
				PrintWriter out = new PrintWriter("schedule.txt");
				for(int d = 0; d < 30; d++){
					String str = "";
					
					for (int t = 0; t < 14; t++){
					  str += schedule[d][t] + (t==13 ? "\n" : ",");
					}
					
	    			out.println(str);
	    			
	    		}
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return true;
    	}
    	return false;
    }
    
    public Boolean CheckIn(String name, String pass, int time){
    	int id = ValidateUser(name, pass);
    	
    	return (id != 0 && id == schedule[this.today][time]);
    }
    
    public Boolean AddUser(String name, String pass){
    	if(GetUser(name) != null) return false;
    	User newUser = new User(name, pass);
    	userList.add(newUser);
    	
    	try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("users.txt", true)))) {
    		out.println(newUser.toString());
    		out.close();
    	}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
    	return true;
    }
  
    private int ValidateUser(String name, String pass){
    	User user = GetUser(name);
    	if(user != null && user.ValidatePass(pass)) return user.GetID();
    	return 0;
    }
  
    private User GetUser(String name){
		for(int i = 0; i < userList.size(); i++){
			if(userList.get(i).GetName().equals(name)) return userList.get(i);
		}
		return null;
	}
  /*
  private User GetUserNr(int i){
	  return userList.get(i);
  }
  */
  private Boolean VacantTime(int time, int day){
	  if(this.schedule[(this.today + day) % 30][time] == 0) return true;
	  return false;
  }
  
  private void NewDay(){
	  this.schedule[today] = new int[14];
	  this.today = (this.today+1) %30;
  }
  
  
	
  
  /*
  public int GetSize(){
	  return userList.size();
  }
*/

    public String sayHello() {
        return "Hello, world!";
    }
}