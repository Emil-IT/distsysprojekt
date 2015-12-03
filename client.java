/* Datakom project client-part
Made by Johan and Anders */

public class client {
	// Properties
	private Array schedule;


	// Update to date and see if any more slots are taken
	void updateSchedule(void) {
		// Get array from back-end

	}

	// A slot has been chosen, we send this to the server-side to reserve the spot for this user.
	void bookTime(int day, int timeslot, String user) {
		// Two bookings max

	}

	// A user want's to remove its booking at this spot.
	void removeBooking(int day, int timeslot, String user) {
		// Check if the spot belongs to the user

		// Else send to server to remove from the array.

	}

	// User checking in on the timeslot to confirm they want it.
	void checkIn(int day, int timeslot, String user) {
		// A token sent to the slot to verify that the user has checked in.

	}

	public static void main(String[] args) {
		
	}
}