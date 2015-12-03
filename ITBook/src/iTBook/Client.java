package iTBook;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.*;

public class Client {
    // Properties
    private Array schedule;
    
    // Constructor
    private Client() {
        
    }

    // Method to interpret the schedule, to convert it to graphics.
	void printSchedule() {
		for (int i = 0; i =< 30; i++) {
			// Graphics for each day and then print each day.
			printDay(i);
		}
	}

	// Method to interpret the int array, to convert a whole day to graphics.
	void printDay(int day) {
		for (int i = 0; i =< 13; i++) {
			// Print each timeslot with the graphics.
			printSlot(day, i);
		}
	}

	// Method to interpret a slot, and convert it to graphics.
	void printSlot(int day, int timeslot) {
		// Retrieves data from that timeslot, like the user that has booked it
		// Prints out the data in the field.
	}


	// Update to date and see if any more slots are taken
	void updateSchedule(void) {
		// Get array from back-end

	}

	// A slot has been chosen, we send this to the server-side to reserve the spot for this user.
	// If the return value is 1, the time has been booked.
	// If the return value is 2, it is occupied already.
	// If the return value is 3, the user already has two booked timeslots.
	int bookTime(int day, int timeslot, String user) {
		// Two bookings max


		// Update schedule after booking.
		this.updateSchedule();
	}

	// A user want's to remove its booking at this spot.
	void removeBooking(int day, int timeslot, String user) {
		// Check if the spot belongs to the user

		// Else send to server to remove from the array.


		// Update schedule after removing.
		this.updateSchedule();
	}

	// User checking in on the timeslot to confirm they want it.
	void checkIn(int day, int timeslot, String user) {
		// A token sent to the slot to verify that the user has checked in.



		// Update schedule after checking in.
		this.updateSchedule();
	}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Interface stub = (Interface) registry.lookup("Hello");
            System.out.println("Contacting server...");
            String response = stub.sayHello();
            System.out.println("response: " + response);
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
