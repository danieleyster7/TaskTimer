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

public final class TicketLoader 
{
	public static void save(ObservableList<WHDTask> tickets)
	{
		WHDTaskArrayWrapper whdArray = new WHDTaskArrayWrapper(tickets.size());
		for(int i = 0; i < tickets.size(); i++) {
			whdArray.whdArray[i] = tickets.get(i);
		}
		
		try 
		{
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
	
	public static ObservableList<WHDTask> load()
	{
		try
		{
			FileInputStream fileIn = new FileInputStream("userData.bin");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			WHDTaskArrayWrapper whdArray;
			whdArray = (WHDTaskArrayWrapper) in.readObject();
			in.close();
			fileIn.close();
			
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
