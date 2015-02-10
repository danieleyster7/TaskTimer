package deyster.timer;

import java.io.IOException;

import ch.deyster.address.MainApp;
import ch.deyster.address.view.PersonEditDialogController;
import deyster.timer.view.RootLayoutController;
import deyster.timer.view.TimerMainController;
import deyster.timer.dialog.NewTaskController;
import deyster.timer.model.Task;
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
	private ObservableList<Task> taskData = FXCollections.observableArrayList();
	
	public MainApp() {}
	
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
            loader.setLocation(MainApp.class.getResource("view/NewTask.fxml"));
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
