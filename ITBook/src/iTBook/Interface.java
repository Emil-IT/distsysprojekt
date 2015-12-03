package iTBook;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote {
    Boolean BookTime(int T) throws RemoteException;
    Boolean RemoveBooking(int T) throws RemoteException;
    Boolean CheckIn(String name, String pass) throws RemoteException;
    int[] GetSchedule() throws RemoteException;
    
    
    String sayHello() throws RemoteException;
}