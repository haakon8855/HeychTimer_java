package app;

public class Clock {
	
	private long startTime, stopTime, time;
	private boolean running = false;
	
	public Clock() {
		// initiates with time = 0
		this.time = 0;
	}
	
	public void start() {
		if (!running) {
			// if timer is not running, starts the clock
			startTime = System.currentTimeMillis();
			stopTime = startTime;
			running = true;
		} else {
			throw new IllegalStateException("Cannot start clock when already running");
		}
	}
	
	public void stop() {
		if (running) {
			// if timer is running, stops the clock
			stopTime = System.currentTimeMillis();
			time = stopTime-startTime;
			running = false;
		} else {
			throw new IllegalStateException("Cannot stop clock when not running");
		}
	}
	
	public long getTime() {
		// returns the time in different ways depending 
		// on whether the clock is running or not
		if (running) {
			time = System.currentTimeMillis()-startTime;
			return time;
		} else {
			return time;
		}
	}
	
}
