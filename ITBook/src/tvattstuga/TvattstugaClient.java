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
            
 loop:      while (true){
            	s = in.nextLine();
            	switch (s){	
	            	case "book": System.out.println("Enter time");
	            		time = in.nextLine();
	            		System.out.println("Enter day");
	            		day = in.nextLine();
	            		System.out.println("Enter name");
	            		name = in.nextLine();
	            		System.out.println("Enter password");
	            		pass = in.nextLine();
	            		break;	
	            		
	            	case "exit": break loop;
            	}
            }
            
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}