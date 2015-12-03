
 import java.rmi.registry.LocateRegistry;
 import java.rmi.registry.Registry;
+
+
+/* Datakom project client-part
+Made by Johan and Anders */
+
 import java.rmi.*;
 
 public class Client {
-    // Properties
-    private Array schedule;
-    
-    // Constructor
-    private Client() {
-        
-    }
-
-    // Method to interpret the schedule, to convert it to graphics.
+	// Properties
+	private Array schedule;
+
+	static RMICall rmi_call = null;
+
+	// Constructor
+	private Client () {
+		this.schedule;
+	}
+
+
+	// Method to interpret the schedule, to convert it to graphics.
 	void printSchedule() {
 		for (int i = 0; i =< 30; i++) {
 			// Graphics for each day and then print each day.
 @@ -33,13 +41,15 @@ void printDay(int day) {
 	void printSlot(int day, int timeslot) {
 		// Retrieves data from that timeslot, like the user that has booked it
 		// Prints out the data in the field.
+		
 	}
 
 
 	// Update to date and see if any more slots are taken
 	void updateSchedule(void) {
-		// Get array from back-end
-
+		// Get array from server
+		
+		
 	}
 
 	// A slot has been chosen, we send this to the server-side to reserve the spot for this user.
 @@ -75,13 +85,16 @@ void checkIn(int day, int timeslot, String user) {
 		this.updateSchedule();
 	}
 
-    public static void main(String[] args) {
-
-        String host = (args.length < 1) ? null : args[0];
+	public static void main(String[] args) {
+		// Create a connection with the server and receive the schedule. 
+		// Then different buttons on the screen can do different things for you.
+		String host = (args.length < 1) ? null : args[0];
         try {
             Registry registry = LocateRegistry.getRegistry(host);
+            
             Interface stub = (Interface) registry.lookup("Hello");
             System.out.println("Contacting server...");
+            
             String response = stub.sayHello();
             System.out.println("response: " + response);
             
 @@ -89,5 +102,5 @@ public static void main(String[] args) {
             System.err.println("Client exception: " + e.toString());
             e.printStackTrace();
         }
-    }
+	}
 }