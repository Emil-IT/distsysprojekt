package tvattstuga;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TvattstugaInterface extends Remote {
	
	Boolean BookTime(int T, int D, String name, String pass) throws RemoteException;
    Boolean RemoveBooking(int T, int D, String name, String pass) throws RemoteException;
    Boolean CheckIn(String name, String pass) throws RemoteException;
    int[][] GetSchedule() throws RemoteException;
    Boolean AddUser(String name, String pass) throws RemoteException;
    int ValidateUser(String name, String pass) throws RemoteException;
    Boolean VacantTime(int time, int day) throws RemoteException;
    User GetUser(String name) throws RemoteException;
    int GetSize() throws RemoteException;
    User GetUserNr(int i) throws RemoteException;
    
    
    String sayHello() throws RemoteException;
}