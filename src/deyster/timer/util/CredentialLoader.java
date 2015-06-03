package deyster.timer.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import deyster.timer.model.Credentials;

/* Static utility class to hold methods to load and save credentials */
public final class CredentialLoader 
{	
	/* Saves user credentials to userData.bin as an object for easy loading */
	public static void save(Credentials credentials)
	{
		try 
		{
			FileOutputStream fileOut = new FileOutputStream("userData.bin");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(credentials);
			out.close();
			fileOut.close();
		}
		catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	/* Loads user credentials from userData.bin */
	public static Credentials load()
	{
		try
		{
			FileInputStream fileIn = new FileInputStream("userData.bin");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Credentials credentials;
			credentials = (Credentials) in.readObject();
			in.close();
			fileIn.close();
			return credentials;
		}
		catch (Exception io) {
			io.printStackTrace();
			return new Credentials();
		}
	}
}
