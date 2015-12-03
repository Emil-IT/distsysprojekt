package iTBook;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Interface {

    public Server() {}

    public String sayHello() {
        return "Hello, world!";
    }
    
    private int schedule[] = {0, 0, 0};
    
    public Boolean BookTime(int time) {
    	if(schedule[time] == 0){
    		schedule[time] = 1;
    		return true;
    	}else
    		return false;
    }
    
    public int[] GetSchedule(){
    	return schedule;
    }
    
    public Boolean RemoveBooking(int time){
    	return false;
    }
    
    public Boolean CheckIn(String name, String pass){
    	return false;
    }
    

    public static void main(String args[]) {

        try {
            Server obj = new Server();
            Interface stub = (Interface) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Hello", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}