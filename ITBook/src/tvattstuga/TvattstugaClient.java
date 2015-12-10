package tvattstuga;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;



public class TvattstugaClient {

    private TvattstugaClient() {
    	
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            TvattstugaInterface stub = (TvattstugaInterface) registry.lookup("Tvattstuga");
            System.out.println("Contacting server...");
            String response = stub.sayHello();
            System.out.println("response: " + response);
            
            
            Scanner in = new Scanner(System.in);
            String s;
            String time;
            String day;
            String name;
            String pass;
            
loop:   	while (true){
	 			System.out.println("Enter command:");
            	s = in.nextLine();
            	switch (s){	
	            	case "book": 
	            		System.out.println("Enter time");
	            		time = in.nextLine();
	            		System.out.println("Enter day");
	            		day = in.nextLine();
	            		System.out.println("Enter name");
	            		name = in.nextLine();
	            		System.out.println("Enter password");
	            		pass = in.nextLine();
	            		response = Boolean.toString(stub.BookTime(Integer.parseInt(time), Integer.parseInt(day), name, pass));
	            		System.out.println("response: " + response);
	            		break;	
	            		
	            	case "add user":
	            		/*
	            		System.out.println("Enter name");
	            		name = in.nextLine();
	            		System.out.println("Enter password");
	            		pass = in.nextLine();
	            		*/
	            		response = Boolean.toString(stub.AddUser("Emil", "pass"));
	            		 
	            		System.out.println("response: " + response);
	            		break;
	            		
	            	case "Get":
	            		int[][] r = stub.GetSchedule();
	            		for(int i = 0; i<30; i++){
	            			response = Arrays.toString(r[i]);
	            			System.out.println("response: " + response);
	            		}
	            		
	            		
	            		break;
	            		
	            	case "validate":
	            		
	            		System.out.println("Enter name");
	            		name = in.nextLine();
	            		System.out.println("Enter password");
	            		pass = in.nextLine();
	            		response = Integer.toString(stub.ValidateUser(name, pass));
	            		System.out.println("response: " + response);
	            		break;
	            		
	            	case "get user":
	            		
	            		response = (stub.GetUser("Emil").GetName());
	            		System.out.println("response: " + response);
	            		break;
	            		
	            	case "size":
	            		System.out.println(Integer.toString(stub.GetSize()));
	            		break;
	            		
	            	
	            	
	            		
	            	case "exit": break loop;
            	}
            }
            in.close();
            
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}