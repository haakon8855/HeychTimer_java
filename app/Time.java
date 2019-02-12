package app;

import java.util.concurrent.TimeUnit;

public class Time {
	
	private long time;
	private Clock clock, inspection;
	private boolean onInspection = false;
	private Scramble scramble;
	private int inspectionTime = 15;
	
	public Time() {
		this.clock = new Clock();
		this.inspection = new Clock();
		this.time = clock.getTime();
		this.time = 0;
		this.scramble = new Scramble();
		this.scramble.newScramble();
	}
	
	public void startInspection() {
		inspection.start();
		onInspection = true;
	}
	
	public void stopInspection() {
		inspection.stop();
		onInspection = false;
	}
	
	public String getScramble() {
		return scramble.toString();
	}
	
	public void reScramble() {
		scramble.newScramble();
	}
	
	public long getTime(Clock clock) {
		time = clock.getTime();
		return this.time;
	}
	
	public String getTimeString() {
		String outString;
		if (onInspection) {
			long millis = this.getTime(inspection);
			long allSecs = TimeUnit.MILLISECONDS.toSeconds(millis);
			outString = ""+(inspectionTime-allSecs);
		} else {
			long millis = this.getTime(clock);
			long allSecs = TimeUnit.MILLISECONDS.toSeconds(millis); 
			long mins = TimeUnit.MILLISECONDS.toMinutes(millis);
			long secs = allSecs - TimeUnit.MINUTES.toSeconds(mins);
			long restMillis = millis - TimeUnit.SECONDS.toMillis(allSecs);
			long centis = restMillis / 10;
			if (millis < 60000) {
				outString = String.format("%2d.%02d", secs, centis);
			} else {
				outString = String.format("%2d:%02d.%02d", mins, secs, centis);
			}
		}
		
		return outString;
	}
	
	public void startTime() {
		clock.start();
	}
	
	public void stopTime() {
		clock.stop();
	}

}








