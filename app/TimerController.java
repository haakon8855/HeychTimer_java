package app;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.util.Duration;

public class TimerController {
	
	@FXML
	Label timeLabel;
	@FXML
	Label scramble;
	@FXML
	ListView<String> listView = new ListView<String>();
	
	// List containing the times
	private List<Time> timeList = new ArrayList<Time>();
	
	// Timeline which updates the clock live
	private Timeline timeline;

	/* 
	 * States:
	 * 	0 - Timer not running, showing last time or 00:00:00 if just started
	 * 	1 - Inspection time running, counting down from 15
	 * 	2 - Timer running, showing running time
	*/
	private int state = 0;
	private int states = 3;
	
	@FXML
	public void initialize() {
		newTime(true);
		updateScramble();
		
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		timeline = new Timeline(
			new KeyFrame(
				Duration.millis(10),
				event -> {
					String timeString = getThisTime().getTimeString();
					if (timeString.equals("-1")) {
						getThisTime().stopInspection();
						getThisTime().startTime();
						setState(2);
					}
					timeLabel.setText(getThisTime().getTimeString());
				}
			)
		);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeLabel.setText("0.00"); // sets label at startup
	}
	
	public void handleSpace() {
		if (state == 0) {
			getThisTime().startInspection();
			timeline.play();			// starts updating the on screen timer
			setState(1);
		} else if (state == 1) {
			getThisTime().stopInspection();
			getThisTime().startTime();	// starts the current Time obj. (i.e. starts the clock)
			setState(2);
		} else if (state == 2) {
			getThisTime().stopTime();	// stops the current Time obj. (i.e. stops the clock)
			timeline.pause();			// temp. stops the on screen timer from updating
			updateListView();
			setState(0);
			newTime(false); 			// creates new Time obj.
			updateScramble();
		}
	}
	
	public void setState(int a) { // sets the current timer state
		if (a < 0 || a >= states) {
			throw new IllegalStateException();
		} else {
			this.state = a;
		}
	}
	
	public int getState() {
		return state;
	}
	
	public void newTime(boolean update) { // creates a new time
		timeList.add(new Time());	// adds a new Time to the list of Times
		if (update) {
			timeLabel.setText(getThisTime().getTimeString()); // updates label
		}
	}
	
	public void deleteTime() {
		int selected = listView.getSelectionModel().getSelectedIndex();
		timeList.remove(selected);
		timeList.remove(timeList.size()-1);
		updateListView();
		newTime(false);
	}
	
	public void reScramble() {
		getThisTime().reScramble();
		updateScramble();
	}
	
	public Time getThisTime() { // return the time of the current time obj.
		return timeList.get(timeList.size()-1);
	}
	
	public void updateListView() {
		// Updates the list of former times after the timer is stopped
		List<String> tempList = new ArrayList<String>();
		timeLabel.setText(getThisTime().getTimeString());
		
		// Pushes all times as strings into a list
		for (int i = 0; i < timeList.size(); i++) {
			tempList.add(i+1 + ".\t" + timeList.get(i).getTimeString());
		}
		
		ObservableList<String> items = FXCollections.observableArrayList(tempList);
		listView.setItems(items);
	}
	
	public void updateScramble() {
		// Updated the scramble label
		scramble.setText(getThisTime().getScramble());
	}
	
}





























