package app;

import java.util.concurrent.TimeUnit;

public class Time {
	
	private long time;
	private Clock clock, inspection, delay;
	private Scramble scramble;
	private boolean onInspection = false;
	private boolean onDelay = false;
	private int inspectionTime = 15;
	
	public boolean pressed = false;
	public int holdColor = 0;
	
	public Time() {
		this.clock = new Clock();
		this.inspection = new Clock();
		this.delay = new Clock();
		this.time = clock.getTime();
		this.scramble = new Scramble();
		this.scramble.newScramble();
	}
	
	public void startInspection() {
		// Starts the inspection time counting down from this.length
		inspection.start();
		onInspection = true;
	}
	
	public void stopInspection() {
		// Stops the inspection time
		inspection.stop();
		onInspection = false;
	}
	
	public void startDelay() {
		if (delay != null) {
			delay.start();
			onDelay = true;
		}
	}
	
	public void stopDelay() {
		if (delay != null) {
			delay.stop();
			onDelay = false;
		}
	}
	
	public String getScramble() {
		return scramble.toString();
	}
	
	public void reScramble() {
		// Creates a new scramble sequence
		scramble.newScramble();
	}
	
	public long getTime(Clock clock) {
		// time variable is simultaneously updated
		time = clock.getTime();
		return this.time;
	}
	
	public String getTimeString() {
		// Returns the string that is to be shown on screen
		String outString;
		
		if (!pressed && onDelay){
			stopDelay();
			if (delay != null) {
				delay = new Clock();
			}
			holdColor = 0;
		}
		
		if (onInspection) {
			// Returns an integer (String) between inspectionTime and 0
			long millis = this.getTime(inspection);
			if (pressed && !onDelay) {
				startDelay();
				holdColor = 1;
			}
			if (pressed && onDelay) {
				if (getTime(delay) > 300) {
					holdColor = 2;
				}
			}
			long allSecs = TimeUnit.MILLISECONDS.toSeconds(millis);
			outString = ""+(inspectionTime-allSecs);
		} else {
			// Returns a time formatted as either 
			// 		s.cc	ss.cc	m:ss.cc		mm:ss.cc 
			long millis = this.getTime(clock);
			long allSecs = TimeUnit.MILLISECONDS.toSeconds(millis); 
			long mins = TimeUnit.MILLISECONDS.toMinutes(millis);
			long secs = allSecs - TimeUnit.MINUTES.toSeconds(mins);
			long restMillis = millis - TimeUnit.SECONDS.toMillis(allSecs);
			long centis = restMillis / 10;
			if (millis < 60000) {
				outString = String.format("%d.%02d", secs, centis);
			} else {
				outString = String.format("%d:%02d.%02d", mins, secs, centis);
			}
		}
		return outString;
	}
	
	public void startTime() {
		// Starts the timer after inspection
		clock.start();
//		delay = null;
//		inspection = null;
	}
	
	public void stopTime() {
		// Stops the timer
		clock.stop();
	}
	
}




























