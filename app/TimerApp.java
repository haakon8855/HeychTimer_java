package app;

import java.util.Timer;

import javafx.application.Application;
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
		TimerController timerController = loader.getController();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("ico.png")));
		primaryStage.show();	
		
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode() == KeyCode.SPACE
				&& timerController.getState() != 1) {
				timerController.handleSpace();
				released = false;
			}
		});
		
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
			root.requestFocus();
		});
		
		timerController.scramble.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			timerController.reScramble();
		});
		
	}
	
	public static void main(final String[] args) {
		Application.launch(args);
	}
	
}
