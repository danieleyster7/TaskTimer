package deyster.timer;

import java.io.IOException;

import deyster.timer.view.RootLayoutController;
import deyster.timer.view.TimerMainController;
import deyster.timer.model.Task;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application 
{
	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Task> taskData = FXCollections.observableArrayList();
	
	public MainApp() 
	{
		//Dummy values for testing purposes
		taskData.add(new Task("Task 1"));
		taskData.add(new Task("Task 2"));
		taskData.add(new Task("Task 3"));
		taskData.add(new Task("Task 4"));
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
	
	public ObservableList<Task> getTaskData()
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
}
