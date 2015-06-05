package deyster.timer.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import deyster.timer.model.Credentials;
import deyster.timer.model.NewNote;
import deyster.timer.model.NoteResponse;
import deyster.timer.model.Ticket;
import deyster.timer.model.TicketDetail;

/* Static utility class to make http requests via api calls
 * Stores user's credentials as a static member so it can be stored and accessed easily by the program */
public final class WHD 
{
	public static Credentials credentials;
	
	/* Pulls all tickets the user is CC'd on
	 * Will be updated to pull tickets based on other criteria */
	public static Ticket[] getTickets() throws IOException, URISyntaxException
	{
		Gson gson = new Gson();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget;
		System.out.println("" + credentials.getAuthType());
		// Structure the http request based on authtype
		if(credentials.getAuthType() == Credentials.API) {
			httpget = new HttpGet("https://webhelpdesk.treca.org/helpdesk/WebObjects/Helpdesk.woa/ra/Tickets?qualifier=(ccAddressesForTech%20like%20%27*deyster@treca.org*%27)&apiKey=" + credentials.getAPIKey());
		}
		else {
			//httpget = new HttpGet("https://webhelpdesk.treca.org/helpdesk/WebObjects/Helpdesk.woa/ra/Tickets/78884?username=" + credentials.getUserName() + "&password=" + credentials.getPassword());
			httpget = new HttpGet("https://webhelpdesk.treca.org/helpdesk/WebObjects/Helpdesk.woa/ra/Tickets?qualifier=(ccAddressesForTech%20like%20%27*deyster@treca.org*%27)&username=" + credentials.getUserName() + "&password=" + credentials.getPassword());
		}
		
		// Anonymous response handler class to handle the tickets returned from the http request
		ResponseHandler<Ticket[]> rh = new ResponseHandler<Ticket[]>()
		{
		    @Override
		    public Ticket[] handleResponse(final HttpResponse response) throws IOException 
		    {
		        StatusLine statusLine = response.getStatusLine();
		        HttpEntity entity = response.getEntity();
		        
		        if (statusLine.getStatusCode() >= 300) {
		            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		        }
		        
		        if (entity == null) {
		            throw new ClientProtocolException("Response contains no content");
		        }
		        
		        Gson gson = new GsonBuilder().create();
		        ContentType contentType = ContentType.getOrDefault(entity);
		        Charset charset = contentType.getCharset();
		        Reader reader = new InputStreamReader(entity.getContent(), charset);
		        return gson.fromJson(reader, Ticket[].class);
		    }
		};
		
		//Return the tickets given the http request and responsehandler, rh
		return httpclient.execute(httpget, rh);
	}
	
	//Pulls the details of the given ticket
	public static TicketDetail getTicketDetails(int id) throws IOException
	{
		Gson gson = new Gson();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget;
		
		if(credentials.getAuthType() == Credentials.API) {
			httpget = new HttpGet("https://webhelpdesk.treca.org/helpdesk/WebObjects/Helpdesk.woa/ra/Tickets/" + id + "?apiKey" + credentials.getAPIKey());
		}
		else {
			httpget = new HttpGet("https://webhelpdesk.treca.org/helpdesk/WebObjects/Helpdesk.woa/ra/Tickets/" + id + "?username=" + credentials.getUserName() + "&password=" + credentials.getPassword());
		}
		
		ResponseHandler<TicketDetail> rh = new ResponseHandler<TicketDetail>()
		{
		    @Override
		    public TicketDetail handleResponse(final HttpResponse response) throws IOException 
		    {
		        StatusLine statusLine = response.getStatusLine();
		        HttpEntity entity = response.getEntity();
		        
		        if (statusLine.getStatusCode() >= 300) {
		            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		        }
		        
		        if (entity == null) {
		            throw new ClientProtocolException("Response contains no content");
		        }
		        
		        Gson gson = new GsonBuilder().create();
		        ContentType contentType = ContentType.getOrDefault(entity);
		        Charset charset = contentType.getCharset();
		        Reader reader = new InputStreamReader(entity.getContent(), charset);
		        return gson.fromJson(reader, TicketDetail.class);
		    }
		};
		
		return httpclient.execute(httpget, rh);
	}
	
	//Pulls the email for the given user's credentials
	public static void getEmail()
	{
		Gson gson = new Gson();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget;
		
		if(credentials.getAuthType() == Credentials.API) {
			httpget = new HttpGet("https://webhelpdesk.treca.org/helpdesk/WebObjects/Helpdesk.woa/ra/Clients?qualifier=(authenticationKey='" + credentials.getAPIKey() + "')&apiKey=" + credentials.getAPIKey());
		}
		else {
			httpget = new HttpGet("https://webhelpdesk.treca.org/helpdesk/WebObjects/Helpdesk.woa/ra/Clients?qualifier=(userName='" + credentials.getUserName() + "')&username=" + credentials.getUserName() + "&password=" + credentials.getPassword());
		}
		
		ResponseHandler<EmailWrapper[]> rh = new ResponseHandler<EmailWrapper[]>()
		{
		    @Override
		    public EmailWrapper[] handleResponse(final HttpResponse response) throws IOException 
		    {
		        StatusLine statusLine = response.getStatusLine();
		        HttpEntity entity = response.getEntity();
		        
		        if (statusLine.getStatusCode() >= 300) {
		            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		        }
		        
		        if (entity == null) {
		            throw new ClientProtocolException("Response contains no content");
		        }
		        
		        Gson gson = new GsonBuilder().create();
		        ContentType contentType = ContentType.getOrDefault(entity);
		        Charset charset = contentType.getCharset();
		        Reader reader = new InputStreamReader(entity.getContent(), charset);
		        return gson.fromJson(reader, EmailWrapper[].class);
		    }
		};
		
		try {
			credentials.setEmail(httpclient.execute(httpget, rh)[0].email);
		}
		catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	/* Sends a note for a ticket given a newNote object */
	public static void sendNote(NewNote newNote)
	{
		Gson gson = new Gson();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String noteJsonString = gson.toJson(newNote);
		
		try
		{
			//Write the json object to a file
			PrintWriter output = new PrintWriter("note.json", "UTF-8");
			output.write(noteJsonString);
			output.close();
			
			//Set the http request and json to be posted
			File file = new File("note.json");
			FileEntity entity = new FileEntity(file, ContentType.create("application/json", Consts.UTF_8));
			HttpPost httppost = new HttpPost("https://webhelpdesk.treca.org/helpdesk/WebObjects/Helpdesk.woa/ra/TechNotes?username=" + credentials.getUserName() + "&password=" + credentials.getPassword());
			httppost.setEntity(entity);
			
			ResponseHandler<NoteResponse> rh = new ResponseHandler<NoteResponse>()
			{
			    @Override
			    public NoteResponse handleResponse(final HttpResponse response) throws IOException 
			    {
			        StatusLine statusLine = response.getStatusLine();
			        HttpEntity entity = response.getEntity();
			        
			        if (statusLine.getStatusCode() >= 300) {
			        	System.out.println("" + statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
			            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
			        }
			        
			        if (entity == null) {
			            throw new ClientProtocolException("Response contains no content");
			        }
			        
			        Gson gson = new GsonBuilder().create();
			        ContentType contentType = ContentType.getOrDefault(entity);
			        Charset charset = contentType.getCharset();
			        Reader reader = new InputStreamReader(entity.getContent(), charset);
			        return gson.fromJson(reader, NoteResponse.class);
			    }
			};
			
			
			httpclient.execute(httppost, rh);
		}
		catch (IOException io) {
			io.printStackTrace();
		}
	}
}
