package tvattstuga;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

/*import java.rmi.*;
import java.awt.Frame;
import java.awt.*;
import java.awt.Frame;
import javax.swing.JFrame;
import java.rmi.registry.*;
import java.util.Arrays;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
*/


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
	            		
	            		System.out.println("Enter name");
	            		name = in.nextLine();
	            		System.out.println("Enter password");
	            		pass = in.nextLine();
	            		
	            		response = Boolean.toString(stub.AddUser(name, pass));
	            		 
	            		System.out.println("response: " + response);
	            		break;
	            	
	            		
	            	case "get":
	            		int[][] r = stub.GetSchedule();
	            		for(int i = 0; i<30; i++){
	            			response = Arrays.toString(r[i]);
	            			System.out.println("response: " + response);
	            		}
	            		break;
	            		
	            	case "check in":
	            		System.out.println("Enter time");
	            		time = in.nextLine();
	            		System.out.println("Enter name");
	            		name = in.nextLine();
	            		System.out.println("Enter password");
	            		pass = in.nextLine();
	            		response = Boolean.toString(stub.CheckIn(name, pass, Integer.parseInt(time)));
	            		System.out.println("response: " + response);
	            		break;
	            		
	            	case "confirm":
	            		System.out.println("Enter name");
	            		name = in.nextLine();
	            		System.out.println("Enter password");
	            		pass = in.nextLine();
	            		response = Boolean.toString(stub.ConfirmBookTime(name, pass));
	            		System.out.println("response: " + response);
	            		break;
	            		
	            	case "remove":
	            		System.out.println("Enter time");
	            		time = in.nextLine();
	            		System.out.println("Enter day");
	            		day = in.nextLine();
	            		System.out.println("Enter name");
	            		name = in.nextLine();
	            		System.out.println("Enter password");
	            		pass = in.nextLine();
	            		response = Boolean.toString(stub.RemoveBooking(Integer.parseInt(time), Integer.parseInt(day), name, pass));
	            		System.out.println("response: " + response);
	            		break;	
	            		
	            		
	            		
	            		
	            		/*
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
	            	
	            	*/
	            		
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

/*JFrame frame = new JFrame("Hello world!");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(1600,1200);
//frame.setBound
frame.setTitle("iTBooks bokningsschema");
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
Date date = new Date();
String datestring = date.toString();
JLabel headerlabel = new javax.swing.JLabel();
headerlabel.setText("Today is :" + datestring);
Box header = Box.createHorizontalBox();
header.add(headerlabel);
Box leftwrapper = Box.createVerticalBox();

 //Blev knas
Box first = Box.createHorizontalBox();
Box second = Box.createHorizontalBox();
Box third = Box.createHorizontalBox();
Box fourth = Box.createHorizontalBox();
Box fifth = Box.createHorizontalBox();
Box sixth = Box.createHorizontalBox();
Box seventh = Box.createHorizontalBox();
for (int i = 0; i < 7; i++) {
	for (int j = 0; j < 14; j++) {
		int hour = j+8;
		String time = hour + ":00";
		switch(i) {
			case 0: first.add(new JCheckBox(time));
			case 1: second.add(new JCheckBox(time));
			case 2: third.add(new JCheckBox(time));
			case 3: fourth.add(new JCheckBox(time));
			case 4: fifth.add(new JCheckBox(time));
			case 5: sixth.add(new JCheckBox(time));
			case 6: seventh.add(new JCheckBox(time));
			default: 
		}
	}
}
Box first = Box.createVerticalBox();
for (int i = 0; i < 14; i++) {
	int hour = i+8;
	String time = hour + ":00";
	first.add(new JCheckBox(time));
}
Box second = Box.createVerticalBox();
for (int i = 0; i < 14; i++) {
	int hour = i+8;
	String time = hour + ":00";
	second.add(new JCheckBox(time));
}
Box third = Box.createVerticalBox();
for (int i = 0; i < 14; i++) {
	int hour = i+8;
	String time = hour + ":00";
	third.add(new JCheckBox(time));
}
Box fourth = Box.createVerticalBox();
for (int i = 0; i < 14; i++) {
	int hour = i+8;
	String time = hour + ":00";
	fourth.add(new JCheckBox(time));
}
Box fifth = Box.createVerticalBox();
for (int i = 0; i < 14; i++) {
	int hour = i+8;
	String time = hour + ":00";
	fifth.add(new JCheckBox(time));
}
Box sixth = Box.createVerticalBox();
for (int i = 0; i < 14; i++) {
	int hour = i+8;
	String time = hour + ":00";
	sixth.add(new JCheckBox(time));
}
Box seventh = Box.createVerticalBox();
for (int i = 0; i < 14; i++) {
	int hour = i+8;
	String time = hour + ":00";
	seventh.add(new JCheckBox(time));
}
Box rightwrapper = Box.createVerticalBox();
Box top = Box.createHorizontalBox();
top.add(leftwrapper);
top.add(first);
top.add(second);
top.add(third);
top.add(fourth);
top.add(fifth);
top.add(sixth);
top.add(seventh);
top.add(rightwrapper);
Box bottom = Box.createHorizontalBox();
JButton bookButton = new javax.swing.JButton();
bookButton.setText("Boka markerad tid");
bookButton.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        bookButtonActionPerformed(evt);
    }
});
bottom.add(bookButton);
Container content = frame.getContentPane();
content.setLayout(new BorderLayout());
content.add(top, BorderLayout.CENTER);
frame.pack();
frame.setVisible(true);*/