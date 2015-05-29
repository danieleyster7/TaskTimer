package deyster.timer.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import deyster.timer.model.Credentials;
import deyster.timer.model.Ticket;
import deyster.timer.model.TicketDetail;

public final class WHD 
{
	
	// Pulls all tickets the user is CC'd on
	public static Ticket[] getTickets(Credentials credentials) throws IOException, URISyntaxException
	{
		Gson gson = new Gson();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget;
		
		/*URIBuilder uriBuild = new URIBuilder();
		uriBuild.setScheme("https").setHost("webhelpdesk.treca.org").setPath("/helpdesk/WebObjects/Helpdesk.woa/ra/Tickets")
			.setParameter("qualifier", "(ccAddressesForTech like '*deyster@treca.org*')");
		uriBuild.
		
		if(credentials.getAuthType() == credentials.API) {
			uriBuild.setParameter("apiKey", credentials.getAPIKey());
		}
		else {
			uriBuild.setParameter("userName", credentials.getUserName()).setParameter("password", credentials.getPassword());
		}
		
		URI uri = uriBuild.build();
		System.out.println(uri.toString());
		HttpGet httpget = new HttpGet(uri);*/
		
		
		
		if(credentials.getAuthType() == credentials.API) {
			httpget = new HttpGet("https://webhelpdesk.treca.org/helpdesk/WebObjects/Helpdesk.woa/ra/Tickets?qualifier=(ccAddressesForTech%20like%20%27*deyster@treca.org*%27)&apiKey=" + credentials.getAPIKey());
		}
		else {
			httpget = new HttpGet("https://webhelpdesk.treca.org/helpdesk/WebObjects/Helpdesk.woa/ra/Tickets?qualifier=(ccAddressesForTech%20like%20%27*deyster@treca.org*%27)&username=" + credentials.getUserName() + "&password=" + credentials.getPassword());
		}
			
			
		
		ResponseHandler<Ticket[]> rh = new ResponseHandler<Ticket[]>()
		{
		    @Override
		    public Ticket[] handleResponse(final HttpResponse response) throws IOException 
		    {
		        StatusLine statusLine = response.getStatusLine();
		        HttpEntity entity = response.getEntity();
		        
		        if (statusLine.getStatusCode() >= 300) {
		            throw new HttpResponseException(
		                    statusLine.getStatusCode(),
		                    statusLine.getReasonPhrase());
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
		
		//Ticket tickets[] = httpclient.execute(httpget, rh);
		
		return httpclient.execute(httpget, rh);
	}
	
	//Pulls the details of the given ticket
	public static TicketDetail getTicketDetails(int id, Credentials credentials) throws IOException
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
		            throw new HttpResponseException(
		                    statusLine.getStatusCode(),
		                    statusLine.getReasonPhrase());
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
	public static String getEmail(Credentials credentials)
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
		            throw new HttpResponseException(
		                    statusLine.getStatusCode(),
		                    statusLine.getReasonPhrase());
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
			return httpclient.execute(httpget, rh)[0].email;
		}
		catch (IOException io) {
			io.printStackTrace();
			return "invalid email";
		}
	}
}
