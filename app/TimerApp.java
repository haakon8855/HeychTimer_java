package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TimerApp extends Application{
	
	private boolean released = true;
	
	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("Timer"); // Sets window title
		FXMLLoader loader = new FXMLLoader(TimerApp.class.getResource("TimerGUI.fxml"));
		Parent root = loader.load();
		// Fetches the controller object
		TimerController timerController = loader.getController();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		// Sets the window icon
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("ico.png")));
		primaryStage.show();	
		
		// Runs whenever the spacebar is pushed down
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode() == KeyCode.SPACE
				&& timerController.getState() != 1) {
				timerController.handleSpace();
				released = false;
			}
		});
		
		// Runs whenever the spacebar is released
		scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
			if (event.getCode() == KeyCode.SPACE
				&& timerController.getState() == 1
				&& released) {
				timerController.handleSpace();
			} else if (event.getCode() == KeyCode.SPACE
					   && timerController.getState() == 1
					   && !released) {
				released = true;
			}
		});
		
		scene.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			// Sets focus to the window when any empty area is clicked
			root.requestFocus();
		});
		
		timerController.scramble.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			// Creates a new scramble sequence when the scramble labes is clicked
			timerController.reScramble();
		});
		
		// Quits program when escape is pressed
		// Purely for ease of use during development
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				Platform.exit();
			}
		});
		
	}
	
	public static void main(final String[] args) {
		Application.launch(args);
	}
	
}
