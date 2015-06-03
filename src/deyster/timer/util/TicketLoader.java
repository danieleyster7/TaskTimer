package deyster.timer.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import deyster.timer.model.Credentials;
import deyster.timer.model.WHDTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/* Static utility class to handle the local saving and loading of user's list of tickets */
public final class TicketLoader 
{
	/* Function to save list of tickets to local disk */
	public static void save(ObservableList<WHDTask> tickets)
	{
		// Wrap the tickets in a serializable class to be written to disk
		WHDTaskArrayWrapper whdArray = new WHDTaskArrayWrapper(tickets.size());
		for(int i = 0; i < tickets.size(); i++) {
			whdArray.whdArray[i] = tickets.get(i);
		}
		
		try 
		{
			// Save tickets
			FileOutputStream fileOut = new FileOutputStream("userTickets.bin");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(whdArray);
			out.close();
			fileOut.close();
		}
		catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	/* Function to load list of tickets from local disk */
	public static ObservableList<WHDTask> load()
	{
		try
		{
			// Load tickets
			FileInputStream fileIn = new FileInputStream("userTickets.bin");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			WHDTaskArrayWrapper whdArray;
			whdArray = (WHDTaskArrayWrapper) in.readObject();
			in.close();
			fileIn.close();

			// Move from the wrapper class to observable array
			ObservableList<WHDTask> tickets = FXCollections.observableArrayList();
			for(int i = 0; i < whdArray.whdArray.length; i++) {
				tickets.add(whdArray.whdArray[i]);
			}
			
			return tickets;
		}
		catch (Exception io) {
			//io.printStackTrace();
			return FXCollections.observableArrayList();
		}
	}
}
