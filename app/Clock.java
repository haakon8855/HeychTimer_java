package app;

public class Clock {
	
	private long startTime, stopTime, time;
	private boolean running = false;
	
	public Clock() {
		this.time = 0;
	}
	
	public void start() {
		if (!running) {
			startTime = System.currentTimeMillis();
			stopTime = startTime;
			running = true;
		} else {
			throw new IllegalStateException("Cannot start clock when already running");
		}
	}
	
	public void stop() {
		if (running) {
			stopTime = System.currentTimeMillis();
			time = stopTime-startTime;
			running = false;
		} else {
			throw new IllegalStateException("Cannot stop clock when not running");
		}
	}
	
	public long getTime() { 
		if (running) {
			time = System.currentTimeMillis()-startTime;
			return time;
		} else {
			return time;
		}
	}
	
}
