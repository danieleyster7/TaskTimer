package deyster.timer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import deyster.timer.view.RootLayoutController;
import deyster.timer.view.TimerMainController;
import deyster.timer.dialog.DeleteTaskController;
import deyster.timer.dialog.NewTaskController;
import deyster.timer.model.Task;
import deyster.timer.model.Ticket;
import deyster.timer.model.WHDTask;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application 
{
	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<WHDTask> taskData = FXCollections.observableArrayList();
	
	public MainApp() throws IOException {	
		getTickets();
	}
	
	public void start(Stage primaryStage) 
	{
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Task Timer");
        this.primaryStage.setResizable(false);
        
        //this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

        initRootLayout();

        showTimerMain();
	}
	
	/*
	 * Initializes root layout (file, edit, etc menus)
	 */
	public void initRootLayout()
	{
		try 
		{
            //load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            //show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            //give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	 //Loads TimerMain.fxml inside root layout
	public void showTimerMain()
	{
		try 
		{
            //load timermain
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TimerMain.fxml"));
            AnchorPane timerMain = (AnchorPane) loader.load();

            //set timermain into the center of root layout.
            rootLayout.setCenter(timerMain);

            //give the controller access to the main app.
            TimerMainController controller = loader.getController();
            controller.setMainApp(this);

        } 
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public boolean showNewTaskDialog(Task tempTask)
	{
		try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("dialog/NewTask.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Task");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NewTaskController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTask(tempTask);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	// TODO: Pass list to be updated
	public boolean showDeleteTaskDialog()
	{
		try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("dialog/DeleteTask.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Delete Task");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            DeleteTaskController controller = loader.getController();
            controller.passTasks(taskData);
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public ObservableList<WHDTask> getTaskData()
	{
		return taskData;
	}
	
	public Stage getPrimaryStage() 
	{
		return primaryStage;
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
	
	public void getTickets() throws IOException
	{
		Gson gson = new Gson();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("https://webhelpdesk.treca.org/helpdesk/WebObjects/Helpdesk.woa/ra/Tickets/mine?apiKey=xrJtiSwVzd8XUxKlKsvyz68YtWrnrX3sdp2hWPA0");
		
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
		
		Ticket tickets[] = httpclient.execute(httpget, rh);
		
		for(int i = 0; i < tickets.length; i++)
		{
			System.out.println(gson.toJson(tickets[i]));
			taskData.add(new WHDTask(tickets[i].getShortSubject(), tickets[i].getID()));
		}
	}
}
